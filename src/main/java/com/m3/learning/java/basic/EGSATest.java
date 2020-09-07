package com.m3.learning.java.basic;

import com.sun.xml.internal.messaging.saaj.util.FinalArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EGSATest {
    static class Animal {}
    static class Dog extends Animal{}
    static class Collie extends Dog{}

    public static void main(String[] args) {

        List<? extends Dog> extendsDogs =new ArrayList<>(Arrays.asList(new Collie()));
        List<? super Dog> superDogs = new ArrayList<>();

        //extendsDogs.add(new Animal());
        //extendsDogs.add(new Dog());
        //extendsDogs.add(new Collie());
        Dog dog = extendsDogs.get(0);
        Animal animal = extendsDogs.get(0);
        //Collie collie = extendsDogs.get(0);

       // superDogs.add(new Animal());
        superDogs.add(new Dog());
        superDogs.add(new Collie());
        Object obj = superDogs.get(0);
        //Animal animal2 = superDogs.get(0);
    }
}
