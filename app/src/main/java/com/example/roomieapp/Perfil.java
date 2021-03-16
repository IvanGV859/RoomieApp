package com.example.roomieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

public class Perfil extends AppCompatActivity {

    //Initialize variable
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        //Assign variable
        drawerLayout = findViewById(R.id.drawer_layout);
    }

    public void ClickMenu(View view){
        //Open drawer
        InterfazPrincipal.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view){
        //Closer Drawer
        InterfazPrincipal.closerDrawer(drawerLayout);
    }

    public void ClickHome(View view){
        //Redirect activity to home
        InterfazPrincipal.redirectActivity(this,InterfazPrincipal.class);
    }

    public void ClickPerfil(View view){
        //Recreate activity
        recreate();
    }

    public void ClickConfiguracion(View view){
        //Redirect activity ti about us
        InterfazPrincipal.redirectActivity(this, Configuracion.class);
    }
    public void ClickRent(View view){
        //Redirect activity ti about us
        InterfazPrincipal.redirectActivity(this, Rentar.class);
    }

    public void ClickLogout(View view){
        //Close app
        InterfazPrincipal.logout(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Closer Drawer
        InterfazPrincipal.closerDrawer(drawerLayout);
    }
}
