//package com.example.uzairkhan.DonationApplication;
//
//import android.app.Dialog;
//import android.app.DialogFragment;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import java.util.ArrayList;
//
///**
// * Created by Uzair Khan on 11/27/2017.
// */
//
//
//public class CentreFragment extends DialogFragment {
//
//    String[] tvshows={"Crisis","Blindspot","BlackList","Game of Thrones","Gotham","Banshee"};
//    ArrayList<String> orgs;
//    RecyclerView rv;
//    CentreRVAdapter adapter;
//
//    static FilterDialogFragment newInstance(ArrayList<String> s) {
//        FilterDialogFragment f = new FilterDialogFragment();
//
//        // Supply num input as an argument.
//        Bundle args = new Bundle();
//        args.putStringArrayList("organizations", s);
//        f.setArguments(args);
//
//        return f;
//    }
//
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        orgs = new ArrayList();
//        orgs = getArguments().getStringArrayList("organizations");
//
//        return super.onCreateDialog(savedInstanceState);
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//        View rootView=inflater.inflate(R.layout.fraglayout,container);
//
//        //RECYCER
//        rv= (RecyclerView) rootView.findViewById(R.id.mRecyerID);
//        rv.setLayoutManager(new LinearLayoutManager(this.getActivity()));
//
//        //ADAPTER
//        adapter=new CentreRVAdapter(tvshows);
//        rv.setAdapter(adapter);
//
//        this.getDialog().setTitle("Organizations");
//
//
//        return rootView;
//    }
//}
