package com.proyectofinal.homelife.Modelo;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import java.time.DateTimeException;
import java.util.Date;
import com.proyectofinal.homelife.Entidad.Reserva;
import com.proyectofinal.homelife.Util.Constantes;
import com.proyectofinal.homelife.Util.SqliteHelper;
import java.util.Date;
public class DAOReserva {
    SqliteHelper helper;
    SQLiteDatabase db;
    private final Context context;

    public DAOReserva(Context context) {
        this.helper = SqliteHelper.getInstance(context);
        this.context = context;
    }
    public void openDB(){
        db = helper.getWritableDatabase();
    }

    public long agregarReserva(Reserva reserva){
        //long cursor;
        try {
            ContentValues values = new ContentValues();
            values.put("idambiente",reserva.getIdambiente());
            values.put("fecha", (byte[]) reserva.getFecha());
            values.put("estadoautoriza",reserva.getEstadoautoriza());
            values.put("idusuario",reserva.getIdusuario());
            return db.insert(Constantes.NOMBRE_TABLARESERVA,null,values);
            //return cursor;
        }catch (Exception e){
            return 0;
        }
    }
}
