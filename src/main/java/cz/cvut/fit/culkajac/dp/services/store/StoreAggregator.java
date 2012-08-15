package cz.cvut.fit.culkajac.dp.services.store;

import java.util.Collection;

import cz.cvut.fit.culkajac.dp.dto.OperationStatusDTO;

public interface StoreAggregator {
	Collection<OperationStatusDTO> process(OperationStatusDTO o);
}
