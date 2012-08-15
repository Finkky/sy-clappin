/**
 * 
 */
package cz.cvut.fit.culkajac.dp.services.googledocs;

import java.util.List;

import cz.cvut.fit.culkajac.dp.FileServiceException;
import cz.cvut.fit.culkajac.dp.dto.FileDTO;
import cz.cvut.fit.culkajac.dp.dto.FileDescriptorDTO;
import cz.cvut.fit.culkajac.dp.dto.FileQueryDTO;
import cz.cvut.fit.culkajac.dp.dto.OperationStatusDTO;

/**
 * @author Finkky
 * 
 */

public interface GoogleDocsServiceAdapter {

	FileDTO retrieve(FileDescriptorDTO fd) throws FileServiceException;

	FileDescriptorDTO store(FileDTO fd) throws FileServiceException;

	OperationStatusDTO delete(FileDescriptorDTO fd) throws FileServiceException;

	List<FileDescriptorDTO> searchAll(FileQueryDTO sq) throws FileServiceException;

	OperationStatusDTO<FileDTO> retrieveSafe(FileDescriptorDTO fd);

	OperationStatusDTO<FileDescriptorDTO> storeSafe(FileDTO f);

	OperationStatusDTO deleteAllSafe(FileDescriptorDTO fd);

	FileDTO retrieveLatest(FileDescriptorDTO fd) throws FileServiceException;

	OperationStatusDTO<FileDTO> retrieveLatestSafe(FileDescriptorDTO fd);
}
