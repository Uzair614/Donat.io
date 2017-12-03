package com.example.uzairkhan.DonationApplication;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SelectingDonationType extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        LocationListener{

    int numChecks = 0;
    private GoogleApiClient mGoogleApiClient;
    private Location mLocation;
    private LocationRequest mLocationRequest;
//    private String connectUrl = getString(R.string.serverAddress) + "sendLocation.php";
    private String connectUrl = "https://donationapptest.000webhostapp.com/testdb/sendLocation.php";
    private ArrayList<String> typesArray = new ArrayList();
    private String centresList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecting_donation_type);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        testFunctionSelectingDonation();

        seeMapButtonPress();
        getDonationCentresFromServer();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d ("onConnected", "In location");
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(1000);
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        findViewById(R.id.selectionError).setVisibility(View.VISIBLE);
        TextView output = (TextView) findViewById(R.id.output);
        output.setText("connection suspended");
        Log.d ("onConnectionFailed", "connection failed");
        Toast.makeText(getApplicationContext(), "Connection Suspended", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        findViewById(R.id.selectionError).setVisibility(View.VISIBLE);
        TextView output = (TextView) findViewById(R.id.output);
        output.setText("connection failed");
        Toast.makeText(getApplicationContext(), "Connection Failed", Toast.LENGTH_LONG).show();
    }

    public void onCheckboxClicked(View view) {
        findViewById(R.id.viewMap).setEnabled(true);
        boolean checked = ((CheckBox)view).isChecked();
        if(checked)
            numChecks++;
        else
            numChecks--;

        if(numChecks > 0) {
            findViewById(R.id.selectionError).setVisibility(View.INVISIBLE);
        }
        else {
            findViewById(R.id.selectionError).setVisibility(View.VISIBLE);
        }

        readyToViewMap();
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            findViewById(R.id.locationError).setVisibility(View.INVISIBLE);
            findViewById(R.id.btn1).setEnabled(true); //enable mapview after location receiveed.
            mLocation = location;
            readyToViewMap();
            Log.d ("onConnected", "In LOCATIONLISTENER" + location.getLatitude());
        }
    }

    private void readyToViewMap() {
        if((numChecks > 0) && (mLocation != null)) {
            findViewById(R.id.readyView).setVisibility(View.VISIBLE);
            findViewById(R.id.viewMap).setEnabled(true);
            findViewById(R.id.viewMap).setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        } else {
            findViewById(R.id.readyView).setVisibility(View.INVISIBLE);
            findViewById(R.id.viewMap).setEnabled(false);
            findViewById(R.id.viewMap).setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
        }
    }

    private void seeMapButtonPress() {
        Button b =  (Button) findViewById(R.id.btn1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("For maps", "On click listener");
                Intent i = new Intent(SelectingDonationType.this, MapActivity.class);
                Bundle args = new Bundle();
                args.putParcelable("userPos", new LatLng(24.912987, 67.088384));
                i.putExtra("Bundle", args);
                i.putExtra("Centres", centresList);

                startActivity(i);
            }
        });
    }

    private void getDonationCentresFromServer() {
        TextView send = (TextView) findViewById(R.id.viewMap);
        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            addCentreTypes();
            String json = new Gson().toJson(typesArray);
            NetworkController.getInstance().GetFromServer(json, connectUrl, new Response.Listener<String>()
            {
                @Override
                public void onResponse(String result)
                {
                    if (!result.isEmpty())
                    {
                        centresList = result;
                        Toast.makeText(getApplicationContext(), "Server Contacted Successfully", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            }
        });

    }


    private void addCentreTypes() {
        typesArray.clear();
        if (((CheckBox)findViewById(R.id.checkBloodMap)).isChecked())
            typesArray.add("Blood");
        if (((CheckBox)findViewById(R.id.checkBooksMap)).isChecked())
            typesArray.add("Books");
        if (((CheckBox)findViewById(R.id.checkFoodMap)).isChecked())
            typesArray.add("Food");
        if (((CheckBox)findViewById(R.id.checkMoneyMap)).isChecked())
            typesArray.add("Money");
        if (((CheckBox)findViewById(R.id.checkClothesMap)).isChecked())
            typesArray.add("Clothes");
    }


    private void testFunctionSelectingDonation() {
        connectUrl = getString(R.string.ip) + "sendLocation.php";
        findViewById(R.id.btn1).setEnabled(true);
        mLocation = new Location("");
        mLocation.setLatitude(24.891991);
        mLocation.setLongitude(67.075204);
    }

}
