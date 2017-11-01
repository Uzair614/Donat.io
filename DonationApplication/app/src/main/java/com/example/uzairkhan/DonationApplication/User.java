package com.example.uzairkhan.DonationApplication;

/**
 * Created by Uzair Khan on 11/1/2017.
 */

public class User {

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

}
