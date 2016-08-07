package com.kaushal.toolkit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.kaushal.toolkit.compass.CompassActivity;
import com.kaushal.toolkit.flash.FlashActivity;

public class MainActivity extends AppCompatActivity {
    Animation alphaAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.button_alpha);
    }

    public void flash_button(View view) {
        view.startAnimation(alphaAnimation);
        Intent intent;
        intent = new Intent(this, FlashActivity.class);
        startActivity(intent);
    }

    public void compass_button(View view) {
        view.startAnimation(alphaAnimation);
        Intent intent;
        intent = new Intent(this, CompassActivity.class);
        startActivity(intent);
    }

    public void barcode_scanner_button(View view) {
        view.startAnimation(alphaAnimation);
    }

    public void photo_meter_button(View view) {
        view.startAnimation(alphaAnimation);
    }

    public void db_meter_button(View view) {
        view.startAnimation(alphaAnimation);
    }

    public void level_button(View view) {
        view.startAnimation(alphaAnimation);
    }

    public void scale_button(View view) {
        view.startAnimation(alphaAnimation);
    }

    public void wishlist_button(View view) {
        view.startAnimation(alphaAnimation);
    }
}
