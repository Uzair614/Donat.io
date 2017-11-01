package com.example.uzairkhan.DonationApplication;

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
    //willl be gotten from JSON

    public DonationRequest(User requester, String type, String location, String other) {
        requestOf = requester;
        this.location = location;
        typeOfDonation = type;
        this.other = other;
    }

    public User getRequestOf() {
        return requestOf;
    }
}
