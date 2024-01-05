package nsu.medpollback.model.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Converter
public class ListConverter implements AttributeConverter<List<Object>, String> {
    private final ObjectMapper objectMapper;

    public ListConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String convertToDatabaseColumn(List<Object> attribute) {
        String infoJson = null;
        try {
            infoJson = objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            System.err.println("JSON writing error: " + e);
        }
        return infoJson;
    }

    @Override
    public List<Object> convertToEntityAttribute(String dbData) {
        List<Object> info = null;
        try {
            info = objectMapper.readValue(dbData, new TypeReference<ArrayList<Object>>() {});
        } catch (IOException e) {
            System.err.println("JSON reading error: " + e);
        }
        return info;
    }
}
