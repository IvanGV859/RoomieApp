package com.example.roomieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

public class Rentar extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rentar);

        drawerLayout = findViewById(R.id.drawer_layout2);
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
    public void ClickConfiguracion(View view){
        //Redirect activity ti dashboar
        InterfazPrincipal.redirectActivity(this, Configuracion.class);
    }

    public void ClickRent(View view){
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
