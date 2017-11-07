package com.example.uzairkhan.DonationApplication;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Uzair Khan on 11/1/2017.
 */

public class User implements Serializable {

    protected String name;
    protected String email;
    protected String CNIC;
    protected String phoneNo;

    public User(String name, String email, String CNIC, String phoneNo) {

        this.name = name;
        this.email = email;
        this.CNIC = CNIC;
        this.phoneNo = phoneNo;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCNIC() {
        return CNIC;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public static boolean isVolunteer(String json) {
        try {
            JSONObject obj = new JSONObject(json);
//            if (true)
                return true;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

}
