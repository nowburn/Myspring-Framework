package com.nowburn.myspring;

import com.nowburn.myspring.bean.Bean;
import com.nowburn.myspring.bean.User;
import com.nowburn.myspring.config.XmlConfig;
import com.nowburn.myspring.core.BeanFactory;
import com.nowburn.myspring.core.ClassPathXmlApplicationContext;

import java.util.Map;

public class Main {

    public static void main(String[] args) {

        testIOC();
      // testXmlConfig();

    }

    private static void testIOC() {
        BeanFactory bf = new ClassPathXmlApplicationContext("/ApplicationContext.xml");
        User user = (User) bf.getBean("user");
       // Address address = (Address) bf.getBean("address");
        System.out.println(user);

        user.say("哈哈");
    }

    private static void testXmlConfig(){
        Map<String, Bean> map = XmlConfig.getConfig("/ApplicationContext.xml");
        for(Map.Entry<String, Bean> entry:map.entrySet()){

            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }
}
