package cz.cvut.fit.culkajac.dp.services.amazons3;

import cz.cvut.fit.culkajac.dp.dto.FileDTO;
import cz.cvut.fit.culkajac.dp.dto.OperationStatusDTO;
import cz.cvut.fit.culkajac.dp.services.amazons3.dto.GetObject;

public interface AmazonS3Stapler {

	OperationStatusDTO<FileDTO> GetObject(GetObject go);

}
