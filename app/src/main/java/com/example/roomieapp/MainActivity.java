package com.example.roomieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //fklnsflknflkbv
        //Esto es un comentario
        //Este es otro comentario
        //Nuevo nuevo comentari
    }

    public void irRegistro (View view) {
        Intent i = new Intent(this, Registro.class);
        startActivity(i);
    }
}
