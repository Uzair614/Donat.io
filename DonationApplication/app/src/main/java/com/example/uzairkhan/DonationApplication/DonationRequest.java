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
    private boolean approvalStatus = false;
    //private list
    //willl be gotten from JSON

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
}
