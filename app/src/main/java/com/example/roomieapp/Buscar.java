package com.example.roomieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Buscar extends AppCompatActivity {
    //Initialize variable
    DrawerLayout drawerLayout;

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    private TextView nombre;
    private TextView municipio;
    private TextView colonia;
    private TextView descripcion;
    private TextView costo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        //Assign variable
        drawerLayout = findViewById(R.id.drawer_layout);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        nombre = findViewById(R.id.txt_nombreDepa_FB);
        municipio = findViewById(R.id.txt_municipio_FB);
        colonia = findViewById(R.id.txt_colonia_FB);
        descripcion = findViewById(R.id.txt_descripcion_FB);
        costo = findViewById(R.id.txt_costo_FB);

        String id = firebaseAuth.getCurrentUser().getUid();
        databaseReference.child("Departamentos").child(id).child("Busco 2 roomies").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    String nombreFB = snapshot.child("Nombre").getValue().toString();
                    String municipioFB = snapshot.child("Municipio").getValue().toString();
                    String coloniaFB = snapshot.child("Lugar").getValue().toString();
                    String descripcionFB = snapshot.child("Descripcion").getValue().toString();
                    String costoFB = snapshot.child("Costo").getValue().toString();

                    nombre.setText("Nombre del departamento: " + nombreFB);
                    municipio.setText("Municipio: " + municipioFB);
                    colonia.setText("Colonia: " + coloniaFB);
                    descripcion.setText("Descripcion: " + descripcionFB);
                    costo.setText("Costo por mes: " + costoFB);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void ClickMenu (View view){
        //Open drawer
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
    //Open drawer layout
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view){
        //Closer drawer
        closerDrawer(drawerLayout);
    }

    public static void closerDrawer(DrawerLayout drawerLayout) {
        //Close drawer layout
        //Check condition
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            //When drawer is open
            //Close drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickHome(View view){
        //Redirect activity to home
        Buscar.redirectActivity(this, Principal.class);
    }

    public void ClickPerfil(View view){
        //Redirect activity to dashboard
        redirectActivity(this, Perfil.class);
    }

    public void ClickConfiguracion(View view){
        //Redirect activity to AboutUs
        redirectActivity(this, Configuracion.class);
    }
    public void ClickRent(View view){
        //Redirect activity to AboutUs
        redirectActivity(this, Rentar.class);
    }

    public void ClickLogout(View view){
        //Redirect activity to Logout
        logout(this);
    }

    public static void logout(final Activity activity){
        //Initialize alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        //set title
        builder.setTitle("Logout");
        //Set message
        builder.setMessage("¿Está seguro que desea cerrar sesión? ");
        //Possitive yes botton
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Finish activity
                activity.finishAffinity();
                //Exit app
                System.exit(0);
            }
        });
        //Negative button
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Dimiss dialog
                dialog.dismiss();
            }
        });
        //Show dialog
        builder.show();
    }

    public static void redirectActivity(Activity activity,Class aClass) {
        //Initialize intent
        Intent intent = new Intent(activity,aClass);
        //Set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //Start activity
        activity.startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Closer Drawer
        closerDrawer(drawerLayout);
    }
}
