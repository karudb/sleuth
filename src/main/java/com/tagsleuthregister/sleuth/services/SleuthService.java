package com.tagsleuthregister.sleuth.services;

public interface SleuthService {
    public void sendRequest(String fieldName, Object data);
    public void sendResponse(Object response);
}
