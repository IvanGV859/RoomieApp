package com.example.roomieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.roomieapp.adapter.AdapterDepartamentos;
import com.example.roomieapp.adapter.AdapterDepartamentosPerfil;
import com.example.roomieapp.pojo.Departamentos;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.EventListener;

public class Perfil extends AppCompatActivity {

    //Initialize variable
    DrawerLayout drawerLayout;


    ArrayList<Departamentos> list;
    RecyclerView recyclerView;
    AdapterDepartamentosPerfil adapter;
    DatabaseReference databaseReference, datareference1;

    private TextView nombreUsuario, nombreUsuarioPerfil;
    private LinearLayoutManager lm;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        //Assign variable
        drawerLayout = findViewById(R.id.drawer_layout);

        mAuth = FirebaseAuth.getInstance();
        String id = mAuth.getCurrentUser().getUid();
        nombreUsuario = (TextView) findViewById(R.id.txt_nombre1);
        nombreUsuarioPerfil = (TextView) findViewById(R.id.txt_nombreUsuario);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        datareference1 = FirebaseDatabase.getInstance().getReference();
        recyclerView = findViewById(R.id.recyclerView_Perfil);
        lm = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(lm);
        list = new ArrayList<>();
        adapter = new AdapterDepartamentosPerfil(list);
        recyclerView.setAdapter(adapter);

        databaseReference.child("Departamentos").orderByChild("Usuario").equalTo(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    list.clear();

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


        datareference1.child("Usuarios").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String nombre = snapshot.child("Usuario").getValue().toString();
                nombreUsuario.setText(nombre);
                nombreUsuarioPerfil.setText(nombre);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void ClickMenu(View view){
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
        //Recreate activity
        recreate();
    }

    public void ClickConfiguracion(View view){
        //Redirect activity ti about us
        Buscar.redirectActivity(this, Configuracion.class);
    }
    public void ClickRent(View view){
        //Redirect activity ti about us
        Buscar.redirectActivity(this, Rentar.class);
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
