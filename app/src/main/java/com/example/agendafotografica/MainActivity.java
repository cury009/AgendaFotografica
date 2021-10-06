package com.example.agendafotografica;

import android.net.Uri;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.agendafotografica.fragments.InicioFragment;
import com.example.agendafotografica.interfaces.IComunicaFragments;

public class MainActivity extends AppCompatActivity implements IComunicaFragments,InicioFragment.OnFragmentInteractionListener {

    Fragment fragmentInicio;

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
    }
    @Override
    public void iniciarCerrarSesion() {

        Toast.makeText(getApplicationContext(), "Iniciar Cerrar Sesion desde el activity", Toast.LENGTH_SHORT).show();
    }
}