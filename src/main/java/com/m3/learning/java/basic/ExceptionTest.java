package com.m3.learning.java.basic;

public class ExceptionTest {
    public static void main(String[] args) {
        try {
            System.out.println("aa:"+func());
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

    public static int func() throws Exception {
        int elm = 1;
        for(int i = 0; i < 2 ; i++) {
            try {
                throw new Exception("bb");
            }
            catch(Exception ex) {
                throw ex;
            }
            finally {
                continue;
            }
        }
        return 0;
    }
}
