package cz.cvut.fit.culkajac.dp.services.amazons3api.impl;

import org.switchyard.component.bean.Service;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;

import cz.cvut.fit.culkajac.dp.Constants;
import cz.cvut.fit.culkajac.dp.dto.OperationStatusDTO;
import cz.cvut.fit.culkajac.dp.services.amazons3api.AmazonS3ApiAdapter;
import cz.cvut.fit.culkajac.dp.transformers.AmazonDTOTransformers;

@Service(AmazonS3ApiAdapter.class)
public class AmazonS3ApiAdapterImpl extends AmazonS3Client implements AmazonS3ApiAdapter {

	private static final String SERVICE_NAME = Constants.AmazonS3.SERVICE_UID;
	private static final String ACCESS_KEY = Constants.AmazonS3.ACCESS_KEY;
	private static final String SECRET_ACCESS_KEY = Constants.AmazonS3.SECRET_ACCESS_KEY;

	AmazonDTOTransformers at = new AmazonDTOTransformers();

	public AmazonS3ApiAdapterImpl() {
		super(new BasicAWSCredentials(ACCESS_KEY, SECRET_ACCESS_KEY));
	}

	@Override
	public Object getObjectSafe(GetObjectRequest getObjectRequest) {
		try {
			return getObject(getObjectRequest);
		} catch (Exception e) {
			return new OperationStatusDTO(500, SERVICE_NAME);
		}
	}

	@Override
	public Object putObjectSafe(PutObjectRequest putObejctRequest) {
		try {
			return putObject(putObejctRequest);
		} catch (Exception e) {
			return new OperationStatusDTO(500, SERVICE_NAME);
		}
	}

}
