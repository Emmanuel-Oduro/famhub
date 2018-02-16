package com.example.nanayawzaza.farmiconn.Utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.example.nanayawzaza.farmiconn.Adopter.ListProductAdopter;
import com.example.nanayawzaza.farmiconn.Adopter.Products;
import com.example.nanayawzaza.farmiconn.ApiRepository.Api;
import com.example.nanayawzaza.farmiconn.Application.AppController;
import com.example.nanayawzaza.farmiconn.MainActivity;
import com.example.nanayawzaza.farmiconn.Models.ApiResponse;
import com.example.nanayawzaza.farmiconn.Models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nana Yaw Zaza on 12/2/2017.
 */

public class Queries extends Activity {
    static String TAG = Queries.class.getSimpleName();

    public static void DisplayProducts(final Context paramContext, final RecyclerView recyclerView, final int method) {

        StringRequest stringRequest = new StringRequest(0, Config.GET_PRODUCTS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<Products> productList = new ArrayList<>();
                Utilities.displayLog(TAG, response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Products products = new Products();

                        JSONObject product = (JSONObject) jsonArray.get(i);
                        JSONObject farmer = product.getJSONObject("farmer");
                        products.setProductName(product.getString("productName"));
                        products.setPrice(product.getString("price"));
                        products.setLocation(product.getString("location"));
                        products.setDateCreated(product.getString("dateCreated"));
                        products.setImage(Config.IP_ADDRESSIMG + product.getString("image"));
                        products.setCategory(product.getString("category"));
                        products.setAudio(Config.IP_ADDRESSIMG + product.getString("audio"));
                        products.setVideo(Config.IP_ADDRESSIMG + product.getString("video"));
                        products.setIndex(String.valueOf(i));
                        products.setPhoneNumber(farmer.getString("phoneNumber"));
                        products.setEmail(farmer.getString("email"));
                        products.setName(farmer.getString("name"));


                        productList.add(products);
                    }
                    Utilities.displayLog(TAG, productList.toString());

                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(paramContext, 2);
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, method, true));
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    ListProductAdopter adapter = new ListProductAdopter(productList, paramContext);
                    recyclerView.setAdapter(adapter);
                    Utilities.displayLog(TAG + " productList", productList.toString());
                } catch (JSONException e) {
                    Utilities.displayLog(TAG, e.toString());
                    Utilities.disPlayToast(paramContext, e.toString() );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utilities.displayLog(TAG, error.toString());
                Utilities.disPlayToast(paramContext, error.toString());
            }
        });

        int socketTimeout = 40000; // 40 seconds. You can change it
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest.setRetryPolicy(policy);
        //stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 1, 1.0F));
        AppController.getInstance().addToRequestQueue(stringRequest);


    }

    public static void DisplayProductsByCategory(final Context paramContext, String category, final RecyclerView recyclerView) {

        StringRequest stringRequest = new StringRequest(0, Config.IP_ADDRESS + "/product/category?category=" + category, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<Products> productList = new ArrayList<>();
                Utilities.displayLog(TAG + " DisplayProductsByCategory ", response.toString());
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Products products = new Products();

                        JSONObject product = (JSONObject) jsonArray.get(i);
                        products.setProductName(product.getString("productName"));
                        products.setPrice(product.getString("price"));
                        products.setLocation(product.getString("location"));
                        products.setDateCreated(product.getString("dateCreated"));
                        products.setImage(Config.IP_ADDRESSIMG + product.getString("image"));
                        products.setCategory(product.getString("category"));
                        products.setIndex(String.valueOf(i));
                        productList.add(products);
                    }
                    Utilities.displayLog(TAG, productList.toString());

                    recyclerView.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(paramContext, 2);
                    recyclerView.setLayoutManager(layoutManager);
                    ListProductAdopter adapter = new ListProductAdopter(productList, paramContext);
                    recyclerView.setAdapter(adapter);


                    Utilities.displayLog(TAG + " productList", "works alright");
                } catch (JSONException e) {
                    // e.printStackTrace();
                    Utilities.displayLog(TAG, e.toString());
                    Utilities.disPlayToast(paramContext, e.toString() + " zaza");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utilities.displayLog(TAG, error.toString());
                Utilities.disPlayToast(paramContext, error.toString());
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 1, 1.0F));
        AppController.getInstance().addToRequestQueue(stringRequest);


    }

    public static void DisplayProductsBymail(final Context paramContext, String email, final RecyclerView recyclerView) {

        StringRequest stringRequest = new StringRequest(0, Config.IP_ADDRESS + "/user/product?email=" + email, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<Products> productList = new ArrayList<>();
                Utilities.displayLog(TAG + ">>", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Products products = new Products();

                        JSONObject product = (JSONObject) jsonArray.get(i);
                        if (product.has("productName")) {
                            products.setProductName(product.getString("productName"));
                            products.setPrice(product.getString("price"));
                            products.setLocation(product.getString("location"));
                            products.setDateCreated(product.getString("dateCreated"));
                            products.setImage(Config.IP_ADDRESSIMG + product.getString("image"));
                            products.setCategory(product.getString("category"));
                            products.setIndex(String.valueOf(i));
                            productList.add(products);
                        }

                    }
                    Utilities.displayLog(TAG, productList.toString());
                    ListProductAdopter adapter = new ListProductAdopter(productList, paramContext);
                    recyclerView.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(paramContext, 2);
                    recyclerView.setLayoutManager(layoutManager);

                    recyclerView.setAdapter(adapter);

                    Utilities.displayLog(TAG + " productList", productList.toString());
                } catch (JSONException e) {
                    // e.printStackTrace();
                    Utilities.displayLog(TAG, e.toString());
                    //Utilities.disPlayToast(paramContext, e.toString() + " zaza");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utilities.displayLog(TAG, error.toString());
                Utilities.disPlayToast(paramContext, error.toString());
            }
        });

        int socketTimeout = 40000; // 40 seconds. You can change it
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest.setRetryPolicy(policy);
        //stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 1, 1.0F));
        AppController.getInstance().addToRequestQueue(stringRequest);


