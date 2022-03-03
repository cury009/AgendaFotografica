package com.example.agendafotografica.actividades.vistas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agendafotografica.R;
import com.example.agendafotografica.actividades.clases.Evento;

import java.util.Calendar;

public class CalendarioActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private TextView dateValueTextView;
    private CardView seleccionarDia;

    private CardView insertarbtn;
    private CardView mostrarbtn;
    private CardView seleccionarbtn;
    public static final String enviarFecha= "fecha";
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);
        insertarbtn = findViewById(R.id.cardinsertar);
        mostrarbtn = findViewById(R.id.cardmostrar);
        //seleccionarbtn = findViewById(R.id.card);

        datePicker = (DatePicker) findViewById(R.id.date_picker);


        dateValueTextView = (TextView) findViewById(R.id.date_selected_text_view);
        seleccionarDia = (CardView) findViewById(R.id.cardseleccionarDia);

        // desactivado los dias anteriores a hoy
        Calendar today = Calendar.getInstance();
        long now = today.getTimeInMillis();
        datePicker.setMinDate(now);
        //dateValueTextView.setText(datePicker.se);

        
    }


    //método boton selecciona el dia
    public void seleccionar(View view) {
        //Toast.makeText(getApplicationContext(), "Selecciona el dia", Toast.LENGTH_SHORT).show();

        dateValueTextView.setText(datePicker.getDayOfMonth() + "/" +(datePicker.getMonth()+1)  + "/" + datePicker.getYear());

        if (!(dateValueTextView == null)) { //si tiene texto entra
            insertarbtn.setVisibility(View.VISIBLE);
            mostrarbtn.setVisibility(View.VISIBLE);
            /*seleccionarDia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    insertarbtn.setEnabled(true);
                }
            });

             */
        }



    }
    //método botón fijar evento
    public void insertar(View view) {
        Toast.makeText(getApplicationContext(), "Iniciar Pantalla Insertar", Toast.LENGTH_SHORT).show();

        String fecha = String.valueOf(dateValueTextView.getText()); //guardar la fecha en una variable String
        Intent insertar = new Intent(this, InsertarActivity.class); //dirige a la pantalla InsertarActivity

        //Evento e = new Evento(fecha); //Clase Evento. Pasamos al constructor el string fecha para guardarlo.
        insertar.putExtra(enviarFecha, fecha); //enviar a la otra pantalla, la fecha elegida.
        startActivity(insertar); //iniciar InsertarActivity

    }
    //método botón mostrar evento
    public void mostrar(View view) {

        Toast.makeText(getApplicationContext(), "Iniciar Pantalla Mostrar", Toast.LENGTH_SHORT).show();
        Intent mostrar = new Intent(this, MostrarActivity.class);
        startActivity(mostrar);


    }


    //método botón volver
    public void volverbtn(View view) {
        //.makeText(getApplicationContext(), "Iniciar Pantalla Mostrar", Toast.LENGTH_SHORT).show();
        Intent volver = new Intent(this, MainActivity.class);
        startActivity(volver);
    }


}