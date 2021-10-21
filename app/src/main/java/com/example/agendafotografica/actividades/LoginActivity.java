package com.example.agendafotografica.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.agendafotografica.MainActivity;
import com.example.agendafotografica.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void irMainActivity(View view) {

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}