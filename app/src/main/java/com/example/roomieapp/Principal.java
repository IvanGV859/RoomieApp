package com.example.roomieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class Principal extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        mAuth = FirebaseAuth.getInstance();
    }

    public void Principal (View view){
        Intent i = new Intent(this, InterfazPrincipal.class);
        startActivity(i);
    }

    public void irRentar (View view){
        Intent i = new Intent(this, Rentar.class);
        startActivity(i);
    }

    public void SalirSesion (View view){
        mAuth.signOut();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}