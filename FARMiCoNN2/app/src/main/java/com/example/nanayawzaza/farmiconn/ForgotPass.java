package com.example.nanayawzaza.farmiconn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nanayawzaza.farmiconn.Utils.Queries;
import com.example.nanayawzaza.farmiconn.Utils.Utilities;

public class ForgotPass extends AppCompatActivity  implements View.OnClickListener{
    EditText phone,emailText,cpasswordText,passwordText;
Button fp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        phone = (EditText) findViewById(R.id.uContact);
        emailText= (EditText) findViewById(R.id.uEmail);
        cpasswordText = (EditText) findViewById(R.id.uPas);
        passwordText = findViewById(R.id.pass);
        fp=findViewById(R.id.regista);
        fp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        fpass();
    }

    public void fpass(){

        if (TextUtils.isEmpty(emailText.getText())) {
            emailText.setError("Please enter your email");
            emailText.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailText.getText()).matches()) {
            emailText.setError("Enter a valid email");
            emailText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(passwordText.getText())) {
            passwordText.setError("Enter a password");
            passwordText.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(cpasswordText.getText())) {
            cpasswordText.setError("Please confirm password");
            cpasswordText.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(phone.getText())) {
            phone.setError("Enter a Phone Number");
            phone.requestFocus();
            return;
        }
        if (!cpasswordText.equals(passwordText)){
            cpasswordText.setError("Does not match with password");
            Utilities.disPlayToast(ForgotPass.this,"confirm password do not match");
            return;
        }


        Queries.ForgottenPassword(this,emailText,phone,passwordText);
    }
}
