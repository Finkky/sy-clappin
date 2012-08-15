package cz.cvut.fit.culkajac.dp.services.amazons3;

import cz.cvut.fit.culkajac.dp.services.amazons3.dto.DeleteObject;
import cz.cvut.fit.culkajac.dp.services.amazons3.dto.DeleteObjectResponse;
import cz.cvut.fit.culkajac.dp.services.amazons3.dto.GetObject;
import cz.cvut.fit.culkajac.dp.services.amazons3.dto.GetObjectExtended;
import cz.cvut.fit.culkajac.dp.services.amazons3.dto.GetObjectExtendedResponse;
import cz.cvut.fit.culkajac.dp.services.amazons3.dto.GetObjectResponse;
import cz.cvut.fit.culkajac.dp.services.amazons3.dto.PutObject;
import cz.cvut.fit.culkajac.dp.services.amazons3.dto.PutObjectInline;
import cz.cvut.fit.culkajac.dp.services.amazons3.dto.PutObjectInlineResponse;
import cz.cvut.fit.culkajac.dp.services.amazons3.dto.PutObjectResponse;

public interface AmazonS3 {

	public GetObjectResponse GetObject(GetObject r);

	public GetObjectExtendedResponse GetObjectExtended(GetObjectExtended r);

	public PutObjectResponse PutObject(PutObject r);

	public PutObjectInlineResponse PutObjectInline(PutObjectInline r);

	public DeleteObjectResponse DeleteObject(DeleteObject r);

}
