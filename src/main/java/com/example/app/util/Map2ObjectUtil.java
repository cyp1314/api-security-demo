package com.example.app.util;

import com.example.app.entity.User;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class Map2ObjectUtil {

    public static Map<Object, Object> getObjectToMap(Object obj) throws IllegalAccessException {
        Map<Object, Object> map = new HashMap<>();
        Class<?> cla = obj.getClass();
        Field[] fields = cla.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String keyName = field.getName();
            Object value = field.get(obj);
            if (value == null)
                value = "";
            map.put(keyName, value);
        }
        return map;
    }

    //Map转Object
    public static Object getMapToObject(Map<Object, Object> map, Class<?> cla) throws Exception {
        if (map == null)
            return null;
        Object obj = cla.newInstance();
        Class<?> claa = obj.getClass();
        Field[] fields = claa.getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                continue;
            }
            field.setAccessible(true);
            if (map.containsKey(field.getName())) {
                field.set(obj, map.get(field.getName()));
            }
        }
        return obj;
    }

    public static void main(String[] args) {
        User user = new User();
        user.setId(1);
        user.setUsername("lisi");
        user.setPassword("123456");

        try {
            Map<Object, Object> objectToMap = Map2ObjectUtil.getObjectToMap(user);


            try {
                User mapToObject = (User) Map2ObjectUtil.getMapToObject(objectToMap, User.class);
                System.out.println(mapToObject.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }

}
