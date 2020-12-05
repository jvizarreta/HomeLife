package com.proyectofinal.homelife.network.services;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.proyectofinal.homelife.MainActivity;
import com.proyectofinal.homelife.RegistroActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistroService extends Base {
    private RegistroActivity activity;

    public RegistroService(RegistroActivity activity) {
        super(activity.getApplicationContext());
        this.activity = activity;
    }
    public void registrar(String tipoDoc, String numeroDoc, String nombres, String apellidos, String correo, String telefono, String password, String departamento){

        JSONObject json = new JSONObject();
        try{
            json.put("document_type", tipoDoc);
            json.put("document_number", numeroDoc);
            json.put("name", nombres);
            json.put("lastname", apellidos);
            json.put("phone", telefono);
            json.put("email", correo);
            json.put("password", password);
            json.put("department", departamento);
        }catch (JSONException e){
            e.printStackTrace();
        }

        client.addToRequestQueue(new JsonObjectRequest(Request.Method.POST, URL_REGISTRO, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("HomeLife", response.toString());
                Toast.makeText(activity, "Se registro correctamente", Toast.LENGTH_SHORT).show();
                activity.startActivity(new Intent(activity.getApplicationContext(), MainActivity.class));
            }
        },  new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("HomeLife", error.getMessage());
                Toast.makeText(activity, "Lo sentimos, no se pudo registrar el usuario", Toast.LENGTH_SHORT).show();
            }
        }));

    }

}
