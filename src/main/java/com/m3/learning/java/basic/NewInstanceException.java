package com.m3.learning.java.basic;

public class NewInstanceException {
    public static void main(String[] args) {
        try {
            Class clz = Class.forName("com.m3.learning.java.basic.NewInstanceException");
            ((NewInstanceException)clz.newInstance()).help();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public NewInstanceException() throws Exception {
        throw new Exception("haha");
    }

    public void help() {
        System.out.println("help.");
    }
}
