package larry.study.minis;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author exccedy
 * @date 2023/3/14
 **/
public class ClassPathXmlApplicationContext {
    private List<BeanDefinition> beanDefinitionList = new ArrayList<>();
    private Map<String, Object> beans = new HashMap<>();

    public ClassPathXmlApplicationContext(String fileName) {
        // 加载Bean里面的数据到beanDefinitionList中
        this.readXml(fileName);
        // 初始化bean
        this.instanceBeans();
    }

    /**
     * 读取xml
     * @param fileName xml文件名称
     */
    private void readXml(String fileName) {
        SAXReader saxReader = new SAXReader();
        try {
            // 通过类加载器加载xml的url
            URL url = this.getClass().getClassLoader().getResource(fileName);
            // 使用SAXReader读取xml文档
            Document document = saxReader.read(url);
            // 获取文档中的模版<bean>....信息
            Element rootElement = document.getRootElement();
            // 遍历每个element
            for (Element element : (List<Element>) rootElement.elements()) {
                String id = element.attributeValue("id");
                String className = element.attributeValue("className");
                // 将bean放入到集合中
                beanDefinitionList.add(new BeanDefinition(id, className));
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 实例化bean
     */
    private void instanceBeans() {
        for (BeanDefinition beanDefinition : beanDefinitionList) {
            try {
                beans.put(beanDefinition.getId(), Class.forName(beanDefinition.getClassName()).newInstance());
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取bean
     * @param beanName
     * @return
     */
    public Object getBean(String beanName) {
        return beans.get(beanName);
    }
}
