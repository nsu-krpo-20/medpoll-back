package nsu.medpollback.model.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Converter
public class HashMapConverter implements AttributeConverter<Map<String, Object>, String> {
    private final ObjectMapper objectMapper;

    public HashMapConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String convertToDatabaseColumn(Map<String, Object> attribute) {
        String infoJson = null;
        try {
            infoJson = objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            System.err.println("JSON writing error: " + e);
        }
        return infoJson;
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String dbData) {
        Map<String, Object> info = null;
        try {
            info = objectMapper.readValue(dbData, new TypeReference<HashMap<String, Object>>() {});
        } catch (IOException e) {
            System.err.println("JSON reading error: " + e);
        }
        return info;
    }
}
