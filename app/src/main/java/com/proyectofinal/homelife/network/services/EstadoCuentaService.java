package com.proyectofinal.homelife.network.services;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.proyectofinal.homelife.EstadoCuentaActivity;
import com.proyectofinal.homelife.MainActivity;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class EstadoCuentaService extends Base{
    private EstadoCuentaActivity activity;

    public EstadoCuentaService(EstadoCuentaActivity activity) {
        super(activity.getApplicationContext());
        this.activity = (EstadoCuentaActivity) activity;
    }

    public void getMovimientos () {
        final String token = mHomeLifePreference.getToken();

        client.addToRequestQueue(new JsonArrayRequest(Request.Method.GET, URL_MOVIMIENTOS, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.i("HomeLife", response.toString());
                activity.setMovimientos(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse =  error.networkResponse;
                Log.i("HomeLife", networkResponse.toString());
                if (networkResponse.statusCode == 401) {
                    logout();
                } else {
                    Toast.makeText(activity, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json; charset=UTF-8");
                params.put("Authorization", "JWT "+ token);
                return params;
            }
        });
    }


    public void logout(){
        Toast.makeText(activity, "Sesión expirada.", Toast.LENGTH_SHORT).show();
        mHomeLifePreference.clear();
        activity.startActivity(new Intent(activity.getApplicationContext(), MainActivity.class));
    }

}
