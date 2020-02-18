package com.arggos.merecomiendas.ui.favoritos;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arggos.merecomiendas.R;


public class favoritos extends Fragment {
    View view = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_favoritos, container, false);



        return view;
    }
}