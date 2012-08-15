package cz.cvut.fit.culkajac.dp.services.dropboxsync.impl;

import org.switchyard.component.bpm.Process;

import cz.cvut.fit.culkajac.dp.services.dropboxsync.DropboxSyncService;

@Process(value = DropboxSyncService.class, 
definition = "/cz/cvut/fit/culkajac/dp/services/dropboxsync/dropboxSync.bpmn", 
definitionType = "BPMN2", 
id = "DropboxSyncProcess"
)
public interface DropboxSyncServiceProcess {

}
