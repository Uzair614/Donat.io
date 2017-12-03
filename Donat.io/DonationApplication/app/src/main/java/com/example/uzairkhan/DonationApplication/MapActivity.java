package com.example.uzairkhan.DonationApplication;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback,
        FilterDialogFragment.FilterDialogListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    GoogleMap n_map;
    boolean responseReady = false;
    boolean locationReady = false;
    private LatLng userPos;
    ArrayList<DonationCentre> donationCentresBooks;
    ArrayList<DonationCentre> donationCentresFood;
    ArrayList<DonationCentre> donationCentresClothes;
    ArrayList<DonationCentre> donationCentresMoney;
    ArrayList<DonationCentre> donationCentresBlood;
    private ArrayList<MarkerOptions> markerList;
    private ArrayList<Marker> markerReturn;
    private ArrayList<String> selectedFilters;
    private String TAG = "MapActivity";

    private GoogleApiClient mGoogleApiClient;
    private Location mLocation;
    private LocationRequest mLocationRequest;
    private String connectUrl = "https://donationapptest.000webhostapp.com/sendLocation.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        selectedFilters = new ArrayList<>();
//        connectUrl = "https://donationapptest.000webhostapp.com/sendLocation.php";

        donationCentresBooks = new ArrayList<>();
        donationCentresBlood = new ArrayList<>();
        donationCentresClothes = new ArrayList<>();
        donationCentresFood = new ArrayList<>();
        donationCentresMoney = new ArrayList<>();
        connectUrl = getString(R.string.serverAddress) + "sendLocation.php";
        selectedFilters = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.donation_array)));

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        testFunctionSelectingDonation();

        getDonationCentresFromServer();
    }


    private void loadMap() {
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    //will have to wait till location services get user location
    @Override
    public void onMapReady(GoogleMap googleMap) {
        locationReady = true;
        n_map = googleMap;

        userPos = new LatLng(mLocation.getLatitude(), mLocation.getLongitude());
        CameraPosition target = CameraPosition.builder().target(userPos).zoom(17).build();
        n_map.moveCamera(CameraUpdateFactory.newCameraPosition(target));



        addMarkerToMap();
    }

    @Override
    public void onDialogPositiveClick(ArrayList<String> selectedItems) {
        markerList = new ArrayList<>();
        n_map.clear();
        DonationCentre d;
        if(selectedItems.contains("Food")) {
            for (int i = 0; i < donationCentresFood.size(); i++) {
                d = donationCentresFood.get(i);
                MarkerOptions m = new MarkerOptions()
                        .position(new LatLng(d.getLatitude(), d.getLongitude()))
                        .title(d.getName());
                markerList.add(m);
            }
        } if(selectedItems.contains("Money")) {
            for (int i = 0; i < donationCentresMoney.size(); i++) {
                d = donationCentresMoney.get(i);
                MarkerOptions m = new MarkerOptions()
                        .position(new LatLng(d.getLatitude(), d.getLongitude()))
                        .title(d.getName());
                markerList.add(m);
            }
        } if(selectedItems.contains("Clothes")) {
            for (int i = 0; i < donationCentresClothes.size(); i++) {
                d = donationCentresClothes.get(i);
                MarkerOptions m = new MarkerOptions()
                        .position(new LatLng(d.getLatitude(), d.getLongitude()))
                        .title(d.getName());
                markerList.add(m);
            }
        } if(selectedItems.contains("Books")) {
            for (int i = 0; i < donationCentresBooks.size(); i++) {
                d = donationCentresBooks.get(i);
                MarkerOptions m = new MarkerOptions()
                        .position(new LatLng(d.getLatitude(), d.getLongitude()))
                        .title(d.getName());
                markerList.add(m);
            }
        } if(selectedItems.contains("Blood")) {
            for (int i = 0; i < donationCentresBlood.size(); i++) {
                d = donationCentresBlood.get(i);
                MarkerOptions m = new MarkerOptions()
                        .position(new LatLng(d.getLatitude(), d.getLongitude()))
                        .title(d.getName() + "\t" + d.getCentreType());
                markerList.add(m);
            }
        }
        addMarkerToMap();
//        for (int i = 0; i < selectedItems.size(); i++)
//            Log.d(TAG, selectedItems.get(i) + "            returned \n");
//        selectedFilters = selectedItems;
    }

    public void showFilterDialog(View view) {
        // Create an instance of the dialog fragment and show it
        DialogFragment newFragment = FilterDialogFragment.newInstance(selectedFilters);
        newFragment.show(getSupportFragmentManager(), "FilterDialogFragment");
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
        Log.d (TAG, "In location");
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(1000);
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
        loadMap();
    }


    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(getApplicationContext(), "Connection Suspended", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(getApplicationContext(), "Connection Failed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            mLocation = location;
            Log.d (TAG, "In LOCATIONLISTENER" + location.getLatitude());
        }
    }


    private void getDonationCentresFromServer() {
        NetworkController.getInstance().GetFromServer(null, connectUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String result) {
                if (!result.isEmpty()) {
                    markerList = new ArrayList<>();
                    JSONArray jArray = null;
                    try {
                        jArray = new JSONArray(result);
                        Log.d(TAG, "onResponse123123:   " + result);
                        for(int i = 0; i < jArray.length(); i++) {
                            JSONObject jObj = jArray.getJSONObject(i);
                            DonationCentre d = new DonationCentre(jObj);
                            if (d.getCentreType().equals("BLOOD")) {
                                donationCentresBlood.add(d);
                            }else if (d.getCentreType().equals("BOOKS")) {
                                donationCentresBooks.add(d);
                            }else if (d.getCentreType().equals("MONEY")) {
                                donationCentresMoney.add(d);
                            }else if (d.getCentreType().equals("CLOTHES")) {
                                donationCentresClothes.add(d);
                            }else if (d.getCentreType().equals("FOOD")) {
                                donationCentresFood.add(d);
                            }
                            MarkerOptions m = new MarkerOptions()
                                    .position(new LatLng(d.getLatitude(), d.getLongitude()))
                                    .title(d.getName() + "\t" + d.getCentreType());
                            markerList.add(m);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

//                    Log.d (TAG, "WierdTags    " + result);
//                    donationCentres = new Gson().fromJson(result, DonationCentre[].class);

//                    for(int i = 0; i < donationCentres.length; i++) {
//
//
//                    }
                    responseReady = true;
                    addMarkerToMap();

                    Toast.makeText(getApplicationContext(), "Server Contacted Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addMarkerToMap() {
        if(responseReady && locationReady) {
            for (MarkerOptions obj: markerList)
                n_map.addMarker(obj);

            MarkerOptions person = new MarkerOptions()
                    .position(userPos)
                    .title("You are Here");
            n_map.addMarker(person);
        }
    }


    private void testFunctionSelectingDonation() {
        connectUrl = getString(R.string.ip) + "sendLocation.php";
        Log.d (TAG, "WierdTags" + connectUrl);
        mLocation = new Location("");
        mLocation.setLatitude(24.856919);
        mLocation.setLongitude(67.264);
        loadMap();
    }

}
