package cz.cvut.fit.culkajac.dp.services.dropboxrs;

import cz.cvut.fit.culkajac.dp.dto.FileDescriptorDTO;

public interface DropboxRSServiceProxy {

	void process(FileDescriptorDTO fd);
}
