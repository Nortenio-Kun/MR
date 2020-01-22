package com.arggos.merecomiendas;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Toast;

import static java.security.AccessController.getContext;

public class space extends AppCompatActivity{
    Bundle mas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





    }

    public void llenar(Context cont, Activity act){

        SpaceNavigationView spaceNavigationView;
        spaceNavigationView =act.findViewById(R.id.space8);
        spaceNavigationView.initWithSaveInstanceState(mas);
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.iconone));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.iconthree));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.iconfour));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.iconfive));
        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                Toast.makeText(getApplicationContext(),"onCentreButtonClick", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                Toast.makeText(getApplicationContext(), itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                //Toast.makeText(Menu.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
