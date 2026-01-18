package com.example.resilience.java.producerConsumerExample;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ProducerConsumerExample {

    public static void main(String[] args){
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);
        Runnable producer = () -> IntStream.rangeClosed(0,11).boxed().forEach(e-> {
        try {
            blockingQueue.put(e);
            System.out.println("producing :"+e);
            Thread.sleep(TimeUnit.MILLISECONDS.toMillis(5000));
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
});
        Runnable consumer = () -> IntStream.rangeClosed(0,11).boxed().forEach(e-> {
            try {
                int item =blockingQueue.take();
                System.out.println("consumed :"+item);
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        });
        new Thread(producer).start();
        new Thread(consumer).start();

    }
}
