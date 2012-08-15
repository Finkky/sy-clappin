package cz.cvut.fit.culkajac.dp.services.s3store.impl;

import org.switchyard.component.rules.Execute;
import org.switchyard.component.rules.Rules;

import cz.cvut.fit.culkajac.dp.dto.FileDescriptorDTO;
import cz.cvut.fit.culkajac.dp.services.s3store.S3StoreSelectionService;

@Rules(value = S3StoreSelectionService.class, resources = { "/cz/cvut/fit/culkajac/dp/services/s3store/s3StoreRules.drl" })
public interface S3StoreSelectionServiceRules extends S3StoreSelectionService {
	@Override
	@Execute
	public void process(FileDescriptorDTO file);
}