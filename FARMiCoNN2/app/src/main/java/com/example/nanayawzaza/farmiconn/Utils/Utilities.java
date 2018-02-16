package com.example.nanayawzaza.farmiconn.Utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nanayawzaza.farmiconn.ProductDisplayer;

public class Utilities {
    public static void disPlayToast(Context paramContext, String paramString) {
        Toast.makeText(paramContext, paramString, Toast.LENGTH_LONG).show();
    }

    public static void displayLog(String TAG, String message) {
        Log.i(TAG, message);
    }
    public static void disPlayToastShort(Context paramContext, String paramString) {
        Toast.makeText(paramContext, paramString, Toast.LENGTH_SHORT).show();
    }

 /*   public static boolean fileIsNull(MultipartFile file){
        return (null == file || file.getOriginalFilename().equals("") || file.getName().equals(""));
    }

    public static boolean isAuthorized(User user){
        return (null != user);
    }*/

    public static boolean isNullOrEmpty(String value) {
        return (null == value || "".equals(value) || value.isEmpty());
    }
     public static void work(Spinner spinner, AdapterView.OnItemSelectedListener paramContext) {
        String value = spinner.getSelectedItem().toString();

        if (value.equals("Tuber")) {
            Intent intent = new Intent((Context) paramContext, ProductDisplayer.class);
            intent.putExtra("category", value);
            ((Context) paramContext).startActivity(intent);

        } else if (value.equals("Tuber")) {
            Intent intent = new Intent((Context) paramContext, ProductDisplayer.class);
            intent.putExtra("category", value);
            ((Context) paramContext).startActivity(intent);

        } else if (value.equals("Fruit")) {
            Intent intent = new Intent((Context) paramContext, ProductDisplayer.class);
            intent.putExtra("category", value);
            ((Context) paramContext).startActivity(intent);

        } else if (value.equals("Vegetable")) {
            Intent intent = new Intent((Context) paramContext, ProductDisplayer.class);
            intent.putExtra("category", value);
            ((Context) paramContext).startActivity(intent);

        } else if (value.equals("Oils")) {
            Intent intent = new Intent((Context) paramContext, ProductDisplayer.class);
            intent.putExtra("category", value);
            ((Context) paramContext).startActivity(intent);

        } else if (value.equals("LiveStock")) {
            Intent intent = new Intent((Context) paramContext, ProductDisplayer.class);
            intent.putExtra("category", value);
            ((Context) paramContext).startActivity(intent);

        } else if (value.equals("Fiber")) {
            Intent intent = new Intent((Context) paramContext, ProductDisplayer.class);
            intent.putExtra("category", value);
            ((Context) paramContext).startActivity(intent);

        } else if (value.equals("Timber")) {
            Intent intent = new Intent((Context) paramContext, ProductDisplayer.class);
            intent.putExtra("category", value);
            ((Context) paramContext).startActivity(intent);

        } else if (value.equals("Tuber")) {
            Intent intent = new Intent((Context) paramContext, ProductDisplayer.class);
            intent.putExtra("category", value);
            ((Context) paramContext).startActivity(intent);
        }
    }

}


