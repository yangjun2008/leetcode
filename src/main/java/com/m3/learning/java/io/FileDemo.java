package com.m3.learning.java.io;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;

public class FileDemo {
    public static void main(String[] args) {
        System.out.println(Files.exists(Paths.get("D:\\t.txt")));
        System.out.println(new File("D:\\t.txt").exists());
    }
}
