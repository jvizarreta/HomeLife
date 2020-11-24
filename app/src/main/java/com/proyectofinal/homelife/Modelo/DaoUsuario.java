package com.proyectofinal.homelife.Modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.proyectofinal.homelife.Entidad.Usuario;
import com.proyectofinal.homelife.Util.Constantes;
import com.proyectofinal.homelife.Util.SqliteHelper;

import java.util.ArrayList;
public class DaoUsuario {
    SqliteHelper helper;
    SQLiteDatabase db;
    private final Context context;

     public DaoUsuario(Context context) {
        this.helper = SqliteHelper.getInstance(context);
        this.context = context;
    }
    public void openDB(){
        db = helper.getWritableDatabase();
    }
    public long agregarUsuario(Usuario usuario){
       try {
            ContentValues values = new ContentValues();
            values.put("nrodocumento",usuario.getNrodocumento());
            values.put("nombres",usuario.getNombres());
            values.put("apellidos",usuario.getApellidos());
            values.put("celular",usuario.getCelular());
            values.put("departamento",usuario.getDepartamento());
            values.put("password",usuario.getPassword());
            values.put("passwordconfirmacion",usuario.getPasswordconfirmacion());
            return db.insert(Constantes.NOMBRE_TABLAUSUARIO,null,values);
            //return cursor;
        }catch (Exception e){
            return 0;
        }
    }
}
