package cz.cvut.fit.culkajac.dp.services.delete.impl;

import org.switchyard.component.bpm.Process;

import cz.cvut.fit.culkajac.dp.services.delete.DeleteFileService;

@Process(value = DeleteFileService.class, 
	definition = "/cz/cvut/fit/culkajac/dp/services/delete/delete.bpmn", 
	definitionType = "BPMN2", 
	id = "DeleteFileProcess"
	)
public interface DeleteFileServiceProcess {
}
