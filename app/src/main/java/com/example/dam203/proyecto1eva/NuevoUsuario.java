package com.example.dam203.proyecto1eva;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by dam203 on 07/11/2017.
 */

public class NuevoUsuario extends AppCompatActivity {
    private Appcomponentes appv;
    UsuarioDAOSQLite usrDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_usuario);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.usrDAO = new UsuarioDAOSQLite(this);
        xestionarEventos();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean resultado = false;
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            default:
                resultado = super.onOptionsItemSelected(item);

        }
        return resultado;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    void xestionarEventos() {
        Button btnNuevoUsuario = findViewById(R.id.crear_usuario);
        btnNuevoUsuario.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                crearUsuario();
            }
        });
    }

    void crearUsuario() {
        String nombre = ((EditText) findViewById(R.id.registro_nombre)).getText().toString();
        String login = ((EditText) findViewById(R.id.registro_login)).getText().toString();
        String password = ((EditText) findViewById(R.id.registro_password)).getText().toString();
    	/*Creación el objeto usuario. Dado que id es autoincrementable en la base de datos
    	el valor del campo id no será procesado en el método de inserción de usuario.
    	Por lo tanto, se le pasará 0, o cualquier otro valor.*/
        Usuario usr = new Usuario(nombre, login, password, 0);
        boolean insercion = this.usrDAO.insertarUsuario(usr);
        //Notificación de la inserción.
        if (insercion)
            Toast.makeText(getApplicationContext(), "Nuevo usuario registrado.", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(), "Error en el registro de usuario.", Toast.LENGTH_LONG).show();
        //Ir a la ventana de inicio de sesión y finalizar la Activity.
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
