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
 *         &lt;element name="PutObjectInlineResponse" type="{http://s3.amazonaws.com/doc/2006-03-01/}PutObjectResult"/>
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
    "putObjectInlineResponse"
})
@XmlRootElement(name = "PutObjectInlineResponse")
public class PutObjectInlineResponse {

    @XmlElement(name = "PutObjectInlineResponse", required = true)
    protected PutObjectResult putObjectInlineResponse;

    /**
     * Gets the value of the putObjectInlineResponse property.
     * 
     * @return
     *     possible object is
     *     {@link PutObjectResult }
     *     
     */
    public PutObjectResult getPutObjectInlineResponse() {
        return putObjectInlineResponse;
    }

    /**
     * Sets the value of the putObjectInlineResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link PutObjectResult }
     *     
     */
    public void setPutObjectInlineResponse(PutObjectResult value) {
        this.putObjectInlineResponse = value;
    }

}
