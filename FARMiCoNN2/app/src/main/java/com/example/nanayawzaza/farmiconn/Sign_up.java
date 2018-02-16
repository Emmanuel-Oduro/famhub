package com.example.nanayawzaza.farmiconn;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nanayawzaza.farmiconn.Utils.Queries;
import com.example.nanayawzaza.farmiconn.Utils.Utilities;

public class Sign_up extends AppCompatActivity implements View.OnClickListener {
    EditText fulname,emailText,phonText,cpasswordText,passw;
    Button signupButton;
    private String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fulname = (EditText) findViewById(R.id.fnam);
        emailText= (EditText) findViewById(R.id.uEmail);
        cpasswordText = (EditText) findViewById(R.id.uPas);
        phonText = (EditText) findViewById(R.id.uContact);
        passw= (EditText) findViewById(R.id.pass);
        signupButton = (Button) findViewById(R.id.regista);

        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        signupButton.setOnClickListener(this);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        Register();
    }
    public void Register(){
        final String username = fulname.getText().toString().trim();
        final String email = emailText.getText().toString().trim();
        final String password = passw.getText().toString().trim();
        final String phone = phonText.getText().toString().trim();
        final String conpass = cpasswordText.getText().toString().trim();

        if (TextUtils.isEmpty(username)) {
            fulname.setError("Please enter Name");
            fulname.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            emailText.setError("Please enter your email");
            emailText.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("Enter a valid email");
            emailText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            passw.setError("Enter a password");
            passw.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(conpass)) {
            cpasswordText.setError("Please confirm password");
            cpasswordText.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            phonText.setError("Enter a Phone Number");
            phonText.requestFocus();
            return;
        }
        if (conpass.equals(password)==false){
            //Toast.makeText(this, "confirm password do not match", Toast.LENGTH_LONG).show();
            Utilities.disPlayToast(Sign_up.this,"confirm password do not match");
            return;
        }
       // Queries.Registration(this,username,email,password,phone,fulname,emailText,passw,phonText);
Queries.Registratio(this,fulname,emailText,passw,phonText);
final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registering...");
        progressDialog.show();
    }
    @Override
    public void onBackPressed() {
        finish();

    }

}
