package com.example.uzairkhan.DonationApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Uzair Khan on 11/28/2017.
 */

public class Admin implements Serializable{
    private int adminID;
    private String orgName;

    public Admin(int adminID, String orgName) {
        this.adminID = adminID;
        this.orgName = orgName;
    }

    public Admin(JSONObject jObj) {
        try {
            this.adminID = jObj.getInt("adminID");
            this.orgName = jObj.getString("orgName");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
