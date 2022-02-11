package com.example.agendafotografica.actividades.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agendafotografica.R;
import com.example.agendafotografica.actividades.clases.Evento;
import com.example.agendafotografica.actividades.clases.Usuario;
import com.example.agendafotografica.actividades.controladores.EventoController;
import com.example.agendafotografica.actividades.controladores.UsuarioController;
import com.example.agendafotografica.actividades.modelos.UsuarioDB;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class InsertarActivity extends AppCompatActivity {

    //Variable para gestionar FirebaseAuth
    private FirebaseAuth mAuth;
    TextView fechaIncorporada;


    private EditText edtNombre = null;
    private EditText edtApellidos = null;
    private EditText edtTelefono = null;
    private EditText edtCorreo = null;

    private Spinner spinnerHora;
    private Spinner spinnerDescripcion;
    private TextView correo;



    private String correoUsuario;
    private String descripcionEvento;
    private String horaSeleccionada;
    private String fecha_recibida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);

        //emparejar objetos del diseño con la clase java
        fechaIncorporada = (TextView) findViewById(R.id.fechaIncorporada);
        edtNombre = (EditText) findViewById(R.id.edtName);
        edtApellidos = (EditText) findViewById(R.id.edtSurname);
        edtTelefono = (EditText) findViewById(R.id.edtTelefono);
        edtCorreo = (EditText) findViewById(R.id.edtCorreo);
        spinnerDescripcion = (Spinner) findViewById(R.id.spinnerDescripcion);
        spinnerHora = (Spinner) findViewById(R.id.spinnerHora);
        correo = (TextView) findViewById(R.id.correoDB);


        //atributos spinner
        String [] horas = {"15:00", "17:00", "19:00"};
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, horas);
        spinnerHora.setAdapter(adapter);

        //atributos spinner
        String [] descripcion = {"instagram", "festival", "boda", "comunion", "bautizo","pareja"};
        ArrayAdapter <String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, descripcion);
        spinnerDescripcion.setAdapter(adapter1);






        Intent intent = getIntent();



        //recojo objetos
        String p = intent.getStringExtra(CalendarioActivity.enviarFecha);
        String u = intent.getStringExtra(LoginActivity.ENVIAR_CORREO_LOGIN);

        //Usuario us = (Usuario)intent.getSerializableExtra(LoginActivity.ENVIAR_CORREO_LOGIN);
        Evento e = (Evento) intent.getSerializableExtra(CalendarioActivity.enviarFecha);
        String fecha = e.getEvento();
        fecha_recibida = fecha;
        //String correo1 = us.getCorreo();
        //String correo_recibido = correo1;
        fechaIncorporada.setText(fecha_recibida);
        //correo.setText("correo: "+ correo_recibido);


        //cogemos el correo
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() !=null) {
            mAuth.getCurrentUser().getEmail(); //el email
            correo.setText("correo: " + mAuth.getCurrentUser().getEmail());
        }

    }


    //método botón insertarEvento
    //aqui irá la sentencia sql para enviarlo a la bases de datos
    public void insertarbtn(View view) {
        //paso spinner a string
        horaSeleccionada = spinnerHora.getSelectedItem().toString();
        descripcionEvento = spinnerDescripcion.getSelectedItem().toString();
        //guardo los edt en strings y int para poderlo enviar a mysql.
        correoUsuario = correo.getText().toString();
        //descripcionEvento= String.valueOf(edt_descripcion.getText().toString());
        
        System.out.println("fecha incorporda:  " + fecha_recibida);
        System.out.println("correo:  " + correoUsuario);
        System.out.println("hora:  " + horaSeleccionada);
        System.out.println("descripcion:  " + descripcionEvento);


        Evento e = new Evento(fecha_recibida,descripcionEvento,horaSeleccionada,correoUsuario); //Clase Usuario. Pasamos al constructor los datos del usuario para guardarlo.
        boolean insercionOK = EventoController.insertarEvento(e);
        if(insercionOK)
        {
            Toast.makeText(this,"evento creado correctamente en sql", Toast.LENGTH_SHORT).show();
            Intent insertar = new Intent(this,CalendarioActivity.class);
            startActivity(insertar);
        }

        else
        {
            Toast.makeText(this,"No se pudo guardar la evento en sql", Toast.LENGTH_SHORT).show();
        }



    }

    //método botón volver
    public void volverbtn(View view) {
        Toast.makeText(getApplicationContext(), "Iniciar Pantalla Mostrar", Toast.LENGTH_SHORT).show();
        Intent volver = new Intent(this, CalendarioActivity.class);
        startActivity(volver);
    }
}