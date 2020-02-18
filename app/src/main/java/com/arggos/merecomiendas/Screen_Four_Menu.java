package com.arggos.merecomiendas;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.arggos.merecomiendas.ui.home.Menup;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import android.view.View;

import androidx.annotation.DrawableRes;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;
import com.squareup.picasso.Picasso;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class Screen_Four_Menu extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    Bundle instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        hidenavigation();
        setContentView(R.layout.activity_four_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        BottomNavigationView navView = findViewById(R.id.button_nav_view);


        //Puta Lupa
        BottomNavigationView BView = findViewById(R.id.imageButton5);
        AppBarConfiguration appBarConfiguration1 = new AppBarConfiguration.Builder(
                R.id.nav_busqueda)
                .build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration1);
        NavigationUI.setupWithNavController(BView, navController);

        //Bottom navigation

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_mensajes, R.id.nav_anuncios, R.id.nav_home, R.id.nav_busqueda,R.id.nav_pagos)
                .build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


        //Navigation Drawer
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_aclaraciones, R.id.nav_anuncios,R.id.nav_ayuda,
                R.id.nav_config,R.id.nav_cotizaciones,R.id.nav_favoritos,R.id.nav_mensajes,
                R.id.nav_pagos,R.id.nav_perfil,R.id.nav_servicios)
                .setDrawerLayout(drawer)
                .build();
        //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        View h = navigationView.getHeaderView(0);
        ImageView perfil = h.findViewById(R.id.imageP);
        SharedPreferences pref = this.getSharedPreferences("API", Context.MODE_PRIVATE);
        String foto = pref.getString("Imagen","No");
        Picasso.get().load(foto+"?type=large").into(perfil);






    }

    @Override
    protected void onResume() {
        super.onResume();
        hidenavigation();
    }

    private void hidenavigation() {
        this.getWindow().getDecorView().
                setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_FULLSCREEN
                );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
    //getMenuInflater().inflate(R.menu.screen__four__menu, menu);


        return true;
    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();

    }

    public void busqueda(View view){

    }
}
