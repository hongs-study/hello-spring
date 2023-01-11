package com.example.hellospring.converttoqueryparam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
public class MultiValueMapConverter {

    private final ObjectMapper objectMapper;

    public MultiValueMapConverter() {
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public MultiValueMap<String, String> convertFrom(Object dto) {
        MultiValueMap<String, String> parameter = new LinkedMultiValueMap<>();
        Map<String, Object> map = objectMapper.convertValue(dto, Map.class);
        for (String key : map.keySet()) {
            System.out.println(key + ": " + map.get(key));
            if (map.get(key) instanceof Collection<?>) {
                MultiValueMap<String, String> listMap = new LinkedMultiValueMap<>();
                listMap.addAll(key, (List) map.get(key));
                parameter.addAll(listMap);
            } else if (map.get(key) == null){
                parameter.set(key, null);
            } else {
                parameter.set(key, map.get(key).toString());
            }
        }
        return parameter;
    }
}
