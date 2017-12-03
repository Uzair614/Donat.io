package com.example.uzairkhan.DonationApplication;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Uzair Khan on 11/1/2017.
 */
/*
public class User implements Serializable {

    protected String name;
    protected String email;
    protected String CNIC;
    protected String phoneNo;


    public User() {

    }

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

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

}
*/
public class User implements Serializable, Parcelable {

    protected String name;
    protected String email;
    protected String CNIC;
    protected String phoneNo;


    public User() {

    }

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

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }


    protected User(Parcel in) {
        name = in.readString();
        email = in.readString();
        CNIC = in.readString();
        phoneNo = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(CNIC);
        dest.writeString(phoneNo);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}