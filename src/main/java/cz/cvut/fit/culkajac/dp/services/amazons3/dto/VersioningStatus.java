//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.03.14 at 07:35:52 odp. CET 
//


package cz.cvut.fit.culkajac.dp.services.amazons3.dto;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for VersioningStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="VersioningStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Enabled"/>
 *     &lt;enumeration value="Suspended"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "VersioningStatus")
@XmlEnum
public enum VersioningStatus {

    @XmlEnumValue("Enabled")
    ENABLED("Enabled"),
    @XmlEnumValue("Suspended")
    SUSPENDED("Suspended");
    private final String value;

    VersioningStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static VersioningStatus fromValue(String v) {
        for (VersioningStatus c: VersioningStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
