package com.example.nanayawzaza.farmiconn;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.nanayawzaza.farmiconn.Utils.Config;
import com.example.nanayawzaza.farmiconn.Utils.Queries;
import com.example.nanayawzaza.farmiconn.Utils.Utilities;

public class ProductByMail extends AppCompatActivity {
    RecyclerView recyclerView;
    static String TAG=ProductByMail.class.getSimpleName();
    String farmerAddress;
BottomNavigationView navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_by_mail);
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME,MODE_PRIVATE);
        farmerAddress= sharedPreferences.getString(Config.EMAIL_SHARED_PREF, "");
        navigation=findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.home:
                 intent=new Intent(ProductByMail.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.profile:
                //mViewPager.setCurrentItem(0);
                 intent=new Intent(ProductByMail.this,Dashboad.class);
                startActivity(intent);
                Log.d(TAG, "zaza \n >>>>>>>>>>>>>>> ");
                break;
            case R.id.products:
                intent = new Intent(ProductByMail.this, ProductByMail.class);
                startActivity(intent);
                break;
            case R.id.pass:
                intent=new Intent(ProductByMail.this,PasswordView.class);
                startActivity(intent);
                break;

        }
        return false;
    }
});
        Menu menu=navigation.getMenu();
        MenuItem menuItem=menu.getItem(2);
        menuItem.setChecked(true);
        //If we will get true
        recyclerView = findViewById(R.id.recyclerView);
        Queries.DisplayProductsBymail(this,farmerAddress,recyclerView);
        Utilities.disPlayToast(this, farmerAddress);
    }
}
