package com.example.resilience.java;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {


    public void countWords() {
        List<String> elements = Stream.of("AA", "BB", "AA", "CC").toList();
        Map<String, Long> du = elements.stream().collect(Collectors.groupingBy(String::valueOf, Collectors.counting()));
        // du.entrySet().forEach(System.out::println);
        du.entrySet()
                .stream()
                .filter(c -> c.getValue() >= 2)
                .forEach(System.out::println);

    }
    public void palindrome(){
        List<String> words = Stream.of("aba","cdc","dea").toList();
        List<String> palinDrome = words
                .stream()
                .filter(this::palindromeUsingStringBuilder)
                .toList();
        palinDrome.forEach(System.out::println);

    }

    public boolean palindromeUsingStringBuilder(String s){
        StringBuilder stringBuilder = new StringBuilder(s);
        String s2 =stringBuilder.reverse().toString();
        return s.equals(s2);
    }

    public static void main(String[] args) {


        App a = new App();
       // a.frequency();
       // a.countWords();
        a.palindrome();

    }

    public void frequency() {
        String s = "It is a boy";
        String s1 = s.toLowerCase().replaceAll(" ", "");
        Map<Character, Long> countMap = s1.codePoints()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c1 -> c1, TreeMap::new, Collectors.counting()));
        countMap.entrySet().forEach(System.out::println);
        System.out.println("=====================");
    }
}
