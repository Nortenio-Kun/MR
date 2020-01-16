package com.arggos.merecomiendas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;

public class On_Boarding extends AppCompatActivity {

    private ViewPager Viewp_slides;
    private Slider_Adapter slider_adapter;
    Button finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on__boarding);

        finish = findViewById(R.id.finishear);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent second = new Intent(On_Boarding.this, Screen_One_LoginandRegister.class);
                startActivity(second);
                finish();
            }
        });

        Viewp_slides = findViewById(R.id.ViewP_slides);

        slider_adapter = new Slider_Adapter(this);

        Viewp_slides.setAdapter(slider_adapter);

    }
}
