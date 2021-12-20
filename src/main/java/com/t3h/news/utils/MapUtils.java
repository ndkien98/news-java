package com.t3h.news.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MapUtils {

    public static <T> T mapRow(T object, ResultSet resultSet){
        Field[] fieldsSubClass = object.getClass().getDeclaredFields();
        Field[] fieldsSupperClass = object.getClass().getSuperclass().getDeclaredFields();
        Field[] totalField = MapUtils.sumArrayField(fieldsSubClass,fieldsSupperClass);
        for (Field field: totalField) {
            field.setAccessible(true);
            try {
                Object data = resultSet.getObject(field.getName(),field.getType());
                if (data != null){
                    field.set(object,resultSet.getObject(field.getName(),field.getType()));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.printf(e.getMessage());
            }
        }
        return object;
    }

    private static Field[] sumArrayField(Field[] first, Field[] second) {
        int length = first.length + second.length;
        Field[] result = new Field[length];
        for (int i = 0; i < first.length; i++) {
            result[i] = first[i];
        }
        int startSecondIndex = first.length;
        for (Field field : second) {
            result[startSecondIndex] = field;
            startSecondIndex++;
        }
        return result;
    }

}
