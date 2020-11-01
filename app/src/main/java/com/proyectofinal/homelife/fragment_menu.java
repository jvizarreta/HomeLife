package com.proyectofinal.homelife;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class fragment_menu extends Fragment {

    Button btnRegistrarpago;

    public fragment_menu() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragmento1, container, false);
        btnRegistrarpago = v.findViewById(R.id.btnRegistrarpago);

        btnRegistrarpago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), activity_pago.class);
                startActivityForResult(intent, 0);
            }
        });
    return v;
    }
}