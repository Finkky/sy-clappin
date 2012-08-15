package cz.cvut.fit.culkajac.dp.services.delete.impl;

import org.switchyard.component.rules.Execute;
import org.switchyard.component.rules.Rules;

import cz.cvut.fit.culkajac.dp.dto.FileDescriptorDTO;
import cz.cvut.fit.culkajac.dp.services.delete.DeleteSelectionService;

@Rules(value = DeleteSelectionService.class, resources = { "/cz/cvut/fit/culkajac/dp/services/delete/deleteRules.drl" })
public interface DeleteSelectionServiceRules extends DeleteSelectionService {
	@Override
	@Execute
	public void process(FileDescriptorDTO fd);
}