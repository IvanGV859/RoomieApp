package com.example.roomieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registro extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText correo;
    private EditText contrasenia;
    private EditText contrasenia2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        correo = findViewById(R.id.txt_correo);
        contrasenia = findViewById(R.id.txt_contrasenia);
        contrasenia2 = findViewById(R.id.txt_contrasenia2);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        Log.i("User;", ""+currentUser);
    }


    public  void RegistrarUsuario (String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("EXITO", "Creando usuario");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            user.sendEmailVerification();
                            Intent i = new Intent(getApplicationContext(), Registro.class);
                            startActivity(i);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("ERROR", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Hubo un error.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public void BotonRegistro (View view){
        String email = correo.getText().toString();
        String contra = contrasenia.getText().toString();
        String contra2 = contrasenia2.getText().toString();

        if (!email.isEmpty() && !contra.isEmpty() && !contra2.isEmpty()){

            if (contra.equals(contra2)){

                if (contra.length()>5){

                    RegistrarUsuario(email, contra);

                }else
                    Toast.makeText(this, "La contraseña debe de ser de 6 caracteres o mas", Toast.LENGTH_SHORT).show();


            }else
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();


        }else
            Toast.makeText(this, "Favor de llenar todos los campos", Toast.LENGTH_SHORT).show();

    }

}