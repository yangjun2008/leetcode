package com.m3.learning.java.basic;

public class CycTest {

    public static void main(String[] args) {
        //test_1();
        //test_2();
        //test_3();
        //test_4();
        //test_5();
        test_6();
    }

    private static void test_6() {
        float end = Float.MAX_VALUE;
        float start = end - 10;
        int count = 0;
        for(float i = start; i<end; i++) {
            count++;
            System.out.println("i="+i+", count="+count);
        }
    }

    private static void test_5() {
        System.out.println(Float.MAX_VALUE);
        for(float i = 100000000; i <100000010; i++) {
            System.out.println(i);
        }
    }

    private static void test_4() {
        System.out.println(Float.MAX_VALUE);
        for(float i = 10000000; i <10000010; i++) {
            System.out.println(i);
        }
    }

    private static void test_3() {
        for(double i = 0; i !=10; i+=0.1) {
            System.out.println(i);
        }
    }

    private static void test_2() {
        for(int i = 0; i !=10; i++) {
            System.out.println(i);
        }
    }


    private static void test_1() {
        int end = Integer.MAX_VALUE;
        int start = end - 50;
        int count = 0;
        for(int i = start; i<=end; i++) {
            count++;
            System.out.println("i="+i+", count="+count);
        }
    }
}
