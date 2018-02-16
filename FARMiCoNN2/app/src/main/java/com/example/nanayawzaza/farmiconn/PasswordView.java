package com.example.nanayawzaza.farmiconn;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class PasswordView extends AppCompatActivity implements View.OnClickListener{
    static String TAG=PasswordView.class.getSimpleName();
    String farmerAddress;
    BottomNavigationView navigation;
    Button Cpass;
    EditText conpass,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_view);
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME,MODE_PRIVATE);
        farmerAddress= sharedPreferences.getString(Config.EMAIL_SHARED_PREF, "");
        Utilities.disPlayToast(this,farmerAddress);
        navigation=findViewById(R.id.navigation);
        password=findViewById(R.id.unam);
        password=findViewById(R.id.upas);
        Cpass=findViewById(R.id.loginbut);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()){
                    case R.id.home:
                        intent=new Intent(PasswordView.this,MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.profile:
                        //mViewPager.setCurrentItem(0);
                        intent=new Intent(PasswordView.this,Dashboad.class);
                        startActivity(intent);
                        Log.d(TAG, "zaza \n >>>>>>>>>>>>>>> ");
                        break;
                    case R.id.products:
                        intent = new Intent(PasswordView.this, ProductByMail.class);
                        startActivity(intent);
                        break;
                    case R.id.pass:
                        intent=new Intent(PasswordView.this,PasswordView.class);
                        startActivity(intent);
                        break;

                }
                return false;
            }
        });
        Menu menu=navigation.getMenu();
        MenuItem menuItem=menu.getItem(3);
        menuItem.setChecked(true);
        Cpass.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ChangePassword();
    }

    public void ChangePassword(){
        if (!conpass.equals(password)){
            //Toast.makeText(this, "confirm password do not match", Toast.LENGTH_LONG).show();
            Utilities.disPlayToast(PasswordView.this,"confirm password do not match");
            return;
        }
        String str1 = password.getText().toString();


        Retrofit retrofit = new Retrofit.Builder()
                // Config.IP_ADDRESS + "/"
                .baseUrl(Config.IP_ADDRESS + "/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        User user = new User(null, farmerAddress, str1, null);

        Call<ApiResponse> call = api.updateProfile(user);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse> call, @NonNull retrofit2.Response<ApiResponse> response) {
                ApiResponse apiResponse = response.body();


                if (apiResponse.isStatus() == true) {
                    Utilities.disPlayToastShort(PasswordView.this, apiResponse.getMessage());
                    Intent intent = new Intent(PasswordView.this, MainActivity.class);
                    startActivity(intent);
                }

            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {
                Utilities.disPlayToast(PasswordView.this, t.getMessage());

            }
        });
        Queries.UpdateProfilePassword(this,password,farmerAddress);

    }
}
