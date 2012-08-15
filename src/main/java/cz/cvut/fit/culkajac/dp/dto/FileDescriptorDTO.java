package cz.cvut.fit.culkajac.dp.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class FileDescriptorDTO implements Serializable {

	private static final long serialVersionUID = -8800063914769240684L;

	private static final String DEFAULT_ETAG = "*";

	private static final String DEFAULT_EXT = "bin";

	private String title;

	private String extension = DEFAULT_EXT;

	private String fileInternalId = "";

	private String etag = DEFAULT_ETAG;

	// private String containerServiceDescriptor = "";

	private String sourceService = "";

	private String targetService = "";

	private Long contentLength = null;

	public FileDescriptorDTO() {
	}

	public FileDescriptorDTO(String title) {
		this.setTitle(title);
	}

	public String getTitle() {
		return this.title;
	}

	public String getFileInternalId() {
		return this.fileInternalId;
	}

	public void setTitle(String title) {
		this.title = title;

		int i = title.lastIndexOf(".");
		if (i > 0) {
			setExtension(title.substring(i + 1));
		}
	}

	public void setFileInternalId(String fileInternalId) {
		this.fileInternalId = fileInternalId;
	}

	public String getEtag() {
		return this.etag;
	}

	public void setEtag(String etag) {
		this.etag = etag;
	}

	public String getExtension() {
		return this.extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public Long getContentLength() {
		return this.contentLength;
	}

	public void setContentLength(Long contentLength) {
		this.contentLength = contentLength;
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	public String getBaseTitle() {
		try {
			return getTitle().substring(0, getTitle().lastIndexOf(".") - 1);
		} catch (Exception e) {
		}
		return getTitle();
	}

	@Override
	public String toString() {
		return String.format("[%s]:[%s]:S[%s]:T[%s]",
				this.getTitle(),
				this.fileInternalId,
				this.sourceService,
				this.targetService);
	}

	public String getTargetService() {
		return targetService;
	}

	public void setTargetService(String targetService) {
		this.targetService = targetService;
	}

	public String getSourceService() {
		return sourceService;
	}

	public void setSourceService(String sourceService) {
		this.sourceService = sourceService;
	}

}
