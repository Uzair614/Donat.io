//package com.example.uzairkhan.DonationApplication;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.TextView;
//
//import java.util.List;
//
///**
// * Created by Uzair Khan on 10/5/2017.
// */
//
//public class DonationRequestAdapter extends ArrayAdapter<DonationRequest> {
//
//
//    public DonationRequestAdapter(@NonNull Context context, @NonNull List<DonationRequest> objects) {
//        super(context, 0, objects);
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//
//        View listItemView = convertView;
//        if(listItemView == null) {
//            listItemView = LayoutInflater.from((getContext())).inflate(R.layout.list_item,parent,false);
//        }
//
//        DonationRequest currentRequest = getItem(position);
//
//        TextView nameTextView = (TextView) listItemView.findViewById(R.id.requesterName);
//        nameTextView.setText(currentRequest.getRequestOf().getName());
//
////        TextView typeofDonationView = (TextView) listItemView.findViewById(R.id.requesterType);
////        typeofDonationView.setText(currentRequest.getTypeOfDonation());
////
////        TextView addressView = (TextView) listItemView.findViewById(R.id.requesterAddress);
////        addressView.setText(currentRequest.getLocation());
//
//        return listItemView;
//    }
//}
