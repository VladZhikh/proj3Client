package org.example;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Client {
    public static void main(String[] args) {
        final String sensorName = "Sensor123";
        RestTemplate restTemplate = new RestTemplate();
        registerSensor(sensorName,restTemplate);
        addMeasurements(sensorName,restTemplate);
    }

   private static void registerSensor(String sensorName, RestTemplate restTemplate) {
        Map<String,String> jsonToRegistration = new HashMap<>();
       jsonToRegistration.put("name",sensorName);
       HttpEntity<Map<String,String>> request = new HttpEntity<>(jsonToRegistration);
       String URLRegistration = "http://localhost:8080/sensors/registration";
       try {
           String response = restTemplate.postForObject(URLRegistration,request,String.class);
           System.out.println("response ="+response);
       } catch (HttpClientErrorException e) {
           System.out.println("Error!");
           System.out.println(e.getMessage());
       }
   }
   public static void addMeasurements(String sensorName, RestTemplate restTemplate){
       double randomValue;
       boolean randomRaining = false;
       Random random = new Random();
       for (int i=1; i<11; ++i){
           randomValue=random.nextDouble(0.0,12.0);
           randomRaining = random.nextBoolean();
           System.out.println(i+": "+randomValue+", "+randomRaining);
           Map<String,Object> jsonSentMeasurement = new HashMap<>();
           jsonSentMeasurement.put("value",randomValue);
           jsonSentMeasurement.put("raining", randomRaining);
           jsonSentMeasurement.put("sensor", Map.of("name", sensorName));

           HttpEntity<Map<String,Object>> reqMeasurement = new HttpEntity<>(jsonSentMeasurement);
           String URLMeasurement = "http://localhost:8080/measurements/add";
           String respMeasurement = restTemplate.postForObject(URLMeasurement,reqMeasurement,String.class);
           System.out.println(respMeasurement);
       }
   }
}