package cz.cvut.fit.culkajac.dp.services.s3store;

import cz.cvut.fit.culkajac.dp.FileServiceException;
import cz.cvut.fit.culkajac.dp.dto.FileDTO;
import cz.cvut.fit.culkajac.dp.dto.FileDescriptorDTO;

public interface S3StoreService {

	public FileDescriptorDTO process(FileDTO fd) throws FileServiceException;
}
