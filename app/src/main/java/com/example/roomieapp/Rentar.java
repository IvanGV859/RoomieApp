package com.example.roomieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Rentar extends AppCompatActivity {

    DrawerLayout drawerLayout;

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    private EditText nombre;
    private EditText descripcion;
    private EditText lugar;
    private EditText costo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rentar);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        nombre = findViewById(R.id.txt_nombreDepa);
        descripcion = findViewById(R.id.txt_descripDepa);
        lugar = findViewById(R.id.txt_coloniaDepa);
        costo = findViewById(R.id.txt_costoDepa);

        drawerLayout = findViewById(R.id.drawer_layout2);
    }

    public void subirDatos (View view){
        String nombreDepa = nombre.getText().toString();
        String descripDepa = descripcion.getText().toString();
        String lugarDepa = lugar.getText().toString();
        String costoDepa = costo.getText().toString();

        int i = (int) (Math.random() * 100 + 1);
        final String nombre = "Departamento" + i;

        if (!nombreDepa.isEmpty() && !lugarDepa.isEmpty() && !descripDepa.isEmpty() && !costoDepa.isEmpty()) {

            Map<String, Object> map = new HashMap<>();
            map.put("Nombre", nombreDepa);
            map.put("Descripcion", descripDepa);
            map.put("Lugar", lugarDepa);
            map.put("Costo", costoDepa);
            String id = mAuth.getCurrentUser().getUid();
            databaseReference.child("Departamentos").child(id).child(nombre).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(Rentar.this, "Datos cargados con exito", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), Rentar.class);
                        startActivity(i);
                        finish();
                    }
                    else{
                        Toast.makeText(Rentar.this, "Los datos no se subieron correctamente", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }else{
            Toast.makeText(this, "Favor de llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
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
    public void ClickConfiguracion(View view){
        //Redirect activity ti dashboar
        Buscar.redirectActivity(this, Configuracion.class);
    }

    public void ClickRent(View view){
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
