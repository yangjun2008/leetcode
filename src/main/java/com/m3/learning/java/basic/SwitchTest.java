package com.m3.learning.java.basic;

public class SwitchTest {
    public static void main(String[] args) {
        switch(5) {
            default:
                System.out.println(5);
            case 0:
                System.out.println(0);
            case 1:
                System.out.println(1);
                break;
            case 2:
                System.out.println(2);
                break;
        }
    }
}
