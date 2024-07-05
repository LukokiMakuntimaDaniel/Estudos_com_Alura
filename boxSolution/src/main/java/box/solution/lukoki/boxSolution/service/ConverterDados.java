package box.solution.lukoki.boxSolution.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverterDados implements IconverterDados{
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T obterDados(String Json, Class<T> classe) {
        try {
            return objectMapper.readValue(Json,classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
