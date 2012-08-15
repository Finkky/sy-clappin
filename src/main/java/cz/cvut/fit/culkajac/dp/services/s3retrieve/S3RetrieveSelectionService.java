package cz.cvut.fit.culkajac.dp.services.s3retrieve;

import cz.cvut.fit.culkajac.dp.dto.FileDescriptorDTO;

public interface S3RetrieveSelectionService {

	void process(FileDescriptorDTO file);

}
