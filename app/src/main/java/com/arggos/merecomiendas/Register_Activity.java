package com.arggos.merecomiendas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

public class Register_Activity extends AppCompatActivity {

    RelativeLayout rlayout;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        setContentView(R.layout.activity_register_);

        rlayout = findViewById(R.id.rlayout);
        animation = AnimationUtils.loadAnimation(this,R.anim.updowndiagonal);
        rlayout.setAnimation(animation);

    }
}
