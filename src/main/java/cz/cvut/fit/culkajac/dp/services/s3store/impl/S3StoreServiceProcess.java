package cz.cvut.fit.culkajac.dp.services.s3store.impl;

import org.switchyard.component.bpm.Process;

import cz.cvut.fit.culkajac.dp.services.s3store.S3StoreService;

@Process(value =  S3StoreService.class, 
definition = "/cz/cvut/fit/culkajac/dp/services/s3store/s3Store.bpmn", 
definitionType = "BPMN2", 
id = "S3StoreFileProcess")
public interface S3StoreServiceProcess {
}
