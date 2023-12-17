package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import netscape.javascript.JSObject;
import org.example.dto.MeasurementDTO;
import org.example.dto.MeasurementsResponse;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DrawChart {

        public static void main(String[] args) throws JsonProcessingException {
//            List<Double> temparatures = getTemperaturesFromServer();
//            drawChart(temparatures);

            final RestTemplate restTemplate = new RestTemplate();
            final String url = "http://localhost:8080/measurements";

            //MeasurementDTO resp= restTemplate.getForObject(url,MeasurementDTO.class);

            //MeasurementsResponse resp = restTemplate.getForObject(url, MeasurementsResponse.class);
            String resp= restTemplate.getForObject(url,String.class);

            System.out.println(resp);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(resp);
            //System.out.println(jsonNode.get(0).get("value"));
            List<Double> temperatures = new ArrayList<>();
            String temp;
            for(JsonNode node : jsonNode){
                temp = node.get("value").toString();
                temperatures.add(Double.parseDouble(temp));
            }
            System.out.println(temperatures);
            drawChart(temperatures);
        }

        private static List<Double> getTemperaturesFromServer() {
            final RestTemplate restTemplate = new RestTemplate();
            final String url = "http://localhost:8080/measurements";
            //String resp= restTemplate.getForObject(url,String.class);

            MeasurementsResponse resp = restTemplate.getForObject(url, MeasurementsResponse.class);
//            if (resp == null || resp.getMeasurements() == null)
//                return Collections.emptyList();

            return resp.getMeasurements().stream().map(MeasurementDTO::getValue)
                    .collect(Collectors.toList());
        }

        private static void drawChart(List<Double> temperatures) {
            double[] xData = IntStream.range(0, temperatures.size()).asDoubleStream().toArray();
            double[] yData = temperatures.stream().mapToDouble(x -> x).toArray();

            XYChart chart = QuickChart.getChart("Temperatures", "X", "Y", "temperature",
                    xData, yData);

            new SwingWrapper(chart).displayChart();
        }
    }
