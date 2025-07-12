package com.example.resilience.example.java;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.function.IntConsumer;

public class DemoSummarizedIntClass {
    public  void summarizedDemoMethod(){
        IntConsumer consumer = System.out::println;
        List<Integer> integerList = Arrays.asList(1,2,3,3,4,5);
        IntSummaryStatistics intSummaryStatistics=integerList
               .stream()
               .mapToInt(value -> value).summaryStatistics();
        consumer.accept(intSummaryStatistics.getMax());
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);


    }
}
