package com.example.uzairkhan.DonationApplication;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Uzair Khan on 10/1/2017.
 */

public class DonationRequest {
//    private String requesterName;
//    private String CNIC;
//    private String phoneNo;
    private User requestOf;
    private String typeOfDonation;
    private String location;
    private String other;
    private boolean approvalStatus = false;
    private String approvedBy;
    //private list
    //willl be gotten from JSON

    public DonationRequest() {
        requestOf = new User();
    }

    public DonationRequest(JSONObject jObj) {
        requestOf = new User();
        try {
            requestOf.setName(jObj.getString("name"));
            requestOf.setCNIC(jObj.getString("phone"));
            requestOf.setPhoneNo(jObj.getString("CNIC"));
            typeOfDonation = jObj.getString("dType");
            location = jObj.getString("address");
            other = jObj.getString("other");
            approvedBy = jObj.getString("orgName");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public DonationRequest(User requester, String type, String location, String other) {
        requestOf = requester;
        this.location = location;
        typeOfDonation = type;
        this.other = other;
    }

    //used when getting requests back from server
    public DonationRequest(User requestOf, String typeOfDonation, String location, String other, boolean approvalStatus) {
        this.requestOf = requestOf;
        this.typeOfDonation = typeOfDonation;
        this.location = location;
        this.other = other;
        this.approvalStatus = approvalStatus;
    }

    public User getRequestOf() {
        return requestOf;
    }

    public String getTypeOfDonation() {
        return typeOfDonation;
    }

    public String getLocation() {
        return location;
    }

    public String getOther() {
        return other;
    }

    public boolean isApprovalStatus() {
        return approvalStatus;
    }

    public void setRequestOf(User requestOf) {
        this.requestOf = requestOf;
    }

    public void setTypeOfDonation(String typeOfDonation) {
        this.typeOfDonation = typeOfDonation;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public void setApprovalStatus(boolean approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getApprovedBy() {
        return approvedBy;
    }
}
