package com.m3.learning.java.basic;

public class AutoInc {
    public static void main(String[] args) {
        int count = 0;
        for(int i = 0; i < 100; i++) {
            count = count++;
        }
        System.out.println(count);

        int test = 2;
        int lim = 5;
        while(test < lim--){
            test++;
        }
        System.out.println(test);
    }
}
