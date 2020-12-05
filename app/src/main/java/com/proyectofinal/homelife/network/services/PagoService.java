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
import com.proyectofinal.homelife.EstadoCuentaActivity;
import com.proyectofinal.homelife.MainActivity;
import com.proyectofinal.homelife.PagoActivity;
import com.proyectofinal.homelife.PagoConfirmacionActivity;
import com.proyectofinal.homelife.ReservarConfirmacionActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PagoService extends Base {
    private PagoActivity activity;

    public PagoService(PagoActivity activity) {
        super(activity.getApplicationContext());
        this.activity = (PagoActivity) activity;
    }

    public void getMisReservas () {
        final String token = mHomeLifePreference.getToken();

        client.addToRequestQueue(new JsonArrayRequest(Request.Method.GET, URL_RESERVA, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.i("HomeLife", response.toString());
                activity.setMisReservas(response);
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

    public void registrarPago(String fecha, String reserva_id, String numero_deposito, String monto) {
        final String token = mHomeLifePreference.getToken();

        JSONArray detalle = new JSONArray();
        JSONObject json = new JSONObject();
        try{
            json.put("fecha", fecha.replace("/", "-").replace("/", "-"));
            json.put("reserva", reserva_id);
            json.put("numero", numero_deposito);
            json.put("monto", monto);

            Gson gson = new Gson();
            String jsonString = gson.toJson(json);
            Log.i("HomeLife", "registrar pago");
            Log.i("HomeLife", jsonString);

        }catch (JSONException e){
            e.printStackTrace();
        }

        client.addToRequestQueue(new JsonObjectRequest(Request.Method.POST, URL_PAGO, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("HomeLife", response.toString());
                Toast.makeText(activity, "Reserva registrada", Toast.LENGTH_SHORT).show();
                activity.startActivity(new Intent(activity.getApplicationContext(), PagoConfirmacionActivity.class));
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
