package cz.cvut.fit.culkajac.dp.binding.restful.impl;

import java.net.URI;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.switchyard.component.bean.Reference;

import cz.cvut.fit.culkajac.dp.binding.restful.FileServiceRESTBinding;
import cz.cvut.fit.culkajac.dp.dto.FileDTO;
import cz.cvut.fit.culkajac.dp.dto.FileDescriptorDTO;
import cz.cvut.fit.culkajac.dp.dto.OperationStatusDTO;
import cz.cvut.fit.culkajac.dp.services.delete.DeleteFileService;
import cz.cvut.fit.culkajac.dp.services.retrieve.RetrieveFileService;
import cz.cvut.fit.culkajac.dp.services.store.StoreFileService;
import cz.cvut.fit.culkajac.dp.services.zipper.ZipperService;
import cz.cvut.fit.culkajac.dp.transformers.MixedTransformers;

enum OverallStatus {
	ALL_OK, PARTIALLY_OK, FAULTS
}

@Path("file")
@RequestScoped
public class FileServiceRESTBindingImpl implements FileServiceRESTBinding {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileServiceRESTBindingImpl.class);
	private final static Pattern p = Pattern.compile("[a-zA-Z_0-9]{1,20}\\.[a-zA-Z0-9]{1,5}");

	private final static String checkTitle(String title) {
		if (!p.matcher(title).matches()) {
			LOGGER.info("DONT MATCH");
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}
		return title;
	}

	private static void checkFileItem(FileItem fi) {
		if (fi.isFormField()) {
			LOGGER.info("IS FORM");
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}

	}

	private static OverallStatus checkOperationStatus(Collection<OperationStatusDTO> coll) {
		int faults = 0;
		for (OperationStatusDTO operationStatusDTO : coll) {
			faults += operationStatusDTO.getStatus() == 200 ? 0 : 1;
		}

		if (faults == 0)
			return OverallStatus.ALL_OK;

		if (faults < coll.size())
			return OverallStatus.PARTIALLY_OK;

		return OverallStatus.FAULTS;
	}

	private static Response toResponse(OverallStatus os) {
		switch (os) {
		case ALL_OK:
			return Response.noContent().build();
		case PARTIALLY_OK:
			return Response.noContent().build();
		default:
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	@Inject
	@Reference
	private RetrieveFileService retrieveService;

	@Inject
	@Reference
	private ZipperService zipperService;

	@Inject
	@Reference
	private StoreFileService storeService;

	@Inject
	@Reference
	private DeleteFileService deleteService;

	@Override
	@GET
	@Path("/{title}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response getFile(@PathParam("title") String title) {

		checkTitle(title);
		Collection<OperationStatusDTO<FileDTO>> coll;
		try {
			FileDescriptorDTO fd = new FileDescriptorDTO(title);
			coll = this.retrieveService.process(fd);
		} catch (Exception e) {
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}

		for (Iterator<OperationStatusDTO<FileDTO>> iterator = coll.iterator(); iterator.hasNext();) {
			if (iterator.next().getStatus() != 200)
				iterator.remove();
		}

		Iterator<OperationStatusDTO<FileDTO>> it = coll.iterator();
		FileDTO file;
		OperationStatusDTO<FileDTO> os;

		try {
			if ((coll.size() > 1 && (file = this.zipperService.zip(new MixedTransformers().toFileDTOCollection(coll))) != null)
					|| (it.hasNext() && (os = it.next()).getStatus() == 200 && (file = os.getContent()) != null)) {
				return Response.ok(file.getData(), MediaType.APPLICATION_OCTET_STREAM)
						.header("Content-Disposition", "attachment; filename=" + file.getMetadata().getTitle()).build();
			}
		} catch (Exception e) {
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		throw new WebApplicationException(Response.Status.NOT_FOUND);
	}

	// http://www.ietf.org/rfc/rfc1867.txt
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@POST
	@Path("/")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response postFile(@Context HttpServletRequest request) {

		System.out.println(request);
		System.out.println(request.getParameterMap());
		try {
			if (!ServletFileUpload.isMultipartContent(request))
				throw new WebApplicationException(Response.Status.BAD_REQUEST);

			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);

			List<FileItem> items = upload.parseRequest(request);
			System.out.println("Items: " + items.size());

			for (FileItem fileItem : items) {
				checkFileItem(fileItem);
				FileDTO fileDTO = new FileDTO(new FileDescriptorDTO(checkTitle(fileItem.getName())), fileItem.get());
				fileDTO.getMetadata().setContentLength(fileItem.getSize());

				Collection<OperationStatusDTO> coll = this.storeService.process(fileDTO);

				Iterator<OperationStatusDTO> it;
				if ((it = coll.iterator()).hasNext() && it.next().getStatus() == 200) {
					return Response.created(new URI(fileDTO.getMetadata().getTitle())).build();
				}
			}
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		} catch (WebApplicationException w) {
			throw w;
		} catch (FileUploadException e) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	@DELETE
	@Path("/{title}")
	@Override
	public Response deleteFile(@PathParam("title") String title) {
		checkTitle(title);
		Collection<OperationStatusDTO> coll;
		try {
			coll = this.deleteService.process(new FileDescriptorDTO(title));
			return toResponse(checkOperationStatus(coll));
		} catch (WebApplicationException w) {
			throw w;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	@GET
	@Path("/getdelete/{title}")
	@Override
	public Response deleteFile(@QueryParam("delete") boolean delete, @PathParam("title") String title) {
		if (delete) {
			return deleteFile(title);
		}
		throw new WebApplicationException(Response.Status.NOT_MODIFIED);
	}

}
