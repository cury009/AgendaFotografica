package com.example.agendafotografica.actividades.vistas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
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

    //variable constante
    public static final String ENVIAR_CORREO_REGISTRAR ="nombre";

    //variables
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
        setContentView(R.layout.activity_registrar); //enlazamos con el layout activity_registar

        //emparejamos con los campos
        edtNombre = (EditText) findViewById(R.id.edtName);
        edtApellidos = (EditText) findViewById(R.id.edtSurname);
        edtTelefono = (EditText) findViewById(R.id.edtTelefono);
        edtCorreo = (EditText) findViewById(R.id.edtCorreo);
        edtPass = (EditText) findViewById(R.id.edtPass);

        //spinner = (Spinner) findViewById(R.id.spinnerRol);

        /*
        //atributos spinnerRol
        String [] rol= {"administrador", "cliente"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, rol);
        spinner.setAdapter(adapter);


         */


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


    //metodo para validar correo
    private boolean validateEmailAddress(EditText edtCorreo) { //devuelve true o false si el campo edt_mail
        correo = String.valueOf(edtCorreo.getText()); //lo guardamos en formato String
        if (!correo.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(correo).matches()) { //si cumple las condicinones de correo devuelve true

            return true;
        } else { //si no las cumple, salta error en el edtCorreo y devuelve falso

            edtCorreo.setError("Escribe el correo correctamente"); //icono de error y mensaje
            edtCorreo.requestFocus();
            return false;
        }
    }
    //metodo para validar Contraseña
    private boolean validatePassword(EditText edtPass) { //devuelve true o false si el campo edtClave
        password = String.valueOf(edtPass.getText()); //lo guardamos en formato String
        if (!password.isEmpty()) { //si contraseña no esta vacio

            return true; //devuelve true
        } else { //si contraseña esta vacio

            edtPass.setError("Escribe una contraseña"); //icono de error y mensaje
            edtPass.requestFocus();
            return false; //devuelve false
        }
    }
    //metodo para validar Nombre
    private boolean validateName(EditText edtNombre) { //devuelve true o false si el campo edtnombre
        nombre = String.valueOf(edtNombre.getText()); //lo guardamos en formato String
        if (!nombre.isEmpty()) { //si nombre
            // no esta vacio

            return true; //devuelve true
        } else { //si nombre esta vacio

            edtNombre.setError("Escribe una nombre"); //icono de error y mensaje
            edtNombre.requestFocus();
            return false; //devuelve false
        }
    }
    //metodo para validar Apellidos
    private boolean validateSurName(EditText edtApellidos) { //devuelve true o false si el campo edtApellidos
        apellidos = String.valueOf(edtApellidos.getText()); //lo guardamos en formato String
        if (!apellidos.isEmpty()) { //si apellidos no esta vacio

            return true; //devuelve true
        } else { //si apellidos esta vacio

            edtApellidos.setError("Escribe apellidos"); //icono de error y mensaje
            edtApellidos.requestFocus();
            return false; //devuelve false
        }
    }
    //metodo para validar Telefono
    private boolean validatePhone(EditText edtTelefono) { //devuelve true o false si el campo edtTelefono
        String telefono = String.valueOf(edtTelefono.getText()); //lo guardamos en formato String
        if (!telefono.isEmpty()) { //si telefono no esta vacio

            return true; //devuelve true
        } else { //si telefono esta vacio

            edtTelefono.setError("Escribe un telefono"); //icono de error y mensaje
            edtTelefono.requestFocus();
            return false; //devuelve false
        }
    }
    //metodo registrar Usuario
    public void registrarUsuario (View view) {
        validateEmailAddress(edtCorreo); //llamamos al metodo validacion de correo
        validatePassword(edtPass); //llamamos al metodo validacion de contraseña
        validateName(edtNombre); //llamamos al metodo validacion de contraseña
        validateSurName(edtApellidos); //llamamos al metodo validacion de contraseña
        validatePhone(edtTelefono); //llamamos al metodo validacion de contraseña
        String email = String.valueOf(edtCorreo.getText()).trim(); //guardamos correo en un string
        String password = String.valueOf(edtPass.getText()); //guardamos contraseña en un string
        if (validateEmailAddress(edtCorreo) == true && validatePassword(edtPass) == true  && validateName(edtNombre) == true
                && validateSurName(edtApellidos) == true && validatePhone(edtTelefono) == true){
            //si correo, contraseña, nombre, apellidos y telefono es true entra
            mAuth.createUserWithEmailAndPassword(email, password) //firebase le pasamos email y password
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.i("firebasedb", "createUserWithEmail:success");

                                Toast.makeText(RegistrarActivity.this, "Usuario creado con exito en Firebase", Toast.LENGTH_SHORT).show();

                                FirebaseUser user = mAuth.getCurrentUser();
                                // updateUI(user);
                                String correo_enviarRegistrar = edtCorreo.getText().toString(); //enviar correo a otras pantallsa
                                //String password_enviar
                                Intent intent = new Intent(RegistrarActivity.this, MainActivity.class); //redirigir a pantalla MainActivity
                                intent.putExtra(ENVIAR_CORREO_REGISTRAR, correo_enviarRegistrar); //pasamos el correo
                                startActivity(intent);
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.i("firebasedb", "fallo en crear el usuario en Firebase", task.getException());
                                Toast.makeText(RegistrarActivity.this, "fallo al crear el usuairo en Firebase", Toast.LENGTH_SHORT).show();
                                //  updateUI(null);
                            }
                        }
                    });
            guardarVariablesenSql();
        } else {
            Toast.makeText(RegistrarActivity.this, "fallo al registrar", Toast.LENGTH_LONG).show();
        }




    }

    //metodo para guardar variables en una clase para pasarselo al sql
    public void guardarVariablesenSql() {
        //guardo los edt en strings y int para poderlo enviar a mysql.
        correo = String.valueOf(edtCorreo.getText().toString());
        password = String.valueOf(edtPass.getText().toString());
        nombre = String.valueOf(edtNombre.getText().toString());
        apellidos = String.valueOf(edtApellidos.getText().toString());
        phone = Integer.valueOf(edtTelefono.getText().toString());

        //spinner.getSelectedItem().toString();
        //rol= (String)spinner.getSelectedItem();
        //rol = String.valueOf(spinner);
        rol = String.valueOf("cliente");

        //spinner = String.valueOf(spinnerRol.getText().toString());


        //creo un usuario
        Usuario u = new Usuario(correo,password,nombre,apellidos,phone,rol); //Clase Usuario. Pasamos al constructor los datos del usuario para guardarlo.
        boolean insercionOK = UsuarioController.insertarUsuario(u);
        if(insercionOK)
        {
            Toast.makeText(this,"usuario creado correctamente en sql", Toast.LENGTH_SHORT).show();
            Intent registrar = new Intent(this,MainActivity.class); //creo una vista
            startActivity(registrar); //abrimos pantalla MainActicity
            finish();
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