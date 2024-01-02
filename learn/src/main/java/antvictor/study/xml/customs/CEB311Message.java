package antvictor.study.xml.customs;


import javax.xml.bind.annotation.*;
import java.util.List;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CEB311Message")
public class CEB311Message {
    @XmlElement(name = "Order")
    private List<Order> order;

    @XmlAttribute(name = "guid")
    private String guid;
 
    @XmlAttribute(name = "version")
    private String version;

    @XmlElement(name = "signature")
    private Signature signature;

    public Signature getSignature() {
        return signature;
    }

    public void setSignature(Signature signature) {
        this.signature = signature;
    }

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