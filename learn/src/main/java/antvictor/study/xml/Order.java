package antvictor.study.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Antvictor
 * @date 2023/12/27
 **/

@XmlAccessorType(XmlAccessType.FIELD)
public class Order {
    @XmlAttribute(name = "no")
    String no;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }
}
