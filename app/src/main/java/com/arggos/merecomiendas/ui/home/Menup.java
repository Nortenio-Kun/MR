package com.arggos.merecomiendas.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.arggos.merecomiendas.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

public class Menup extends Fragment implements View.OnClickListener{

    FloatingActionButton fab2,fab3,fab4,fab5;
    ImageButton fabMain;
    float translationX = 180f;
    OvershootInterpolator interpolator = new OvershootInterpolator();
    Boolean fabMenu = false;
    View  view= null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.activity_menu, container, false);


           /* SpaceNavigationView spaceNavigationView = (SpaceNavigationView) view.findViewById(R.id.space8);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.iconone));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.iconthree));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.iconfour));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.iconfive));
        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                Toast.makeText(getContext(), "onCentreButtonClick", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                Toast.makeText(getContext(), itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                //Toast.makeText(Menu.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }
        });*/
        //spaceNavigationView.showBadgeAtIndex(0, 3, 2);
        initFabMenu();
return view;
    }

    private void initFabMenu() {
        fabMain = view.findViewById(R.id.fabMain);
        fab2 = view.findViewById(R.id.fab2);
        fab3 = view.findViewById(R.id.fab3);
        fab4 = view.findViewById(R.id.fab4);
        fab5 = view.findViewById(R.id.fab5);


        fabMain.setAlpha(1f);
        fab2.setAlpha(0f);
        fab3.setAlpha(0f);
        fab4.setAlpha(0f);
        fab5.setAlpha(0f);

        fab2.setTranslationX(translationX);
        fab3.setTranslationX(translationX);
        fab4.setTranslationX(translationX);
        fab5.setTranslationX(translationX);

        fabMain.setOnClickListener(this);
        fab2.setOnClickListener(this);
        fab3.setOnClickListener(this);
        fab4.setOnClickListener(this);
        fab5.setOnClickListener(this);
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
        }
    }
}
