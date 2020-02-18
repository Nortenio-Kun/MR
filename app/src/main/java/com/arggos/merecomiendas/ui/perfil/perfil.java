package com.arggos.merecomiendas.ui.perfil;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.arggos.merecomiendas.R;
import com.squareup.picasso.Picasso;


public class perfil extends Fragment {
    View view = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_perfil, container, false);
        ImageView perfil = view.findViewById(R.id.perfil);

        SharedPreferences pref = getContext().getSharedPreferences("API", Context.MODE_PRIVATE);
        String foto = pref.getString("Imagen","No");
        Picasso.get().load(foto+"?type=large").into(perfil);




        return view;
    }
}