package com.yjh.rememberme.common.dto;

import com.yjh.rememberme.database.repository.dto.GetObjectDTO;

import java.util.List;
import java.util.Map;

public class ResponseMessage {
    private int httpStatus;
    private String message;
    private Map<String, Object> results;

    public ResponseMessage(int httpStatus, String message, Map<String, Object> results) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.results = results;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getResults() {
        return results;
    }

    public void setResults(Map<String, Object> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "ResponseMessage{" +
                "httpStatus=" + httpStatus +
                ", message='" + message + '\'' +
                ", results=" + results +
                '}';
    }
}
