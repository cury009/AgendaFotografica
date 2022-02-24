package com.example.agendafotografica.actividades.vistas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agendafotografica.R;
import com.example.agendafotografica.actividades.clases.Evento;
import com.example.agendafotografica.actividades.clases.EventoViewHolder;
import com.example.agendafotografica.actividades.controladores.EventoController;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MostrarDetallesEventoActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    TextView txt_detalle_ideventoe = null;
    TextView txt_detalle_correoe = null;
    TextView txt_detalle_descripcione = null;
    TextView txt_detalle_horae = null;
    TextView txt_detalle_fechae = null;

    //private Evento evento;
    Integer idEvento;
    private Evento eseleccionado;
    private ArrayAdapter<Evento> madapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_detalles_evento);
        mAuth = FirebaseAuth.getInstance();
        System.out.println("estamos en mostrarDetallesEventoActivity");
        txt_detalle_ideventoe = findViewById(R.id.txt_detalle_ideventoe);
        txt_detalle_correoe = findViewById(R.id.txt_detalle_nombree);
        txt_detalle_descripcione = findViewById(R.id.txt_detalle_descripcione);
        txt_detalle_horae = findViewById(R.id.txt_detalle_horae);
        txt_detalle_fechae = findViewById(R.id.txt_detalle_fechae);
        Intent intent = getIntent();
        if(intent != null)
        {
            Evento e = (Evento) intent.getSerializableExtra(EventoViewHolder.EXTRA_OBJETO_EVENTO);
            txt_detalle_correoe.setText(e.getCorreoUsuario());
            txt_detalle_ideventoe.setText("id evento: " + String.valueOf(e.getIdEvento()));
            txt_detalle_correoe.setText("correo: " + String.valueOf(e.getCorreoUsuario()));
            txt_detalle_descripcione.setText("descripcion: " + String.valueOf(e.getDescripcionEvento()));
            txt_detalle_horae.setText("hora: " + String.valueOf(e.getHoraSeleccionada()));
            txt_detalle_fechae.setText("fecha: " + String.valueOf(e.getFecha_recibida()));
            //System.out.println("quiero borrar idevento" +txt_detalle_ideventoe);
            idEvento = Integer.valueOf(e.getIdEvento());
            System.out.println("idevento que tiene?" +idEvento);

        }
    }



    //método botón borrar evento
    public void borrar(View view) {

        System.out.println("idevento que tiene EN BORRAR?" +idEvento);
        //Toast.makeText(getApplicationContext(), "Iniciar Pantlla Borrar", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder alerta1 = new AlertDialog.Builder(this);
        alerta1.setTitle("De verdad quieres borrar el curso?");
        //alerta1.setMessage(" no -> cancelar, si-> guardar");
        System.out.println("quiero borrar idevento dentro del metodo borrar" +idEvento);
        alerta1.setPositiveButton("si", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                //borrar evento

                //System.out.println("que tiene borrado?" + idEvento); //no selecciona el evento
                boolean borradoOK = EventoController.borrarEvento(idEvento, mAuth.getCurrentUser().getEmail()); //llamada a una funcion. Le pasas IdEvento
                //System.out.println("que tiene borrado?" + idEvento); //no selecciona el evento
                if(borradoOK)
                {
                    mostrarToast("evento borrado correctamente");
                    // actualizamos el spinner (como el livedata)
                    ArrayList<Evento> eventos = EventoController.obtenereventos(mAuth.getCurrentUser().getEmail());
                    //asignarAdaptadorSpinnerCursos(cursos);

                    //al finalizar vuelve a mostrar Activity
                    ArrayList<Evento> eventos1 = EventoController.obtenereventos(mAuth.getCurrentUser().getEmail()); //obtienes todos los eventos de la base de datos
                    if(eventos1 != null) { //si contiene cursos entra
                        MostrarActivity.mAdapter.setListaEventos(eventos1); //se lo asigna al adapttador
                    }
                    finish();

                }
                else{
                    mostrarToast("el evento no se pudo borrar");
                }
            }
        });
        alerta1.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alerta1.show();


    }







    //método botón volver
    public void volverbtn(View view) {
        //.makeText(getApplicationContext(), "Iniciar Pantalla Mostrar", Toast.LENGTH_SHORT).show();
        Intent volver = new Intent(this, CalendarioActivity.class);
        startActivity(volver);
    }

    public void mostrarToast(String mensaje)
    {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}