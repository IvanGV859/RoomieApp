package com.example.roomieapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerView_Config {
    private Context mContext;
    private DepartamentosAdapter mDepartamentoAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<Departamento> departamentos, List<String> keys){
        mContext = context;
        mDepartamentoAdapter = new DepartamentosAdapter(departamentos, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mDepartamentoAdapter);
    }

    class DepartamentoItemView extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private TextView mDescripcion;
        private TextView mLugar;
        private TextView mMunicipio;
        private TextView mCosto;

        private String key;

        public DepartamentoItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.book_list_item, parent, false));

            mTitle = (TextView) itemView.findViewById(R.id.title_txtView);
            mDescripcion = (TextView) itemView.findViewById(R.id.descripcion_txtView);
            mLugar = (TextView) itemView.findViewById(R.id.lugar_txt);
            mMunicipio = (TextView) itemView.findViewById(R.id.municipio_txt);
            mCosto = (TextView) itemView.findViewById(R.id.precio_txt);
        }
        public void bind(Departamento departamento, String key){
            mTitle.setText(departamento.getNombre());
            mDescripcion.setText(departamento.getDescripcion());
            mLugar.setText(departamento.getLugar());
            mMunicipio.setText(departamento.getMunicipio());
            mCosto.setText(departamento.getCosto());
            this.key = key;
        }
    }
    class DepartamentosAdapter extends RecyclerView.Adapter<DepartamentoItemView>{
        private List<Departamento> mDepartamentoList;
        private List<String> mKeys;

        public DepartamentosAdapter(List<Departamento> mDepartamentoList, List<String> mKeys) {
            this.mDepartamentoList = mDepartamentoList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public DepartamentoItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new DepartamentoItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull DepartamentoItemView holder, int position) {
            holder.bind(mDepartamentoList.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mDepartamentoList.size();
        }
    }
}
