package com.example.resilience.java.producerConsumerExample;

import java.util.concurrent.BlockingQueue;
import java.util.stream.IntStream;

public class ProducerExample implements Runnable{
    private BlockingQueue<Integer> blockingQueue;
    //= new ArrayBlockingQueue<>();
   ProducerExample(BlockingQueue<Integer> blockingQueue){
        this.blockingQueue = blockingQueue;
    }
    @Override
    public void run() {
        IntStream.rangeClosed(0,10).boxed().forEach(e->
        {
            try {
                System.out.println("Producing :"+e);
                blockingQueue.put(e);
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        });
    }
}
