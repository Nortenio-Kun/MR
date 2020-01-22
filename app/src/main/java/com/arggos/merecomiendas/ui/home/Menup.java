package com.arggos.merecomiendas.ui.home;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.arggos.merecomiendas.Otros_Servicios;
import com.arggos.merecomiendas.R;
import com.arggos.merecomiendas.Screen_One_LoginandRegister;
import com.arggos.merecomiendas.Screen_Two_FrmApi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

public class Menup extends Fragment implements View.OnClickListener{

    FloatingActionButton fab2,fab3,fab4,fab5,espera;
    ImageButton fabMain,Uno,Dos,Tres;
    float translationX = 180f;
    OvershootInterpolator interpolator = new OvershootInterpolator();
    Boolean fabMenu = false;
    View  view= null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.activity_menu, container, false);

        initFabMenu();

return view;
    }

    private void initFabMenu() {
        fabMain = view.findViewById(R.id.fabMain);
        fab2 = view.findViewById(R.id.fab2);
        fab3 = view.findViewById(R.id.fab3);
        fab4 = view.findViewById(R.id.fab4);
        fab5 = view.findViewById(R.id.fab5);
        Uno = view.findViewById(R.id.Unouno);
        Dos = view.findViewById(R.id.Dosdos);
        Tres = view.findViewById(R.id.Trestres);
        espera = view.findViewById(R.id.Otros);


        fabMain.setAlpha(1f);
        Uno.setAlpha(1f);
        Dos.setAlpha(1f);
        Tres.setAlpha(1f);
        fab2.setAlpha(0f);
        fab3.setAlpha(0f);
        fab4.setAlpha(0f);
        fab5.setAlpha(0f);

        fab2.setTranslationX(translationX);
        fab3.setTranslationX(translationX);
        fab4.setTranslationX(translationX);
        fab5.setTranslationX(translationX);

        fabMain.setOnClickListener(this);
        Uno.setOnClickListener(this);
        Dos.setOnClickListener(this);
        Tres.setOnClickListener(this);
        fab2.setOnClickListener(this);
        fab3.setOnClickListener(this);
        fab4.setOnClickListener(this);
        fab5.setOnClickListener(this);
        espera.setOnClickListener(this);
    }

    private void openFab(){
        fabMenu = !fabMenu;
        fabMain.animate().setInterpolator(interpolator).setDuration(300).start();
        fab2.animate().translationX(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fab3.animate().translationX(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fab4.animate().translationX(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fab5.animate().translationX(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();


    }

    private void closeFab(){
        fabMenu = !fabMenu;
        fabMain.animate().setInterpolator(interpolator).setDuration(300).start();
        fab2.animate().translationX(translationX).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fab3.animate().translationX(-translationX).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fab4.animate().translationX(translationX).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fab5.animate().translationX(-translationX).alpha(0f).setInterpolator(interpolator).setDuration(300).start();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fabMain:
                if(fabMenu){
                    closeFab();
                }else{
                    openFab();
                }
                break;
            case R.id.fab2:
                openDialog();
                break;
            case R.id.fab3:
                openDialog();
                break;
            case R.id.fab4:
                openDialog();
                break;
            case R.id.fab5:
                openDialog();
                break;
            case R.id.Otros:
                Intent op = new Intent(getActivity(), Otros_Servicios.class);
                startActivity(op);
                break;
        }
    }

    private void openDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog, null);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button OK = view.findViewById(R.id.vale);
        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
