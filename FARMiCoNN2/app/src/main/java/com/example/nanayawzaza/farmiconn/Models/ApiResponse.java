package com.example.nanayawzaza.farmiconn.Models;

/**
 * Created by T-Kel on 11/25/2017.
 */

public class ApiResponse {
    // Variables
    private boolean status;
    private String message;
    private Object result;
    private int count;
    private int code;

    // User constructor
    public ApiResponse (boolean status, String message, Object result, int count, int code) {
        this.status = status;
        this.message = message;
        this.result = result;
        this.count = count;
        this.code = code;
    }

    //User getters methods
    public boolean isStatus() {return status;}
    public String getMessage() {return message;}
    public Object getResult() {return result;}
    public int getCount() {return count;}
    public int getCode() {return code;}

    // User Setters methods
    public void setStatus(boolean status) {this.status = status;}
    public void setMessage(String message) {this.message = message;}
    public void setResult(Object result) {this.result = result;}
    public void setCount(int count) {this.count = count;}
    public void setCode(int code) {this.code = code;}
}
