package com.example.uzairkhan.DonationApplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

public class AdminViewActivity extends AppCompatActivity {

    private User mainUser;
    TextView name;
    EditText phone;
    EditText cnic;
    TextView orgName;
    private String connectUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        connectUrl = getString(R.string.serverAddress) + "profile.php";

        testfunc();
        mainUser = (User)getIntent().getSerializableExtra("mainUser");
        name = (TextView)findViewById(R.id.profileName);
        phone = (EditText)findViewById(R.id.profilePhone);
        cnic = (EditText)findViewById(R.id.profileCNIC);
        orgName = (TextView)findViewById(R.id.profileOrgName);
        orgName.setVisibility(View.GONE);
        name.setText(mainUser.getName());
        cnic.setText(mainUser.getCNIC());
        phone.setText(mainUser.getPhoneNo());


//        Log.d("Profile", "phone is1   " + phone.getEditableText().toString());
//        Log.d("Profile", "phone is2   " + mainUser.getPhoneNo());
    }

    private void testfunc() {
        connectUrl = getString(R.string.ip) + "profile.php";
    }

    public void sendRequest(View view) {

        final String CNIC = cnic.getText().toString();
        final String Phone = phone.getText().toString();

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
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        NetworkController.getInstance().PostToServer(param1.toString(), connectUrl, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            Toast.makeText(getApplicationContext(), "Profile Updated", Toast.LENGTH_SHORT).show();
            mainUser.setPhoneNo(Phone);
            mainUser.setCNIC(CNIC);
            }

        });

    }
}
