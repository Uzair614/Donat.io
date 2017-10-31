package com.example.uzairkhan.DonationApplication;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Uzair Khan on 10/16/2017.
 */

public class DonationCentre {
    String name;
    private double latitude;
    private double longitude;
    private ArrayList<String> arr;

    DonationCentre() {
        arr = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
        Log.d("ResponseClass", name);
    }

    public void setLatlng(LatLng ltlg) {
        latitude = ltlg.latitude;
        longitude = ltlg.longitude;
    }

    public void addType(String s) {
        arr.add(s);
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

    public ArrayList<String> getArr() {
        return arr;
    }

    public void clearArr() {
        arr.clear();
    }

}
