package com.tagsleuthregister.sleuth.services.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tagsleuthregister.sleuth.services.SleuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cloud.sleuth.SpanCustomizer;

@Service
public class SleuthServiceImpl implements SleuthService {

    private SpanCustomizer spanCustomizer;

    @Autowired(required=true)
    public SleuthServiceImpl(SpanCustomizer spanCustomizer) {
        this.spanCustomizer = spanCustomizer;
    }

    @Override
    public void sendRequest(String fieldName, Object data) {
        spanCustomizer.tag(fieldName, toJson(data));
    }

    @Override
    public void sendResponse(Object response) {
        spanCustomizer.tag("response", toJson(response));
    }

    private String toJson(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            return String.format("{ \"error\": %s }", e.getMessage());
        }
    }
}
