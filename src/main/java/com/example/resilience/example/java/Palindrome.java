package com.example.resilience.example.java;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Palindrome {
    public boolean isPalindrome(int x) {
       if(x<0){
           return  false;
       } else {
           int rX = IntStream.iterate(x, operand -> operand / 10)
                   .map(operand -> operand % 10)
                   .limit(String.valueOf(x).length())
                   .reduce(0, (a, b) -> a * 10 + b);
           return rX == x;
       }
    }
public boolean isPalinDromeUsingWhile(int x){
       if(x < 0 || x % 10 == 0) {
           return  false;

       }else{
        List<Integer> result = new ArrayList<>();
           while(x/10 > 0){
               result.add(x%10);
               x=x/10;
           }
           result.add(x);
           String r= result.stream().map(Object::toString).collect(Collectors.joining());
           StringBuilder sb= new StringBuilder(r);
           String reversedString = sb.reverse().toString();
           System.out.println("r : "+r + " : reversed"+reversedString);
           return  r.equals(reversedString);
       }
}
    public List<Integer> generate(int x){
       List<Integer> resultList= IntStream.range(0,x).boxed().collect(Collectors.toList());
       return resultList;
    }


}
