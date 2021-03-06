package com.arggos.merecomiendas;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Servicios extends RecyclerView.Adapter<Servicios.Profesiones>{

    List<Profesion> profesiones;
    public static Context mcon;

    public Servicios(Context con, List<Profesion> profesiones) {
        this.profesiones = profesiones;
        mcon = con;
    }

    @NonNull
    @Override
    public Profesiones onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recicle_profesion, parent,false);
        Profesiones holder = new Profesiones(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Profesiones holder, int position) {
        Profesion profesion = profesiones.get(position);
        holder.Nombre.setText(profesion.getNombre());
    }

    @Override
    public int getItemCount() {
        return profesiones.size();

}
    public static class Profesiones extends RecyclerView.ViewHolder {

        TextView Nombre;
        ImageButton boton;

        public Profesiones(@NonNull View itemView) {
            super(itemView);
            Nombre = itemView.findViewById(R.id.nombreprofesion);
            boton = itemView.findViewById(R.id.Boton);
            boton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mcon.startActivity(new Intent(mcon, snormal.class));
                }
            });

        }
    }

}
