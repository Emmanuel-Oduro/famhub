package com.example.nanayawzaza.farmiconn;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

public class Sign_in extends AppCompatActivity implements View.OnClickListener {
    EditText emailText, passwordText;
    TextView value;
    public static String TAG = Sign_in.class.getSimpleName();
    Button signInButton;
    TextView regilink, forgetpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        emailText = findViewById(R.id.unam);
        passwordText = findViewById(R.id.upas);
        signInButton =  findViewById(R.id.loginbut);

        forgetpass = findViewById(R.id.forgetp);
        value = findViewById(R.id.value);
        regilink = findViewById(R.id.reglink);
        regilink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Regliks = new Intent(Sign_in.this, Sign_up.class);
                Sign_in.this.startActivity(Regliks);
            }
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Regliks = new Intent(Sign_in.this, ForgotPass.class);
                Sign_in.this.startActivity(Regliks);
            }
        });
        signInButton.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        login(emailText, passwordText, this);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Logging you in...");
        progressDialog.show();
    }
    public boolean login(final EditText emailText, EditText passwordText, final Context paramContext) {
        final String emailText1 = emailText.getText().toString().trim();
        final String passwordText1 = passwordText.getText().toString().trim();
        if (TextUtils.isEmpty(emailText1)) {
            emailText.setError("Please enter Email");
            emailText.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(passwordText1)) {
            passwordText.setError("Please enter Password");
            passwordText.requestFocus();
            return false;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailText1).matches()) {
            emailText.setError("Enter a valid email");
            emailText.requestFocus();
            return false;
        }


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.IP_ADDRESS + "/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        User user = new User(null, emailText.getText().toString(), passwordText.getText().toString(), null);
        Call<ApiResponse> call = api.login(user);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse> call, @NonNull retrofit2.Response<ApiResponse> response) {
                ApiResponse apiResponse = response.body();


                if (apiResponse.isStatus() == true) {
                    if (!TextUtils.isEmpty(emailText1)) {
                        Utilities.disPlayToast(paramContext, apiResponse.getMessage());
                        Utilities.displayLog(TAG + "Login : ", apiResponse.getResult().toString());
                        Utilities.displayLog(TAG + "Login : ", String.valueOf(apiResponse.getCode()));
                        SharePreferences(emailText1);
                        Intent intent = new Intent(paramContext,MainActivity.class);
                        paramContext.startActivity(intent);
                    }
                    try {
                        Thread.sleep(700);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Utilities.disPlayToast(paramContext, "Did not work");
                        Utilities.displayLog(TAG + "Login : ", e.toString());
                    }


                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {
                Utilities.disPlayToast(paramContext, t.getMessage());
            }
        });

        return true;
    }

    private void SharePreferences(String x) {
        //Creating a shared preference
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        //Creating editor to store values to shared preferences
        SharedPreferences.Editor editor = sharedPreferences.edit();


        Utilities.disPlayToast(this, x);
        //Adding values to editor
        editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
        editor.putString(Config.EMAIL_SHARED_PREF, x);

        //Saving values to editor
        editor.commit();


    }
}
