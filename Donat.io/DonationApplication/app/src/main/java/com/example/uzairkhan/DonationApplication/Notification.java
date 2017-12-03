package com.example.uzairkhan.DonationApplication;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Uzair Khan on 11/27/2017.
 */

public class Notification {
    private String Message;
    private String Audience;
    private String orgName;

    public Notification(String message, String audience) {
        Message = message;
        Audience = audience;
    }

    public Notification(JSONObject jObj) {

        try {
            Message = jObj.getString("message");
            orgName = jObj.getString("orgName");
            if(jObj.getInt("Audience") == 1){
                Audience = "All Users";
            } else {
                Audience = "Volunteers";
            }
            Log.d("NotificationConstructor", Message);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getAudience() {
        return Audience;
    }

    public void setAudience(String audience) {
        Audience = audience;
    }

    public String getorgName() {
        return orgName;
    }

    public void setorgName(String orgName) {
        this.orgName = orgName;
    }
}
