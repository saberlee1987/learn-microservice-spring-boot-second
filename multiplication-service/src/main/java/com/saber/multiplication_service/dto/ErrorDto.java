package com.saber.multiplication_service.dto;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.google.gson.ToNumberPolicy;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ErrorDto implements Serializable {
    private Integer code;
    private String text;
    @JsonRawValue
    private Object originalMessage;
    private List<ValidationDto> validations;
    @Override
    public String toString() {
        return new GsonBuilder()
                .setLenient()
                .setPrettyPrinting()
                .enableComplexMapKeySerialization()
                .setLongSerializationPolicy(LongSerializationPolicy.DEFAULT)
                .setObjectToNumberStrategy(ToNumberPolicy.BIG_DECIMAL)
                .create().toJson(this, ErrorDto.class);

    }
}
