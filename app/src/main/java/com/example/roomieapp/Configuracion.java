package com.example.roomieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class Configuracion extends AppCompatActivity {

    DrawerLayout drawerLayout;
    private TextView nombreUsuario;
    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        mAuth = FirebaseAuth.getInstance();
        String id = mAuth.getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        drawerLayout = findViewById(R.id.drawer_layout);
        nombreUsuario = (TextView) findViewById(R.id.txt_nombre1);

        if(findViewById(R.id.fragment_container)!=null)
        {
            if (savedInstanceState!=null)
                return;

            getFragmentManager().beginTransaction().add(R.id.fragment_container,new SettingsFragment()).commit();

        }
        Button cambiarLenguaje = findViewById(R.id.CambiarMiLenguaje);
        cambiarLenguaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //show AlertDialog to display list of lenguages
                showDialogoCambioLenguaje();
            }
        });
        databaseReference.child("Usuarios").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String nombre = snapshot.child("Usuario").getValue().toString();
                nombreUsuario.setText(nombre);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void showDialogoCambioLenguaje() {
        // array of lenguages to display in alert dialog
        final String[] listItems = {"Ingles", "Español"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(Configuracion.this);
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


    public void ClickMenu (View view){
        //Open drawer
        Buscar.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view){
        //Closer Drawer
        Buscar.closerDrawer(drawerLayout);
    }

    public void ClickHome(View view){
        //Redirect activity to home
        Buscar.redirectActivity(this, Principal.class);
    }

    public void ClickPerfil(View view){
        //Redirect activity ti dashboar
        Buscar.redirectActivity(this, Perfil.class);
    }
    public void ClickRent(View view){
        //Redirect activity ti dashboar
        Buscar.redirectActivity(this, Rentar.class);
    }

    public void ClickConfiguracion(View view){
        //Recreate activity
        recreate();

    }

    public void ClickLogout(View view){
        //Close app
        Buscar.logout(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Closer Drawer
        Buscar.closerDrawer(drawerLayout);
    }




}
