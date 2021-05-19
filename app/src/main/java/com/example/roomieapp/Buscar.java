package com.example.roomieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.roomieapp.adapter.AdapterDepartamentos;
import com.example.roomieapp.pojo.Departamentos;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Buscar extends AppCompatActivity {
    //Initialize variable
    DrawerLayout drawerLayout;

    DatabaseReference databaseReference, databaseReference1;
    ArrayList<Departamentos> list;
    RecyclerView recyclerView;
    SearchView searchView;
    AdapterDepartamentos adapter;

    private LinearLayoutManager lm;
    private TextView nombreUsuario;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        //Assign variable
        drawerLayout = findViewById(R.id.drawer_layout);

        mAuth = FirebaseAuth.getInstance();
        String id = mAuth.getCurrentUser().getUid();

        databaseReference1 = FirebaseDatabase.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Departamentos");
        recyclerView = findViewById(R.id.recyclerView_departamentos);
        searchView = findViewById(R.id.searchView);
        lm = new LinearLayoutManager(this);
        nombreUsuario = (TextView) findViewById(R.id.txt_nombre1);

        recyclerView.setLayoutManager(lm);
        list = new ArrayList<>();
        adapter = new AdapterDepartamentos(list);
        recyclerView.setAdapter(adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Departamentos ms = dataSnapshot.getValue(Departamentos.class);
                        list.add(ms);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                buscar(s);
                return true;
            }
        });

        databaseReference1.child("Usuarios").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String nombre = snapshot.child("Usuario").getValue().toString();
                nombreUsuario.setText(nombre);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    } //Fin del onCreate

    public void MensajeOmar (View view){
        Intent i = new Intent(this, Main2Activity.class);
        startActivity(i);
    }
    private void buscar(String s)
    {
        ArrayList<Departamentos> milista = new ArrayList<>();
        for (Departamentos obj : list) {
            if (obj.getMunicipio().toLowerCase().contains(s.toLowerCase())) {
                milista.add(obj);
            }
        }
        AdapterDepartamentos adapter = new AdapterDepartamentos(milista);
        recyclerView.setAdapter(adapter);
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
