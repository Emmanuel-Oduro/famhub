package com.example.nanayawzaza.farmiconn.Models;

import java.util.List;

/**
 * Created by T-Kel on 11/25/2017.
 */

public class ApiUserResponse {

    private String status;
    private String message;
    private List<User> result;
    private String count;
    private String code;

    // ApiUserResponse Constructor

    public ApiUserResponse(String status, String message, List<User> result, String count, String code) {
        this.status = status;
        this.message = message;
        this.result = result;
        this.count = count;
        this.code = code;
    }

    //Set method
    public void setStatus(String status) {this.status = status;}
    public void setMessage(String message) {this.message = message;}
    public void setResult(List<User> result) {this.result = result;}
    public void setCount(String count) {this.count = count;}
    public void setCode(String code) {this.code = code;}

    // Get method
    public String getStatus() {return status;}
    public String getMessage() {return message;}
    public List<User> getResult() {return result;}
    public String getCount() {return count;}
    public String getCode() {return code;}
}
