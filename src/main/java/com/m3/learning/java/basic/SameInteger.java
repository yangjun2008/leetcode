package com.m3.learning.java.basic;

public class SameInteger {
    public static void main(String[] args) {
        Integer a = 2;
        int b = Integer.parseInt("2");
        Integer c = Integer.valueOf("2");
        System.out.println(a == b);
        System.out.println(a == c);
    }
}
