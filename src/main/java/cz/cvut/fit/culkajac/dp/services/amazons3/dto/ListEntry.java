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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ListEntry complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ListEntry">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Key" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LastModified" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="ETag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Size" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Owner" type="{http://s3.amazonaws.com/doc/2006-03-01/}CanonicalUser" minOccurs="0"/>
 *         &lt;element name="StorageClass" type="{http://s3.amazonaws.com/doc/2006-03-01/}StorageClass"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListEntry", propOrder = {
    "key",
    "lastModified",
    "eTag",
    "size",
    "owner",
    "storageClass"
})
public class ListEntry {

    @XmlElement(name = "Key", required = true)
    protected String key;
    @XmlElement(name = "LastModified", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastModified;
    @XmlElement(name = "ETag", required = true)
    protected String eTag;
    @XmlElement(name = "Size")
    protected long size;
    @XmlElement(name = "Owner")
    protected CanonicalUser owner;
    @XmlElement(name = "StorageClass", required = true)
    protected StorageClass storageClass;

    /**
     * Gets the value of the key property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the value of the key property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKey(String value) {
        this.key = value;
    }

    /**
     * Gets the value of the lastModified property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastModified() {
        return lastModified;
    }

    /**
     * Sets the value of the lastModified property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastModified(XMLGregorianCalendar value) {
        this.lastModified = value;
    }

    /**
     * Gets the value of the eTag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getETag() {
        return eTag;
    }

    /**
     * Sets the value of the eTag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setETag(String value) {
        this.eTag = value;
    }

    /**
     * Gets the value of the size property.
     * 
     */
    public long getSize() {
        return size;
    }

    /**
     * Sets the value of the size property.
     * 
     */
    public void setSize(long value) {
        this.size = value;
    }

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
     * Gets the value of the storageClass property.
     * 
     * @return
     *     possible object is
     *     {@link StorageClass }
     *     
     */
    public StorageClass getStorageClass() {
        return storageClass;
    }

    /**
     * Sets the value of the storageClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link StorageClass }
     *     
     */
    public void setStorageClass(StorageClass value) {
        this.storageClass = value;
    }

}
