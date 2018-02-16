package com.example.nanayawzaza.farmiconn.Adopter;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nana Yaw Zaza on 12/2/2017.
 */

public class Products  implements Parcelable {
    private String productName;
    private String price;
    private String location;
    private String image;
    private String dateCreated;
    private String index;
    private String category;
    private String video;
    private String audio;
    private String description;
    private String name;
    private String email;
    private String phoneNumber;

    public Products() {
    }


    public Products(String productName, String price, String location, String image, String dateCreated, String index, String category, String video, String audio, String description, String name, String email, String phoneNumber) {
        this.productName = productName;
        this.price = price;
        this.location = location;
        this.image = image;
        this.dateCreated = dateCreated;
        this.index = index;
        this.category = category;
        this.video = video;
        this.audio = audio;
        this.description = description;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }


}
