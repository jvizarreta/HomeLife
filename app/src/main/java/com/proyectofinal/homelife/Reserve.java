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
import com.proyectofinal.homelife.Util.SqliteHelper;

import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Reserve <DateTime> extends AppCompatActivity  {
    TextView tvDate;

    EditText etDate;
    Spinner spinner;
    Button btnregis;

    DatePickerDialog.OnDateSetListener setListener;
    EditText t2;
    private int mYearIni, mMonthIni, mDayIni, sYearIni, sMonthIni, sDayIni;
    static final int DATE_ID = 0;
    Calendar C = Calendar.getInstance();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        Context context = this;

        sDayIni = C.get(Calendar.DAY_OF_MONTH);
        sMonthIni = C.get(Calendar.MONTH);
        sYearIni = C.get(Calendar.YEAR);
        t2 = (EditText) findViewById(R.id.et_date);
        btnregis = (Button) findViewById(R.id.btnregReser);
        spinner = (Spinner) findViewById(R.id.spinner);

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

    public void onRegistrarReserva  (View view) {
        DAOReserva reservaDao = new DAOReserva(getApplicationContext());
        Log.i("Registro Reserva", "");
        Reserva reserva = new Reserva();

        reserva.setIdambiente(Integer.valueOf(((Spinner) findViewById(R.id.spinner)).getId()));
        reserva.setFecha(DateTime.valueOf(((EditText) findViewById(R.id.et_date)).getDrawingTime()));
        Log.i("Registro Reserva", reserva.toString());
        if(reserva.getEstadoautoriza().equals(reserva.getEstadoautoriza())) {
            reservaDao.openDB();
           reservaDao.agregarReserva(reserva);
            Toast.makeText(this, "Reserva Registrada con Exito", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "ERROR: No se registro reserva.", Toast.LENGTH_LONG).show();
        }
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