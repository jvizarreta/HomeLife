package com.proyectofinal.homelife.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public final class ApiServiceSingleton {
    private static ApiServiceSingleton singleton;
    private RequestQueue requestQueue;
    private static Context context;

    private ApiServiceSingleton(Context context) {
        ApiServiceSingleton.context = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized ApiServiceSingleton getInstance(Context context) {
        if (singleton == null) {
            singleton = new ApiServiceSingleton(context);
        }
        return singleton;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context);
        }
        return requestQueue;
    }

    public void addToRequestQueue(Request request) {
        getRequestQueue().add(request);
    }
}
