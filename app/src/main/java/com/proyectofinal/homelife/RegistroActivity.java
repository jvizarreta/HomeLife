package com.proyectofinal.homelife;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.proyectofinal.homelife.network.services.RegistroService;

public class RegistroActivity extends AppCompatActivity {
    RegistroActivity activity;
    RegistroService servicios;

    EditText txtEmail,txtPassword;
    Button btnRegistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        final RegistroActivity activity = this;
        servicios = new RegistroService(activity);
    }



    public void onRegistrarUser(View view) {
        /*
        DaoUsuario userDao = new DaoUsuario(getApplicationContext());
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
         */

        String tipoDoc, numeroDoc, nombres, apellidos, correo, telefono, password, password2, departamento;

        tipoDoc = "DNI";
        numeroDoc = ((EditText) findViewById(R.id.txtUsername)).getText().toString();
        nombres = ((EditText) findViewById(R.id.txtName)).getText().toString();
        apellidos = ((EditText) findViewById(R.id.txtLastname)).getText().toString();
        correo = ((EditText) findViewById(R.id.txtEmail)).getText().toString();
        telefono = ((EditText) findViewById(R.id.txtPhone)).getText().toString();
        password = ((EditText) findViewById(R.id.txtPassword)).getText().toString();
        password2 = ((EditText) findViewById(R.id.txtPasswordConfirm)).getText().toString();
        departamento = ((EditText) findViewById(R.id.txtDepartamento)).getText().toString();

        if(password.equals(password2)) {
            servicios.registrar(tipoDoc, numeroDoc, nombres, apellidos, correo ,telefono, password, departamento);
            //Toast.makeText(this, "Usuario registrado", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "ERROR: Las contraseñas deben ser iguales.", Toast.LENGTH_LONG).show();
        }

    }
}