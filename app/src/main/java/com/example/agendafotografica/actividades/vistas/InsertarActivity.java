package com.example.agendafotografica.actividades.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agendafotografica.R;
import com.example.agendafotografica.actividades.clases.Evento;

public class InsertarActivity extends AppCompatActivity {

    TextView fechaIncorporada;

    private EditText edtNombre = null;
    private EditText edtApellidos = null;
    private EditText edtTelefono = null;
    private EditText edtCorreo = null;
    private EditText edtDescripcion = null;
    private Spinner spinner;

    private String horaSeleccionada = "";

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
        edtDescripcion = (EditText) findViewById(R.id.edtDescripcion);
        spinner = (Spinner) findViewById(R.id.spinnerHora);

        //atributos spinner
        String [] horas = {"15.00", "17.00", "19.00"};
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, horas);
        spinner.setAdapter(adapter);



        Intent intent = getIntent();
        String p = intent.getStringExtra(CalendarioActivity.enviarFecha);

        Evento e = (Evento) intent.getSerializableExtra(CalendarioActivity.enviarFecha);
        String fecha = e.getEvento();
        String fecha_recibida = fecha;
        fechaIncorporada.setText(fecha_recibida);


    }


    //método botón insertarEvento
    //aqui irá la sentencia sql para enviarlo a la bases de datos
    public void insertarbtn(View view) {

        horaSeleccionada = spinner.getSelectedItem().toString();
        System.out.println(horaSeleccionada);


    }

    //método botón volver
    public void volverbtn(View view) {
        Toast.makeText(getApplicationContext(), "Iniciar Pantalla Mostrar", Toast.LENGTH_SHORT).show();
        Intent volver = new Intent(this, CalendarioActivity.class);
        startActivity(volver);
    }
}