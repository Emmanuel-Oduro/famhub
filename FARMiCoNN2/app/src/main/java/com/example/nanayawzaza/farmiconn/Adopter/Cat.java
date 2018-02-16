package com.example.nanayawzaza.farmiconn.Adopter;

/**
 * Created by Nana Yaw Zaza on 12/5/2017.
 */

public class Cat {
    private String categoryName;
    private String message;
    private int image;

    public Cat() {
    }

    public Cat(String categoryName, String message, int image) {
        this.categoryName = categoryName;
        this.message = message;
        this.image = image;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
