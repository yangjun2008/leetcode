package com.m3.learning.java.basic;

/**
 * Java继承访问成员变量，等号左边是谁就用谁，没有向上找父类的
 */
public class ExtendOrder {

    static class Base {
        public int id = 100;

        public void doSomething() {
            System.out.println("Base");
        }
    }

    static class Child extends Base {
        public int id = 101;

        @Override
        public void doSomething() {
            System.out.println("Child");
        }
    }

    public static void main(String[] args) {
        Base base = new Child();
        System.out.println(base.id);
        base.doSomething();

        Child child = new Child();
        System.out.println(child.id);
    }
}
