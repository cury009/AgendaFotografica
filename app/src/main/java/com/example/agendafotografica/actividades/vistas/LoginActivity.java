package com.example.agendafotografica.actividades.vistas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agendafotografica.R;
import com.example.agendafotografica.actividades.clases.Evento;
import com.example.agendafotografica.actividades.clases.Usuario;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;

public class LoginActivity extends AppCompatActivity {

    public static final String ENVIAR_CORREO_LOGIN ="nombre";


    //private Button btnSignIn;
    private EditText edt_email;
    private EditText edt_clave;
    //Constante
    int RC_SIGN_IN = 1;
    String TAG = "GoogleSignIn";
    //Variable para gestionar FirebaseAuth
    private FirebaseAuth mAuth;
    //Agregar cliente de inicio de sesión de Google
    private GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //emparejar objetos del diseño con la clase java
        edt_email = findViewById(R.id.edtEmail);
        edt_clave = findViewById(R.id.edtPassword);

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

    public void loguearse(View view) {
        String email = String.valueOf(edt_email.getText());
        String password = String.valueOf(edt_clave.getText());
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("firebasedb", "signInWithEmail:success");
                            Toast.makeText(LoginActivity.this, "Exito!", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                            System.out.println("se ve?" + user.getEmail());
                            //String correo_enviarLogin = edt_email.getText().toString();
                            String correo_enviarLogin =user.getEmail(); //recoger email
                            Usuario u = new Usuario(correo_enviarLogin); //Clase Usuario. Pasamos al constructor el string usuario para guardarlo.
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra(ENVIAR_CORREO_LOGIN, user.getEmail());

                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i("firebasedb", "Datos incorrectos", task.getException());
                            Toast.makeText(LoginActivity.this, "signInWithEmail:failure", Toast.LENGTH_SHORT).show();
                            // updateUI(null);
                        }
                    }
                });
    }

    public void registrar(View view)
    {
        Intent i = new Intent(LoginActivity.this, RegistrarActivity.class);
        startActivity(i);
        /*
        String email = String.valueOf(edt_email.getText()).trim();
        String password = String.valueOf(edt_clave.getText());
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("firebasedb", "createUserWithEmail:success");
                            Toast.makeText(LoginActivity.this, "createUserWithEmail:success", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            // updateUI(user);
                            Intent intent = new Intent(LoginActivity.this, RegistrarActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i("firebasedb", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            //  updateUI(null);
                        }
                    }
                });*/
    }

    public void salir(View view) {
        FirebaseAuth.getInstance().signOut();
    }


    //========================================================================================================firebase
    /*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Resultado devuelto al iniciar el Intent de GoogleSignInApi.getSignInIntent (...);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            if (task.isSuccessful()) {
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                    firebaseAuthWithGoogle(account.getIdToken());
                } catch (ApiException e) {
                    // Google Sign In fallido, actualizar GUI
                    Log.w(TAG, "Google sign in failed", e);
                }
            } else {
                Log.d(TAG, "Error, login no exitoso:" + task.getException().toString());
                Toast.makeText(this, "Ocurrio un error. " + task.getException().toString(),
                        Toast.LENGTH_LONG).show();
            }
        }
    }*/
    /*
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            //FirebaseUser user = mAuth.getCurrentUser();
                            //Iniciar DASHBOARD u otra actividad luego del SigIn Exitoso
                            Intent MainActivity = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(MainActivity);
                            LoginActivity.this.finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());

                        }
                    }
                });
    }


    */


    /*
    @Override
    protected void onStart() {
        FirebaseUser user = mAuth.getCurrentUser();
        if(user!=null){ //si no es null el usuario ya esta logueado
            //mover al usuario al dashboard
            user.getEmail();

            
            Intent dashboardActivity = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(dashboardActivity);
        }
        super.onStart();
    }*/
    //====================================================================================================================



    /*private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }*/

    //método boton google
    public void loginGoogle (View view) {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();

        startActivityForResult(signInIntent, RC_SIGN_IN);
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
    }
    //método pasar al MainActivity
    /*public void irMainActivity (View view){

        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
    }*/

}