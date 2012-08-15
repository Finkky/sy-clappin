package cz.cvut.fit.culkajac.dp.transformers;

import java.util.Collection;

import org.switchyard.annotations.Transformer;

import com.google.common.collect.Lists;

import cz.cvut.fit.culkajac.dp.dto.FileDTO;
import cz.cvut.fit.culkajac.dp.dto.OperationStatusDTO;

public class MixedTransformers {

	@Transformer(from = "{http://s3.amazonaws.com/doc/2006-03-01/}WrappedFiles", to = "{http://s3.amazonaws.com/doc/2006-03-01/}Files")
	public Collection<FileDTO> toFileDTOCollection(Collection<OperationStatusDTO<FileDTO>> poi) {
		Collection<FileDTO> files = Lists.newArrayList();
		for (OperationStatusDTO<FileDTO> operationStatusDTO : poi) {
			if (operationStatusDTO.getStatus() == 200 && operationStatusDTO.getContent() != null) {
				files.add(operationStatusDTO.getContent());
			}

		}
		return files;
	}
}
