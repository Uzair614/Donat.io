package com.example.uzairkhan.DonationApplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class AdminViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Button button1 = (Button) findViewById(R.id.MakeadminButton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Button button2 = (Button) findViewById(R.id.AdminApproveDonationButton);
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminViewActivity.this, ViewDonationRequests.class);
                startActivity(i);
            }
        });

        Button button3 = (Button) findViewById(R.id.AdminIssuenotificationButton);
        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminViewActivity.this, DonationRequestForm.class);
                startActivity(i);
            }
        });

        Button button4 = (Button) findViewById(R.id.AdminAddCentreButton);
        button4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminViewActivity.this, AddCentreActivity.class);
                startActivity(i);
            }
        });
    }

}
