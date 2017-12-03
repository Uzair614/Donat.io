package com.example.uzairkhan.DonationApplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Uzair Khan on 11/26/2017.
 */

public class DonationRequestRVAdapter extends RecyclerView.Adapter<DonationRequestRVAdapter.DonationRequestViewHolder>{

    public static class DonationRequestViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView personName;
        TextView personCNIC;
        TextView personPhone;
        TextView typeOfDonation;
        TextView location;
        TextView other;
        TextView approvedBy;


        DonationRequestViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            personName = (TextView)itemView.findViewById(R.id.person_name);
            personCNIC = (TextView)itemView.findViewById(R.id.person_cnic);
            personPhone = (TextView)itemView.findViewById(R.id.person_phone);
            typeOfDonation = (TextView)itemView.findViewById(R.id.type_of_donation);
            location = (TextView)itemView.findViewById(R.id.location);
            other = (TextView)itemView.findViewById(R.id.other);
            approvedBy = (TextView)itemView.findViewById(R.id.approved_by);
        }


    }

    private String TAG = "DonationRequestRVAdapter";
    ArrayList<DonationRequest> requests;


    DonationRequestRVAdapter(ArrayList<DonationRequest> requests){
        this.requests = requests;
    }

    @Override
    public DonationRequestViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.donationrequestcardview, viewGroup, false);
        return new DonationRequestViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DonationRequestViewHolder holder, int i) {
        User requester = requests.get(i).getRequestOf();
        holder.personName.setText("Name: " + requester.getName());
        holder.personCNIC.setText("CNIC: " + requester.getCNIC());
        holder.personPhone.setText("Phone Number: " + requester.getPhoneNo());
        holder.typeOfDonation.setText("Request Type: " + requests.get(i).getTypeOfDonation());
        holder.location.setText("Location: " + requests.get(i).getLocation());
        holder.approvedBy.setText("Approved By: " + requests.get(i).getApprovedBy());
        if(requests.get(i).getOther().equals("")) {
            holder.other.setVisibility(View.GONE);
        } else {
            holder.other.setText("Details: " + requests.get(i).getOther());
        }
    }

    @Override
    public int getItemCount() {
        return requests.size();

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }




}