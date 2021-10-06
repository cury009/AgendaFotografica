package com.example.agendafotografica.interfaces;

import android.net.Uri;

public interface IComunicaFragments {

    void onFragmentInteraction(Uri uri);

    public void iniciarCalendario();
    public void iniciarInfo();
    public void iniciarFacebook();
    public void iniciarCerrarSesion();
}
