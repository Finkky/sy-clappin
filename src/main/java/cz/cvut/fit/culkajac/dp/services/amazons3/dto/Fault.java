package cz.cvut.fit.culkajac.dp.services.amazons3.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Fault")
public class Fault {

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "detail")
	public static class Detail {

		@XmlElement(name = "key", namespace = "")
		protected String key;

		public void setKey(String key) {
			this.key = key;
		}

		public String getKey() {
			return key;
		}
	}

	@XmlElement(name = "faultcode", required = true, namespace = "")
	protected String faultCode;
	@XmlElement(name = "faultstring", namespace = "")
	protected String faultString;
	@XmlElement(name = "detail", namespace = "")
	protected Detail detail;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public String getFaultCode() {
		return faultCode;
	}

	public String getFaultString() {
		return faultString;
	}

	public Detail getDetail() {
		return detail;
	}

	public void setFaultCode(String faultCode) {
		this.faultCode = faultCode;
	}

	public void setFaultString(String faultString) {
		this.faultString = faultString;
	}

	public void setDetail(Detail detail) {
		this.detail = detail;
	}

}
