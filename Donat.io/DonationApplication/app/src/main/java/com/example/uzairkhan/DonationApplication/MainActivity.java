package com.example.uzairkhan.DonationApplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    Integer i = 0;
    private String TAG = "MainActivity";
    private User mainUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainUser = (User)getIntent().getSerializableExtra("mainUser");
        if (mainUser != null)
            Log.d(TAG, "onCreate: MainUserName : " + mainUser.getName() + "\nemail : " + mainUser.getEmail());



        TextView selectDonationView = (TextView) findViewById(R.id.donateButton);
        selectDonationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent donationIntent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(donationIntent);
            }
        });

        TextView becomeVolunteerView = (TextView) findViewById(R.id.volunteerButtonMain);
        becomeVolunteerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent becomeVolunteerIntent = new Intent(MainActivity.this, VolunteerForm.class);
                becomeVolunteerIntent.putExtra("mainUser", (Serializable) mainUser);
                becomeVolunteerIntent.putExtra("Intent", "Main");
                startActivity(becomeVolunteerIntent);
            }
        });

        TextView donationRequestFormView = (TextView)findViewById(R.id.requestDonationButton);
        donationRequestFormView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent donationRequestFormIntent = new Intent(MainActivity.this, DonationRequestForm.class);
                donationRequestFormIntent.putExtra("mainUser", (Serializable) mainUser);
                startActivity(donationRequestFormIntent);
            }
        });

        TextView viewDonationRequestsView = (TextView)findViewById(R.id.viewRequestButton);
        viewDonationRequestsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewDonationRequestIntent = new Intent(MainActivity.this, ViewDonationRequests.class);
                startActivity(viewDonationRequestIntent);
            }
        });

        TextView adminButtonView = (TextView)findViewById(R.id.adminButton);
        adminButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent adminButtonViewIntent = new Intent(MainActivity.this, AdminViewActivity.class);
                adminButtonViewIntent.putExtra("mainUser", (Serializable) mainUser);
                startActivity(adminButtonViewIntent);
            }
        });

        TextView notificationButton = (TextView)findViewById(R.id.notificationButton);
        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NotificationsActivity.class);
                i.putExtra("mainUser", (Serializable) mainUser);
                startActivity(i);
            }
        });

        update();
    }

    private void update() {
        String connectUrl = getString(R.string.serverAddress) + "getProfile.php";
        connectUrl = getString(R.string.ip) + "getProfile.php";

        Log.d("Notificatityemail:  ", mainUser.getEmail());
        NetworkController.getInstance().GetFromServer(mainUser.getEmail(), connectUrl, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mainUser = new Gson().fromJson(response, User.class);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putParcelable("mainUser", mainUser);
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);
        mainUser = savedInstanceState.getParcelable("mainUser");
        update();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: " + i);
        update();
        super.onResume();
    }
}
