package cz.cvut.fit.culkajac.dp.services.retrieve.impl;

import org.switchyard.component.rules.Execute;
import org.switchyard.component.rules.Rules;

import cz.cvut.fit.culkajac.dp.dto.FileDescriptorDTO;
import cz.cvut.fit.culkajac.dp.services.retrieve.RetrieveSelectionService;

@Rules(value = RetrieveSelectionService.class, resources = { "/cz/cvut/fit/culkajac/dp/services/retrieve/retrieveRules.drl" })
public interface RetrieveSelectionServiceRules extends RetrieveSelectionService {
	@Override
	@Execute
	public void process(FileDescriptorDTO file);
}