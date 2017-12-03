package com.example.uzairkhan.DonationApplication;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Uzair Khan on 10/16/2017.
 */

public class DonationCentre {
    private String name;
    private double latitude;
    private double longitude;
//    private ArrayList<String> centreType;
    private String centreType;

    DonationCentre() {
//        centreType = new ArrayList<>();
    }

    public DonationCentre(JSONObject jObj) {
        try {
            name = jObj.getString("name");
            latitude = jObj.getDouble("latitude");
            longitude = jObj.getDouble("longitude");
            centreType = jObj.getString("centreType");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void setName(String name) {
        this.name = name;
        Log.d("ResponseClass", name);
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        String returnType = new String();
        return name + "\t" + latitude + "\t" + longitude + "\t" + returnType + "\n";
    }

    public String getCentreType() {
        return centreType;
    }

    public void setCentreType(String centreType) {
        this.centreType = centreType;
    }
}
