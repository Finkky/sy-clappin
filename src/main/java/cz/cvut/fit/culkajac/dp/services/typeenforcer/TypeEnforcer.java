package cz.cvut.fit.culkajac.dp.services.typeenforcer;

import cz.cvut.fit.culkajac.dp.dto.FileDTO;
import cz.cvut.fit.culkajac.dp.dto.FileDescriptorDTO;
import cz.cvut.fit.culkajac.dp.dto.OperationStatusDTO;

public interface TypeEnforcer {

	public FileDTO enforceFile(FileDTO rf);
	
	public FileDescriptorDTO enforceFileDescriptor(FileDescriptorDTO rf);
	
	public OperationStatusDTO enforceOperationStatus(OperationStatusDTO rf);
		
}
