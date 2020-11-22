package com.proyectofinal.homelife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView menuBotones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        asignarReferencias();
    }

    private void asignarReferencias() {
        abrirFragmento(new fragmento1());
        menuBotones = findViewById(R.id.botonNavegacion);
        menuBotones.setItemIconTintList(null);
        menuBotones.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_1){
                    //Toast.makeText(MainActivity.this, "Seleccionaste Opción 2", Toast.LENGTH_SHORT).show();
                    abrirFragmento(new fragmento1());
                }
                if (item.getItemId() == R.id.menu_2){
                    //Toast.makeText(MainActivity.this, "Seleccionaste Opción 2", Toast.LENGTH_SHORT).show();
                    abrirFragmento(new fragmento2());
                }

                return false;
            }
        });
    }
    private void abrirFragmento(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
    }
}
