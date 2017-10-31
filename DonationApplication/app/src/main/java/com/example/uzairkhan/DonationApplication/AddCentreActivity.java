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
import android.widget.CheckBox;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class AddCentreActivity extends AppCompatActivity {

    int PLACE_PICKER_REQUEST = 1;
    int numChecks = 0;
    DonationCentre sendObj = new DonationCentre();
    private String connectUrl = "https://donationapptest.000webhostapp.com/insertLocation.php";
    //URL being changed in test function
    RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_centre);
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

        placePicker();

        testFunction();

        sendServerRequest();

    }

    public void onCheckboxClickedCentre(View view) {
        boolean checked = ((CheckBox)view).isChecked();
        if(checked)
            numChecks++;
        else
            numChecks--;

        if(numChecks > 0) {
            findViewById(R.id.sendButton).setEnabled(true);
        }
        else {
            findViewById(R.id.sendButton).setEnabled(false);
        }
    }

    private void placePicker() {
        Button getPlace = (Button) findViewById(R.id.placePickerButton);
        getPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                Intent i;
                try {
                    i = builder.build(AddCentreActivity.this);
                    startActivityForResult(i, PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);

//                String toastMsg = String.format("Place: %s", place.getName());
//                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
                sendObj.setName(String.format("%s", place.getName()));
                sendObj.setLatlng(place.getLatLng());
                Log.d("ResponseLocation", sendObj.getName());
            }
        }
    }

    private void sendServerRequest() {
        Button send = (Button) findViewById(R.id.sendButton);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queue = Volley.newRequestQueue(getApplicationContext());
                StringRequest request = new StringRequest(Request.Method.POST,
                        connectUrl,
                        createMyReqSuccessListener(),
                        createMyReqErrorListener()) {

                    protected Map<String, String> getParams() throws AuthFailureError {
                        addCentreTypes();
                        String json = new Gson().toJson(sendObj);
                        Map<String, String> parameters = new HashMap<>();
                        parameters.put("Centre", json);
                        return parameters;
                    };
                };
                queue.add(request);
            }
        });

    }

    private void addCentreTypes() {
        sendObj.clearArr();
        if (((CheckBox)findViewById(R.id.checkBloodCentre)).isChecked())
            sendObj.addType("Blood");
        if (((CheckBox)findViewById(R.id.checkBooksCentre)).isChecked())
            sendObj.addType("Books");
        if (((CheckBox)findViewById(R.id.checkFoodCentre)).isChecked())
            sendObj.addType("Food");
        if (((CheckBox)findViewById(R.id.checkMoneyCentre)).isChecked())
            sendObj.addType("Money");
        if (((CheckBox)findViewById(R.id.checkClothesCentre)).isChecked())
            sendObj.addType("Clothes");
    }

    private Response.Listener<String> createMyReqSuccessListener() {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response", "Success\t" + response);
                Toast.makeText(getApplicationContext(), "Location Added Successfully", Toast.LENGTH_SHORT).show();

            }
        };
    }

    private Response.ErrorListener createMyReqErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error Adding Location", Toast.LENGTH_SHORT).show();
                Log.d("Response", error.toString());
            }
        };
    }

    private void testFunction () {
        connectUrl = getString(R.string.ip) + "insertLocation.php";
        sendObj.setName("Hellh");
        sendObj.setLatlng(new LatLng(24.98197, 54.745674));
    }

}
