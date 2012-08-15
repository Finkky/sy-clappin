package cz.cvut.fit.culkajac.dp.services.s3retrieve.impl;

import org.switchyard.component.rules.Execute;
import org.switchyard.component.rules.Rules;

import cz.cvut.fit.culkajac.dp.dto.FileDescriptorDTO;
import cz.cvut.fit.culkajac.dp.services.s3retrieve.S3RetrieveSelectionService;

@Rules(value = S3RetrieveSelectionService.class, resources = { "/cz/cvut/fit/culkajac/dp/services/s3retrieve/s3RetrieveRules.drl" })
public interface S3RetrieveSelectionServiceRules extends S3RetrieveSelectionService {
	@Override
	@Execute
	public void process(FileDescriptorDTO file);
}