package cz.cvut.fit.culkajac.dp.services.amazons3api;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;

import cz.cvut.fit.culkajac.dp.dto.FileDTO;
import cz.cvut.fit.culkajac.dp.dto.FileDescriptorDTO;
import cz.cvut.fit.culkajac.dp.dto.OperationStatusDTO;

public interface AmazonS3ApiAdapter /* extends AmazonS3 */{

	public S3Object getObject(GetObjectRequest getObjectRequest) throws AmazonClientException;

	public PutObjectResult putObject(PutObjectRequest arg0) throws AmazonClientException;
	
	public Object getObjectSafe(GetObjectRequest getObjectRequest);

	public Object putObjectSafe(PutObjectRequest arg0);
}