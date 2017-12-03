package com.example.uzairkhan.DonationApplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Uzair Khan on 11/27/2017.
 */

public class OrgNamesRVAdapter extends RecyclerView.Adapter<OrgNamesRVAdapter.OrgNamesViewHolder> {

    public static class OrgNamesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CardView cv;
        TextView orgName;
        private int adminID;
        private final Context context;


        OrgNamesViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            cv = (CardView)itemView.findViewById(R.id.orgcv);
            orgName = (TextView)itemView.findViewById(R.id.org_name);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d("orgName sending:   ", orgName.getText().toString());
            Intent i = new Intent(context, VolunteerForm.class);
            b.putSerializable("org", orgNames.get(getAdapterPosition()));
//            b.putString("org", orgNames.get(getAdapterPosition()).getOrgName());
//            b.putInt("id", orgNames.get(getAdapterPosition()).getAdminID());
            i.putExtra("Intent", "Form");
            i.putExtras(b);
            context.startActivity(i);
        }
    }

    private String TAG = "OrgNamesRVAdapter";
    static ArrayList<Admin> orgNames;
    static Bundle b;

    OrgNamesRVAdapter(ArrayList<Admin> orgNames, Bundle b)
    {
        this.orgNames = orgNames;
        this.b = b;
    }

    @Override
    public OrgNamesViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.orgnamescardview, viewGroup, false);
        return new OrgNamesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(OrgNamesViewHolder holder, int i) {
        holder.orgName.setText("Organization: " + orgNames.get(i).getOrgName());
    }

    @Override
    public int getItemCount() {
        return orgNames.size();

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
