package com.example.nanayawzaza.farmiconn.ApiRepository;

import com.example.nanayawzaza.farmiconn.Models.ApiProductResponse;
import com.example.nanayawzaza.farmiconn.Models.ApiResponse;
import com.example.nanayawzaza.farmiconn.Models.ApiUserResponse;
import com.example.nanayawzaza.farmiconn.Models.User;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;


/**
 * Created by T-Kel on 11/25/2017.
 */

public interface Api {


    @GET("user")
    Call<ApiUserResponse> getUser();

    @GET("product/category")
    Call<ApiProductResponse> getProductByCategory(@Query("category") String category);

    @GET("search")
    Call<ApiProductResponse> searchProductByName(@Query("productName") String productName);

    @GET("user/product")
    Call<ApiProductResponse> usersProduct(@Query("email") String email);

    //productName,price,farmer, description,location,category,image,video,audio
    @Multipart
    @POST("product/create")
    Call<RequestBody> upload(
            @Part("productName") RequestBody productName,
            @Part("price") RequestBody price,
            @Part("farmer") RequestBody farmer,
            @Part("description") RequestBody description,
            @Part("location") RequestBody location,
            @Part("category") RequestBody category,
            @Part MultipartBody.Part image,
            @Part MultipartBody.Part video,
            @Part MultipartBody.Part audio
    );

    @POST("user/login")
    Call<ApiResponse> login(@Body User user);

    @POST("user/create")
    Call<ApiResponse> register(@Body User user);

    @POST("user/update")
    Call<ApiResponse> updateProfile(@Body User user);

    @GET("/home")
    Call<ApiProductResponse> home();

    @Multipart
    @POST("product/image")
    Call<RequestBody> setPic(@Part MultipartBody.Part file);

    @POST("user/update")
    Call<ApiResponse> update(User user);

}
