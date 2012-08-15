package cz.cvut.fit.culkajac.dp.services.dropboxrs.impl;

import org.switchyard.component.bpm.Process;

import cz.cvut.fit.culkajac.dp.services.dropboxrs.DropboxRSService;

@Process(value = DropboxRSService.class, 
	definition = "/cz/cvut/fit/culkajac/dp/services/dropboxrs/dropboxRS.bpmn", 
	definitionType = "BPMN2", 
	id = "DropboxRSProcess"
	)
public interface DropboxRSServiceProcess {

}
