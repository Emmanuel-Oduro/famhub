package com.example.nanayawzaza.farmiconn;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nanayawzaza.farmiconn.ApiRepository.Api;
import com.example.nanayawzaza.farmiconn.Models.ApiResponse;
import com.example.nanayawzaza.farmiconn.Models.User;
import com.example.nanayawzaza.farmiconn.Utils.Config;
import com.example.nanayawzaza.farmiconn.Utils.Queries;
import com.example.nanayawzaza.farmiconn.Utils.Utilities;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Dashboad extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = Dashboad.class.getSimpleName();
    BottomNavigationView navigation;
    Intent intent;
    String farmerAddress;
    Button button;
    EditText name, phoneNumber, email1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        name = findViewById(R.id.nam);
        phoneNumber = findViewById(R.id.phone);
        email1 = findViewById(R.id.email);
        button = findViewById(R.id.regista);

        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, MODE_PRIVATE);
        farmerAddress = sharedPreferences.getString(Config.EMAIL_SHARED_PREF, "");
        User user = new User();
        button.setOnClickListener(this);
        name.setText(user.getName());
        email1.setText(farmerAddress);
        phoneNumber.setText(user.getPhoneNumber());


        //If we will get true
        Queries.DisplayDetailsBymail(this, farmerAddress, name, email1, phoneNumber);
//        Utilities.disPlayToast(this,farmerAddress);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        intent = new Intent(Dashboad.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.profile:
                        intent = new Intent(Dashboad.this, Dashboad.class);
                        startActivity(intent);
                        Log.d(TAG, "zaza \n >>>>>>>>>>>>>>> ");
                        break;
                    case R.id.products:
                        intent = new Intent(Dashboad.this, ProductByMail.class);
                        startActivity(intent);
                        break;
                    case R.id.pass:
                        intent = new Intent(Dashboad.this, PasswordView.class);
                        startActivity(intent);
                        break;


                }

                return false;
            }
        });

        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        finish();
    }


    @Override
    public void onClick(View v) {
        if (!TextUtils.isEmpty(name.getText()) && !TextUtils.isEmpty(phoneNumber.getText())) {
            Update();
        }
    }

    public void Update() {
        //Queries.UpdateProfile(this, name, email1, phoneNumber);
        Utilities.displayLog(TAG+" ","here");
        String str1 = name.getText().toString();
        String str2 = email1.getText().toString();
        String str4 = phoneNumber.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                // Config.IP_ADDRESS + "/"
                .baseUrl(Config.IP_ADDRESS + "/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        User user = new User(str1, str2, null, str4);
        Call<ApiResponse> call = api.update(user);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
                public void onResponse(@NonNull Call<ApiResponse> call, @NonNull retrofit2.Response<ApiResponse> response) {
                    ApiResponse apiResponse = response.body();


                    if (apiResponse.isStatus() == true) {
                        Utilities.disPlayToastShort(Dashboad.this, apiResponse.getMessage());
                        Intent intent = new Intent(Dashboad.this, MainActivity.class);
                        startActivity(intent);
                    }

                }

                @Override
                public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {
                    Utilities.disPlayToast(Dashboad.this, t.getMessage());

                }
        });
    }
    }
