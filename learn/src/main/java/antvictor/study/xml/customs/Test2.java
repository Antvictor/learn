package antvictor.study.xml.customs;



import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Antvictor
 * @date 2023/12/27
 **/
public class Test2 {
    public static void main(String[] args) throws JAXBException {

        CEB311Message obj = new CEB311Message();
        Order order = new Order();
        order.setNo("1234567788");
        List<Order> orderList = new ArrayList<>();
        orderList.add(order);
        Order order1 = new Order();
        order1.setNo("231241234123412");
        orderList.add(order1);
        obj.setOrder(orderList);
        obj.setGuid("123123123123");
        obj.setVersion("1.0");
        JAXBContext context = JAXBContext.newInstance(CEB311Message.class);
        Marshaller marshaller = context.createMarshaller();
        //格式化xml格式
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        //去掉生成xml的默认报文头
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

        StringWriter writer = new StringWriter();
        writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "\n    ");  // 由于不能优雅的去掉 standalone="yes" 所以只能去掉整个头部，然后手动插入一个符合条件的头部   该行为不优雅需要进行升级
        marshaller.marshal(obj, writer);

        try (FileOutputStream fop = new FileOutputStream(new File("/Users/exccedy/project/common/study/learn/src/main/resources/dd.xml"))) {
            byte[] bytes = writer.toString().getBytes();
            fop.write(bytes);
            fop.flush();
            fop.close();
            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();


        }
    }
}
