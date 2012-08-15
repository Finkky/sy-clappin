package cz.cvut.fit.culkajac.dp.services.retrieve.impl;

import org.switchyard.component.bpm.Process;

import cz.cvut.fit.culkajac.dp.services.retrieve.RetrieveFileService;

@Process(value = RetrieveFileService.class, 
	definition = "/cz/cvut/fit/culkajac/dp/services/retrieve/retrieve.bpmn", 
	definitionType = "BPMN2", 
	id = "RetrieveFileProcess"
	)
public interface RetrieveFileServiceProcess {
}
