package com.arggos.merecomiendas.ui.home;

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
import androidx.fragment.app.Fragment;

import com.arggos.merecomiendas.R;
import com.arggos.merecomiendas.snormalmap;

public class Menup extends Fragment implements View.OnClickListener{
    ImageButton fab2,fab3,fab4,fab5;
    ImageButton fabmain,fabmain2,fabmain3,fabmain4,Main;
    float translationX = 180f;
    OvershootInterpolator interpolator = new OvershootInterpolator();
    Boolean fabMenu1 = false;
    Boolean fabMenu2 = false;
    Boolean fabMenu3 = false;
    Boolean fabMenu4 = false;
    View  view= null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.activity_menu, container, false);

        initFabMenu();

return view;
    }

    private void initFabMenu() {
        fabmain = view.findViewById(R.id.fab1_main);
        fabmain2 = view.findViewById(R.id.fab2_main);
        fabmain3 = view.findViewById(R.id.fab3_main);
        fabmain4 = view.findViewById(R.id.fab4_main);



        fabmain.setAlpha(1f);
        fabmain2.setAlpha(1f);
        fabmain3.setAlpha(1f);
        fabmain4.setAlpha(1f);


        fabmain.setOnClickListener(this);
        fabmain2.setOnClickListener(this);
        fabmain3.setOnClickListener(this);
        fabmain4.setOnClickListener(this);



        fab2 = view.findViewById(R.id.fab2_2);
        fab3 = view.findViewById(R.id.fab2_3);
        fab4 = view.findViewById(R.id.fab2_4);
        fab5 = view.findViewById(R.id.fab2_5);

        fab2.setAlpha(0f);
        fab3.setAlpha(0f);
        fab4.setAlpha(0f);
        fab5.setAlpha(0f);

        fab2.setTranslationX(translationX);
        fab3.setTranslationX(translationX);
        fab4.setTranslationX(translationX);
        fab5.setTranslationX(translationX);

        fab2 = view.findViewById(R.id.fab3_2);
        fab3 = view.findViewById(R.id.fab3_3);
        fab4 = view.findViewById(R.id.fab3_4);
        fab5 = view.findViewById(R.id.fab3_5);

        fab2.setAlpha(0f);
        fab3.setAlpha(0f);
        fab4.setAlpha(0f);
        fab5.setAlpha(0f);

        fab2.setTranslationX(translationX);
        fab3.setTranslationX(translationX);
        fab4.setTranslationX(translationX);
        fab5.setTranslationX(translationX);

        fab2 = view.findViewById(R.id.fab4_2);
        fab3 = view.findViewById(R.id.fab4_3);
        fab4 = view.findViewById(R.id.fab4_4);
        fab5 = view.findViewById(R.id.fab4_5);

        fab2.setAlpha(0f);
        fab3.setAlpha(0f);
        fab4.setAlpha(0f);
        fab5.setAlpha(0f);

        fab2.setTranslationX(translationX);
        fab3.setTranslationX(translationX);
        fab4.setTranslationX(translationX);
        fab5.setTranslationX(translationX);

        fab2 = view.findViewById(R.id.fab1_2);
        fab3 = view.findViewById(R.id.fab1_3);
        fab4 = view.findViewById(R.id.fab1_4);
        fab5 = view.findViewById(R.id.fab1_5);

        fab2.setAlpha(0f);
        fab3.setAlpha(0f);
        fab4.setAlpha(0f);
        fab5.setAlpha(0f);

        fab2.setTranslationX(translationX);
        fab3.setTranslationX(translationX);
        fab4.setTranslationX(translationX);
        fab5.setTranslationX(translationX);

    }

    private void openFab(int i){
        Main=view.findViewById(i);
        if(i==R.id.fab1_main){  fab2 = view.findViewById(R.id.fab1_2);
            fab3 = view.findViewById(R.id.fab1_3);
            fab4 = view.findViewById(R.id.fab1_4);
            fab5 = view.findViewById(R.id.fab1_5);
            fabMenu1 = !fabMenu1;}
        if(i==R.id.fab2_main){fab2 = view.findViewById(R.id.fab2_2);
            fab3 = view.findViewById(R.id.fab2_3);
            fab4 = view.findViewById(R.id.fab2_4);
            fab5 = view.findViewById(R.id.fab2_5);
            fabMenu2 = !fabMenu2;}
        if(i==R.id.fab3_main){fab2 = view.findViewById(R.id.fab3_2);
            fab3 = view.findViewById(R.id.fab3_3);
            fab4 = view.findViewById(R.id.fab3_4);
            fab5 = view.findViewById(R.id.fab3_5);
            fabMenu3 = !fabMenu3;}
        if(i==R.id.fab4_main){fab2 = view.findViewById(R.id.fab4_2);
            fab3 = view.findViewById(R.id.fab4_3);
            fab4 = view.findViewById(R.id.fab4_4);
            fab5 = view.findViewById(R.id.fab4_5);
            fabMenu4 = !fabMenu4;}
        fab2.setOnClickListener(this);
        fab3.setOnClickListener(this);
        fab4.setOnClickListener(this);
        fab5.setOnClickListener(this);



        Main.animate().setInterpolator(interpolator).setDuration(300).start();
        fab2.animate().translationX(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fab3.animate().translationX(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fab4.animate().translationX(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fab5.animate().translationX(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();


    }

    private void closeFab(int i){
        Main.animate().setInterpolator(interpolator).setDuration(300).start();
        if(i==R.id.fab1_main){  fab2 = view.findViewById(R.id.fab1_2);
            fab3 = view.findViewById(R.id.fab1_3);
            fab4 = view.findViewById(R.id.fab1_4);
            fab5 = view.findViewById(R.id.fab1_5);
            fabMenu1 = !fabMenu1;}
        if(i==R.id.fab2_main){fab2 = view.findViewById(R.id.fab2_2);
            fab3 = view.findViewById(R.id.fab2_3);
            fab4 = view.findViewById(R.id.fab2_4);
            fab5 = view.findViewById(R.id.fab2_5);
            fabMenu2 = !fabMenu2;}
        if(i==R.id.fab3_main){fab2 = view.findViewById(R.id.fab3_2);
            fab3 = view.findViewById(R.id.fab3_3);
            fab4 = view.findViewById(R.id.fab3_4);
            fab5 = view.findViewById(R.id.fab3_5);
            fabMenu3 = !fabMenu3;}
        if(i==R.id.fab4_main){fab2 = view.findViewById(R.id.fab4_2);
            fab3 = view.findViewById(R.id.fab4_3);
            fab4 = view.findViewById(R.id.fab4_4);
            fab5 = view.findViewById(R.id.fab4_5);
            fabMenu4 = !fabMenu4;}
        fab2.animate().translationX(translationX).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fab3.animate().translationX(-translationX).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fab4.animate().translationX(translationX).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fab5.animate().translationX(-translationX).alpha(0f).setInterpolator(interpolator).setDuration(300).start();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab1_main:
                if(fabMenu1){
                    closeFab(R.id.fab1_main);
                }else{
                    openFab(R.id.fab1_main);
                }
                break;
            case R.id.fab2_main:
                if(fabMenu2){
                    closeFab(R.id.fab2_main);
                }else{
                    openFab(R.id.fab2_main);
                }
                break;
            case R.id.fab3_main:
                if(fabMenu3){
                    closeFab(R.id.fab3_main);
                }else{
                    openFab(R.id.fab3_main);
                }
                break;
            case R.id.fab4_main:
                if(fabMenu4){
                    closeFab(R.id.fab4_main);
                }else{
                    openFab(R.id.fab4_main);
                }
                break;

            case R.id.fab1_2:
                if(fabMenu1){
                    openDialog();
                }else{
                    //openDialog();
                }
                break;

            case R.id.fab1_3:
                if(fabMenu1){
                    openDialog();
                }else{
                    // openDialog();
                }
                break;

            case R.id.fab1_4:
                if(fabMenu1){
                    openDialog();
                }else{
                    // openDialog();
                }
                break;

            case R.id.fab1_5:
                if(fabMenu1){
                    openDialog();
                }else{
                    // openDialog();
                }
                break;

            case R.id.fab2_2:
                if(fabMenu2){
                    openDialog();
                }else{
                    //openDialog();
                }
                break;

            case R.id.fab2_3:
                if(fabMenu2){
                    openDialog();
                }else{
                    // openDialog();
                }
                break;

            case R.id.fab2_4:
                if(fabMenu2){
                    openDialog();
                }else{
                    // openDialog();
                }
                break;

            case R.id.fab2_5:
                if(fabMenu2){
                    openDialog();
                }else{
                    // openDialog();
                }
                break;

            case R.id.fab3_2:
                if(fabMenu3){
                    openDialog();
                }else{
                    //openDialog();
                }
                break;

            case R.id.fab3_3:
                if(fabMenu3){
                    openDialog();
                }else{
                    // openDialog();
                }
                break;

            case R.id.fab3_4:
                if(fabMenu3){
                    openDialog();
                }else{
                    // openDialog();
                }
                break;

            case R.id.fab3_5:
                if(fabMenu3){
                    openDialog();
                }else{
                    // openDialog();
                }
                break;

            case R.id.fab4_2:
                if(fabMenu4){
                    openDialog();
                }else{
                    //openDialog();
                }
                break;

            case R.id.fab4_3:
                if(fabMenu4){
                    openDialog();
                }else{
                    // openDialog();
                }
                break;

            case R.id.fab4_4:
                if(fabMenu4){
                    openDialog();
                }else{
                    // openDialog();
                }
                break;

            case R.id.fab4_5:
                if(fabMenu4){
                    openDialog();
                }else{
                    // openDialog();
                }
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

                Intent i = new Intent(getContext(), snormalmap.class);

                startActivity(i);

            }

        });

        dialog.show();

    }

}