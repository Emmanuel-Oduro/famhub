package com.example.nanayawzaza.farmiconn.Models;

import java.util.List;

/**
 * Created by T-Kel on 11/30/2017.
 */
public class ApiProductResponse {
    private String status;
    private String message;
    private List<Product> result;
    private String count;
    private String code;

    public ApiProductResponse(String status, String message, List<Product> result, String count, String code) {
        this.status = status;
        this.message = message;
        this.result = result;
        this.count = count;
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Product> getResult() {
        return result;
    }

    public void setResult(List<Product> result) {
        this.result = result;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
