package com.proyectofinal.homelife.network.services;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.proyectofinal.homelife.MainActivity;
import com.proyectofinal.homelife.ReservarActivity;
import com.proyectofinal.homelife.ReservarConfirmacionActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ReservaService extends Base {
    private ReservarActivity activity;

    public ReservaService(ReservarActivity activity) {
        super(activity.getApplicationContext());
        this.activity = (ReservarActivity) activity;
    }

    public void getAmbientes () {
        final String token = mHomeLifePreference.getToken();

        client.addToRequestQueue(new JsonArrayRequest(Request.Method.GET, URL_AMBIENTES_LIST, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.i("HomeLife", response.toString());
                activity.setAmbientes(response);
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

    public void registrarReserva(String fecha, String id, String nombre, String precio) {
        final String token = mHomeLifePreference.getToken();

        JSONArray detalle = new JSONArray();
        JSONObject json = new JSONObject();
        try{
            json.put("fecha", fecha.replace("/", "-").replace("/", "-"));
            json.put("description", "Reserva de "+  nombre);
            json.put("cargo_en_cuenta", "NO");
            json.put("price", precio);

            Gson gson = new Gson();
            String jsonString = gson.toJson(json);
            Log.i("HomeLife", jsonString);

        }catch (JSONException e){
            e.printStackTrace();
        }

        client.addToRequestQueue(new JsonObjectRequest(Request.Method.POST, URL_RESERVA, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("HomeLife", response.toString());
                Toast.makeText(activity, "Reserva registrada", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(activity.getApplicationContext(), ReservarConfirmacionActivity.class);
                Bundle b = new Bundle();
                b.putString("fecha", fecha);
                b.putString("ambiente", nombre);
                b.putString("precio", precio);
                intent.putExtras(b);
                activity.startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("HomeLife", error.getMessage());
                Toast.makeText(activity, "No se pudo registrar su reserva, inténtelo nuevamente", Toast.LENGTH_SHORT).show();
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
