package com.example.nanayawzaza.farmiconn;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.nanayawzaza.farmiconn.Adopter.Cat;
import com.example.nanayawzaza.farmiconn.Adopter.CategoryAdopter;
import com.example.nanayawzaza.farmiconn.Models.Displaylogic;
import com.example.nanayawzaza.farmiconn.Utils.Queries;

import java.util.ArrayList;

public class Category extends AppCompatActivity {
    private final int imag[] = new int[]{
            R.drawable.amarga,
            R.drawable.allspice1,
            R.drawable.cassava,
            R.drawable.hhgjf,
            R.drawable.howtad,
            R.drawable.index,
            R.drawable.tuber1,
            R.drawable.timx,
            R.drawable.asas
    };
    private   final String categoryNames[]=new String[]{"Tuber","Fruit","Vegetable","Cereal","Oils","LiveStock","Fiber","Timber","Spices"};
    private  final String categoryMessage[]=new String[]{"Tuber message","Fruit message","Vegetable message","Cereal message","Oils message","LiveStock message","Fiber message","Timber message","Spices message"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        //Queries.DisplayProductsByCategory(this,"Tuber");
     //   Displaylogic.getProductByCategory("Tuber",this);

     initViews();
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
      finish();
    }
    private void initViews(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerViewCat);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<Cat> CategoryItem = prepareData();
        CategoryAdopter adapter = new CategoryAdopter(CategoryItem,Category.this);
        recyclerView.setAdapter(adapter);

    }
    private ArrayList<Cat> prepareData(){

        ArrayList<Cat> catigoryList = new ArrayList<>();
        for(int i=0;i<categoryNames.length;i++){
           Cat cat=new Cat();
           cat.setCategoryName(categoryNames[i]);
           cat.setMessage(categoryMessage[i]);
           cat.setImage(imag[i]);
            catigoryList.add(cat);
        }
        return catigoryList;
    }



}
