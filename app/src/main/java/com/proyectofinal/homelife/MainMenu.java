package com.proyectofinal.homelife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.proyectofinal.homelife.network.services.LoginService;
import com.proyectofinal.homelife.network.services.MenuService;

public class MainMenu extends AppCompatActivity {
    MainMenu activity;
    MenuService servicios;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        final MainMenu activity = this;
        servicios = new MenuService(activity);

        final Button btnCerrarSesion = findViewById(R.id.btnCerrarSesion);
        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                servicios.logout();
            }
        });

    }


    public void RegistrarPago (View view){
        Intent registar = new Intent(this, MainPago.class);
        startActivity(registar);
    }

    public void estado (View view){
        Intent estado = new Intent(this, EstadoCuenta.class);
        startActivity(estado);
    }

    public void reserva (View view){
        Intent reserva = new Intent(this,Reserve.class);
        startActivity(reserva);
    }

    public void LugaresInteres (View view){
        Intent lugar = new Intent(this, MainMapa.class);
        startActivity(lugar);
    }
}