/**
 * 
 */
package cz.cvut.fit.culkajac.dp.services.googlephotos;

import cz.cvut.fit.culkajac.dp.FileServiceException;
import cz.cvut.fit.culkajac.dp.dto.FileDTO;
import cz.cvut.fit.culkajac.dp.dto.FileDescriptorDTO;
import cz.cvut.fit.culkajac.dp.dto.OperationStatusDTO;

/**
 * @author Finkky
 * 
 */

public interface GooglePhotosServiceAdapter {

	FileDTO retrieve(FileDescriptorDTO fd) throws FileServiceException;

	FileDescriptorDTO store(FileDTO fd) throws FileServiceException;

	OperationStatusDTO delete(FileDescriptorDTO fd) throws FileServiceException;

	OperationStatusDTO<FileDTO> retrieveSafe(FileDescriptorDTO fd);

	OperationStatusDTO deleteAllSafe(FileDescriptorDTO fd);

	OperationStatusDTO<FileDescriptorDTO> storeSafe(FileDTO f);

	FileDTO retrieveLatest(FileDescriptorDTO fd) throws FileServiceException;

	OperationStatusDTO<FileDTO> retrieveLatestSafe(FileDescriptorDTO fd);
}
