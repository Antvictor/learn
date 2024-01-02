package antvictor.study.xml;
 
import javax.xml.bind.annotation.*;

import java.util.List;

import static antvictor.study.xml.NameSpace.NAMESPACE_CEB_URI;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CEB311Message", namespace = NAMESPACE_CEB_URI)
public class CEB311Message {
    @XmlElement(name = "Order", namespace = NAMESPACE_CEB_URI)
    private List<Order> order;

    @XmlAttribute(name = "guid")
    private String guid;
 
    @XmlAttribute(name = "version")
    private String version;

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}