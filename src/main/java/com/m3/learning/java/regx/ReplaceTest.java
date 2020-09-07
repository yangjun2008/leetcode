package com.m3.learning.java.regx;

import java.util.regex.Pattern;

public class ReplaceTest {
    public static void main(String[] args) {
        String s1 = "123\\d";
        System.out.println(s1.replaceAll("\\d", "456"));
        System.out.println(s1.replaceAll(Pattern.quote("\\d"), "456"));
        System.out.println(s1.replace("\\d", "456"));

        char a = '\n';
        char b = 'f';
    }
}
