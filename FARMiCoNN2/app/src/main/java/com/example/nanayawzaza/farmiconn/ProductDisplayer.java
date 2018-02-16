package com.example.nanayawzaza.farmiconn;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ScrollingTabContainerView;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.nanayawzaza.farmiconn.Utils.Queries;
import com.example.nanayawzaza.farmiconn.Utils.Utilities;

public class ProductDisplayer extends AppCompatActivity {
    //the recyclerview
    RecyclerView recyclerView;
    String category;
    Spinner categoryText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_displayer);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Intent intent = getIntent();
        String x = getIntent().getExtras().getString("category");
        Utilities.disPlayToast(this, x);


        recyclerView = findViewById(R.id.recyclerView);

        displayer(this, x, recyclerView);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    private int getIndex(Spinner spinner, String myString) {

        int index = 0;

        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(myString)) {
                index = i;
            }
        }
        return index;
    }

    private static void displayer(Context paramContext, String category,RecyclerView recyclerView) {

            Queries.DisplayProductsByCategory(paramContext, category, recyclerView);

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
