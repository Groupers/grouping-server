package com.covengers.grouping.configuration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.AbstractFormWriter;
import org.springframework.cloud.openfeign.support.JsonFormWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.covengers.grouping.adapter.Adapter;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableFeignClients(basePackageClasses = Adapter.class)
@RequiredArgsConstructor
public class FeignConfiguration {

    private final ObjectMapper objectMapper;

    @Bean
    @Primary
    public Decoder feignDecoder() {
        final Decoder delegate = new JacksonDecoder(objectMapper);

        return new ResponseValidateDecoder(delegate);
    }

    @Bean
    public AbstractFormWriter feignJsonFormWriter() {
        return new JsonFormWriter();
    }
}
