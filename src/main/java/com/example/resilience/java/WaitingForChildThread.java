package com.example.resilience.java;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class WaitingForChildThread {

    public static void main(String[] args) throws InterruptedException {
      
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                long starttime = System.nanoTime();
                for (int i = 0; i < 10; i++) {
                    System.out.println("First : " + i + "," + (System.nanoTime() - starttime / 1000000 + " milliseconds"));
                    try {
                        String s= "k";
                        s.toLowerCase();
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("Interrupted");
                    }
                }
            }
        };
        Thread thread = new Thread(runnable,"");
        thread.start();
        //Thread.startVirtualThread(runnable);
        //Make the current thread to sleep for 2 mins
        //using java.util.concurrent.timeunit
TimeUnit.MINUTES.sleep(2);
    }
}
