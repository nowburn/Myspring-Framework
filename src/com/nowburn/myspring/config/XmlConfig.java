package com.nowburn.myspring.config;

import com.nowburn.myspring.bean.Bean;
import com.nowburn.myspring.bean.Property;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Auther: hope email: pangxianran@hyperchain.cn
 * Date: 2018/10/17 下午4:20
 * Description:
 **/
public class XmlConfig {


    public static Map<String, Bean> getConfig(String path) {

        Map<String, Bean> beanMap = new HashMap<>();
        SAXReader reader = new SAXReader();
        InputStream is = XmlConfig.class.getResourceAsStream(path);
        Document doc;
        try {
            doc = reader.read(is);
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new RuntimeException("读取配置文件失败");
        }
        String xpath = "//bean";

        List<Element> nodes = doc.selectNodes(xpath);
        if (nodes == null) {
            throw new RuntimeException("配置文件中bean错误");
        }

        for (Element element : nodes) {
            Bean bean = new Bean();
            String id = element.attributeValue("id");
            bean.setId(id);
            bean.setName(element.attributeValue("class"));

            List<Property> propertyList = new ArrayList<>();
            List<Element> properties = element.elements("property");
            if (properties != null) {
                for (Element e : properties) {
                    Property p = new Property();
                    p.setName(e.attributeValue("name"));
                    p.setValue(e.attributeValue("value"));
                    p.setRef(e.attributeValue("ref"));
                    propertyList.add(p);
                }
            }
            bean.setPropertyList(propertyList);

            beanMap.put(id, bean);
        }

        return beanMap;

    }
}
