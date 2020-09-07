package com.m3.learning.java.refrect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class FieldTest {

    static class A {
        private String a = "A";

        public A() {
        }

        public A(String a) {
            this.a = a;
        }

        public int test(int a, Integer b) {
            return a+b;
        }
    }

    public static void main(String[] args) {
        try {
            A a = A.class.newInstance();
            Field[] fields = A.class.getFields();
            for(Field f : fields) {
                System.out.println(f.get(a));
            }

            Method testMethod = A.class.getMethod("test", new Class[]{int.class, Integer.class});
            Object invoke1 = testMethod.invoke(a, new Object[]{1, Integer.valueOf(2)});
            System.out.printf("%d", invoke1);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
