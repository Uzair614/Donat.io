package com.example.uzairkhan.DonationApplication;

import java.util.List;

/**
 * Created by Uzair Khan on 11/1/2017.
 */

public class Volunteer extends User {

    private boolean isVolunteer;
    private List<DonationCentre> volunteerOf;
    public Volunteer(String name, String email, String CNIC, String phoneNo, boolean isVolunteer, List<DonationCentre> volunteerOf) {
        super(name, email, CNIC, phoneNo);
        this.isVolunteer = isVolunteer;
        this.volunteerOf = volunteerOf;
    }

}
