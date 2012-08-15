package cz.cvut.fit.culkajac.dp.services.dropboxsync;

import java.util.Collection;

import cz.cvut.fit.culkajac.dp.dto.FileDescriptorDTO;

public interface ThrottlingSplitter {
	void process(Collection<FileDescriptorDTO> o);
}
