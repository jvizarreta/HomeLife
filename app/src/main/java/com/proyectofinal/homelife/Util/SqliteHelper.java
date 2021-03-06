package com.proyectofinal.homelife.Util;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class SqliteHelper extends SQLiteOpenHelper {
    private static SqliteHelper sqliteHelper;
    private static final String DATABASE_NAME = Constantes.NOMBRE_BD;
    private static final int DATABASE_VERSION = Constantes.VERSION;


    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static SqliteHelper getInstance(Context context) {

        if (sqliteHelper == null) {
            synchronized (SqliteHelper.class){
                if (sqliteHelper == null)
                    sqliteHelper = new SqliteHelper(context);
            }
        }

        return sqliteHelper;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE "+ Constantes.NOMBRE_TABLAUSUARIO+
                        " (id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        " nrodocumento TEXT NOT NULL,"+
                        " nombres TEXT NOT NULL,"+
                        " apellidos TEXT NOT NULL,"+
                        " celular INTEGER NOT NULL,"+
                        " departamento TEXT NOT NULL,"+
                        " password TEXT NOT NULL,"+
                        " passwordconfirmacion TEXT NOT NULL);";

        db.execSQL(query);
       /* String query2 =
                "CREATE TABLE "+ Constantes.NOMBRE_TABLAESTADOCUENTA+
                        " (id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        " nrodocumento TEXT NOT NULL,"+
                        " nombres TEXT NOT NULL,"+
                        " apellidos TEXT NOT NULL,"+
                        " celular INTEGER NOT NULL,"+
                        " departamento TEXT NOT NULL,"+
                        " password TEXT NOT NULL,"+
                        " passwordconfirmacion TEXT NOT NULL);";
        db.execSQL(query2);*/
        String query3 =
                "CREATE TABLE "+ Constantes.NOMBRE_TABLAPAGO+
                        " (id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        " concepto TEXT NOT NULL,"+
                        " numerodeposito INTEGER NOT NULL,"+
                        " MONTO DECIMAL(18,2) NOT NULL,"+
                        " foto BLOB NOT NULL," +
                        " idusuario INTEGER NOT NULL," +
                        " FOREIGN KEY ("+ Constantes.FKUSER +") REFERENCES "+ Constantes.NOMBRE_TABLAUSUARIO +"("+Constantes.ID+"));";

        db.execSQL(query3);

        String query4 =
                "CREATE TABLE "+ Constantes.NOMBRE_TABLARESERVA+
                        " (id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        " ambiente TEXT NOT NULL,"+
                        " fecha DATE NOT NULL,"+
                        " estadoautoriza INTEGER NOT NULL,"+
                        " idusuario INTEGER NOT NULL,"+
                        " FOREIGN KEY ("+ Constantes.FKUSER +") REFERENCES "+ Constantes.NOMBRE_TABLAUSUARIO +"("+Constantes.ID+"));";

        db.execSQL(query4);

        String query5 =
                "CREATE TABLE "+ Constantes.NOMBRE_TABLAEMERGENCIA+
                        " (id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        " descripcion TEXT NOT NULL,"+
                        " numeroemergencia integer NOT NULL);";

        db.execSQL(query5);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}
