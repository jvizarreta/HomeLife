package com.proyectofinal.homelife;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.proyectofinal.homelife.network.services.PagoService;
import com.proyectofinal.homelife.network.services.ReservaService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class PagoActivity extends AppCompatActivity {
    PagoActivity activity;
    PagoService servicios;

    Spinner spReservas;
    EditText dpFecha;
    EditText txtNumDepo;
    EditText txtMonto;
    Button btnRegistrarPago;

    ArrayList<String> reservasValues = new ArrayList<String>();
    ArrayList<String> reservasLabels = new ArrayList<String>();

    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago);

        final PagoActivity activity = this;
        servicios = new PagoService(activity);

        spReservas = findViewById(R.id.spReservas);
        spReservas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                servicios.mHomeLifePreference.putInt("reserva", position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        dpFecha = findViewById(R.id.dpFecha);
        dpFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.dpFecha:
                        showDatePickerDialog();
                        break;
                }
            }
        });

        txtNumDepo = findViewById(R.id.txtNumDepo);
        txtMonto = findViewById(R.id.txtMonto);
        btnRegistrarPago = findViewById(R.id.btnRegistrarPago);
        btnRegistrarPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int posicion = spReservas.getSelectedItemPosition();
                servicios.registrarPago(dpFecha.getText().toString(), reservasValues.get(posicion), txtNumDepo.getText().toString(), txtMonto.getText().toString());
            }
        });

        servicios.getMisReservas();
    }


    public void setMisReservas(JSONArray data) {

        reservasLabels = new ArrayList<String>();
        reservasValues = new ArrayList<String>();

        ArrayList<HashMap<String,String>> result = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < data.length(); i++)
        {
            try {
                JSONObject item = data.getJSONObject(i);
                Log.i("HomeLife", item.getBoolean("pagado") ? "SI" : "NO");
                if(!item.getBoolean("pagado")) {
                    reservasValues.add(j, item.getString("id"));
                    reservasLabels.add(j, item.getString("fecha").substring(0, 10) + " " + item.getString("description"));
                    j++;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        Gson gson = new Gson();
        String jsonString = gson.toJson(reservasLabels);
        Log.i("HomeLife", jsonString);

        if(reservasValues.size() > 0) {

            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, reservasLabels);
            spReservas.setAdapter(adapter);
            int addressSelected = servicios.mHomeLifePreference.getInt("reservas");
            if (addressSelected != -1) {
                spReservas.setSelection(addressSelected);
            }
        } else {
            Toast.makeText(getApplicationContext(), "No tiene pagos pendientes", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), MenuActivity.class));
        }
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = year + "/" + (month+1) + "/" + day;
                dpFecha.setText(selectedDate);
            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}