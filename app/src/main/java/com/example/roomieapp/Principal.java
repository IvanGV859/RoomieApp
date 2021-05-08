package com.example.roomieapp;

import androidx.appcompat.app.AppCompatActivity;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class Principal extends AppCompatActivity {

    private FirebaseAuth mAuth;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //Assign variable
        drawerLayout = findViewById(R.id.drawer_layout);
        mAuth = FirebaseAuth.getInstance();
    }

    public void Principal (View view){
        Intent i = new Intent(this, Buscar.class);
        startActivity(i);
    }

    public void irRentar (View view){
        Intent i = new Intent(this, Rentar.class);
        startActivity(i);
    }

    public void ClickMensajeria (View view){
        Intent i = new Intent(this, Mensajeria.class);
        startActivity(i);
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


    public void ClickPerfil(View view){
        //Redirect activity to dashboard
        redirectActivity(this, Perfil.class);
    }

    public void ClickConfiguracion(View view){
        //Redirect activity to AboutUs
        redirectActivity(this, Configuracion.class);
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