//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.03.14 at 07:35:52 odp. CET 
//


package cz.cvut.fit.culkajac.dp.services.amazons3.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AccessControlPolicy complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccessControlPolicy">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Owner" type="{http://s3.amazonaws.com/doc/2006-03-01/}CanonicalUser"/>
 *         &lt;element name="AccessControlList" type="{http://s3.amazonaws.com/doc/2006-03-01/}AccessControlList"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccessControlPolicy", propOrder = {
    "owner",
    "accessControlList"
})
public class AccessControlPolicy {

    @XmlElement(name = "Owner", required = true)
    protected CanonicalUser owner;
    @XmlElement(name = "AccessControlList", required = true)
    protected AccessControlList accessControlList;

    /**
     * Gets the value of the owner property.
     * 
     * @return
     *     possible object is
     *     {@link CanonicalUser }
     *     
     */
    public CanonicalUser getOwner() {
        return owner;
    }

    /**
     * Sets the value of the owner property.
     * 
     * @param value
     *     allowed object is
     *     {@link CanonicalUser }
     *     
     */
    public void setOwner(CanonicalUser value) {
        this.owner = value;
    }

    /**
     * Gets the value of the accessControlList property.
     * 
     * @return
     *     possible object is
     *     {@link AccessControlList }
     *     
     */
    public AccessControlList getAccessControlList() {
        return accessControlList;
    }

    /**
     * Sets the value of the accessControlList property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccessControlList }
     *     
     */
    public void setAccessControlList(AccessControlList value) {
        this.accessControlList = value;
    }

}