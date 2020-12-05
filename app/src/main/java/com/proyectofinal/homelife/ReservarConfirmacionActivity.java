package com.proyectofinal.homelife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ReservarConfirmacionActivity extends AppCompatActivity {
    TextView txtFecha;
    TextView txtAmbiente;
    TextView txtPrecio;
    Button btnMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservar_confirmacion);

        Bundle b = getIntent().getExtras();

        txtFecha = findViewById(R.id.txtFecha);
        txtAmbiente = findViewById(R.id.txtAmbiente);
        txtPrecio = findViewById(R.id.txtPrecio);
        btnMainMenu = findViewById(R.id.btnMainMenu);

        txtFecha.setText("Fecha: "+ b.getString("fecha").toString());
        txtAmbiente.setText("Ambiente: "+ b.getString("ambiente").toString());
        txtPrecio.setText("Precio: S/ "+ b.getString("precio").toString());

        btnMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
            }
        });

    }
}