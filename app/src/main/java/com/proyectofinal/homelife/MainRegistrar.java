package com.proyectofinal.homelife;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.proyectofinal.homelife.Entidad.Usuario;
import com.proyectofinal.homelife.Modelo.DaoUsuario;

import java.util.regex.Pattern;

public class MainRegistrar extends AppCompatActivity {

    EditText txtEmail,txtPassword;
    Button btnRegistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_registrar);

    }



    public void onRegistrarUser(View view) {
        DaoUsuario userDao = new DaoUsuario(getApplicationContext());
        Log.i("Registro USUARIO", "HOLLAAAA");
        Usuario user = new Usuario();

        user.setNrodocumento(String.valueOf(((EditText) findViewById(R.id.txtUsername)).getText()));
        user.setNombres(String.valueOf(((EditText) findViewById(R.id.txtName)).getText()));
        user.setApellidos(String.valueOf(((EditText) findViewById(R.id.txtLastname)).getText()));
        user.setCelular(Integer.parseInt(String.valueOf(((EditText) findViewById(R.id.txtPhone)).getText())));
        user.setPassword(String.valueOf(((EditText) findViewById(R.id.txtPassword)).getText()));
        user.setPasswordconfirmacion(String.valueOf(((EditText) findViewById(R.id.txtPasswordConfirm)).getText()));
        user.setDepartamento("Departamento");
        Log.i("Registro USUARIO", user.toString());
        if(user.getPassword().equals(user.getPasswordconfirmacion())) {
            userDao.openDB();
            userDao.agregarUsuario(user);
            Toast.makeText(this, "Usuario registrado", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "ERROR: Las contraseñas deben ser iguales.", Toast.LENGTH_LONG).show();
        }
    }
}