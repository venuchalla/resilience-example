package com.example.resilience.example.java;

import java.util.HashMap;
import java.util.Map;

public class RomanToInt {
    public int romanToInt(String s){
        //Map<String ,Integer>  hashMap = new HashMap<String,Integer>();
        Map<String,Integer> hashMap = new HashMap<>();

        //int result=0;
        hashMap.put("I",1);
        hashMap.put("V",5);
        hashMap.put("X",10);
        hashMap.put("L",50);
        hashMap.put("C",100);
        hashMap.put("D",500);
        hashMap.put("M",1000);
         int result= s.chars().mapToObj(value -> {
             char c = (char) value;
             String s1 = String.valueOf(c);
             return hashMap.getOrDefault(s1,0);
         }).reduce(0,Integer::sum);
         return result;
    }
}
