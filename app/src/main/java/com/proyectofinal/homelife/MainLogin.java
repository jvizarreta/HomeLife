package com.proyectofinal.homelife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.proyectofinal.homelife.Modelo.DaoUsuario;
import com.proyectofinal.homelife.Modelo.DaoUsuario;
import com.proyectofinal.homelife.Util.SqliteHelper;

public class MainLogin extends AppCompatActivity {
EditText txtIngreseUsuario,txtIngreseContrasena;
Button btnIniciarSesion;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaoUsuario userDao = new DaoUsuario(getApplicationContext());
        setContentView(R.layout.activity_main_login);
        txtIngreseUsuario =(EditText) findViewById(R.id.txtIngreseUsuario);
        txtIngreseContrasena =(EditText) findViewById(R.id.txtIngreseContrasena);
        btnIniciarSesion =(Button) findViewById(R.id.btnIniciarSesion);
        //DB= new SqliteHelper(this);

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = txtIngreseUsuario.getText().toString();
                String password = txtIngreseContrasena.getText().toString();
                if(user.equals("")||password.equals(""))
                    Toast.makeText(MainLogin.this,"escriba sus credenciales correctas",Toast.LENGTH_SHORT).show();
                else {
                    userDao.openDB();
                    Boolean checkuser=userDao.checkpaswrord(user,password);
                    if(checkuser==true)
                    {
                        Toast.makeText(MainLogin.this,"se inicio de sesión correcto",Toast.LENGTH_SHORT).show();
                        Intent iniciar = new Intent(getApplicationContext(),MainMenu.class);
                        startActivity(iniciar);
                    }
                    else
                    {
                        Toast.makeText(MainLogin.this,"sus credenciales es incorrecto",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    //Esta liena hace el cambio de MainLogin a MainMenu
    /*public void IniciarSesion (View view){
        Intent iniciar = new Intent(this, MainMenu.class);
        startActivity(iniciar);
    }*/
}