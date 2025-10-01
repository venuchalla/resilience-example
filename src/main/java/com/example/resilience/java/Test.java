package com.example.resilience.java;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {


    public static void main(String[] args) {
        List<Integer> inputList = Arrays.asList(2, 4, 1, 3, 5, 8, 9, 10, 6, 5, 3, 4, 10, 12, 14, 9);
        //List<Integer> inputList = Stream.of(2,4,1,3,5,8,9,10,6,5,3,4,10,12,14,9).toList();
        List<Integer> longestClimb = new ArrayList<>();
        List<Integer> currentClimb = new ArrayList<>();
        for (int i = 0; i < inputList.size(); i++) {
            int element = inputList.get(i);
            //System.out.println( "loop number : "+ i +" element : "+element);
            if(!currentClimb.isEmpty() && (element < currentClimb.get(currentClimb.size()-1))){
                System.out.println("currentClimb.size() : "+ currentClimb.size() + " : longestClimb.size() : "+ longestClimb.size());
                if(currentClimb.size() > longestClimb.size()){
                    longestClimb = List.copyOf(currentClimb);
                }
                currentClimb.clear();
            }
           // System.out.println("longest climb : "+longestClimb);

            currentClimb.add(element);

        }

        // final check
        if (currentClimb.size() > longestClimb.size()) {
            longestClimb = currentClimb;
        }
        System.out.println("Longest climb: " + longestClimb);
    }
}
