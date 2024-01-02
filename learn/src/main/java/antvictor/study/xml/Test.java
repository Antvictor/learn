package antvictor.study.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 * @author Antvictor
 * @date 2023/12/27
 **/
public class Test {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException {
        try {
            // 创建 XML 文档
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            // 创建根元素 CEB621Message，并添加命名空间和命名空间引用
            Element ceb621Message = doc.createElement("ceb:CEB621Message");
            ceb621Message.setAttribute("xmlns:ceb", "http://www.chinaport.gov.cn/ceb");
            ceb621Message.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            ceb621Message.setAttribute("guid", "4CDE1CFD-EDED-46B1-946C-B8022E42FC94");
            ceb621Message.setAttribute("version", "1.0");
            doc.appendChild(ceb621Message);

            // 创建 Inventory 元素及其子元素，并添加到根元素
            Element order = doc.createElement("ceb:Order");
            Element orderHead = doc.createElement("ceb:OrderHead");
            Element guid = doc.createElement("ceb:guid");
            guid.appendChild(doc.createTextNode("RTFV1CFD-EDED-46B1-946C-B8022E42FC94"));

            orderHead.appendChild(guid);
            order.appendChild(orderHead);
            ceb621Message.appendChild(order);

            // 将 Document 转换为 XML 字符串
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            // 保存 XML 到文件
            File outputFile = new File("/Users/exccedy/project/common/study/learn/src/main/resources/bb.xml");
            StreamResult result = new StreamResult(outputFile);
            transformer.transform(source, result);

            System.out.println("XML 文件已生成到：" + outputFile.getAbsolutePath());
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

}
