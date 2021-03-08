package com.example.roomieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText correo;
    private EditText contrasenia;
    private FirebaseAuth mAuth;
    //Prueba5555555555555555
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_main);

        //change actionbar title, if you dont change it will be according to your
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));

        Button cambiarLenguaje = findViewById(R.id.CambiarMiLenguaje);
        cambiarLenguaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //show AlertDialog to display list of lenguages
                showDialogoCambioLenguaje();
            }
        });

        correo = findViewById(R.id.txt_correo_main);
        contrasenia = findViewById(R.id.txt_contrasenia_main);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

    }
//  lets create separate strings.xml for each languaje first
    private void showDialogoCambioLenguaje() {
        // array of lenguages to display in alert dialog
        final String[] listItems = {"English", "Español"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        mBuilder.setTitle("Selecciona Idioma...");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i){
                if (i == 0){
                    //English
                    setLocale("en");
                    recreate();
                }
                if (i == 1){
                    //Español
                    setLocale("es");
                    recreate();
                }

                //dismiss alert dialog when language selected
                dialogInterface.dismiss();

            }
        });

        AlertDialog mDialog = mBuilder.create();
        //show alert dialog
        mDialog.show();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        // save data to shared preference
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }
    //load languagesaved in shared preference
    public void loadLocale(){
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        setLocale(language);
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
