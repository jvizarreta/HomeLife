package com.proyectofinal.homelife.network.services;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.proyectofinal.homelife.LoginActivity;
import com.proyectofinal.homelife.MainActivity;
import com.proyectofinal.homelife.MenuActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginService extends Base {
    private AppCompatActivity activity;

    public LoginService(LoginActivity activity) {
        super(activity.getApplicationContext());
        this.activity = (LoginActivity) activity;
    }
    public LoginService(MainActivity activity) {
        super(activity.getApplicationContext());
        this.activity = (MainActivity) activity;
    }

    public void login(String username, String password) {
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("username", username);
            jsonBody.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        client.addToRequestQueue(new JsonObjectRequest(Request.Method.POST, URL_AUTH_LOGIN, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("homelife", response.toString());
                try {
                    mHomeLifePreference.setToken(response.getString("token"));
                    mHomeLifePreference.setUserId(response.getJSONObject("user").getString("name"));
                    mHomeLifePreference.setUserName(response.getJSONObject("user").getString("name"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                activity.startActivity(new Intent(activity.getApplicationContext(), MenuActivity.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse =  error.networkResponse;
                Log.i("homelife", networkResponse.toString());

                Toast.makeText(activity, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        }));
    }



    public void verify() {

        final String token = mHomeLifePreference.getToken();
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("token", token);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        client.addToRequestQueue(new JsonObjectRequest(Request.Method.POST, URL_AUTH_VERIFY, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("homelife", response.toString());
                try {
                    mHomeLifePreference.setToken(response.getString("token"));
                    mHomeLifePreference.setUserId(response.getJSONObject("user").getString("name"));
                    mHomeLifePreference.setUserName(response.getJSONObject("user").getString("name"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                activity.startActivity(new Intent(activity.getApplicationContext(), MenuActivity.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse =  error.networkResponse;
                //Log.i("homelife", networkResponse.statusCode);

                logout();
            }
        }));
    }

    public void logout(){
        Toast.makeText(context, "Sesión expirada.", Toast.LENGTH_SHORT).show();
        mHomeLifePreference.clear();
        activity.startActivity(new Intent(activity.getApplicationContext(), LoginActivity.class));
    }
}
