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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CreateBucketReturn" type="{http://s3.amazonaws.com/doc/2006-03-01/}CreateBucketResult"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "createBucketReturn"
})
@XmlRootElement(name = "CreateBucketResponse")
public class CreateBucketResponse {

    @XmlElement(name = "CreateBucketReturn", required = true)
    protected CreateBucketResult createBucketReturn;

    /**
     * Gets the value of the createBucketReturn property.
     * 
     * @return
     *     possible object is
     *     {@link CreateBucketResult }
     *     
     */
    public CreateBucketResult getCreateBucketReturn() {
        return createBucketReturn;
    }

    /**
     * Sets the value of the createBucketReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreateBucketResult }
     *     
     */
    public void setCreateBucketReturn(CreateBucketResult value) {
        this.createBucketReturn = value;
    }

}
