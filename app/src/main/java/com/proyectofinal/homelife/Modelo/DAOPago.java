package com.proyectofinal.homelife.Modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.proyectofinal.homelife.Entidad.Pago;
import com.proyectofinal.homelife.Util.SqliteHelper;
import com.proyectofinal.homelife.Util.Constantes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

public class DAOPago {
    SqliteHelper helper;
    SQLiteDatabase db;
    private final Context context;

    public DAOPago(Context context) {
        this.helper = new SqliteHelper(context);
        this.context = context;
    }
    public void openDB(){
        db = helper.getWritableDatabase();
    }
 /*public ArrayList<Pago> getListaPago(){
        ArrayList<Pago> listaPago = new ArrayList<>();
        Cursor cursor = null;
        try{
            cursor = db.rawQuery("SELECT * FROM "+Constantes.NOMBRE_TABLAPAGO,null);
            while (cursor.moveToNext()){
                listaPago.add(new Pago(cursor.getInt(0),cursor.getString(1),cursor.getInt(2), cursor.getDouble(3),cursor.getBlob(4),cursor.getInt(5)));
            }
        }catch (Exception e){
            return null;
        }finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return listaPago;
    }*/
    public long agregarPago(Pago pago){
        //long cursor;
        try {
            ContentValues values = new ContentValues();
            values.put("concepto",pago.getConcepto());
            values.put("numerodeposito",pago.getNumerodeposito());
            values.put("monto",pago.getMonto());
            values.put("foto",pago.getFoto());
            values.put("idusuario",pago.getIdusuario());
            return db.insert(Constantes.NOMBRE_TABLAPAGO,null,values);
            //return cursor;
        }catch (Exception e){
            return 0;
        }
    }

}
