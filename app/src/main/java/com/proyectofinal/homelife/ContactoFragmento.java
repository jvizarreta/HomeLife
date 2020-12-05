package com.proyectofinal.homelife;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ContactoFragmento extends Fragment {
    Button btnCentralTelefonica1;
    Button btnCentralTelefonica2;
    Button btnCentralTelefonica3;

    public ContactoFragmento() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contacto,
                container, false);
        btnCentralTelefonica1 = (Button) view.findViewById(R.id.btnCentralTelefonica1);
        btnCentralTelefonica1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = ("tel:14215060");
                Intent mIntent = new Intent(Intent.ACTION_CALL);
                mIntent.setData(Uri.parse(number));
                if (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.CALL_PHONE}, 0);

                } else {
                    try {
                        startActivity(mIntent);
                    } catch(SecurityException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        btnCentralTelefonica2 = (Button) view.findViewById(R.id.btnCentralTelefonica2);
        btnCentralTelefonica2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = ("tel:14215070");
                Intent mIntent = new Intent(Intent.ACTION_CALL);
                mIntent.setData(Uri.parse(number));
                if (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.CALL_PHONE}, 0);

                } else {
                    try {
                        startActivity(mIntent);
                    } catch(SecurityException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        btnCentralTelefonica3 = (Button) view.findViewById(R.id.btnCentralTelefonica3);
        btnCentralTelefonica3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = ("tel:994018970");
                Intent mIntent = new Intent(Intent.ACTION_CALL);
                mIntent.setData(Uri.parse(number));
                if (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.CALL_PHONE}, 0);

                } else {
                    try {
                        startActivity(mIntent);
                    } catch(SecurityException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        return view;
    }
}