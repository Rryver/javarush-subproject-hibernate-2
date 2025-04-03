package org.example.models.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Converter
public class MySqlSetConverter implements AttributeConverter<Set<String>, String> {
    @Override
    public String convertToDatabaseColumn(Set<String> attributes) {
        return StringUtils.join(attributes, ",");
    }

    @Override
    public Set<String> convertToEntityAttribute(String dbData) {
        return new HashSet<>(Arrays.asList(StringUtils.split(dbData, ",")));
    }
}
