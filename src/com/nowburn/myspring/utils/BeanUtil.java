package com.nowburn.myspring.utils;

import com.nowburn.myspring.bean.Bean;
import com.nowburn.myspring.bean.Property;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Auther: hope email: pangxianran@hyperchain.cn
 * Date: 2018/10/17 下午6:19
 * Description:
 **/
public class BeanUtil {


    public static Object getObject(Map<String, Object> ioc, Bean bean) {

        String className = bean.getName();
        Object object;
        try {
            Class<?> clazz = Class.forName(className);
            object = clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("配置文件中bean的name错误：" + className);
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException("实例化类出错：" + className + e);
        }

        // 将bean的属性封装到对象中
        List<Property> propertyList = bean.getPropertyList();
        if (propertyList == null) {
            return object;
        }

        for (Property property : propertyList) {
            if (property.getValue() != null) {
                Method setMethod = getSetMethod(object, property.getName());
                try {
                    setMethod.invoke(object, property.getValue());
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException("属性名称不合法或没有相应的get方法");
                }
            }

            if (property.getRef() != null) {
                Method setMethod = getSetMethod(object, property.getName());
                Object obj = ioc.get(property.getRef());

                if (obj == null) {
                    throw new RuntimeException("没有找到依赖的对象:" + property.getRef());
                }

                try {
                    setMethod.invoke(object, obj);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException("属性名称不合法或没有相应的get方法");
                }
            }

        }

        return object;

    }

    private static Method getSetMethod(Object obj, String name) {
        Method method = null;
        name = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);

        Method[] methods = obj.getClass().getMethods();
        for (Method m : methods) {
            if (m.getName().equals(name)) {
                try {
                    method = obj.getClass().getMethod(name, m.getParameterTypes());
                    break;
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }

        return method;
    }
}
