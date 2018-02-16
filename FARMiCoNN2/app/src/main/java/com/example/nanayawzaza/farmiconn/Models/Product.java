package com.example.nanayawzaza.farmiconn.Models;

public class Product {

    /******************************* DECLARATION *******************************/

    private String productName;
    private String price;
    private User farmer;
    private String location;
    private String category;
    private String image;
    private String video;
    private String audio;
    private String description;
    private String dateCreated;

    public Product() {
    }

    public Product(String productName, String price, User farmer, String location, String category, String image, String video, String audio, String description, String dateCreated) {
        this.productName = productName;
        this.price = price;
        this.farmer = farmer;
        this.location = location;
        this.category = category;
        this.image = image;
        this.video = video;
        this.audio = audio;
        this.description = description;
        this.dateCreated = dateCreated;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public User getFarmer() {
        return farmer;
    }

    public void setFarmer(User farmer) {
        this.farmer = farmer;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
}
