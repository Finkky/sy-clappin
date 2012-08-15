package cz.cvut.fit.culkajac.dp.services.s3retrieve.impl;

import org.switchyard.component.bpm.Process;

import cz.cvut.fit.culkajac.dp.services.s3retrieve.S3RetrieveService;

@Process(value = S3RetrieveService.class, 
	definition = "/cz/cvut/fit/culkajac/dp/services/s3retrieve/s3Retrieve.bpmn", 
	definitionType = "BPMN2", 
	id = "S3RetrieveFileProcess"
	)
public interface S3RetrieveServiceProcess {
}
