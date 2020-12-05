package com.proyectofinal.homelife;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.proyectofinal.homelife.Modelo.DaoUsuario;
import com.proyectofinal.homelife.network.services.LoginService;

public class LoginActivity extends AppCompatActivity {
    LoginActivity activity;
    LoginService servicios;
EditText txtIngreseUsuario,txtIngreseContrasena;
Button btnIniciarSesion;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final LoginActivity activity = this;
        servicios = new LoginService(activity);

        String token = servicios.mHomeLifePreference.getToken();
        if (!token.equals("")) {
            servicios.verify();
        }

        DaoUsuario userDao = new DaoUsuario(getApplicationContext());
        setContentView(R.layout.activity_login);
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
                    Toast.makeText(LoginActivity.this,"escriba sus credenciales correctas",Toast.LENGTH_SHORT).show();
                else {
                    servicios.login(user, password);
                    /*
                    userDao.openDB();
                    Boolean checkuser=userDao.checkpaswrord(user,password);
                    if(checkuser==true)
                    {
                        Toast.makeText(LoginActivity.this,"se inicio de sesión correcto",Toast.LENGTH_SHORT).show();
                        Intent iniciar = new Intent(getApplicationContext(),MenuActivity.class);
                        startActivity(iniciar);
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this,"sus credenciales es incorrecto",Toast.LENGTH_SHORT).show();
                    }
                     */
                }
            }
        });
    }


    //Esta liena hace el cambio de LoginActivity a MenuActivity
    /*public void IniciarSesion (View view){
        Intent iniciar = new Intent(this, MenuActivity.class);
        startActivity(iniciar);
    }*/
}