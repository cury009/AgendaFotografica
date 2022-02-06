package com.example.agendafotografica.actividades.vistas;

import android.content.Intent;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.agendafotografica.R;
import com.google.firebase.auth.FirebaseAuth;
//import com.example.agendafotografica.actividades.vistas.LoginActivity;


//import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    private Button facebook;
    String url = "https://de-de.facebook.com/Raul-Salva-Jimeno-Fotografia-101462022196478/?ref=page_internal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);    }


    //método boton calendario
    public void calendario(View view) {
        Toast.makeText(getApplicationContext(), "Iniciar Pantalla Calendario", Toast.LENGTH_SHORT).show();
        Intent calendario = new Intent(this, CalendarioActivity.class);
        startActivity(calendario);

    }
    //método botón info
    public void info(View view) {
        Toast.makeText(getApplicationContext(), "Iniciar Pantalla Info", Toast.LENGTH_SHORT).show();
        Intent info = new Intent(this, InfoActivity.class);
        startActivity(info);

    }
    //método botón facebook
    public void facebook(View view) {
        Toast.makeText(getApplicationContext(), "Iniciar Pantalla Facebook", Toast.LENGTH_SHORT).show();
        Uri facebook= Uri.parse(url);
        Intent i = new Intent(Intent.ACTION_VIEW,facebook);
        startActivity(i);

    }
    //método botón cerrarSesion

    public void cerrarSesion(View view) {
        Toast.makeText(getApplicationContext(), "Cerrar Sesion Calendario", Toast.LENGTH_SHORT).show();
        FirebaseAuth.getInstance().signOut();
        Intent cerrarSesion = new Intent(this, LoginActivity.class);
        startActivity(cerrarSesion);

    }



}

