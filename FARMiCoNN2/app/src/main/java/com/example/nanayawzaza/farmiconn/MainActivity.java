package com.example.nanayawzaza.farmiconn;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.SQLException;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.nanayawzaza.farmiconn.Adopter.ListProductAdopter;
import com.example.nanayawzaza.farmiconn.Adopter.Products;
import com.example.nanayawzaza.farmiconn.Utils.Config;
import com.example.nanayawzaza.farmiconn.Utils.Queries;
import com.example.nanayawzaza.farmiconn.Utils.Utilities;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{
    NavigationView navigationView;
    private ArrayList<Products> products ;
    static String TAG = MainActivity.class.getSimpleName();
    private boolean loggedIn;
    RecyclerView recyclerView;
    SearchView search_button;

    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;

    private Location mLastLocation;

    // Google client to interact with Google API
    private GoogleApiClient mGoogleApiClient;

    // boolean flag to toggle periodic location updates
    private boolean mRequestingLocationUpdates = false;

    private LocationRequest mLocationRequest;

    // Location updates intervals in sec
    private static int UPDATE_INTERVAL = 10000; // 10 sec
    private static int FATEST_INTERVAL = 5000; // 5 sec
    private static int DISPLACEMENT = 10; // 10 meters

    // UI elements
    private double latitude, longitude;
    private String address;

    private String city;
    private String country;
private ListProductAdopter adapter ;

    private static String locations;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        onCurrentLocationClick();
        search_button = findViewById(R.id.search_button);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //getting the recyclerview from xml
        recyclerView = findViewById(R.id.recyclerView);
        //search_button.setOnQueryTextListener(this);
        Queries.DisplayProducts(this, recyclerView, dpToPx(10));
        //Fetching the boolean value form sharedpreferences
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, MODE_PRIVATE);
        loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, false);

        //If we will get true
        if (loggedIn) {
            //We will start the Profile Activity
            showItem();
        } else {
            hideItem();
        }
        adapter=new ListProductAdopter();
        search_button.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
              adapter.getFilter().filter(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
             adapter.getFilter().filter(s);
             return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to close?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        // MainActivity.super.onBackPressed();
                        System.exit(arg1);
                    }
                }).create().show();
    }

 /*   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.dashb) {
            Intent intent = new Intent(this, Dashboad.class);
            startActivity(intent);
        } else if (id == R.id.farmproduct) {
            Intent intent = new Intent(this, Category.class);
            startActivity(intent);
        } else if (id == R.id.login) {
            Intent intent = new Intent(this, Sign_in.class);
            startActivity(intent);

        } else if (id == R.id.farmadvert) {
            SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, MODE_PRIVATE);

            //Fetching the boolean value form sharedpreferences
            loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, false);

            //If we will get true
            if (loggedIn) {
                Intent intent = new Intent(this, PublishProduct.class);
                intent.putExtra("location",locations);
                startActivity(intent);
            } else {
                Intent intent = new Intent(this, Sign_in.class);
                startActivity(intent);
            }
//            Intent intent = new Intent(this,PublishProduct.class);
//            startActivity(intent);

        } else if (id == R.id.farmmembers) {
            Intent intent = new Intent(this, Sign_up.class);
            startActivity(intent);
        } else if (id == R.id.logout) {
            DestroySharedPreference();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void DestroySharedPreference() {
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        checkPlayServices();
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, MODE_PRIVATE);

        //Fetching the boolean value form sharedpreferences
        loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, false);

        //If we will get true
        if (loggedIn) {
            //We will start the Profile Activity
            showItem();
            hideItemafterlogin();
        }
    }

    private void hideItem() {
        navigationView = findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.logout).setVisible(false);
        nav_Menu.findItem(R.id.dashb).setVisible(false);

    }

    private void hideItemafterlogin() {
        navigationView =  findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.login).setVisible(false);
        nav_Menu.findItem(R.id.farmmembers).setVisible(false);

    }

    private void showItem() {
        navigationView =  findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.logout).setVisible(true);
        nav_Menu.findItem(R.id.dashb).setVisible(true);

    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

   /* @Override
    public boolean onQueryTextSubmit(String s) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        String input = s.toLowerCase(Locale.getDefault());
        adapter.filter(input);
        adapter.setSongsList(members);
        return false;
    }*/


    public void onCurrentLocationClick() {
        if (checkPlayServices()) {

            // Building the GoogleApi client
            buildGoogleApiClient();

            if ((buildGoogleApiClient())) {
                onStartConn();

                if ((onStartConn())) {
                    checkPlayServices();
                }
            }
        }
    }
    private void displayLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        onStopCon();

        if (mLastLocation != null) {
            latitude = mLastLocation.getLatitude();
            longitude = mLastLocation.getLongitude();


            Geocoder geocoder;
            List<Address> addresses = null;
            geocoder = new Geocoder(this, Locale.getDefault());

            try {
                addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            } catch (IOException e) {
                e.printStackTrace();
            }

            address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            city = addresses.get(0).getLocality();
            country = addresses.get(0).getCountryName();

            MainActivity.locations = address;
            Utilities.disPlayToast(this,locations);





        } else {
//Toast.makeText(getApplicationContext()," Couldn't get the location. Make sure location is enabled on the device").show();
            Log.i(TAG+" Locaton >"," Couldn't get the location. Make sure location is enabled on the device");
        }
    }


    public void onStopCon() {
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

    }
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "This device is not supported.", Toast.LENGTH_LONG)
                        .show();
                finish();
            }
            return false;
        }
        return true;
    }

    protected synchronized boolean buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
        return true;
    }

    public boolean onStartConn() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
        return true;
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        displayLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        SQLException result = null;
        Log.i(TAG+" onConnectionFailed ",result.getMessage());
    }

}
