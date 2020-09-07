package com.m3.learning.java.basic;

/**
 * 执行顺序： return ret, return 0, 0覆盖了ret
 */
public class FinallyReturn {

    public static void main(String[] args) {
        System.out.println(fun(2));
    }

    public static int fun(int n) {
        int[] number = {1, 2, 3, 4};
        try {
            int ret = number[n]*2;
            return ret;
        }
        catch(Exception e) {
            return n*3;
        }
        finally {
            if(n == 2) {
                return 0;
            }
        }
    }
}
