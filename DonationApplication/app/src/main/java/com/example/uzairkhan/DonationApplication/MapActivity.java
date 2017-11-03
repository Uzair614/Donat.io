package com.example.uzairkhan.DonationApplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback{

    GoogleMap n_map;
    boolean mapReady = false;
    private LatLng userPos;
    DonationCentre[] result;
    private String TAG = "MapActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        Bundle args = getIntent().getParcelableExtra("Bundle");
        if (args != null) {
            userPos = args.getParcelable("userPos");
        }

        Log.d(TAG, "OnCreate");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapReady = true;
        n_map = googleMap;

        CameraPosition target = CameraPosition.builder().target(userPos).zoom(17).build();
        n_map.moveCamera(CameraUpdateFactory.newCameraPosition(target));

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String response = extras.getString("Centres");
            result = new Gson().fromJson(response, DonationCentre[].class);
            MarkerOptions person = new MarkerOptions()
                    .position(userPos)
                    .title("You are Here");
            n_map.addMarker(person);
            for(int i = 0; i < result.length; i++) {
                Log.d(TAG, "MapResponse : " + result[i].getName());
                MarkerOptions m = new MarkerOptions()
                        .position(new LatLng(result[i].getLatitude(), result[i].getLongitude()))
                        .title(result[i].getName());
                n_map.addMarker(m);
            }
        }
    }
}
