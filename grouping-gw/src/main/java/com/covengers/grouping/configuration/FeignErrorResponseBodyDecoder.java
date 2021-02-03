package com.covengers.grouping.configuration;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.Optional;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FeignErrorResponseBodyDecoder {

    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public static <T> Optional<T> decode(Response response, Class<T> clazz) {
        try {
            Reader reader = response.body().asReader();
            if (!reader.markSupported()) {
                reader = new BufferedReader(reader, 1);
            }
            reader.mark(1);
            if (reader.read() == -1) {
                return Optional.empty();
            }
            reader.reset();
            return Optional.of(mapper.readValue(reader, clazz));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
