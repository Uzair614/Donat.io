package com.example.uzairkhan.DonationApplication;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Uzair Khan on 11/27/2017.
 */

public class NotificationRVAdapter extends RecyclerView.Adapter<NotificationRVAdapter.NotificationViewHolder> {



    public static class NotificationViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView message;
        TextView audience;
        TextView orgName;

        NotificationViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cvNotification);
            message = (TextView)itemView.findViewById(R.id.notificationMessage);
            audience = (TextView)itemView.findViewById(R.id.notificationAudience);
            orgName = (TextView)itemView.findViewById(R.id.notificationOrg);
        }

    }

    private String TAG = "NotificationRVAdapter";
    ArrayList<Notification> notifications;

    NotificationRVAdapter(ArrayList<Notification> notifications){
        this.notifications= notifications;
    }

    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.notificationcardview, parent, false);
        return new NotificationRVAdapter.NotificationViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NotificationViewHolder holder, int position) {

        holder.message.setText("Message: " + notifications.get(position).getMessage());
        holder.audience.setText("To: " + notifications.get(position).getAudience());
        holder.orgName.setText("From: " + notifications.get(position).getorgName());
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
