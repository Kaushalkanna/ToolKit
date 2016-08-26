package com.kaushal.toolkit.magneto_meter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.kaushal.toolkit.R;

import java.text.DecimalFormat;

/**
 * Created by xkxd061 on 8/26/16.
 */
public class MagnetoMeterActivity extends Activity implements MagnetoMeterListener {

    private static Context context;
    public DecimalFormat df = new DecimalFormat("#.#");

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.magneto_meter_main);
        context = this;
    }

    protected void onResume() {
        super.onResume();
        if (MagnetoMeterManager.isSupported()) {
            MagnetoMeterManager.startListening(this);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (MagnetoMeterManager.isListening()) {
            MagnetoMeterManager.stopListening();
        }

    }

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCompassChanged(float x, float y, float z) {
        ((TextView) findViewById(R.id.x)).setText(String.valueOf(df.format(x)));
        ((TextView) findViewById(R.id.y)).setText(String.valueOf(df.format(y)));
        ((TextView) findViewById(R.id.z)).setText(String.valueOf(df.format(z)));
    }

}