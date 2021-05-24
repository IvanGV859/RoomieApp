package com.example.roomieapp.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomieapp.Perfil;
import com.example.roomieapp.R;
import com.example.roomieapp.Registro;
import com.example.roomieapp.Rentar;
import com.example.roomieapp.pojo.Departamentos;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdapterDepartamentosPerfil extends RecyclerView.Adapter<AdapterDepartamentosPerfil.viewholderperfil>
{
    List<Departamentos> departamentosList;

    public AdapterDepartamentosPerfil(List<Departamentos> departamentosList) {
        this.departamentosList = departamentosList;
    }

    @NonNull
    @Override
    public viewholderperfil onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_departamentos_perfil, parent, false);
        viewholderperfil holder = new viewholderperfil(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholderperfil holder, final int position) {
        final Departamentos ms = departamentosList.get(position);

        holder.txt_nombre.setText(ms.getNombre());
        holder.txt_descripcion.setText(ms.getDescripcion());
        holder.txt_colonia.setText(ms.getLugar());
        holder.txt_municipio.setText(ms.getMunicipio());
        holder.txt_costo.setText("Costo: $" + ms.getCosto());

        holder.bt_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.txt_nombre.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                        .setExpanded(true, 1200)
                        .create();

                View myview = dialogPlus.getHolderView();
                TextView editNombre = myview.findViewById(R.id.txt_nombredepa_edit);
                EditText editDescripcion = myview.findViewById(R.id.txt_descripciondepa_edit);
                EditText editColonia = myview.findViewById(R.id.txt_coloniadepa_edit);
                EditText editMunicipio = myview.findViewById(R.id.txt_municipiodepa_edit);
                EditText editCosto = myview.findViewById(R.id.txt_costodepa_edit);
                Button actualizar = myview.findViewById(R.id.bt_actualizar);

                editNombre.setText(ms.getNombre());
                editDescripcion.setText(ms.getDescripcion());
                editColonia.setText(ms.getLugar());
                editMunicipio.setText(ms.getMunicipio());
                editCosto.setText(ms.getCosto());

                dialogPlus.show();

                actualizar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("Descripcion", editDescripcion.getText().toString());
                        map.put("Lugar", editColonia.getText().toString());
                        map.put("Municipio", editMunicipio.getText().toString());
                        map.put("Costo", editCosto.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Departamentos")
                                .child(ms.getNombre()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
            }
        });

        holder.bt_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.txt_nombre.getContext());
                builder.setTitle("Borrar Departamento");
                builder.setMessage("Estas seguro de borrar este departamento");

                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Departamentos")
                                .child(ms.getNombre()).removeValue();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return departamentosList.size();
    }

    public class viewholderperfil extends RecyclerView.ViewHolder {

        TextView txt_nombre, txt_descripcion, txt_colonia, txt_municipio, txt_costo;
        Button bt_editar, bt_eliminar;

        public viewholderperfil(@NonNull View itemView) {
            super(itemView);

            txt_nombre = itemView.findViewById(R.id.txt_nombreDepaPerfil);
            txt_descripcion = itemView.findViewById(R.id.txt_descripPerfil);
            txt_colonia = itemView.findViewById(R.id.txt_coloniaPerfil);
            txt_municipio = itemView.findViewById(R.id.txt_municipioPerfil);
            txt_costo = itemView.findViewById(R.id.txt_costoPerfil);

            bt_editar = (Button) itemView.findViewById(R.id.bt_editar);
            bt_eliminar = (Button) itemView.findViewById(R.id.bt_eliminar);
        }
    }
}