//
//        StringRequest stringRequest = new StringRequest(0, Config.IP_ADDRESS + "/user?email=" +email, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                ArrayList<Products> productList = new ArrayList<Products>();
//                Utilities.displayLog(TAG + " DisplayProductsBymail ", response);
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    JSONArray jsonArray = jsonObject.getJSONArray("result");
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        Products products = new Products();
//
//                        JSONObject product = (JSONObject) jsonArray.get(i);
//                        products.setProductName(product.getString("productName"));
//                        products.setPrice(product.getString("price"));
//                        products.setLocation(product.getString("location"));
//                        products.setDateCreated(product.getString("dateCreated"));
//                        products.setImage(Config.IP_ADDRESSIMG + product.getString("image"));
//                        products.setCategory(product.getString("category"));
//                        products.setIndex(String.valueOf(i));
//                        productList.add(products);
//                    }
//                    Utilities.displayLog(TAG, productList.toString());
//
//                    recyclerView.setHasFixedSize(true);
//                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(paramContext, 3);
//                    recyclerView.setLayoutManager(layoutManager);
//                    ListProductAdopter adapter = new ListProductAdopter(productList, paramContext);
//                    recyclerView.setAdapter(adapter);
//
//
//                    Utilities.displayLog(TAG + " productList", "works alright");
//                } catch (JSONException e) {
//                    // e.printStackTrace();
//                    Utilities.displayLog(TAG, e.toString());
//                    Utilities.disPlayToast(paramContext, e.toString() + " zaza");
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                Utilities.displayLog(TAG, error.toString());
//                Utilities.disPlayToast(paramContext, error.toString());
//            }
//        });
//        // stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 1, 1.0F));
//        AppController.getInstance().addToRequestQueue(stringRequest);
//

    }

    public static void DisplayDetailsBymail(final Context paramContext, final String email, final EditText name, final EditText email1, final EditText phoneNumber) {

        StringRequest stringRequest = new StringRequest(0, Config.IP_ADDRESS + "/user?email=" + email, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Utilities.displayLog(TAG + " DisplayDetailsBymail ", response);
                try {

                    JSONObject parentObject = new JSONObject(response);
                    JSONObject userDetails = parentObject.getJSONObject("result");

                    name.setText(userDetails.getString("name"));
                    phoneNumber.setText(userDetails.getString("phoneNumber"));
//                    email1.setText(product.getString("email"));
//                    user.setPhoneNumber(userDetails.getString("phoneNumber"));
//                    user.setEmail(userDetails.getString("email"));
                    Utilities.displayLog(TAG+">>", userDetails.toString());

                    //    Utilities.displayLog(TAG, product.toString());


                    Utilities.displayLog(TAG + " productList", "works alright");
                } catch (JSONException e) {
                    // e.printStackTrace();
                    Utilities.displayLog(TAG, e.toString());
                    Utilities.disPlayToast(paramContext, e.toString() );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utilities.displayLog(TAG, error.toString());
                Utilities.disPlayToast(paramContext, error.toString());
            }
        });
        // stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 1, 1.0F));
        AppController.getInstance().addToRequestQueue(stringRequest);


    }


    public static void DisplayProductsDetailsByCategoryAnadIndex(final Context paramContext, String category, final int index, final TextView productName, final TextView price, final ImageView image, final TextView dateCreated, final TextView location, final TextView categorys, final VideoView video, final MediaPlayer mediaPlayer, final TextView description, final TextView name, final TextView email, final TextView phoneNumber) {

        StringRequest stringRequest = new StringRequest(0, Config.IP_ADDRESS + "/product/category?category=" + category, new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {
                ArrayList<Products> productList = new ArrayList<>();
                Utilities.displayLog(TAG + " DisplayProductsDetailsByCategoryAnadIndex ", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    Products products = new Products();
                    JSONObject product = (JSONObject) jsonArray.get(index);
                    Utilities.displayLog(TAG, String.valueOf(product.length()));

                    Utilities.displayLog(TAG, product.toString());
                    JSONObject farmer = product.getJSONObject("farmer");
                    Utilities.displayLog(TAG, farmer.toString());
                    productName.setText(product.getString("productName"));
                    categorys.setText(product.getString("category"));
                    price.setText(product.getString("price"));
                    description.setText(product.getString("description"));
                    if (null !=product.getString("video")) {
                        video.setVideoPath(Config.IP_ADDRESSIMG + product.getString("video"));
                        MediaController mediaController = new MediaController(paramContext);
                        // mediaController.setEnabled(true);
                        mediaController.setAnchorView(video);
                        // mediaController.show();
                        mediaController.setVisibility(View.VISIBLE);
                        video.setMediaController(mediaController);
                        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                Utilities.displayLog(TAG, "Duration = " + video.getDuration());
                            }
                        });
                        video.start();
                    }
//                    audio.setText(Config.IP_ADDRESSIMG + product.getString("audio"));
                    dateCreated.setText(product.getString("dateCreated"));
                    location.setText(product.getString("location"));
                    name.setText(farmer.getString("name"));
                    email.setText(farmer.getString("email"));
                    phoneNumber.setText(farmer.getString("phoneNumber"));

                    Glide.with(paramContext)
                            .load(Config.IP_ADDRESSIMG + product.getString("image"))
                            .into(image);
                    try {
                        mediaPlayer.setDataSource(Config.IP_ADDRESSIMG + product.getString("audio"));
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        Utilities.displayLog(TAG+" DisplayProductsDetailsByCategoryAnadIndex" ,e.getMessage());
                        e.printStackTrace();
                        Utilities.disPlayToast(paramContext,e.toString());
                    }
                    productList.add(products);
                    Utilities.displayLog(TAG + " trying", productList.toString());
                    Utilities.displayLog(TAG + " productList", "works alright");
                } catch (JSONException e) {
                    // e.printStackTrace();
                    Utilities.displayLog(TAG, e.toString());
                    Utilities.disPlayToast(paramContext, "z "+e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utilities.displayLog(TAG, error.toString());
                Utilities.disPlayToast(paramContext, error.toString());
            }
        });
        // stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 1, 1.0F));
        AppController.getInstance().addToRequestQueue(stringRequest);


    }

    public static void ForgottenPassword(final Context paramContext, EditText emailText, EditText phone, EditText passwordText) {
        //Forgotten Password Codes here


    }

    public static void Registratio(final Context paramContext, final EditText name, EditText email, EditText password, EditText phoneNumber) {
        String str1 = name.getText().toString();
        String str2 = email.getText().toString();
        String str3 = password.getText().toString();
        String str4 = phoneNumber.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                // Config.IP_ADDRESS + "/"
                .baseUrl(Config.IP_ADDRESS + "/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        User user = new User(str1, str2, str3, str4);
        Call<ApiResponse> call = api.register(user);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse> call, @NonNull retrofit2.Response<ApiResponse> response) {
                ApiResponse apiResponse = response.body();


                if (apiResponse.isStatus() == true) {
                    Utilities.disPlayToastShort(paramContext, apiResponse.getMessage());
                    Intent intent = new Intent(paramContext, MainActivity.class);
                    paramContext.startActivity(intent);
                }

            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {
                Utilities.disPlayToast(paramContext, t.getMessage());

            }
        });
    }




    public static void UpdateProfilePassword(final Context paramContext, EditText password,String mail) {

    }


    private static class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }

        }
    }

}
