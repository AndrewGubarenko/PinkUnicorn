package com.pink.unicorn.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pink.unicorn.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 *
 */
@Component
public class Converter {

    private static final Logger LOGGER = Logger.getLogger(UserService.class.getSimpleName());
    private final ObjectMapper objectMapper;

    @Autowired
    public Converter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    public String ObjectToJSON(Object object) {
        String response;
        try {
            //Convert object to JSON string
            response = objectMapper.writeValueAsString(object);

        } catch (JsonGenerationException e) {
            LOGGER.warn(e.getMessage());
            response = "JsonGenerationException error";
        } catch (JsonMappingException e) {
            LOGGER.warn(e.getMessage());
            response = "JsonMappingException error";
        } catch (IOException e) {
            LOGGER.warn(e.getMessage());
            response = "IOException error";
        }
        return response;
    }
}
