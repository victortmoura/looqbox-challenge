package com.looqbox.challenge.configurer;

import com.looqbox.challenge.sort.MergeSort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public MergeSort mergeSort() {
        return new MergeSort();
    }
}
