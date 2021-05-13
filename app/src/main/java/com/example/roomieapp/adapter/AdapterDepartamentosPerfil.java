package com.example.roomieapp.adapter;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomieapp.Perfil;
import com.example.roomieapp.R;
import com.example.roomieapp.Rentar;
import com.example.roomieapp.pojo.Departamentos;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.EventListener;
import java.util.List;

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
    }

    @Override
    public int getItemCount() {
        return departamentosList.size();
    }

    public class viewholderperfil extends RecyclerView.ViewHolder {

        TextView txt_nombre, txt_descripcion, txt_colonia, txt_municipio, txt_costo;

        public viewholderperfil(@NonNull View itemView) {
            super(itemView);

            txt_nombre = itemView.findViewById(R.id.txt_nombreDepaPerfil);
            txt_descripcion = itemView.findViewById(R.id.txt_descripPerfil);
            txt_colonia = itemView.findViewById(R.id.txt_coloniaPerfil);
            txt_municipio = itemView.findViewById(R.id.txt_municipioPerfil);
            txt_costo = itemView.findViewById(R.id.txt_costoPerfil);
        }
    }
}
