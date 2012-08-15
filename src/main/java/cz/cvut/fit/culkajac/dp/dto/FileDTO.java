package cz.cvut.fit.culkajac.dp.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class FileDTO {

	public FileDTO(FileDescriptorDTO metadata, byte[] data) {
		super();
		this.metadata = metadata;
		this.data = data;
	}

	private FileDescriptorDTO metadata;
	private byte[] data;

	public FileDescriptorDTO getMetadata() {
		return this.metadata;
	}

	public byte[] getData() {
		return this.data;
	}

	public void setMetadata(FileDescriptorDTO metadata) {
		this.metadata = metadata;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "FileDTO#" + ReflectionToStringBuilder.toString(this.metadata);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
