package com.m3.learning.java.basic;

import javax.swing.text.TabExpander;

public class FinallyModify {

    static class Test {
        int val;
    }

    public static Test test() {
        Test t = new Test();
        try {
            t.val = 20;
            return t;
        } finally {
            t.val = 30;
        }
    }

    public static int test2() {
        try {
            throw  new NullPointerException("Failed to execute.");
        }
        catch(Exception e) {
            return 0;
        }
        finally {
            return 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(test().val);
        System.out.println(test2());
    }
}
