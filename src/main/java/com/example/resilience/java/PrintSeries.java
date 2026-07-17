package com.example.resilience.java;

import java.util.Scanner;

public class PrintSeries {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    System.out.println("input : a " + a);
    int b = scanner.nextInt();
    System.out.println("b :" + b);
    int n = scanner.nextInt();
    System.out.println("c :" + n);

    int result = a;
    for (int i = 0; i < n; i++) {
      result = result + (int) (Math.pow(2, i)) * b;
      System.out.println("result : " + result);
    }
  }
}
