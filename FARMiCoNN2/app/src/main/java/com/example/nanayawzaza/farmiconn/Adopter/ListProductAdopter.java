package com.example.nanayawzaza.farmiconn.Adopter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nanayawzaza.farmiconn.FullProductInfoDisplaer;
import com.example.nanayawzaza.farmiconn.R;
import com.example.nanayawzaza.farmiconn.Utils.Utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nana Yaw Zaza on 12/2/2017.
 */

public class ListProductAdopter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  implements Filterable {
    private String TAG = ListProductAdopter.class.getSimpleName();
    private Context context;
    //private LayoutInflater inflater;
    private ArrayList<Products> productList = new ArrayList<>();
    private List<Products> contactList;
    private List<Products> contactListFiltered;
    private ContactsAdapterListener listener;
    //private ArrayList<Products> list = null;

    public ListProductAdopter(){

    }
    public ListProductAdopter(ArrayList<Products> productList, Context paramContext) {
        this.productList = productList;
        this.context = paramContext;
        Utilities.displayLog(TAG+" text", String.valueOf(productList.size()));
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homeproduct_list, parent, false);
        return new MyHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder = (MyHolder) holder;
        Utilities.displayLog(TAG + "Z", String.valueOf(position));

        try {
            final Products products = productList.get(position);
            myHolder.productName.setText(products.getProductName());
            myHolder.location.setText(products.getLocation());
            myHolder.dateCreated.setText(products.getDateCreated());
            //loading the image
            Glide.with(context)
                    .load(products.getImage())
                    .into(myHolder.image);
            myHolder.price.setText(products.getPrice());
            myHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Utilities.disPlayToast(context,products.getProductName().toString()+products.getIndex()+products.getCategory().toString());
                    Intent intent = new Intent(context, FullProductInfoDisplaer.class);
                    intent.putExtra("proname", products.getProductName());
                    intent.putExtra("pimg", products.getImage());
                    intent.putExtra("cat", products.getCategory());
                    intent.putExtra("price", products.getPrice());
                    intent.putExtra("plocation", products.getLocation());
                    intent.putExtra("pdate", products.getDateCreated());
                    intent.putExtra("pfname", products.getName());
                    intent.putExtra("pfphone", products.getPhoneNumber());
                    intent.putExtra("pfemail", products.getEmail());
                    intent.putExtra("paudioa", products.getAudio());
                    intent.putExtra("pvideo", products.getVideo());
                    intent.putExtra("itemindex", products.getIndex());
                    intent.putExtra("des", products.getDescription());
                    Utilities.displayLog(TAG, products.getIndex());
//                    intent.putExtra("itemcategory",products.getCategory());
//                    intent.putExtra("itemname",products.getProductName());
                    context.startActivity(intent);
                   /* Intent intent = getIntent();
                    getIntent().getExtras().getString("username")*/
                }
            });
        } catch (Exception ex) {
            Utilities.displayLog(TAG, ex.toString());
        }
    }

    @Override
    public int getItemCount() {
        Utilities.displayLog(TAG + "ZZ", String.valueOf(this.productList.size()));
        return this.productList.size();
    }

    public long getItemId(int paramInt) {
        Utilities.displayLog(TAG + "ZZ", String.valueOf(paramInt));

        return super.getItemId(paramInt);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    contactListFiltered = contactList;
                } else {
                    List<Products> filteredList = new ArrayList<>();
                    for (Products row : contactList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getProductName().toLowerCase().contains(charString.toLowerCase()) || row.getName().contains(charSequence)
                                || row.getCategory().contains(charSequence) || row.getLocation().contains(charSequence) || row.getCategory().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    contactListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = contactListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                contactListFiltered = (ArrayList<Products>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
    public interface ContactsAdapterListener {
        void onContactSelected(Products products);
    }

    static class MyHolder extends RecyclerView.ViewHolder {
        TextView productName, price, location, dateCreated;
        ImageView image;
        CardView cardView;


        MyHolder(View itemView) {
            super(itemView);
            productName =  itemView.findViewById(R.id.pname);
            location =  itemView.findViewById(R.id.location);
            price =  itemView.findViewById(R.id.price);
            dateCreated =  itemView.findViewById(R.id.pdate);
            image = itemView.findViewById(R.id.thumbnail);
            cardView =  itemView.findViewById(R.id.card_view);
        }

    }



    /*public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    contactListFiltered = contactList;
                } else {
                    List<Products> filteredList = new ArrayList<>();
                    for (Products row : contactList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getProductName().toLowerCase().contains(charString.toLowerCase()) || row.getName().contains(charSequence)
                                || row.getCategory().contains(charSequence) || row.getLocation().contains(charSequence) || row.getCategory().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    contactListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = contactListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                contactListFiltered = (ArrayList<Contact>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }*/



   /* // Filter method
    public void filter(ArrayList<Products> newList) {

        charText = charText.toLowerCase(Locale.getDefault());
        Utilities.displayLog(TAG+" here>>", String.valueOf(productList.size()));
        //productList.clear();
            for(Products sl : productList) {
                if(sl.getProductName().toLowerCase(Locale.getDefault()).contains(charText)) {
                   // list.add(sl);
                    Utilities.disPlayToast(context, "done");
                }
            }
        notifyDataSetChanged();
    }
*/
/*
    public void setFilteringList(ArrayList<Products> newList) {
        productList.addAll(newList);
        notifyDataSetChanged();
    }
*/

  /*  public List<Products> filter(List<Products> products, String query) {
        query = query.toLowerCase();

        ArrayList<Products> filteredCompanyList = new ArrayList<>();

        for (Products item : products) {
            final String productName = item.getProductName().toLowerCase();
            final String productPrice = item.getPrice().toLowerCase();
            if (productName.contains(query) || productPrice.contains(query)) {
                filteredCompanyList.add(item);
            }
        }
        return filteredCompanyList;
    }

    public void animateTo(List<Products> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(List<Products> newModels) {
        for (int i = productList.size() - 1; i >= 0; i--) {
            final Products model = productList.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<Products> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final Products model = newModels.get(i);
            if (!productList.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<Products> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final Products model = newModels.get(toPosition);
            final int fromPosition = productList.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public Products removeItem(int position) {
        final Products model = productList.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, Products model) {
        productList.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final Products model = productList.remove(fromPosition);
        productList.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }*/
}
