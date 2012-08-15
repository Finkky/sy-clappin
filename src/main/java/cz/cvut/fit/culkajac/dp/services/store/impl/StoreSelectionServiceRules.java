package cz.cvut.fit.culkajac.dp.services.store.impl;

import org.switchyard.component.rules.Execute;
import org.switchyard.component.rules.Rules;

import cz.cvut.fit.culkajac.dp.dto.FileDTO;
import cz.cvut.fit.culkajac.dp.services.store.StoreSelectionService;

@Rules(value = StoreSelectionService.class, resources = { "/cz/cvut/fit/culkajac/dp/services/store/storeRules.drl" })
public interface StoreSelectionServiceRules extends StoreSelectionService {
	@Override
	@Execute
	public void process(FileDTO file);
}