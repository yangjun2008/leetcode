package com.m3.learning.java.basic;

public class CycBreak {
    public static void main(String[] args) {
        out:
        while (true) {
            for (int i = 0; i < 10; i++) {
                if (i == 0) {
                    break out;
                }
            }
            System.out.println("hello");
        }
        System.out.println("world");

        continueOuter();
        breakOuter();
    }

    private static void continueOuter(){
        int[] a = {100, 101, 102};
        OUTER:
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < a.length; j++) {
                if (j == 1) {
                    continue OUTER;
                }
                else {
                    System.out.println('A');
                }
            }
            System.out.println('B');
        }
        System.out.println('C');
    }

    private static void breakOuter(){
        int[] a = {100, 101, 102};
        OUTER:
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < a.length; j++) {
                if (j == 1) {
                    break OUTER;
                }
                else {
                    System.out.println('A');
                }
            }
            System.out.println('B');
        }
        System.out.println('C');
    }

    public native void XXX();
}
