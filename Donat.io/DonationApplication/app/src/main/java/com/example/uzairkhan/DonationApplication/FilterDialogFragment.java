package com.example.uzairkhan.DonationApplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Uzair Khan on 11/4/2017.
 */


public class FilterDialogFragment extends DialogFragment {

    ArrayList<String> mSelectedItems;  // Where we track the selected items
    String[] donation_types;
    String TAG = "FilterDialogFragment";

    static FilterDialogFragment newInstance(ArrayList<String> s) {
        FilterDialogFragment f = new FilterDialogFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putStringArrayList("selectedItems", s);
        f.setArguments(args);

        return f;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mSelectedItems = new ArrayList();
        donation_types = getResources().getStringArray(R.array.donation_array);

        mSelectedItems = getArguments().getStringArrayList("selectedItems");
        final boolean[] checkedItems = new boolean[5];
        for (int i = 0; i < donation_types.length; i++) {
            if (mSelectedItems.contains(donation_types[i]))
                checkedItems[i] = true;
            else
                checkedItems[i] = false;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Set the dialog title
        builder.setTitle("FILTERS")
                // Specify the list array, the items to be selected by default (null for none),
                // and the listener through which to receive callbacks when items are selected
                .setMultiChoiceItems(donation_types, checkedItems,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which,
                                                boolean isChecked) {
                                if (isChecked) {
                                    // If the user checked the item, add it to the selected items
//                                    checkedItems[i] = !checkedItems[which];
                                    mSelectedItems.add(donation_types[which]);
                                } else if (mSelectedItems.contains(donation_types[which])) {
                                    // Else, if the item is already in the array, remove it
                                    mSelectedItems.remove(donation_types[which]);
                                }
                            }
                        })
                // Set the action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK, so save the mSelectedItems results somewhere
                        // or return them to the component that opened the dialog
                        mListener.onDialogPositiveClick(mSelectedItems);
                    }
                });

        return builder.create();
    }

    public interface FilterDialogListener {
        public void onDialogPositiveClick(ArrayList<String> selectedItems);
    }

    // Use this instance of the interface to deliver action events
    FilterDialogListener mListener;

    // Override the Fragment.onAttach() method to instantiate the FilterDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the FilterDialogListener so we can send events to the host
            mListener = (FilterDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement FilterDialogListener");
        }
    }
}
