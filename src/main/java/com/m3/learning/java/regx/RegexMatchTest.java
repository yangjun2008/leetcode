package com.m3.learning.java.regx;

public class RegexMatchTest {
    public static void main(String[] args) {
        String str = "abc@x.y+com";
        String str1 = "ab_c@x.y.com";
        String regrex = "^[a-zA-Z0-9.-_]+@([a-zA-Z0-9]+.)+com$";

        System.out.println(str.matches(regrex));
        System.out.println(str1.matches(regrex));
        System.out.println("a+".matches("([a-zA-Z0-9].)+"));
    }
}
