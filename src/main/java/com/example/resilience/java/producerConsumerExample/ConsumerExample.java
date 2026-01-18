package com.example.resilience.java.producerConsumerExample;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.IntStream;

public class ConsumerExample implements Runnable{

    private final BlockingQueue<Integer> blockingQueue;
    private final String id ;
            //= new ArrayBlockingQueue<>();
    ConsumerExample(BlockingQueue<Integer> blockingQueue,String id){
        this.blockingQueue = blockingQueue;
        this.id= id;
    }
    @Override
    public void run() {

        IntStream.rangeClosed(0,10).boxed().forEach( e->
        {
            try {
                int item =blockingQueue.take();
                System.out.println("consumed :"+item + ": consumer id : "+id);
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        });
    }
}
