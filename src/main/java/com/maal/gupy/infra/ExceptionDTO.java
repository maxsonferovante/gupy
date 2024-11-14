package com.maal.gupy.infra;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ExceptionDTO {

        String message;
        Integer code;
        public ExceptionDTO(String message, Integer code) {
            this.message = message;
            this.code = code;
        }}
