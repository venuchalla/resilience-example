package com.example.resilience.java;


import java.util.Arrays;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
       // Palindrome p =new Palindrome();
       // boolean b=  p.isPalinDromeUsingWhile(121);
        //System.out.println(b);
       // RomanToInt romanToInt = new RomanToInt();
//int result = romanToInt.romanToInt("MCMXCIV");
//System.out.println(result);
//RemoveDuplicatesFromArray removeDuplicatesFromArray= new RemoveDuplicatesFromArray();
//int[] input = new int[]{0,0,1,1,1,2,2,3,3,4};
//removeDuplicatesFromArray.removeDuplicates(input);
//DemoSummarizedIntClass demoSummarizedIntClass = new DemoSummarizedIntClass();
//demoSummarizedIntClass.summarizedDemoMethod();
        Arrays.stream(colour.values()).forEachOrdered(System.out::println);
        Stream.of(colour.values()).forEach(System.out::println);
    }
    enum colour{
        red,blue,green,yellow
    }

}
