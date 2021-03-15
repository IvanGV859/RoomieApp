package com.example.roomieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

public class Dashboard extends AppCompatActivity {

    //Initialize variable
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

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

    public void ClickDashboard(View view){
        //Recreate activity
        recreate();
    }

    public void ClickAboutUs(View view){
        //Redirect activity ti about us
        InterfazPrincipal.redirectActivity(this,Aboutus.class);
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
