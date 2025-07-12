package com.example.resilience.example;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Test {


    private static void findFirstNonRepeatingCharcter(){

        String s = "swiss";
     //   s.chars().mapToObj(c->(char)c).collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new,))


    }

    private static  void test(){
        System.out.println("teste");
        int a[] = {2,3,2,3,2,4};

        Map<Integer, Integer> resultMap = new HashMap<>();
        for( Integer i : a){
            if(resultMap.get(i) != null){
                Integer count = resultMap.get(i)+1;
                resultMap.put(i, count);
            }else{
                resultMap.put(i,1);
            }
        }

        resultMap.entrySet().forEach(System.out::println);
        System.out.println("=====");
        // convert string int array
        Map<Integer,Long>  duplcateMap = Arrays
                .stream(a)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
                .entrySet()
                .stream()
                .filter(i -> i.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
        duplcateMap.entrySet().forEach(System.out::println);



    }
    public static void main(String[] args){
       findFirstNonRepeatingCharcter();
    }
}
