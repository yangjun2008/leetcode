package com.m3.learning.java.basic;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class ArrayCreation {
    public static void main(String[] args) {
        float a[][] = new float[6][6];
        float []b[] = new float[6][6];

        //float c[][] = new float[][6];
        float [][]d = new float[6][6];
        float [][]f = new float[6][];
        float[][][] g  = new float[9][][];


        char abc = 1;
        byte abcd = 1;
        System.out.println(abc);
        System.out.println(abcd);
        System.out.println(abc == abcd);

        Hashtable<Integer, Integer> test = new Hashtable<>();
        test.put(2, null);
    }
}
