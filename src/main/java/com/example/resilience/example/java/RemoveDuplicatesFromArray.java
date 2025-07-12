package com.example.resilience.example.java;

import java.util.Arrays;

public class RemoveDuplicatesFromArray {

    public int removeDuplicates(int[] nums){
        int result=0;
        String s ;
        int[] temp = Arrays.stream(nums).distinct().toArray();
        int size = temp.length;
        Arrays.stream(temp).forEach(System.out::println);
        for(int index =0 ; index < size;index++){
            nums[index] = temp[index];
        }
        System.out.println("updated nums");
        Arrays.stream(nums).forEach(System.out::println);
        //Set<Integer> r = Arrays.stream(nums).mapToObj(Integer::valueOf).collect(Collectors.toSet());

        return temp.length;
    }
}
