package com.example.uzairkhan.DonationApplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewDonationRequests extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_donation_requests);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

/*
        ArrayList<DonationRequest> donationList = new ArrayList<>();
        donationList.add(new DonationRequest("Uzair", "Home", "Money"));
        donationList.add(new DonationRequest("Uzair1", "Home2", "Money"));
        donationList.add(new DonationRequest("Uzair2", "Home3", "Money"));

        DonationRequestAdapter requestAdapter = new DonationRequestAdapter(this, donationList);

        ListView listView = (ListView)findViewById(R.id.listView_donation);
        listView.setAdapter(requestAdapter);
*/
    }

}
