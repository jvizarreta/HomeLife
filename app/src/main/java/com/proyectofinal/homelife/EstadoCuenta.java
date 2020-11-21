package com.proyectofinal.homelife;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;

public class EstadoCuenta extends AppCompatActivity {
    ListView lstEstadoCuenta;
    String[] fromMP = new String[] {"Descripción","Moneda","Fecha","Monto"};
    int[] toMP = new int[] {R.id.txtDescripcion, R.id.txtMoneda, R.id.txtFecha, R.id.txtMonto};

    BottomNavigationView menuBotones;
    public EstadoCuenta(){
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_estado_cuenta);
        this.setMovimientos();
    }

    public void setMovimientos() {
        ArrayList<HashMap<String,String>> miEstadoDeCuenta = new ArrayList<>();
        for(int l=1; l<6; l++){
            HashMap<String,String> movimiento = new HashMap<>();
            movimiento.put("Descripcion", "Descripción " + String.valueOf(l));
            movimiento.put("Moneda ", "Soles");
            movimiento.put("Fecha", "12/12/2020");
            movimiento.put("Monto", "100.00");

            miEstadoDeCuenta.add(movimiento);
        }

        lstEstadoCuenta = findViewById((R.id.lstMiEstadoCuenta));
        SimpleAdapter adapter3 = new SimpleAdapter(getApplicationContext(), miEstadoDeCuenta, R.layout.activity_estado_cuenta,fromMP, toMP);
        lstEstadoCuenta.setAdapter(adapter3);
    }
    public void onBackMainMenu (View view){
        Intent registar = new Intent(this, MainMenu.class);
        startActivity(registar);
    }

}








