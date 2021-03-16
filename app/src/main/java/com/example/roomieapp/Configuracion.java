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
        //Redirect activity ti dashboar
        InterfazPrincipal.redirectActivity(this, Perfil.class);
    }
    public void ClickRent(View view){
        //Redirect activity ti dashboar
        InterfazPrincipal.redirectActivity(this, Rentar.class);
    }

    public void ClickConfiguracion(View view){
        //Recreate activity
        recreate();

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
