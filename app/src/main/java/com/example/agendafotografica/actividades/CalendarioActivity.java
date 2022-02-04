package com.example.agendafotografica.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agendafotografica.R;

import java.util.Calendar;

public class CalendarioActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private TextView dateValueTextView;
    private CardView seleccionarDia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        datePicker = (DatePicker) findViewById(R.id.date_picker);
        dateValueTextView = (TextView) findViewById(R.id.date_selected_text_view);
        seleccionarDia = (CardView) findViewById(R.id.cardseleccionarDia);

        // desactivado los dias anteriores a hoy
        Calendar today = Calendar.getInstance();
        long now = today.getTimeInMillis();
        datePicker.setMinDate(now);
    }

    //método boton selecciona el dia
    public void seleccionar(View view) {
        Toast.makeText(getApplicationContext(), "Selecciona el dia", Toast.LENGTH_SHORT).show();
        dateValueTextView.setText("Dia Seleccionado: " + datePicker.getDayOfMonth() + "/" +(datePicker.getMonth()+1)  + "/" + datePicker.getYear());


    }
    //método botón mostrar evento
    public void mostrar(View view) {
        Toast.makeText(getApplicationContext(), "Iniciar Pantalla Mostrar", Toast.LENGTH_SHORT).show();
        Intent mostrar = new Intent(this, MostrarActivity.class);
        startActivity(mostrar);

    }
    //método botón fijar evento
    public void insertar(View view) {
        Toast.makeText(getApplicationContext(), "Iniciar Pantalla Insertar", Toast.LENGTH_SHORT).show();
        Intent insertar = new Intent(this, InsertarActivity.class);
        startActivity(insertar);

    }
    //método botón borrar evento
    public void borrar(View view) {
        Toast.makeText(getApplicationContext(), "Iniciar Pantlla Borrar", Toast.LENGTH_SHORT).show();
        Intent borrar = new Intent(this, BorrarActivity.class);
        startActivity(borrar);

    }
}