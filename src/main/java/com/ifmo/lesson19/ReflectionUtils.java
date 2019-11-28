package com.ifmo.lesson19;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public class ReflectionUtils {

    private static ArrayList<Class> primitiv = new ArrayList<>();

    public static void main(String[] args) throws IllegalAccessException {
        primitiv.add(int.class);
        primitiv.add(boolean.class);
        primitiv.add(byte.class);
        primitiv.add(short.class);
        primitiv.add(long.class);
        primitiv.add(String.class);

        MyClass cls = new MyClass(1, "2", new MyClass());
        System.out.println(toString(cls));
    }

    public static String toString(Object obj) throws IllegalAccessException {
        if(obj == null)
            return "";

        StringBuilder sb = new StringBuilder(obj.getClass().getName()).append("{\n");
        for(Field field : obj.getClass().getDeclaredFields()){
            if (field.getModifiers() == 2){
                field.setAccessible(true);
            }

            if(!field.isAnnotationPresent(Exclude.class))
            if(field.getType().isPrimitive() || field.getType() == String.class){
                    sb.append(" ").append(field.getName()).append("=").append(field.get(obj).toString()).append(";\n");
            } else {
                sb.append(toString(field.get(obj))).append("\n");
            }
        }

        return sb.append("}").toString();
    }


    public static  Object  deepClone(Object obj){
        return null;
    }


}
