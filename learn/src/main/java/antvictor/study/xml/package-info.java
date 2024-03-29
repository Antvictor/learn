/**
 * package-info.java
 * 放在和实体同一个包路径下
 */
@XmlSchema(
        xmlns = {
                @XmlNs(prefix = "ceb", namespaceURI = NAMESPACE_CEB_URI),
                @XmlNs(prefix = "xsi", namespaceURI = NAMESPACE_XSI_URI)
        }
)
package antvictor.study.xml;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlSchema;

import static antvictor.study.xml.NameSpace.NAMESPACE_CEB_URI;
import static antvictor.study.xml.NameSpace.NAMESPACE_XSI_URI;



