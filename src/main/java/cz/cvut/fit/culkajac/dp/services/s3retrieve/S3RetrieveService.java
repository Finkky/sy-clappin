package cz.cvut.fit.culkajac.dp.services.s3retrieve;

import cz.cvut.fit.culkajac.dp.FileServiceException;
import cz.cvut.fit.culkajac.dp.dto.FileDTO;
import cz.cvut.fit.culkajac.dp.dto.FileDescriptorDTO;

public interface S3RetrieveService {
	public FileDTO process(FileDescriptorDTO fd) throws FileServiceException;
}
