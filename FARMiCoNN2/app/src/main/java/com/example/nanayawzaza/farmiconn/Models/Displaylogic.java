package com.example.nanayawzaza.farmiconn.Models;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.nanayawzaza.farmiconn.ApiRepository.Api;
import com.example.nanayawzaza.farmiconn.Utils.Config;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by T-Kel on 11/30/2017.
 */

public class Displaylogic {
    public  static String TAG=Displaylogic.class.getSimpleName();

    List<User> userList;
    static List<Product> productList;

    public List<User> showUsers() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.IP_ADDRESS+"/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<ApiUserResponse> call = api.getUser();

        call.enqueue(new Callback<ApiUserResponse>() {
            @Override
            public void onResponse(Call<ApiUserResponse> call, Response<ApiUserResponse> response) {

                ApiUserResponse apiUserResponse = response.body();

                userList = apiUserResponse.getResult();
            }

            @Override
            public void onFailure(Call<ApiUserResponse> call, Throwable t) {
                userList = null;
            }
        });
        return userList;
    }

    public List<Product> home() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.IP_ADDRESS+"/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<ApiProductResponse> call = api.home();

        call.enqueue(new Callback<ApiProductResponse>() {
            @Override
            public void onResponse(Call<ApiProductResponse> call, Response<ApiProductResponse> response) {

                ApiProductResponse apiProductResponse = response.body();

                //pro = apiProductResponse.getResult();
            }

            @Override
            public void onFailure(Call<ApiProductResponse> call, Throwable t) {
               // pro = null;
            }
        });
        return productList;
    }

    public static List<Product> getProductByCategory(String category, final Context ParameterContext) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.IP_ADDRESS+"/"+category)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<ApiProductResponse> call = api.getProductByCategory(category);

        call.enqueue(new Callback<ApiProductResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiProductResponse> call, @NonNull Response<ApiProductResponse> response) {

                ApiProductResponse apiProductResponse = response.body();

                if (apiProductResponse != null) {
                    productList = apiProductResponse.getResult();
                    Toast.makeText(ParameterContext, productList.toString(), Toast.LENGTH_LONG).show();
                    Log.i(TAG,productList.toString());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiProductResponse> call, @NonNull Throwable t) {
                productList = null;
            }
        });
        return productList;
    }

    public List<Product> searchByProductName(String productName) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.IP_ADDRESS+"/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<ApiProductResponse> call = api.searchProductByName(productName);

        call.enqueue(new Callback<ApiProductResponse>() {
            @Override
            public void onResponse(Call<ApiProductResponse> call, Response<ApiProductResponse> response) {

                ApiProductResponse apiProductResponse = response.body();

                productList = apiProductResponse.getResult();
            }

            @Override
            public void onFailure(Call<ApiProductResponse> call, Throwable t) {
                productList = null;
            }
        });
        return productList;
    }

    public List<Product> getUsersProduct(String email) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.IP_ADDRESS+"/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<ApiProductResponse> call = api.usersProduct(email);

        call.enqueue(new Callback<ApiProductResponse>() {
            @Override
            public void onResponse(Call<ApiProductResponse> call, Response<ApiProductResponse> response) {

                ApiProductResponse apiProductResponse = response.body();

                productList = apiProductResponse.getResult();
            }

            @Override
            public void onFailure(Call<ApiProductResponse> call, Throwable t) {
                productList = null;
            }
        });
        return productList;
    }
}
