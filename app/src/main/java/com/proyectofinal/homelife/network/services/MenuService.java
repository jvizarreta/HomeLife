package com.proyectofinal.homelife.network.services;

import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.proyectofinal.homelife.MainActivity;
import com.proyectofinal.homelife.MenuActivity;

public class MenuService extends Base {
    private AppCompatActivity activity;

    public MenuService(MenuActivity activity) {
        super(activity.getApplicationContext());
        this.activity = (MenuActivity) activity;
    }

    public void logout(){
        Toast.makeText(context, "Sesión expirada.", Toast.LENGTH_SHORT).show();
        mHomeLifePreference.clear();
        activity.startActivity(new Intent(activity.getApplicationContext(), MainActivity.class));
    }
}
