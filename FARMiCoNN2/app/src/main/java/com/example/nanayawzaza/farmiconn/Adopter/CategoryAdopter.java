package com.example.nanayawzaza.farmiconn.Adopter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nanayawzaza.farmiconn.ProductDisplayer;
import com.example.nanayawzaza.farmiconn.R;
import com.example.nanayawzaza.farmiconn.Utils.Utilities;

import java.util.ArrayList;

/**
 * Created by Nana Yaw Zaza on 12/5/2017.
 */

public class CategoryAdopter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String TAG = CategoryAdopter.class.getSimpleName();
    private Context context;
    //private LayoutInflater inflater;
    private ArrayList<Cat> CategoryList = new ArrayList();

    public CategoryAdopter(ArrayList<Cat> productList,Context paramContext) {

        this.CategoryList = productList;
        this.context = paramContext;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_category, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder = (MyHolder) holder;
        Utilities.displayLog(TAG + "Z", String.valueOf(position));
        try {
            final Cat cat=(Cat) CategoryList.get(position);
            myHolder.categoryName.setText(cat.getCategoryName());
            myHolder.message.setText(cat.getMessage());
                      //loading the image
            Glide.with(context)
                    .load(cat.getImage())
                    .into(myHolder.image);
            myHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Utilities.disPlayToast(context,cat.getCategoryName());
                   Intent intent=new Intent(context, ProductDisplayer.class);
                    intent.putExtra("category",cat.getCategoryName());
                  context.startActivity(intent);

                }
            });
        }catch (Exception ex){
            Utilities.displayLog(TAG,ex.toString());
        }

    }

    @Override
    public int getItemCount() {
        Utilities.displayLog(TAG + "ZZ", String.valueOf(this.CategoryList.size()));
        return this.CategoryList.size();
    }

    public long getItemId(int paramInt) {
        Utilities.displayLog(TAG + "ZZ", String.valueOf(paramInt));

        return super.getItemId(paramInt);
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView categoryName, message;
        ImageView image;
        CardView cardView;


        public MyHolder(View itemView) {
            super(itemView);
            categoryName =  itemView.findViewById(R.id.pname);
            message =  itemView.findViewById(R.id.message);
            image =  itemView.findViewById(R.id.thumbnail);
            cardView =  itemView.findViewById(R.id.card_view);
        }

    }
}
