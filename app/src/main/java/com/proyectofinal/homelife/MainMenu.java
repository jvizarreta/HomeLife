package com.proyectofinal.homelife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
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