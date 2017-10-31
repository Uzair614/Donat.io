package com.example.uzairkhan.DonationApplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.google.gson.Gson;

public class DonationRequestForm extends AppCompatActivity {

    private String connectUrl = "https://donationapptest.000webhostapp.com/createDonationRequest.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_request_form);
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

        Spinner spinner = (Spinner) findViewById(R.id.donationRequestTypeSpinner);
        ArrayAdapter<CharSequence> donationRequestAdapter= ArrayAdapter.createFromResource(this, R.array.donation_array,
                android.R.layout.simple_spinner_item);
        donationRequestAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(donationRequestAdapter);

        testingFunc();
        sendDonationRequestForm();
    }

    private void testingFunc() {
        connectUrl = getString(R.string.ip) + "createDonationRequest.php";
    }


    private void sendDonationRequestForm() {
        TextView send = (TextView) findViewById(R.id.sendRequestFormButton);
        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //get name phone and cnic from sqlite stored data
                Spinner donationRequestSpinner=(Spinner) findViewById(R.id.donationRequestTypeSpinner);
                EditText address = (EditText) findViewById(R.id.addressText);
                EditText otherText = (EditText) findViewById(R.id.otherText);
                DonationRequest toSend = new DonationRequest(   getString(R.string.nameDebug),
                                                                getString(R.string.CNICDebug),
                                                                getString(R.string.phoneDebug),
                                                                donationRequestSpinner.getSelectedItem().toString(),
                                                                address.getText().toString(),
                                                                otherText.getText().toString()
                                                                );
                String json = new Gson().toJson(toSend);
                NetworkController.getInstance().PostToServer(json, connectUrl, new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String result)
                    {
                        if (!result.isEmpty())
                        {
                            Toast.makeText(getApplicationContext(), "Donation Request Sent", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
}
}
