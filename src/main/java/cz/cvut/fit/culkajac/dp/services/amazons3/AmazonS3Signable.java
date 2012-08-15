package cz.cvut.fit.culkajac.dp.services.amazons3;

import java.util.Date;


public interface AmazonS3Signable {

	void setSignature(String signature);

	void setTimestamp(Date timestamp);
	
	void setAWSAccessKeyId(String accessKey);
}
