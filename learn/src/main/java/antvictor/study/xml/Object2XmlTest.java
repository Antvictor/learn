package antvictor.study.xml;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Antvictor
 * @date 2023/12/27
 **/
public class Object2XmlTest {
    public static void main(String[] args) throws JAXBException, IOException {
//        out();

        validate();

    }

    private static void out() throws IOException, JAXBException {
        // 创建对象并设置数据
        Object2Xml message = new Object2Xml();
        message.setGuid("1234567890");
        message.setVersion("1.0");

// 使用 JAXB 的 Marshaller 将对象转换为 XML 字符串

        FileWriter writer = new FileWriter("/Users/exccedy/project/common/study/learn/src/main/resources/aa.xml");

        JAXBContext context = JAXBContext.newInstance(Object2Xml.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(message, writer);
        writer.close();
    }

    private static void validate() {
        String xmlFilePath = "/Users/exccedy/project/common/study/learn/src/main/resources/bb.xml"; // 替换为你生成的 XML 文件路径
        String xsdFilePath = "/Users/exccedy/project/common/study/learn/src/main/resources/CEB311Message.xsd"; // 替换为你的 XSD 文件路径

        boolean isValid = validateXMLAgainstXSD(xmlFilePath, xsdFilePath);
        if (isValid) {
            System.out.println("XML is valid against XSD.");
        } else {
            System.out.println("XML is NOT valid against XSD.");
        }
    }

    private static boolean validateXMLAgainstXSD(String xmlFilePath, String xsdFilePath) {
        try {
            // 创建 XSD Schema 对象
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdFilePath));

            // 创建 Validator 对象
            Validator validator = schema.newValidator();

            // 准备 XML 文件作为验证源
            Source source = new StreamSource(new File(xmlFilePath));

            // 执行验证
            validator.validate(source);
            return true; // 验证通过
        } catch (Exception e) {
            // 验证失败，打印错误信息
            e.printStackTrace();
            return false; // 验证失败
        }
    }
}
