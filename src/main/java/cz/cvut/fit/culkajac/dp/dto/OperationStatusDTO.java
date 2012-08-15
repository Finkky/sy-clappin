package cz.cvut.fit.culkajac.dp.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class OperationStatusDTO<DTO> {

	private final int status;

	private final String serviceName;

	private final DTO content;

	private final Class<DTO> contentClass;

	public OperationStatusDTO(int status, String serviceName) {
		this.status = status;
		this.serviceName = serviceName;
		this.content = null;
		this.contentClass = null;
	}

	public OperationStatusDTO(int status, String serviceName, DTO content, Class<DTO> contentClass) {
		super();
		this.status = status;
		this.serviceName = serviceName;
		this.content = content;
		this.contentClass = contentClass;
	}

	public int getStatus() {
		return this.status;
	}

	public String getServiceName() {
		return this.serviceName;
	}

	public DTO getContent() {
		return this.content;
	}
	
	public Class<DTO> getContentClass() {
		return this.contentClass;
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
