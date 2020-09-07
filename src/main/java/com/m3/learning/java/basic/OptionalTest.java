package com.m3.learning.java.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionalTest {
    public static void main(String[] args) {
        List<String> arrayList = new ArrayList<String>();
        Optional<List<String>> test = Optional.of(arrayList);
        Optional a = null;
        System.out.println(test.get());
    }
}
