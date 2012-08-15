package cz.cvut.fit.culkajac.dp.services.store.impl;

import org.switchyard.component.bpm.Process;

import cz.cvut.fit.culkajac.dp.services.store.StoreFileService;

@Process(value = StoreFileService.class, 
	definition = "/cz/cvut/fit/culkajac/dp/services/store/store.bpmn", 
	definitionType = "BPMN2", 
	id = "StoreFileProcess"
	)
public interface StoreFileServiceProcess {
}
