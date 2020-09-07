package com.m3.learning.java.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class VolatileTest {
    public volatile int inc = 0;
    public volatile int inc2 = 0;
    public volatile int inc3 = 0;
    public AtomicInteger inc4 = new AtomicInteger();

    private ReentrantLock lock = new ReentrantLock();

    public void increase() {
        inc++;
    }

    public synchronized void increase2() {
        inc2++;
    }

    public void increase3() {
        try {
            lock.lock();
            inc3++;
        }finally {
            lock.unlock();
        }
    }

    public void increase4() {
        inc4.incrementAndGet();
        inc4.getAndAdd(1);
        //inc4.set(inc4.getAndAdd(1));
    }

    public static void main(String[] args) {
        final VolatileTest test = new VolatileTest();
        for(int i = 0; i < 10; i++) {
            new Thread(() -> {
                for(int j = 0; j < 1000; j++) {
                    test.increase();
                    test.increase2();
                    test.increase3();
                    test.increase4();
                }
            }).start();
        }

        while(Thread.activeCount()>1)
            Thread.yield();
        System.out.println(test.inc);
        System.out.println(test.inc2);
        System.out.println(test.inc3);
        System.out.println(test.inc4.get());
    }
}
