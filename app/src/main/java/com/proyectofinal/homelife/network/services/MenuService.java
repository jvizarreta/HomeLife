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
import com.proyectofinal.homelife.MainActivity;
import com.proyectofinal.homelife.MainLogin;
import com.proyectofinal.homelife.MainMenu;

import org.json.JSONException;
import org.json.JSONObject;

public class MenuService extends Base {
    private AppCompatActivity activity;

    public MenuService(MainMenu activity) {
        super(activity.getApplicationContext());
        this.activity = (MainMenu) activity;
    }

    public void logout(){
        Toast.makeText(context, "Sesión expirada.", Toast.LENGTH_SHORT).show();
        mHomeLifePreference.clear();
        activity.startActivity(new Intent(activity.getApplicationContext(), MainActivity.class));
    }
}
