package antvictor.study.xml.customs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Antvictor
 * @date 2023/12/28
 **/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Signature", propOrder = {
        "signatureValue"
})
public class Signature {
    @XmlElement(name = "SignatureValue")
    private String signatureValue;
}
