package com.example.roomieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ContraseniaNueva extends AppCompatActivity {

    private EditText correoNuevaContra;
    private String correo = "";

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contrasenia_nueva);

        correoNuevaContra = (EditText)findViewById(R.id.txt_correoNuevaContra);

        mAuth = FirebaseAuth.getInstance();

    }

    public void restablecerContrasenia(View view){
        correo = correoNuevaContra.getText().toString();
        if(!correo.isEmpty())
        {
            mAuth.signOut();
            mAuth.setLanguageCode("es");
            mAuth.sendPasswordResetEmail(correo).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(ContraseniaNueva.this, "Se esta enviando el correo de restablecer contraseña", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        finish();
                    }else {
                        Toast.makeText(ContraseniaNueva.this, "No se pudo enviar el correo de restablecer contraseña", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }else{
            Toast.makeText(this, "Favor de ingresar un correo", Toast.LENGTH_SHORT).show();
        }
    }

}