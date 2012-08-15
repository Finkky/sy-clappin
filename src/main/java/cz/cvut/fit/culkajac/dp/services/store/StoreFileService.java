package cz.cvut.fit.culkajac.dp.services.store;

import java.util.Collection;

import cz.cvut.fit.culkajac.dp.dto.FileDTO;
import cz.cvut.fit.culkajac.dp.dto.OperationStatusDTO;

public interface StoreFileService {

	@SuppressWarnings("rawtypes")
	Collection<OperationStatusDTO> process(FileDTO file);

}
