//package com.example.uzairkhan.DonationApplication;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
///**
// * Created by Uzair Khan on 11/27/2017.
// */
//
//public class CentreRVAdapter extends RecyclerView.Adapter<CentreRVAdapter.CentreRVHolder> {
//    ArrayList<String> names;
//
//
//    public static class CentreRVHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        TextView orgName;
//
//        CentreRVHolder(View itemView) {
//            super(itemView);
//            orgName = (TextView) itemView.findViewById(R.id.orgName);
//            itemView.setOnClickListener(this);
//        }
//
//        @Override
//        public void onClick(View v) {
//
//        }
//    }
//
//    public CentreRVAdapter(ArrayList<String> names) {
//        this.names = names;
//    }
//
//    //INITIALIE VH
//    @Override
//    public CentreRVHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.model,parent,false);
//        CentreRVHolder holder=new CentreRVHolder(v);
//        return holder;
//    }
//
//    //BIND DATA
//    @Override
//    public void onBindViewHolder(CentreRVHolder holder, int position) {
//        holder.nameTxt.setText(tvshows[position]);
//    }
//
//    @Override
//    public int getItemCount() {
//        return names.length;
//    }
//
//
//    }
