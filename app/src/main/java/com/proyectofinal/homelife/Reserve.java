package com.proyectofinal.homelife;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.proyectofinal.homelife.Entidad.Reserva;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Reserve extends AppCompatActivity {
    TextView tvDate;
    EditText etDate;
    DatePickerDialog.OnDateSetListener setListener;
    EditText t2;
    private int mYearIni, mMonthIni, mDayIni, sYearIni, sMonthIni, sDayIni;
    static final int DATE_ID = 0;
    Calendar C = Calendar.getInstance();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        sDayIni = C.get(Calendar.DAY_OF_MONTH);
        sMonthIni = C.get(Calendar.MONTH);
        sYearIni = C.get(Calendar.YEAR);
        t2 = (EditText) findViewById(R.id.et_date);



        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialog(DATE_ID);
            }
        });



    }


    private void colocar_fecha() {
        t2.setText(mDayIni + "-" + (mMonthIni + 1) + "-" + mYearIni+" ");
    }



    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    mYearIni = year;
                    mMonthIni = monthOfYear;
                    mDayIni = dayOfMonth;
                    colocar_fecha();

                }

            };


    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_ID:
                return new DatePickerDialog(this, mDateSetListener, sYearIni, sMonthIni, sDayIni);


        }


        return null;
    }


}