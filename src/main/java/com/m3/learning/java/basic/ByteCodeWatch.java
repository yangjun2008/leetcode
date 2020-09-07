package com.m3.learning.java.basic;

public class ByteCodeWatch {
    public static void main(String[] args) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("sss");
            sb = null;
            sb.append(1);
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
