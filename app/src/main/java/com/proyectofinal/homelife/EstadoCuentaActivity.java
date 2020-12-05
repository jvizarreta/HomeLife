package com.proyectofinal.homelife;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.proyectofinal.homelife.network.services.EstadoCuentaService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class EstadoCuentaActivity extends AppCompatActivity {
    EstadoCuentaActivity activity;
    EstadoCuentaService servicios;

    ListView lstEstadoCuenta;
    String[] fromMP = new String[] {"Descripcion","Fecha","Monto"};
    int[] toMP = new int[] {R.id.txtDescripcion, R.id.txtFecha, R.id.txtMonto};

    BottomNavigationView menuBotones;
    public EstadoCuentaActivity(){
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_estado_cuenta);
        // this.setMovimientos();

        final EstadoCuentaActivity activity = this;
        servicios = new EstadoCuentaService(activity);

        servicios.getMovimientos();
    }

    public void setMovimientos(JSONArray data) {
        ArrayList<HashMap<String,String>> miEstadoDeCuenta = new ArrayList<>();

        for (int i = 0; i < data.length(); i++)
        {
            try {
                JSONObject item = data.getJSONObject(i);
                HashMap<String, String> movimiento = new HashMap<>();
                movimiento.put("Descripcion", item.getString("descripcion"));
                movimiento.put("Moneda ", "Soles");
                movimiento.put("Fecha", item.getString("fecha").substring(0, 10));
                movimiento.put("Monto", item.getString("tipo") + " " +item.getString("monto"));

                miEstadoDeCuenta.add(movimiento);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        Gson gson = new Gson();
        String jsonString = gson.toJson(miEstadoDeCuenta);
        Log.i("HomeLife", jsonString);

        lstEstadoCuenta = findViewById((R.id.lstMiEstadoCuenta));
        SimpleAdapter adapter3 = new SimpleAdapter(getApplicationContext(), miEstadoDeCuenta, R.layout.activity_estado_cuenta,fromMP, toMP);
        lstEstadoCuenta.setAdapter(adapter3);
    }
    public void onBackMainMenu (View view){
        Intent registar = new Intent(this, MenuActivity.class);
        startActivity(registar);
    }

}








