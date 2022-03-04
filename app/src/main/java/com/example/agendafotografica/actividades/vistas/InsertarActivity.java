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


        Intent intent = getIntent();
        //recojo objetos
        String p = intent.getStringExtra(CalendarioActivity.enviarFecha);
        String u = intent.getStringExtra(LoginActivity.ENVIAR_CORREO_LOGIN);

        //U
        //Evento e = (Evento) intent.getSerializableExtra(CalendarioActivity.enviarFecha);
        //String fecha = e.getEvento();
        String fecha = (String) intent.getSerializableExtra(CalendarioActivity.enviarFecha);
        fecha_recibida = fecha;
        //String correo1 = us.getCorreo();
        //String correo_recibido = correo1;
        fechaIncorporada.setText(fecha_recibida);
        //correo.setText("correo: "+ correo_recibido);


        //atributos spinner --borrar si sale bien
        /*String [] horas = {"15:00", "17:00", "19:00"};
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, horas);
        spinnerHora.setAdapter(adapter);

         */

        //creo una un arraylist para añadir horas
        //quiero hacer una consulta si esa hora del dia esta ocupada, en el caso que esté sin ocupar añadir al arraylist
        ArrayList<String> myList = new ArrayList<String>();

        //boolean horaOK = EventoController.saberSiHoraEstaOcupada(spinnerHora);
        //if (horaOK) {
        myList.add("15:00");
        //}
        //else {
        //si esta ocupado no añadir
        //}

        myList.add("17:00");
        myList.add("19:00");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, myList);
        spinnerHora.setAdapter(adapter);

        //atributos spinner
        String[] descripcion = {"instagram", "festival", "boda", "comunion", "bautizo", "pareja"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, descripcion);
        spinnerDescripcion.setAdapter(adapter1);


        //cogemos el correo
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            mAuth.getCurrentUser().getEmail(); //el email
            correo.setText(mAuth.getCurrentUser().getEmail());
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
        boolean horaOK = EventoController.saberSiHoraEstaOcupado(fecha_recibida, horaSeleccionada);
        if (horaOK) { //rellenar con el metodo de consulta hora ocupada
            //EventoController.saberSiHoraEstaOcupado(fecha_recibida, horaSeleccionada);
            Toast.makeText(this, "hora no ocupada", Toast.LENGTH_SHORT).show();
            Evento e = new Evento(fecha_recibida, descripcionEvento, horaSeleccionada, correoUsuario); //Clase Usuario. Pasamos al constructor los datos del usuario para guardarlo.
            boolean insercionOK = EventoController.insertarEvento(e);
            if (insercionOK) { //si es true inserta
                Toast.makeText(this, "evento creado correctamente en sql", Toast.LENGTH_SHORT).show();
                Intent insertar = new Intent(this, CalendarioActivity.class);
                startActivity(insertar);
            } else { //si es false= esta ocupado
                Toast.makeText(this, "No se pudo guardar la evento en sql", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "hora ocupada", Toast.LENGTH_SHORT).show();

        }
    }

    //método botón volver
    public void volverbtn(View view) {
        //.makeText(getApplicationContext(), "Iniciar Pantalla Mostrar", Toast.LENGTH_SHORT).show();
        Intent volver = new Intent(this, CalendarioActivity.class);
        startActivity(volver);
    }
}