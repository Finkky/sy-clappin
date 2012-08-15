package cz.cvut.fit.culkajac.dp.services.zipper.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.switchyard.annotations.OperationTypes;
import org.switchyard.component.bean.Service;

import cz.cvut.fit.culkajac.dp.FileServiceException;
import cz.cvut.fit.culkajac.dp.dto.FileDTO;
import cz.cvut.fit.culkajac.dp.dto.FileDescriptorDTO;
import cz.cvut.fit.culkajac.dp.services.zipper.ZipperService;

@Service(ZipperService.class)
public class ZipperServiceImpl implements ZipperService {

	private static final String EMPTY_NAME = "empty_archive";

	@Override
	@OperationTypes(in = "{http://s3.amazonaws.com/doc/2006-03-01/}Files")
	public FileDTO zip(Collection<FileDTO> files) throws FileServiceException {
		try {

			FileDTO f = files.iterator().next();

			String title = f == null ? EMPTY_NAME : f.getMetadata().getTitle();

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ZipOutputStream zos = new ZipOutputStream(baos);

			for (FileDTO file : files) {

				ZipEntry entry = new ZipEntry(file.getMetadata().getSourceService() + "_" + file.getMetadata().getTitle());
				zos.putNextEntry(entry);
				IOUtils.copy(new ByteArrayInputStream(file.getData()), zos);
				zos.closeEntry();

			}

			zos.close();

			return new FileDTO(new FileDescriptorDTO(title + ".zip"), baos.toByteArray());
		} catch (IOException io) {
			throw new FileServiceException(io);
		}
	}
}
