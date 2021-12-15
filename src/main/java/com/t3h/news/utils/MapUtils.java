package com.t3h.news.utils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MapUtils {

    public static <T> T mapRow(T object, ResultSet resultSet){
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field: fields) {
            field.setAccessible(true);
            try {
                Object data = resultSet.getObject(field.getName(),field.getType());
                if (data != null){
                    field.set(object,resultSet.getObject(field.getName(),field.getType()));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return object;
    }
}
