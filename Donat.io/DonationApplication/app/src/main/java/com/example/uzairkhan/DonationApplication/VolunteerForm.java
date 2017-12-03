package com.example.uzairkhan.DonationApplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

public class VolunteerForm extends AppCompatActivity {

    private User mainUser;
    private Admin org;
    TextView name;
    EditText phone;
    EditText cnic;
    TextView orgName;
    private boolean centreSelected = false;
    String TAG = "VolunteerForm";
    private String connectUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        connectUrl = getString(R.string.serverAddress) + "insertVolunteer.php";

        testfunc();
        name = (TextView)findViewById(R.id.volunteerName);
        phone = (EditText)findViewById(R.id.volunteerPhone);
        cnic = (EditText)findViewById(R.id.volunteerCNIC);
        orgName = (TextView)findViewById(R.id.orgName);
        if(savedInstanceState == null) { //new activity
            Bundle b = this.getIntent().getExtras();
//            for (String key : b.keySet())
//            {
//                Log.d("Bundle Debug", key + " = \"" + b.get(key) + "\"");
//            }
            if(getIntent().getStringExtra("Intent").equals("Main")) { //from main
                mainUser = (User)getIntent().getSerializableExtra("mainUser");
                name.setText(mainUser.getName());
                if(mainUser.getPhoneNo() != null)
                    phone.setText(mainUser.getPhoneNo());

                if(mainUser.getCNIC() != null)
                    cnic.setText(mainUser.getCNIC());
            } else { //from selection

                String[] array=b.getStringArray("params");
                mainUser = (User)b.getSerializable("mainUser");
                org = (Admin)b.getSerializable("org");
                name.setText(mainUser.getName());
                cnic.setText(array[0]);
                phone.setText(array[1]);
                orgName.setText(org.getOrgName());
                centreSelected = true;
            }
        }



    }

    private void testfunc() {
        connectUrl = getString(R.string.ip) + "insertVolunteer.php";
    }

    public void sendRequest(View view) {

        String CNIC = cnic.getText().toString();
        String Phone = phone.getText().toString();

        if(!Pattern.matches("^(\\d{11})$|^(\\d{4}(\\s|\\-)\\d{7})$|^(\\+92\\d{10})$", Phone)) {
            Toast.makeText(getApplicationContext(), "Invalid Phone Number", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!Pattern.matches("^(\\d{13})|(\\d{5}(\\s|\\-)\\d{7}(\\s|\\-)\\d)$", CNIC)) {
            Toast.makeText(getApplicationContext(), "Invalid CNIC", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!centreSelected)
            return;

        JSONObject param1 = new JSONObject();
        try {
            param1.put("email", mainUser.getEmail());
            param1.put("phone", Phone);
            param1.put("cnic", CNIC);
            param1.put("org", org.getAdminID());
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

    }

    public void viewCentres(View view) {
        Intent i = new Intent(this, SelectOrgActivity.class);
        Bundle b = new Bundle();
        b.putStringArray("params", new String[]{cnic.getEditableText().toString(), phone.getEditableText().toString()});
        b.putSerializable("mainUser", mainUser);
        if(!orgName.getText().toString().equals(""))
            b.putString("org", orgName.getText().toString());
        i.putExtras(b);

        for (String key : b.keySet())
        {
            Log.d("Bundle Debug1111", key + " = \"" + b.get(key) + "\"");
        }

        startActivity(i);
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        Log.d("shamalamadingdong", mainUser.getName());
        savedInstanceState.putParcelable("mainUser", mainUser);
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);
        mainUser = savedInstanceState.getParcelable("mainUser");

    }
}
