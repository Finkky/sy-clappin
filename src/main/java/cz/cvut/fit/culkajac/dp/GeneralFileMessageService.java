package cz.cvut.fit.culkajac.dp;

import java.util.List;

import cz.cvut.fit.culkajac.dp.dto.FileDTO;
import cz.cvut.fit.culkajac.dp.dto.FileDescriptorDTO;
import cz.cvut.fit.culkajac.dp.dto.FileQueryDTO;

public interface GeneralFileMessageService {

	FileDTO retrieve(FileDescriptorDTO fd) throws FileServiceException;

	void store(FileDTO fd) throws FileServiceException;

	void delete(FileDescriptorDTO fd) throws FileServiceException;

	List<FileDescriptorDTO> searchAll(FileQueryDTO sq) throws FileServiceException;
}
