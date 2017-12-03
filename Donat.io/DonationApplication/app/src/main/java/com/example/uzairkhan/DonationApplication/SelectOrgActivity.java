package com.example.uzairkhan.DonationApplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SelectOrgActivity extends AppCompatActivity {

    private String TAG = "SelectOrgActivity";
    private ArrayList<Admin> orgNamesList;
    private String connectUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_org);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        orgNamesList = new ArrayList<>();
        connectUrl = getString(R.string.serverAddress) + "sendOrgNames.php";
        testfunc();
        NetworkController.getInstance().GetFromServer(null, connectUrl, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d(TAG, "onResponse1:     ooooooooorgggggggggggggg    " + response);
                    JSONArray jArray = new JSONArray(response);
                    for(int i = 0; i < jArray.length(); i++) {
                        JSONObject jObj = jArray.getJSONObject(i);
                        orgNamesList.add(new Admin(jObj));

                    }

                    initialize();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        });
    }

    private void testfunc() {
        connectUrl = getString(R.string.ip) + "sendOrgNames.php";
    }

    private void initialize() {
        Log.d(TAG, "onResponse:     ooooooooorgggggggggggggg    " + orgNamesList.toString());
        RecyclerView rv = (RecyclerView)findViewById(R.id.rvOrg);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);
        OrgNamesRVAdapter adapter = new OrgNamesRVAdapter(orgNamesList, this.getIntent().getExtras());
        rv.setAdapter(adapter);

    }
}
