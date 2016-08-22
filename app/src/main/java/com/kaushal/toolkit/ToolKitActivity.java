package com.kaushal.toolkit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.kaushal.toolkit.compass.CompassActivity;
import com.kaushal.toolkit.db_meter.DbMeterActivity;
import com.kaushal.toolkit.flash.FlashActivity;
import com.kaushal.toolkit.level.LevelActivity;
import com.kaushal.toolkit.light_meter.LightMeterActivity;
import com.kaushal.toolkit.scale.ScaleActivity;
import com.kaushal.toolkit.scanner.ScannerActivity;
import com.kaushal.toolkit.wishlist.WishListActivity;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class ToolKitActivity extends AppCompatActivity {
    Animation alphaAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolkit_main);
        alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.button_alpha);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);
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

    public void scale_button(View view) {
        view.startAnimation(alphaAnimation);
        Intent intent;
        intent = new Intent(this, ScaleActivity.class);
        startActivity(intent);
    }

    public void db_meter_button(View view) {
        view.startAnimation(alphaAnimation);
        Intent intent;
        intent = new Intent(this, DbMeterActivity.class);
        startActivity(intent);
    }

    public void level_button(View view) {
        view.startAnimation(alphaAnimation);
        Intent intent;
        intent = new Intent(this, LevelActivity.class);
        startActivity(intent);
    }

    public void barcode_scanner_button(View view) {
        view.startAnimation(alphaAnimation);
        Intent intent;
        intent = new Intent(this, ScannerActivity.class);
        startActivity(intent);
    }

    public void light_meter_button(View view) {
        view.startAnimation(alphaAnimation);
        Intent intent;
        intent = new Intent(this, LightMeterActivity.class);
        startActivity(intent);
    }

    public void wishlist_button(View view) {
        view.startAnimation(alphaAnimation);
        Intent intent;
        intent = new Intent(this, WishListActivity.class);
        startActivity(intent);
    }
}
