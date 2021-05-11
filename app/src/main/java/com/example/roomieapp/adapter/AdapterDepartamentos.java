package com.example.roomieapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomieapp.Departamento;
import com.example.roomieapp.R;
import com.example.roomieapp.pojo.Departamentos;

import java.util.List;

public class AdapterDepartamentos extends RecyclerView.Adapter<AdapterDepartamentos.viewholderdepartamentos>
{
    List<Departamentos> departamentosList;

    public AdapterDepartamentos(List<Departamentos> departamentosList) {
        this.departamentosList = departamentosList;
    }

    @NonNull
    @Override
    public viewholderdepartamentos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_departamentos, parent, false);
        viewholderdepartamentos holder = new viewholderdepartamentos(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholderdepartamentos holder, int position) {

        Departamentos ms = departamentosList.get(position);

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

    public class viewholderdepartamentos extends RecyclerView.ViewHolder {

        TextView txt_nombre, txt_descripcion, txt_colonia, txt_municipio, txt_costo;

        public viewholderdepartamentos(@NonNull View itemView) {
            super(itemView);

            txt_nombre = itemView.findViewById(R.id.txt_nombreDepaFB);
            txt_descripcion = itemView.findViewById(R.id.txt_descripDepaFB);
            txt_colonia = itemView.findViewById(R.id.txt_coloniaDepaFB);
            txt_municipio = itemView.findViewById(R.id.txt_municipioDepaFB);
            txt_costo = itemView.findViewById(R.id.txt_costoDepaFB);
        }
    }
}
