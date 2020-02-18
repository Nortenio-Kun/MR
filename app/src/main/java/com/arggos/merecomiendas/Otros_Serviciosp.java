package com.arggos.merecomiendas;



import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.recyclerview.widget.RecyclerView;



import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;



import com.google.firebase.database.DataSnapshot;

import com.google.firebase.database.DatabaseError;

import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

import java.util.List;



public class Otros_Serviciosp extends Fragment {



    RecyclerView rv;

    List<Profesion> prof;

    Servicios serv;



    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container,

                                          Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.activity_otros__servicios, container, false);




        rv = view.findViewById(R.id.Res1);

        //rv.setLayoutManager(new LinearLayoutManager(this));

        rv.setLayoutManager(new GridLayoutManager(getContext(), 3));

        prof = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        serv = new Servicios(getContext(), prof);

        rv.setAdapter(serv);

        database.getReference().getRoot().child("Profeciones").addValueEventListener(new ValueEventListener() {

            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {

                prof.removeAll(prof);

                for(DataSnapshot snapshot: dataSnapshot.getChildren()){

                    Profesion profe = new Profesion(snapshot.getValue(String.class));

                    prof.add(profe);

                }

                serv.notifyDataSetChanged();

            }



            @Override

            public void onCancelled(DatabaseError databaseError) {



            }

        });
        return  view;

    }

}