package com.techelevator.reflection;

import java.lang.reflect.Method;

public class RectangleIntrospector {

    public static void main(String[] args) {

        Rectangle rect = new Rectangle();
        rect.setHeight(10);
        rect.setWidth(5);

        Method[] methods = Rectangle.class.getMethods();
        for (Method method : methods) {
            System.out.format("Method: %s%n", method.getName());

            if (method.getName().equals("getArea")) {
                try {
                    System.out.format("Invoking getArea on rect returns %d%n", method.invoke(rect));
                } catch (Exception e) {
                    System.out.format("Caught unexpected %s. Msg: %s%n", e.getClass().getName(), e.getMessage());
                }
            }
        }
    }
}