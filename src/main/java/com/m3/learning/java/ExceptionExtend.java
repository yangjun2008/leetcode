package com.m3.learning.java;

public class ExceptionExtend {

    static class ExceptionA extends Exception {

    }

    static class ExceptionB extends ExceptionA {

    }

    public static void main(String[] args) {
        try {
            int a = 3;
            throw new ExceptionB();
        }
        catch(ExceptionA e1) {
            System.out.println("ExceptionA");
        }
        catch(Exception e) {
            System.out.println("Exception");
        }
        finally {
            System.out.println("Finally");
        }
    }
}
