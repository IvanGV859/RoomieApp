package com.example.roomieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

public class Configuracion extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        drawerLayout = findViewById(R.id.drawer_layout);
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
        Buscar.redirectActivity(this, Buscar.class);
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
