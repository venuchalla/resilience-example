package com.example.resilience.java.producerConsumerExample;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class PCExample {

    public static void main(String[] args){
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(11);

        ConsumerExample consumerExample = new ConsumerExample(blockingQueue,"1");
       // ConsumerExample consumerExample2 = new ConsumerExample(blockingQueue,"2");

        ProducerExample producerExample = new ProducerExample(blockingQueue);
        new Thread(producerExample).start();
        new Thread(consumerExample).start();
    }
}
