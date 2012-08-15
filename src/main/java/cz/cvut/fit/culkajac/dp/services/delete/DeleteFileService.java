package cz.cvut.fit.culkajac.dp.services.delete;

import java.util.Collection;

import cz.cvut.fit.culkajac.dp.dto.FileDescriptorDTO;
import cz.cvut.fit.culkajac.dp.dto.OperationStatusDTO;

public interface DeleteFileService {

	Collection<OperationStatusDTO> process(FileDescriptorDTO file);

}
