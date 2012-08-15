package cz.cvut.fit.culkajac.dp.services.amazons3;

import cz.cvut.fit.culkajac.dp.services.amazons3.dto.DeleteObject;
import cz.cvut.fit.culkajac.dp.services.amazons3.dto.GetObject;
import cz.cvut.fit.culkajac.dp.services.amazons3.dto.PutObjectInline;

public interface SecurityAdapter {

	<T extends AmazonS3Signable> T sign(T r);

	GetObject signGet(GetObject r);

	PutObjectInline signPut(PutObjectInline r);

	DeleteObject signDelete(DeleteObject r);

}
