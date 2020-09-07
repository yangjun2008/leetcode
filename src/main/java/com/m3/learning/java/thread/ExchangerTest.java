package com.m3.learning.java.thread;

import java.util.concurrent.Exchanger;

public class ExchangerTest {

    static class Vehicle extends Thread {

        private Exchanger<String> exchanger;

        public Vehicle(Exchanger<String> exchanger, String name) {
            this.exchanger = exchanger;
            setName(name);
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " : " + exchanger.exchange(Thread.currentThread().getName()));
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        Vehicle car = new Vehicle(exchanger, "Car");
        Vehicle bike = new Vehicle(exchanger, "bike");
        car.start();
        bike.start();
    }
}
