package com.example.roomieapp;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceDepartamentos;
    private List<Departamento> departamentos = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<Departamento> departamentos, List<String> keys);
        void DataIsInserted();
        void DataIsUpdate();
        void DataIsDeleted();

    }

    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceDepartamentos = mDatabase.getReference("Departamentos");
    }

    public void readDepartamentos(final DataStatus dataStatus){
        mReferenceDepartamentos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                departamentos.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Departamento departamento = keyNode.getValue(Departamento.class);
                    departamentos.add(departamento);
                }
                dataStatus.DataIsLoaded(departamentos,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseerror) {

            }
        });
    }

}
