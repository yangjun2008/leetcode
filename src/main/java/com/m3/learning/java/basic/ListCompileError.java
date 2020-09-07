package com.m3.learning.java.basic;

import java.util.ArrayList;
import java.util.List;

public class ListCompileError {
    public static void main(String[] args) {
        List strList = new ArrayList<>();
        List objList = new ArrayList<>();
        System.out.println("START");
        objList = strList;
        System.out.println("END");
    }
}
