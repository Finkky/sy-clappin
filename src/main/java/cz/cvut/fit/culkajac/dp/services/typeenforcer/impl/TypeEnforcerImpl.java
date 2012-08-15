package cz.cvut.fit.culkajac.dp.services.typeenforcer.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.switchyard.component.bean.Service;

import cz.cvut.fit.culkajac.dp.dto.FileDTO;
import cz.cvut.fit.culkajac.dp.dto.FileDescriptorDTO;
import cz.cvut.fit.culkajac.dp.dto.OperationStatusDTO;
import cz.cvut.fit.culkajac.dp.services.typeenforcer.TypeEnforcer;

@Service(TypeEnforcer.class)
public class TypeEnforcerImpl implements TypeEnforcer {

	private static final Logger LOGGER = LoggerFactory.getLogger(TypeEnforcerImpl.class);

	@Override
	public FileDTO enforceFile(FileDTO rf) {
		return rf;
	}

	@Override
	public FileDescriptorDTO enforceFileDescriptor(FileDescriptorDTO rf) {
		return rf;
	}

	@Override
	public OperationStatusDTO enforceOperationStatus(OperationStatusDTO rf) {
		return rf;
	}
}
