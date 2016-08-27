package com.kaushal.toolkit.level;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.kaushal.toolkit.R;

import java.text.DecimalFormat;

public class LevelActivity extends AppCompatActivity implements LevelListener {

    private static Context context;
    public DecimalFormat df = new DecimalFormat("#.#");
    private float lastXValue = 0f;
    private float lastYValue = 0f;
    ImageView imgAnimationX;
    ImageView imgAnimationY;
    ImageView imgAnimationXY;
    TranslateAnimation animation;
    String currentOrientation;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_main);
        context = this;
    }

    protected void onResume() {
        super.onResume();
        if (LevelManager.isSupported()) {
            LevelManager.startListening(this);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (LevelManager.isListening()) {
            LevelManager.stopListening();
        }
    }

    public static Context getContext() {
        return context;
    }

    public void onShake(float force) {

    }
    
    public void onAccelerationChanged(float x, float y, float z) {
        float animMultiplier = 50f;
        float animX = x * animMultiplier;
        float animY = -y * animMultiplier;


//        TextView value = (TextView) findViewById(R.id.value);
        imgAnimationX = (ImageView) findViewById(R.id.imageViewX);
        imgAnimationY = (ImageView) findViewById(R.id.imageViewY);
        imgAnimationXY = (ImageView) findViewById(R.id.imageViewXY);
//        assert value != null;

        imgAnimationX.startAnimation(bubbleAnimation(lastXValue, animX, 0f, 0f));
//        value.setText(String.valueOf(df.format(x)));


        imgAnimationY.startAnimation(bubbleAnimation(0f, 0f, lastYValue, animY));
//        value.setText(String.valueOf(df.format(y)));

        imgAnimationXY.startAnimation(bubbleAnimation(lastXValue, animX, lastYValue, animY));
//        value.setText(String.valueOf(df.format(y)));
        lastXValue = animX;
        lastYValue= animY;

    }

    private TranslateAnimation bubbleAnimation(float lX, float x, float lY, float y) {
        animation = new TranslateAnimation(lX, x, lY, y);
        animation.setDuration(5000);
        animation.setFillAfter(true);
        return animation;

    }
}
