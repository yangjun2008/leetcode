package com.m3.learning.java.basic;

public class IntegerEquals {

    public static void main(String[] args) {
        Integer in1 = 11;
        Integer in2 = new Integer(11);
        System.out.println(in1 == in2);
        System.out.println(in1 == Integer.valueOf(in2));
    }
}
