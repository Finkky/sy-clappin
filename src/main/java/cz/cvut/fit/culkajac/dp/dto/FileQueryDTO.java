package cz.cvut.fit.culkajac.dp.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class FileQueryDTO {

	private boolean exact = false;

	private String fileTitle;

	public boolean isExact() {
		return this.exact;
	}

	public String getTitle() {
		return this.fileTitle;
	}

	public void setExact(boolean exact) {
		this.exact = exact;
	}

	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
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
