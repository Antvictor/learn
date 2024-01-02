package antvictor.study.xml;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author Antvictor
 * @date 2023/12/27
 **/



@XmlRootElement(name = "CEB311Message")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "uploadFiles", propOrder = {
        "guid",
        "version"
})
public class Object2Xml {
    private String guid;

    private String version;

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
