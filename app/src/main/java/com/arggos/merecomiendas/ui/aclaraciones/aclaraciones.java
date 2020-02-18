package com.arggos.merecomiendas.ui.aclaraciones;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arggos.merecomiendas.R;


public class aclaraciones extends Fragment {
    View view = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_aclaraciones, container, false);



        return view;
    }
}
