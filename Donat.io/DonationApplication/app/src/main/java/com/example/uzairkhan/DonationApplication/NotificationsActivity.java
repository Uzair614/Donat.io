package com.example.uzairkhan.DonationApplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NotificationsActivity extends AppCompatActivity {

    private String TAG = "ViewDonationRequests";
    private ArrayList<Notification> NotificationsArrayList;
    private String connectUrl;
    private User mainUser;
//    private String connectUrl = getString(R.string.serverAddress) + "sendNotifications.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        testfunc();
        mainUser = (User)getIntent().getSerializableExtra("mainUser");
        Log.d("Notificatityemail:  ", mainUser.getEmail());
        Log.d("url:  ", connectUrl);

        NotificationsArrayList = new ArrayList<>();
        NetworkController.getInstance().GetFromServer(mainUser.getEmail(), connectUrl, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jArray = new JSONArray(response);
                    Log.d(TAG, "onResponse123123:   " + response);
                    for(int i = 0; i < jArray.length(); i++) {
                        JSONObject jObj = jArray.getJSONObject(i);
                        NotificationsArrayList.add(new Notification(jObj));
                    }
                    initialize();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        });
    }

    private void testfunc() {
        connectUrl = getString(R.string.ip) + "sendNotifications.php";
    }

    private void initialize() {
        RecyclerView rv = (RecyclerView)findViewById(R.id.rvNotifications);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);
        NotificationRVAdapter adapter = new NotificationRVAdapter(NotificationsArrayList);
        rv.setAdapter(adapter);
        Log.d(TAG, "onResponse:     ffffffffffffffaaaaasd     " + NotificationsArrayList.toString());
    }

}
