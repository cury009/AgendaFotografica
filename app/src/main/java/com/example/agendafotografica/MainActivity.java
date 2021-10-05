package com.example.agendafotografica;

import android.net.Uri;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.agendafotografica.fragments.InicioFragment;

public class MainActivity extends AppCompatActivity implements InicioFragment.OnFragmentInteractionListener {

    Fragment fragmentInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentInicio = new InicioFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,fragmentInicio).commit();
    }
    //@Override
    public void onFragmentInteraction(Uri uri) {

    }
}