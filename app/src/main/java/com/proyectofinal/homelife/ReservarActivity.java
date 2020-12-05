package com.proyectofinal.homelife;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.proyectofinal.homelife.network.services.LoginService;
import com.proyectofinal.homelife.network.services.ReservaService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ReservarActivity extends AppCompatActivity {

    Button btnReservar;
    EditText dpFecha;
    Spinner spAmbiente;

    ReservarActivity activity;
    ReservaService servicios;

    ArrayList<String> ambientesValues = new ArrayList<String>();
    ArrayList<String> ambientesLabels = new ArrayList<String>();
    ArrayList<String> ambientesPrices = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservar);

        final ReservarActivity activity = this;
        servicios = new ReservaService(activity);

        dpFecha = (EditText) findViewById(R.id.dpFecha);
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

        spAmbiente = findViewById(R.id.spAmbiente);
        spAmbiente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                servicios.mHomeLifePreference.putInt("ambiente", position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        btnReservar = findViewById(R.id.btnReservar);
        btnReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int posicion = spAmbiente.getSelectedItemPosition();
                servicios.registrarReserva(dpFecha.getText().toString(), ambientesValues.get(posicion), ambientesLabels.get(posicion), ambientesPrices.get(posicion));
            }
        });

        servicios.getAmbientes();
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

    public void setAmbientes(JSONArray data) {
        ambientesLabels = new ArrayList<String>();
        ambientesValues = new ArrayList<String>();
        ambientesPrices = new ArrayList<String>();

        ArrayList<HashMap<String,String>> result = new ArrayList<>();
        for (int i = 0; i < data.length(); i++)
        {
            try {
                JSONObject item = data.getJSONObject(i);

                ambientesValues.add(i, item.getString("id"));
                ambientesLabels.add(i, item.getString("name"));
                ambientesPrices.add(i, item.getString("price"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        ArrayAdapter adapter = new ArrayAdapter (this, android.R.layout.simple_list_item_activated_1, ambientesLabels);
        spAmbiente.setAdapter(adapter);
        int addressSelected = servicios.mHomeLifePreference.getInt("ambiente");
        if (addressSelected != -1) {
            spAmbiente.setSelection(addressSelected);
        }
    }
}