package com.m3.learning.java.thread;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    static CyclicBarrier c = new CyclicBarrier(5, new A());

    public static void main(String[] args) {
        for(int i = 0; i <5; i++) {
            final int t = i;
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        System.out.println(t+" 到达栅栏A");
                        c.await();
                        System.out.println(t+" 冲破栅栏A");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(1);
                }
            }).start();
        }
        System.out.println(2);
    }

    static class A implements Runnable {
        @Override
        public void run() {
            System.out.println(3);
        }
    }
}




