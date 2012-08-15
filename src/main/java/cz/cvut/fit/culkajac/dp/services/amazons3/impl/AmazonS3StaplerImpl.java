package cz.cvut.fit.culkajac.dp.services.amazons3.impl;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.switchyard.component.bean.Reference;
import org.switchyard.component.bean.Service;

import cz.cvut.fit.culkajac.dp.Constants;
import cz.cvut.fit.culkajac.dp.dto.FileDTO;
import cz.cvut.fit.culkajac.dp.dto.FileDescriptorDTO;
import cz.cvut.fit.culkajac.dp.dto.OperationStatusDTO;
import cz.cvut.fit.culkajac.dp.services.amazons3.AmazonS3;
import cz.cvut.fit.culkajac.dp.services.amazons3.AmazonS3Stapler;
import cz.cvut.fit.culkajac.dp.services.amazons3.dto.GetObject;
import cz.cvut.fit.culkajac.dp.services.amazons3.dto.GetObjectResponse;

@Service(AmazonS3Stapler.class)
public class AmazonS3StaplerImpl implements AmazonS3Stapler {

	private static final String SERVICE_NAME = Constants.AmazonS3.SERVICE_UID;
	@Inject
	@Reference
	private AmazonS3 amazonS3;

	private static final Logger LOGGER = LoggerFactory.getLogger(AmazonS3StaplerImpl.class);

	@Override
	public OperationStatusDTO<FileDTO> GetObject(GetObject go) {

		try {

			GetObjectResponse gor = this.amazonS3.GetObject(go);

			FileDescriptorDTO fd = new FileDescriptorDTO();
			fd.setTitle(go.getKey());
			fd.setSourceService(SERVICE_NAME);
			fd.setContentLength((long) gor.getGetObjectResponse().getData().length);

			FileDTO f = new FileDTO(fd, gor.getGetObjectResponse().getData());
			return new OperationStatusDTO<FileDTO>(200, SERVICE_NAME, f, FileDTO.class);
		} catch (Exception e) {
			LOGGER.warn(e.getMessage());
			return new OperationStatusDTO(500, SERVICE_NAME);
		}

	}
}
