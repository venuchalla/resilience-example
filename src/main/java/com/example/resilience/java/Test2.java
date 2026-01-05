package com.example.resilience.java;


import com.example.resilience.dto.EmployeeDTO;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
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
        int a =2;
        Integer w = Integer.valueOf(2);


        //test2.stringReverseUsingtStringBuilder(str);
        //test2.stringReverse(str);
        // test2.swapNumbers(1255,1355);
      // test2.maxAndMinAndAvg();
       // test2.max();
        //test2.extractWordsStartwithS();
        //test2.findDistinct();
     // boolean re=  test2.isAnagramUsingStream("venu","unev");
      //  System.out.println(re);
      //test2.sortEmployeeBasedOnSalary();
        //test2.groupbyExample();


        System.out.println("uuuuuuu");

    }

    public void continueTest() {
        for (int i = 0; i < 3; i++) {
            if (i == 1) {
                continue;

            }
            System.out.println("i =" + i);
        }
    }

    public void swapNumbers(int x, int y) {

        System.out.println("x:" + x + " y:" + y);
        x = x + y;
        y = x - y;
        x = x - y;
        System.out.println(" after swapping x:" + x + " y:" + y);

    }

    public void max() {
        Integer[] intarray = {4,4,4,9,8,4,3,2};
        Arrays.stream(intarray).sorted(Integer::compare).forEach(System.out::println);
        System.out.println("Max:");
        Arrays.stream(intarray).max(Integer::compare).ifPresent(System.out::println);
        System.out.println("Min:");
        Arrays.stream(intarray).min(Comparator.naturalOrder()).ifPresent(System.out::println);
        System.out.println("Avg:");
        double d= Arrays.stream(intarray).collect(Collectors.averagingDouble(i -> i));
        System.out.println(d);
       Integer sum =  Arrays.stream(intarray).reduce(0, Integer::sum);
        System.out.println("sum : "+sum);
    }
    BiConsumer<String ,Object> printConsumer = (s, o) -> System.out.println(s+o);

    public void maxAndMinAndAvg() {
        int[] arrayInt = {4, 5, 6, 7,2,7};
       // Arrays.stream(arrayInt).sorted().forEach(System.out::println);

        Arrays.stream(arrayInt).max().ifPresent(a ->{
           printConsumer.accept("max : ",a);
        });

        Arrays.stream(arrayInt)
                .boxed()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .ifPresent(c -> System.out.println("second highest : "+ c));
        Arrays.stream(arrayInt).min().ifPresent(min -> {
            System.out.println("Min:"+min);
        });
        Arrays.stream(arrayInt).average().ifPresent(avg -> {
            System.out.println("avg usee : "+ avg);
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
           String s =  decimalFormat.format(avg);
            System.out.println("s avg : "+s);
           double d = Double.parseDouble(s);
           System.out.println("d avg : "+d);
        });
    }


    public void extractWordsStartwithS(){
        List<List<String>> listList= Arrays.asList(
                Arrays.asList("venu","seee","ssss"),
                Arrays.asList("jjjj","kkkkk","sssss")
        );

        Set<String> output = listList.stream()
                .flatMap(Collection::stream)
                .map(String::toLowerCase)
                .filter(s-> s.startsWith("s"))
                .distinct()
                .sorted(String::compareTo)
                .peek(System.out::println)
                .collect(Collectors.toSet());
    }

    public void findDistinct(){
        int[] arrays = {1,2,3,4,5,6,2,3};
        int[] oArray= Arrays.stream(arrays).distinct().toArray();
        Arrays.stream(oArray).forEach(System.out::println);

        String[] r= {"v", "venu","venu","enu"};
        Arrays.stream(r).distinct().forEach(System.out::println);
       double d= Arrays.stream(arrays).summaryStatistics().getAverage();
       System.out.println("avg :"+d);
    }

    public boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length()) return false;

        int[] count = new int[256]; // ASCII

        for (char c : s1.toCharArray()) {
            System.out.println(c);
            count[c]++;
        }

        for (char c : s2.toCharArray()) {
            count[c]--;
        }

        for (int c : count) {
            if (c != 0) return false;
        }

        return true;
    }
    public boolean isAnagramUsingStream(String s1, String s2) {

        if(s1.length() != s2.length()){
            return false;
        }
        Map<Character,Long> firstMap=s1.chars()
                .mapToObj(c-> (char) c)
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        Map<Character,Long> secondMap=s2.chars()
                .mapToObj(c-> (char) c)
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
       return  firstMap.equals(secondMap);
    }

    public  void sortEmployeeBasedOnSalary(){
        EmployeeDTO employee_1 = new EmployeeDTO(1,"venu","90000","12","www.google.com","","","");
        EmployeeDTO employee_2 = new EmployeeDTO(2,"venu","20000","25","www.google.com","","","");
        EmployeeDTO employee_3 = new EmployeeDTO(3,"venu","30000","26","www.google.com","","","");
        EmployeeDTO employee_4 = new EmployeeDTO(4,"venu","40000","30","www.google.com","","","");
        EmployeeDTO employee_5 = new EmployeeDTO(5,"venu","50000","34","www.google.com","","","");

                List<EmployeeDTO> employees = new ArrayList<>();
        employees.add(employee_1);
        employees.add(employee_2);
        employees.add(employee_3);
        employees.add(employee_4);
        employees.add(employee_5);
       List<EmployeeDTO> sortedById =  employees
               .stream()
               .sorted(Comparator.comparing((EmployeeDTO::getId)).reversed())
               .toList();

        List<EmployeeDTO> sortedBySalary =  employees
                .stream()
                .sorted(Comparator.comparing(employee -> Integer.valueOf(employee.getEmployee_salary())))
                        .toList();
        sortedBySalary.forEach(System.out::println);
        employees
                .stream()
                .mapToLong( e-> Long.parseLong(e.getEmployee_salary()))
                .average().ifPresent(d-> {
                    System.out.println("avg :"+d);
                });

    }

   public void groupbyExample(){
        List<String> stringListOne= Arrays.asList("venu","ram","sumanth","venu","ele","jjj",null,null);
       List<String>  stringList  = stringListOne.stream().flatMap(Stream::ofNullable).toList();
       stringList.forEach(System.out::println);
       //stringList.stream().collect(Collectors.teeing(Collectors.maxBy()))
        Map<Integer,List<String>> result= stringList.stream().collect(Collectors.groupingBy(String::length));

       // result.forEach((key, value) -> System.out.println("key :" + key + " : value :" + value));
       Map<Boolean,List<String>> result1= stringList.stream().collect(Collectors.partitioningBy(n-> n.length()==3));
       //result1.forEach((key, value) -> System.out.println("key :" + key + " : value :" + value));
       Map<String ,Long> result3= stringList.stream().collect(Collectors.groupingBy(String::valueOf,Collectors.counting()));
       //result3.forEach((key, value) -> System.out.println("key :" + key + " : value :" + value));

       Map<String ,Optional<String>> result4= stringList.stream().collect(Collectors.groupingBy(String::valueOf,Collectors.maxBy(Comparator.comparing(String::length))));
       result4.forEach((key, value) -> System.out.println("key :" + key + " : value :" + value));

   }

   public void checkAge(int a) throws ArithmeticException{
        if(a < 15){
           // throw new ArithmeticException("age is less than 15");
        }
   }

}
