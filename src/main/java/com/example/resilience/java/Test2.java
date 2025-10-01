package com.example.resilience.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test2 {


    public void stringReverseUsingtStringBuilder(String string) {

        String result = new StringBuilder(string)
                .reverse().toString();
        System.out.println(result);

    }

    public void stringReverse(String str) {
        System.out.println(str);
        int length = str.length();
        String reverseString = IntStream
                .range(0, str.length())
                .map(i -> (length - 1) - i)
                .mapToObj(str::charAt)
                .map(String::valueOf).collect(Collectors.joining());
        //.collect(Collectors.joining());
        System.out.println(reverseString);
    }

    public static void main(String[] args) {
        String str = "automation";
        Test2 test2 = new Test2();
        //test2.stringReverseUsingtStringBuilder(str);
        //test2.stringReverse(str);
        test2.swapNumbers(1255,1355);
    }

    public void continueTest() {
        for (int i = 0; i < 3; i++) {
            if (i == 1) {
                continue;

            }
            System.out.println("i =" + i);
        }
    }

    public void swapNumbers(int x,int y) {

        System.out.println("x:" + x + " y:" + y);
        x = x+y;
        y =x-y;
        x= x-y;
        System.out.println(" after swapping x:" + x + " y:" + y);

    }
    public void generateFibonacci(int x){
        int a=0 ,b=0,c=1;

        IntStream.range(0,x).forEach(new IntConsumer() {
            @Override
            public void accept(int value) {
                a=b;
                b=c;
                c=a+b;
            }
        });
    }

}
