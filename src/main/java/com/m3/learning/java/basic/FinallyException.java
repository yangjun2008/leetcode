package com.m3.learning.java.basic;

public class FinallyException {
    public static void main(String[] args) {
        try {
            int num1 = 10;
            int num2 = 0;
            int result = num1/num2;
            System.out.println("calcute end");
            char abc = 1;
        }
        catch(NumberFormatException e) {
            System.out.println("NumberFormatException");
        }
        catch(Exception e) {
            System.out.println("Exception");
        }
        finally {
            System.out.println("finally");
        }
    }
}
