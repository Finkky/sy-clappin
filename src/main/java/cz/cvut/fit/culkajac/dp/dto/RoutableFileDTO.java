package cz.cvut.fit.culkajac.dp.dto;

import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.google.common.collect.Sets;

public class RoutableFileDTO {

	private FileDTO message;

	private Set<String> destination;

	public RoutableFileDTO(FileDTO f) {
		this.message = f;
		this.destination = Sets.newHashSet();
	}

	public FileDTO getMessage() {
		return this.message;
	}

	public Set<String> getDestination() {
		return this.destination;
	}

	public void setMessage(FileDTO message) {
		this.message = message;
	}

	public void setDestination(Set<String> destination) {
		this.destination = destination;
	}

	public void insertRoute(String routeName) {
		this.destination.add(routeName);
	}

	public boolean hasRoute(String routeName) {
		// return true;
		return this.destination.contains(routeName);
	}

	@Override
	public String toString() {
		return "RoutableFile";
		//return ReflectionToStringBuilder.toString(this);
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
