package com.example.agendafotografica.actividades.vistas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.agendafotografica.R;
import com.example.agendafotografica.actividades.clases.Evento;
import com.example.agendafotografica.actividades.clases.Usuario;
import com.example.agendafotografica.actividades.controladores.UsuarioController;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrarActivity extends AppCompatActivity {

    public static final String ENVIAR_CORREO_REGISTRAR ="nombre";

    private EditText edtNombre = null;
    private EditText edtApellidos = null;
    private EditText edtTelefono = null;
    private EditText edtCorreo = null;
    private EditText edtPass = null;
    private EditText edtDescripcion = null;
    private Spinner spinner;

    public String correo;
    public String password;
    public String nombre;
    public String apellidos;
    public int phone;
    public String rol;

    //Variable para gestionar FirebaseAuth
    private FirebaseAuth mAuth;
    //Agregar cliente de inicio de sesión de Google
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        //emparejamos java con el xml
        edtNombre = (EditText) findViewById(R.id.edtName);
        edtApellidos = (EditText) findViewById(R.id.edtSurname);
        edtTelefono = (EditText) findViewById(R.id.edtTelefono);
        edtCorreo = (EditText) findViewById(R.id.edtCorreo);
        edtPass = (EditText) findViewById(R.id.edtPass);

        spinner = (Spinner) findViewById(R.id.spinnerRol);

        //atributos spinnerRol
        String [] rol= {"administrador", "cliente"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, rol);
        spinner.setAdapter(adapter);



        // Configurar Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("374992452102-jq15gobr7vfvr1rrnbhf5d59ch8u6tsn.apps.googleusercontent.com")
                .requestEmail()
                .build();
        // Crear un GoogleSignInClient con las opciones especificadas por gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        //referencia al boton signIN y asignar el evento onClick
        //btnSignIn = findViewById(R.id.btnSignIn);


        // Inicializar Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    //creamos método registrarUsuario
    //metodo registrar Usuario
    public void registrarUsuario (View view) {
        String email = String.valueOf(edtCorreo.getText()).trim();
        String password = String.valueOf(edtPass.getText());
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("firebasedb", "createUserWithEmail:success");

                            Toast.makeText(RegistrarActivity.this, "Usuario creado con exito en Firebase", Toast.LENGTH_SHORT).show();

                            FirebaseUser user = mAuth.getCurrentUser();
                            // updateUI(user);
                            String correo_enviarRegistrar = edtCorreo.getText().toString();
                            Intent intent = new Intent(RegistrarActivity.this, MainActivity.class);
                            intent.putExtra(ENVIAR_CORREO_REGISTRAR, correo_enviarRegistrar);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i("firebasedb", "fallo en crear el usuario en Firebase", task.getException());
                            Toast.makeText(RegistrarActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            //  updateUI(null);
                        }
                    }
                });
        guardarVariablesenSql();


    }

    //metodo para guardar variables en una clase
    public void guardarVariablesenSql() {
        //guardo los edt en strings y int para poderlo enviar a mysql.
        correo = String.valueOf(edtCorreo.getText().toString());
        password = String.valueOf(edtPass.getText().toString());
        nombre = String.valueOf(edtNombre.getText().toString());
        apellidos = String.valueOf(edtApellidos.getText().toString());
        phone = Integer.valueOf(edtTelefono.getText().toString());

        spinner.getSelectedItem().toString();
        rol= (String)spinner.getSelectedItem();
        //rol = String.valueOf(spinner);

        //spinner = String.valueOf(spinnerRol.getText().toString());
        //prueba de que se guardan
        System.out.println("correo: " + correo);
        System.out.println("password: " + password);
        System.out.println("nombre: " + nombre);
        System.out.println("apellidos: " + apellidos);
        System.out.println("phone: " + phone);
        System.out.println("spinner: " + spinner);
        System.out.println("rol: " + rol);

        Usuario u = new Usuario(correo,password,nombre,apellidos,phone,rol); //Clase Usuario. Pasamos al constructor los datos del usuario para guardarlo.
        boolean insercionOK = UsuarioController.insertarUsuario(u);
        if(insercionOK)
        {
            Toast.makeText(this,"usuario creado correctamente en sql", Toast.LENGTH_SHORT).show();
            Intent registrar = new Intent(this,MainActivity.class);
            startActivity(registrar);
        }

        else
        {
            Toast.makeText(this,"No se pudo guardar la categoria en sql", Toast.LENGTH_SHORT).show();
        }
    }



    //método volver
    public void volverbtn(View view) {
        Intent volver = new Intent(this, LoginActivity.class);
        startActivity(volver);
    }

}