package cz.cvut.fit.culkajac.dp.services.s3store;

import cz.cvut.fit.culkajac.dp.dto.FileDescriptorDTO;

public interface S3StoreSelectionService {

	void process(FileDescriptorDTO file);

}
