package com.example.uzairkhan.DonationApplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ViewDonationRequests extends AppCompatActivity {

    private String TAG = "ViewDonationRequests";
    private ArrayList<DonationRequest> requestArrayList;
    private String connectUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_donation_requests);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        connectUrl = getString(R.string.serverAddress) + "sendDonationRequests.php";
        testfunc();
        requestArrayList = new ArrayList<>();

        NetworkController.getInstance().GetFromServer(null, connectUrl, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jArray = new JSONArray(response);
                    for(int i = 0; i < jArray.length(); i++) {
                        JSONObject jObj = jArray.getJSONObject(i);
                        requestArrayList.add(new DonationRequest(jObj));
                    }

                    initialize();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        });


    }

    private void testfunc() {
        connectUrl = getString(R.string.ip) + "sendDonationRequests.php";
    }

    private void initialize() {
        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);
        DonationRequestRVAdapter adapter = new DonationRequestRVAdapter(requestArrayList);
        rv.setAdapter(adapter);
        Log.d(TAG, "onResponse:     ffffffffffffffaaaaasd     " + requestArrayList.toString());
    }

}
