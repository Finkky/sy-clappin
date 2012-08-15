package cz.cvut.fit.culkajac.dp.services.retrieve;

import java.util.Collection;

import cz.cvut.fit.culkajac.dp.dto.FileDTO;
import cz.cvut.fit.culkajac.dp.dto.FileDescriptorDTO;
import cz.cvut.fit.culkajac.dp.dto.OperationStatusDTO;

public interface RetrieveFileService {

	Collection<OperationStatusDTO<FileDTO>> process(FileDescriptorDTO file);

}
