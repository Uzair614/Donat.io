package com.example.uzairkhan.DonationApplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

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
                i += 3;
            }
        });

        TextView becomeVolunteerView = (TextView) findViewById(R.id.volunteerButtonMain);
        becomeVolunteerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent becomeVolunteerIntent = new Intent(MainActivity.this, VolunteerForm.class);
                startActivity(becomeVolunteerIntent);
                i += 40;
            }
        });

        TextView donationRequestFormView = (TextView)findViewById(R.id.requestDonationButton);
        donationRequestFormView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent donationRequestFormIntent = new Intent(MainActivity.this, DonationRequestForm.class);
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
                startActivity(adminButtonViewIntent);
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
        // Save the user's current game state
//        savedInstanceState.putInt(STATE_SCORE, mCurrentScore);
//        savedInstanceState.putInt(STATE_LEVEL, mCurrentLevel);
//        savedInstanceState;

//        savedInstanceState.put

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }


    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: " + i);
        super.onResume();
    }
}
