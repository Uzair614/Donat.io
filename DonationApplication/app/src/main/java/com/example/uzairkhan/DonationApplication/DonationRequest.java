package com.example.uzairkhan.DonationApplication;

/**
 * Created by Uzair Khan on 10/1/2017.
 */

public class DonationRequest {
    private String requesterName;
    private String CNIC;
    private String phoneNo;
    private String typeOfDonation;
    private String location;
    private String other;
    //willl be gotten from JSON

    public DonationRequest(String rName, String CNIC, String phone, String type, String location, String other) {
        requesterName = rName;
        this.CNIC = CNIC;
        this.phoneNo = phone;
        this.location = location;
        typeOfDonation = type;
        this.other = other;
    }

    public String getRequesterName() {
        return requesterName;
    }

    public String getTypeOfDonation() {
        return typeOfDonation;
    }

    public String getLocation() {
        return location;
    }
}
