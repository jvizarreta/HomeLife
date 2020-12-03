package com.proyectofinal.homelife;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.proyectofinal.homelife.Entidad.Reserva;
import com.proyectofinal.homelife.Entidad.Usuario;
import com.proyectofinal.homelife.Modelo.DAOReserva;
import com.proyectofinal.homelife.Modelo.DaoUsuario;
import com.proyectofinal.homelife.Util.Constantes;
import com.proyectofinal.homelife.Util.SqliteHelper;

import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class Reserve <DateTime> extends AppCompatActivity  {
    TextView tvDate;

    EditText et_date;
    Spinner spinner;
    Button btnregis;
    DAOReserva daoReserva = new DAOReserva(this);
    String[] ambiente = {"Terraza", "Parrilla"};
    boolean flag = false;
    Reserva reserva = null;

    DatePickerDialog.OnDateSetListener setListener;
    EditText t2;
    private int mYearIni, mMonthIni, mDayIni, sYearIni, sMonthIni, sDayIni;
    static final int DATE_ID = 0;
    Calendar C = Calendar.getInstance();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            reserva = bundle.getParcelable(Constantes.ARGUM1);
            flag = bundle.getBoolean(Constantes.ARGUM2);
        }

        spinner = findViewById(R.id.spinner);
        et_date = findViewById(R.id.et_date);
        btnregis = findViewById(R.id.btnregReser);

        daoReserva.openDB();
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ambiente);
        spinner.setAdapter(adapter);

        if(flag){
            //ACA ME SALE ERROR
            et_date.setText(reserva.getFecha());

            if(reserva.getambiente().equals("Terraza"))
               spinner.setSelection(0);
           else
               spinner.setSelection(1);
           btnregis.setVisibility(View.GONE);

        }

        btnregis.setOnClickListener(view ->{
            String ambiente;
            Date fecha;
            Reserva reserva;
            long rpta;

            ambiente = spinner.getSelectedItem().toString();

            //IGUAL
            reserva = new Reserva(ambiente, fecha);
            rpta = daoReserva.agregarReserva(reserva);
            if (rpta > 0){
                Toast.makeText(this, "Se agregó el registro correctamente", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Ocurrió un error", Toast.LENGTH_SHORT).show();
            }

            finish();

        });


        sDayIni = C.get(Calendar.DAY_OF_MONTH);
        sMonthIni = C.get(Calendar.MONTH);
        sYearIni = C.get(Calendar.YEAR);


        SqliteHelper sqliteHelper = new SqliteHelper(getApplicationContext());

            

        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialog(DATE_ID);
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.ambiente_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

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