package com.example.uzairkhan.DonationApplication;

import android.content.Context;
import android.util.Log;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Uzair Khan on 10/31/2017.
 */

public class NetworkController{


    private static final String TAG = "NetworkController";
    private static NetworkController instance = null;

    //for Volley API
    public RequestQueue requestQueue;

    private NetworkController(Context context)
    {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());

    }

    public static synchronized NetworkController getInstance(Context context)
    {
        if (null == instance) {
            instance = new NetworkController(context);
            Log.d(TAG + ": ", "Singleton Created");
        }
        return instance;
    }

    //this is so you don't need to pass context each time
    public static synchronized NetworkController getInstance()
    {
        if (null == instance)
        {
            throw new IllegalStateException(NetworkController.class.getSimpleName() +
                    " is not initialized, call getInstance(...) first");
        }
        return instance;
    }

    public void GetFromServer(String param1, String url, final Response.Listener<String> listener)
    {
        String connectUrl = url + String.format("?param1=%1$s", param1);

        StringRequest request = new StringRequest(Request.Method.GET,
                connectUrl,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        Log.d(TAG + ": ", "Successful Response : " + response);
                        if(null != response.toString())
                            listener.onResponse(response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        if (null != error.networkResponse)
                        {
                            Log.d(TAG + ": ", "Error Response code: " + error.networkResponse.statusCode);
                        }
                    }
                });
        requestQueue.add(request);
        }

    public void PostToServer(final String param1, String connectUrl, final Response.Listener<String> listener)
    {
        StringRequest request = new StringRequest(Request.Method.POST,
                connectUrl,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        Log.d(TAG + ": ", "Successful Response : " + response);
                        if(null != response.toString())
                            listener.onResponse(response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        if (null != error.networkResponse)
                        {
                            Log.d(TAG + ": ", "Error Response code: " + error.networkResponse.statusCode);
                        }
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parameters = new HashMap<>();
                parameters.put("param1", param1);
                return parameters;
            }
        };
        requestQueue.add(request);
    }
}
/*
        Map<String, Object> jsonParams = new HashMap<>();
        jsonParams.put("param1", param1);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(jsonParams),
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        Log.d(TAG + ": ", "somePostRequest Response : " + response.toString());
                        if(null != response.toString())
                            listener.getResult(response.toString());
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        if (null != error.networkResponse)
                        {
                            Log.d(TAG + ": ", "Error Response code: " + error.networkResponse.statusCode);
                            listener.getResult(false);
                        }
                    }
                });

        requestQueue.add(request);

    }

}
*/

