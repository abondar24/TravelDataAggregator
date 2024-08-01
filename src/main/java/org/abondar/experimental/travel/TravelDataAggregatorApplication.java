package org.abondar.experimental.travel;


import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class TravelDataAggregatorApplication {
    public static void main(String[] args) {
        SpringApplication.run(TravelDataAggregatorApplication.class, args);
    }
}