package com.m3.learning.java.thread;

import java.util.concurrent.Phaser;

public class PhaserTest {
    //模拟了100米赛宝，10名选手，只等裁判一声令下。当所有人都达到终点时，比赛结束。
    public static void main(String[] args) throws InterruptedException {
        final Phaser phaser = new Phaser(1);
        for(int index = 0; index < 10; index++) {
            phaser.register();
            new Thread(new Player(phaser), "player"+index).start();
        }
        System.out.println("Game Start");
        phaser.arriveAndDeregister();
        while(!phaser.isTerminated()) {

        }
        System.out.println("Game Over");
    }

    static class Player implements Runnable {
        private final Phaser phaser;

        Player(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            try {
                phaser.arriveAndAwaitAdvance();

                Thread.sleep((long)Math.random()*10000);
                System.out.println(Thread.currentThread().getName()+" ready.");
                phaser.arriveAndAwaitAdvance();

                Thread.sleep((long)Math.random()*10000);
                System.out.println(Thread.currentThread().getName()+ " arrived");
                phaser.arriveAndDeregister();
            }
            catch (Exception e) {

            }
        }
    }
}
