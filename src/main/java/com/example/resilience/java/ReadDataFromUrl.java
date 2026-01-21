package com.example.resilience.java;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

import java.util.Map;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ReadDataFromUrl {
    private static final String url = "https://coderbyte.com/api/challenges/json/age-counting";

    public void executorExample(){
        ExecutorService executorService = Executors.newFixedThreadPool(5);
    }

    public static void main(String[] args) {

        HttpClient httpClient = HttpClient
                .newBuilder()
                .build();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String s = httpResponse.body();

            s = s.replace("{", "").replace("}", "");
            String[] s2 = s.split(":");
            String[] values = s2[1].replace("\"", "").split(",");

            Long result = Arrays.stream(values)
                    //.map(String::trim)
                    .filter(f -> f.startsWith(" age="))
                    //f.contains(" age="))
                    .peek(System.out::println)
                    .map(r -> Integer.valueOf(r.replace("age=", "").trim()))
                    .filter(a -> a > 50).count();
            //.forEach(System.out::println);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("e" + e.getLocalizedMessage());
        }

    }

    private static void UsingHttpUrlConenction() {
        try {
            URL url = new URL(ReadDataFromUrl.url);
            URLConnection urlConnection = url.openConnection();
            InputStreamReader inputStreamReader = new InputStreamReader(urlConnection.getInputStream());
            BufferedReader bufferedInputStream = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String s;
            while ((s = bufferedInputStream.readLine()) != null) {
                stringBuilder.append(s);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> outPutMap = objectMapper.readValue(stringBuilder.toString(), Map.class);
            String dataString = outPutMap.get("data");
            //"key=IAfpK, age=58, key=WNVdi, age=64, key=jp9zt, age=47";
            String[] strings = dataString.split(", ");
            //Arrays.stream(strings).forEach(s -> System.out.println(s));
            //List<Integer>
            Long valueOfGreaterThan50 = Arrays.stream(strings)
                    .filter(s1 -> s1.contains("age"))
                    .map(s2 -> Integer.valueOf(s2.replace("age=", "").trim()))
                    .filter(integer -> integer > 50)
                    .count();
            //.collect(Collectors.toList());
            System.out.println(valueOfGreaterThan50);

        } catch (Exception e) {
            System.out.println("exception " + e.getLocalizedMessage());
        }
    }
}
