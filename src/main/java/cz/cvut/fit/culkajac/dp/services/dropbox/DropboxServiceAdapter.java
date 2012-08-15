package cz.cvut.fit.culkajac.dp.services.dropbox;

import java.util.Collection;
import java.util.List;

import cz.cvut.fit.culkajac.dp.FileServiceException;
import cz.cvut.fit.culkajac.dp.dto.FileDTO;
import cz.cvut.fit.culkajac.dp.dto.FileDescriptorDTO;
import cz.cvut.fit.culkajac.dp.dto.FileQueryDTO;
import cz.cvut.fit.culkajac.dp.dto.OperationStatusDTO;

public interface DropboxServiceAdapter {

	OperationStatusDTO delete(FileDescriptorDTO fd) throws FileServiceException;

	FileDTO retrieve(FileDescriptorDTO fd) throws FileServiceException;

	List<FileDescriptorDTO> searchAll(FileQueryDTO sq) throws FileServiceException;

	OperationStatusDTO storeOverwrite(FileDTO fd) throws FileServiceException;

	OperationStatusDTO<FileDTO> retrieveSafe(FileDescriptorDTO fd);

	OperationStatusDTO storeOverwriteSafe(FileDTO f);

	OperationStatusDTO deleteSafe(FileDescriptorDTO fd);

	Collection<FileDescriptorDTO> retrieveCurrentDelta() throws FileServiceException;
}
