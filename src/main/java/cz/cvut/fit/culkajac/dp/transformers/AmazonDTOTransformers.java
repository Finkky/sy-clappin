package cz.cvut.fit.culkajac.dp.transformers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.switchyard.annotations.Transformer;

import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.dyuproject.protostuff.ByteArrayInput;

import cz.cvut.fit.culkajac.dp.Constants;
import cz.cvut.fit.culkajac.dp.dto.FileDTO;
import cz.cvut.fit.culkajac.dp.dto.FileDescriptorDTO;
import cz.cvut.fit.culkajac.dp.dto.OperationStatusDTO;
import cz.cvut.fit.culkajac.dp.services.amazons3.dto.DeleteObject;
import cz.cvut.fit.culkajac.dp.services.amazons3.dto.DeleteObjectResponse;
import cz.cvut.fit.culkajac.dp.services.amazons3.dto.Fault;
import cz.cvut.fit.culkajac.dp.services.amazons3.dto.GetObject;
import cz.cvut.fit.culkajac.dp.services.amazons3.dto.GetObjectResponse;
import cz.cvut.fit.culkajac.dp.services.amazons3.dto.PutObjectInline;
import cz.cvut.fit.culkajac.dp.services.amazons3.dto.PutObjectInlineResponse;
import static cz.cvut.fit.culkajac.dp.Constants.*;

public class AmazonDTOTransformers {

	@Transformer
	public PutObjectInline toPutObjectInline(FileDTO f) {
		PutObjectInline poi = new PutObjectInline();

		poi.setBucket(AmazonS3.BUCKET);
		poi.setKey(f.getMetadata().getTitle());
		poi.setData(f.getData());
		poi.setContentLength(f.getData().length);

		return poi;
	}

	@Transformer
	public FileDTO toFileDTO(PutObjectInline poi) {
		FileDescriptorDTO fd = new FileDescriptorDTO();
		fd.setTitle(poi.getKey());
		FileDTO f = new FileDTO(fd, poi.getData());

		return f;
	}

	/**
	 * @param poir
	 */
	@Transformer
	public OperationStatusDTO toOperationStatusDTO(PutObjectInlineResponse poir) {
		return new OperationStatusDTO(200, AmazonS3.SERVICE_UID);
	}

	@Transformer
	public OperationStatusDTO toOperationStatusDTO(FileDescriptorDTO fd) {
		return new OperationStatusDTO(200, fd.getSourceService());
	}

	@Transformer
	public GetObjectRequest toGetObjectRequest(FileDescriptorDTO fd) {
		return new GetObjectRequest(AmazonS3.BUCKET, fd.getTitle());
	}

	@Transformer
	public PutObjectRequest toPutObjectRequest(FileDTO fd) {
		ObjectMetadata om = new ObjectMetadata();
		om.setContentLength(fd.getData().length);
		return new PutObjectRequest(AmazonS3.BUCKET, fd.getMetadata().getTitle(), new ByteArrayInputStream(fd.getData()), om);

	}

	/**
	 * @param poir
	 */
	@Transformer
	public OperationStatusDTO toOperationStatusDTO(PutObjectResult poir) {
		return new OperationStatusDTO(200, AmazonS3.SERVICE_UID);
	}

	@Transformer
	public OperationStatusDTO<FileDTO> toOperationStatusDTO(S3Object s3o) {
		try {
			FileDescriptorDTO fd = new FileDescriptorDTO();
			fd.setTitle(s3o.getKey());
			fd.setSourceService(AmazonS3.SERVICE_UID);
			fd.setContentLength(s3o.getObjectMetadata().getContentLength());

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			IOUtils.copy(s3o.getObjectContent(), baos);

			FileDTO f = new FileDTO(fd, baos.toByteArray());
			return new OperationStatusDTO<FileDTO>(200, AmazonS3.SERVICE_UID, f, FileDTO.class);
		} catch (Exception e) {
			return new OperationStatusDTO<FileDTO>(500, AmazonS3.SERVICE_UID);
		}
	}

	@Transformer
	public GetObject toGetObject(FileDescriptorDTO fd) {
		GetObject go = new GetObject();
		go.setBucket(AmazonS3.BUCKET);
		go.setInlineData(true);
		go.setKey(fd.getTitle());
		go.setGetData(true);
		go.setGetMetadata(true);

		return go;
	}

	@Transformer
	public OperationStatusDTO<FileDTO> toOperationStatusDTO(GetObjectResponse or) {
		try {
			FileDescriptorDTO fd = new FileDescriptorDTO();

			fd.setSourceService(AmazonS3.SERVICE_UID);
			fd.setContentLength((long) or.getGetObjectResponse().getData().length);

			FileDTO f = new FileDTO(fd, or.getGetObjectResponse().getData());
			return new OperationStatusDTO<FileDTO>(200, AmazonS3.SERVICE_UID, f, FileDTO.class);
		} catch (Exception e) {
			return new OperationStatusDTO<FileDTO>(500, AmazonS3.SERVICE_UID);
		}
	}

	@Transformer
	public DeleteObject toDeleteObject(FileDescriptorDTO fd) {
		DeleteObject go = new DeleteObject();
		go.setBucket(AmazonS3.BUCKET);
		go.setKey(fd.getTitle());

		return go;
	}

	/**
	 * @param or
	 */
	@Transformer
	public OperationStatusDTO<FileDTO> toOperationStatusDTO(DeleteObjectResponse or) {
		return new OperationStatusDTO<FileDTO>(200, AmazonS3.SERVICE_UID);
	}

	/**
	 * @param f
	 */
	@Transformer
	public OperationStatusDTO<FileDTO> toOperationStatusDTO(Fault f) {
		Logger LOGGER = LoggerFactory.getLogger("Amazon Fault Transformer");
		LOGGER.error(f.toString());

		return new OperationStatusDTO<FileDTO>(500, AmazonS3.SERVICE_UID);
	}
}
