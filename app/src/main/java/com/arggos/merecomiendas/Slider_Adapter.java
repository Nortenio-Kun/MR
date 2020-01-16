package com.arggos.merecomiendas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.airbnb.lottie.LottieAnimationView;

public class Slider_Adapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public Slider_Adapter(Context context) {
        this.context = context;
    }

    public int[] slide_animations = {
        R.raw.onboard_one,
        R.raw.onboard_two,
        R.raw.onboard_three
    };

    public String[] slide_headers = {
        "Uno",
        "Dos",
        "Tres"
    };

    public String[] slide_descriptions = {
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
    };

    @Override
    public int getCount() {
        return slide_headers.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.onboarding_layout,container,false);

        LottieAnimationView Slideanimation = view.findViewById(R.id.Slide_Animation);
        TextView slideheader = view.findViewById(R.id.Slide_Headers);
        TextView slidedescription = view.findViewById(R.id.Slide_Description);

        Slideanimation.setAnimation(slide_animations[position]);
        slideheader.setText(slide_headers[position]);
        slidedescription.setText(slide_descriptions[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
