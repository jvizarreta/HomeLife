package com.proyectofinal.homelife;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import java.util.Calendar;


public class fragment_Pago extends AppCompatActivity {

    EditText t1;
    private int mYearIni, mMonthIni, mDayIni, sYearIni, sMonthIni, sDayIni;
    static final int DATE_ID = 0;
    Calendar C = Calendar.getInstance();
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sMonthIni = C.get(Calendar.MONTH);
        sDayIni = C.get(Calendar.DAY_OF_MONTH);
        sYearIni = C.get(Calendar.YEAR);
        t1 = (EditText) findViewById(R.id.etDate);

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialog(DATE_ID);
            }
        });

    }

    private  void colocar_fecha(){
        t1.setText((mMonthIni + 1) + "-" + mDayIni + "-" + mYearIni + "");
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

  protected Dialog onCreateDialog(int id) {
      switch (id){
          case DATE_ID:
              return new DatePickerDialog(this, mDateSetListener, sYearIni, sMonthIni,sDayIni);
      }
      return null;
  }


}