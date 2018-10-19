package com.nowburn.myspring.core;

import com.nowburn.myspring.bean.Bean;
import com.nowburn.myspring.config.XmlConfig;
import com.nowburn.myspring.utils.BeanUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Auther: hope email: pangxianran@hyperchain.cn
 * Date: 2018/10/17 下午5:57
 * Description:
 **/
public class ClassPathXmlApplicationContext implements BeanFactory {

    private Map<String, Object> ioc;
    private Map<String, Bean> beanMap;

    public ClassPathXmlApplicationContext(String path) {

        readXmlAndInstanceBeans(path);


    }

    @Override
    public Object getBean(String id) {
        return ioc.get(id);
    }


    private void readXmlAndInstanceBeans(String path) {
        ioc = new HashMap<>();
        beanMap = XmlConfig.getConfig(path);

        if (beanMap == null) {
            throw new RuntimeException("获取配置文件中的bean失败");
        }

        for (Map.Entry<String, Bean> entry : beanMap.entrySet()) {
            String id = entry.getKey();
            Bean bean = entry.getValue();

            Object object = BeanUtil.getObject(ioc, bean);
            ioc.put(id, object);
        }
    }

}
