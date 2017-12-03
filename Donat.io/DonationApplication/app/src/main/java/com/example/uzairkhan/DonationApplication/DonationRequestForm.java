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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

public class DonationRequestForm extends AppCompatActivity {

    private User mainUser;
    TextView name;
    EditText phone;
    EditText cnic;
    TextView orgName;
    private String connectUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_request_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab1);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        connectUrl = getString(R.string.serverAddress) + "insertDonationRequest.php";

        Spinner spinner = (Spinner) findViewById(R.id.donationRequestTypeSpinner);
        ArrayAdapter<CharSequence> donationRequestAdapter= ArrayAdapter.createFromResource(this, R.array.donation_array,
                android.R.layout.simple_spinner_item);
        donationRequestAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(donationRequestAdapter);

        testingFunc();

        mainUser = (User)getIntent().getSerializableExtra("mainUser");
        name = (TextView)findViewById(R.id.requestName);
        phone = (EditText)findViewById(R.id.requestPhone);
        cnic = (EditText)findViewById(R.id.requestCNIC);
        name.setText(mainUser.getName());
        cnic.setText(mainUser.getCNIC());
        phone.setText(mainUser.getPhoneNo());


        sendDonationRequestForm();


    }

    private void testingFunc() {
        connectUrl = getString(R.string.ip) + "insertDonationRequest.php";
    }


    private void sendDonationRequestForm() {
        TextView send = (TextView) findViewById(R.id.sendRequestFormButton);
        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final String CNIC = cnic.getText().toString();
                final String Phone = phone.getText().toString();
                Spinner donationRequestSpinner=(Spinner) findViewById(R.id.donationRequestTypeSpinner);
                EditText address = (EditText) findViewById(R.id.addressText);
                EditText otherText = (EditText) findViewById(R.id.otherText);

                if(!Pattern.matches("^(\\d{11})$|^(\\d{4}(\\s|\\-)\\d{7})$|^(\\+92\\d{10})$", Phone)) {
                    Toast.makeText(getApplicationContext(), "Invalid Phone Number", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!Pattern.matches("^(\\d{13})|(\\d{5}(\\s|\\-)\\d{7}(\\s|\\-)\\d)$", CNIC)) {
                    Toast.makeText(getApplicationContext(), "Invalid CNIC", Toast.LENGTH_SHORT).show();
                    return;
                }

                JSONObject param1 = new JSONObject();
                try {
                    param1.put("email", mainUser.getEmail());
                    param1.put("phone", Phone);
                    param1.put("cnic", CNIC);
                    param1.put("dType", donationRequestSpinner.getSelectedItem().toString());
                    param1.put("address", address.getText().toString());
                    param1.put("other", otherText.getText().toString());

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                NetworkController.getInstance().PostToServer(param1.toString(), connectUrl, new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), "Request Sent", Toast.LENGTH_SHORT).show();
                    }

                });


//                DonationRequest toSend = new DonationRequest(   ,
//                                                                donationRequestSpinner.getSelectedItem().toString(),
//                                                                address.getText().toString(),
//                                                                otherText.getText().toString()
//                                                                );
//                String json = new Gson().toJson(toSend);
//                NetworkController.getInstance().PostToServer(json, connectUrl, new Response.Listener<String>()
//                {
//                    @Override
//                    public void onResponse(String result)
//                    {
//                        if (!result.isEmpty())
//                        {
//                            Toast.makeText(getApplicationContext(), "Donation Request Sent", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });

            }
        });
}
}
