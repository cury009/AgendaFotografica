package com.example.agendafotografica;

import android.content.Intent;
import android.net.Uri;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.agendafotografica.actividades.LoginActivity;
import com.example.agendafotografica.fragments.InicioFragment;
import com.example.agendafotografica.interfaces.IComunicaFragments;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements IComunicaFragments,InicioFragment.OnFragmentInteractionListener {

    Fragment fragmentInicio;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentInicio = new InicioFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,fragmentInicio).commit();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void iniciarCalendario() {

        Toast.makeText(getApplicationContext(), "Iniciar Calendario desde el activity", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void iniciarInfo() {

        Toast.makeText(getApplicationContext(), "Iniciar Info desde el activity", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void iniciarFacebook() {

        Toast.makeText(getApplicationContext(), "Iniciar Facebook desde el activity", Toast.LENGTH_SHORT).show();
        Intent facebook = new Intent(Intent.ACTION_VIEW, Uri.parse("https://de-de.facebook.com/Raul-Salva-Jimeno-Fotografia-101462022196478/?ref=page_internal"));
        startActivity(facebook);

    }
    @Override
    public void CerrarSesion() {

        //Cerrar session con Firebase
        mAuth.signOut();
        //Abrir MainActivity
        Intent LoginActivity = new Intent(getApplicationContext(),
                MainActivity.class);
        startActivity(LoginActivity);
        MainActivity.this.finish();
        Toast.makeText(getApplicationContext(), " Cerrar Sesion desde el activity", Toast.LENGTH_SHORT).show();
    }
}