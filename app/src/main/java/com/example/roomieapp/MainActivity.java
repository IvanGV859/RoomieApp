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

public class MainActivity extends AppCompatActivity {

    private EditText correo;
    private EditText contrasenia;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        correo = findViewById(R.id.txt_correo_main);
        contrasenia = findViewById(R.id.txt_contrasenia_main);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        Log.i("User", ""+currentUser);
    }

    public void irRegistro (View view) {
        Intent i = new Intent(this, Registro.class);
        startActivity(i);
    }

    public void RestablecerContrasenia (View view) {
        Intent i = new Intent(this, ContraseniaNueva.class);
        startActivity(i);
    }

    public void IniciarSesion (String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(mAuth.getCurrentUser().isEmailVerified()) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("Exito", "Bienvenido");
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                                Intent i = new Intent(getApplicationContext(), Principal.class);
                                startActivity(i);
                                finish();
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Error", "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Hubo un error", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public void BotonIniciar (View view) {
        String email = correo.getText().toString();
        String contra = contrasenia.getText().toString();

        if(!email.isEmpty() && !contra.isEmpty()){

            if (contra.length()>5)
            {
                IniciarSesion(email, contra);

            }else {
                Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "Favor de llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

}
