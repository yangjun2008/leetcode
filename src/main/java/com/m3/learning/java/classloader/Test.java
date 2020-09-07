package com.m3.learning.java.classloader;

public class Test {
    public static void main(String[] args) throws  InterruptedException {
        JuntorClass ju = new JuntorClass();
    }

}

class SentorClass {
    private String name ="p";
    public SentorClass() {
        System.out.println("Parent construct");
        toString();
    }

    @Override
    public String toString() {
        System.out.println("Sentor");
        return "Sentor";
    }
}

class JuntorClass extends SentorClass{
    private String name;

    public JuntorClass() {
        super();
        System.out.println("Child constructor");
        name = "Juntor";
    }

    @Override
    public String toString() {
        System.out.println(name);
        return name.toUpperCase();
    }
}
