package cz.cvut.fit.culkajac.dp.services.zipper;

import java.util.Collection;

import cz.cvut.fit.culkajac.dp.FileServiceException;
import cz.cvut.fit.culkajac.dp.dto.FileDTO;

public interface ZipperService {
	FileDTO zip(Collection<FileDTO> files) throws FileServiceException;
}
