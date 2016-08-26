package com.kaushal.toolkit.magneto_meter;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.widget.TextView;

import com.kaushal.toolkit.R;

import java.text.DecimalFormat;

/**
 * Created by Kaushal Dontula on 8/26/16.
 */
public class MagnetoMeterActivity extends Activity implements SensorEventListener {

    private DecimalFormat df = new DecimalFormat("#.#");
    private String magneticMeterUnit =  " \u00B5" + "T"; // Micro Tesla unit of magnetic flux density
    private int vibrationDuration = 50;
    private SensorManager sensorManager;
    private Sensor magnetoSensor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.magneto_meter_main);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        magnetoSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    protected void onResume() {
        sensorManager.registerListener(this, magnetoSensor, SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(this);
        super.onPause();
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        if (sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            Log.i("Sensor Changed", "Accuracy :" + accuracy);
        }

    }

    public void onSensorChanged(SensorEvent event) {
        TextView xValue = (TextView) findViewById(R.id.x);
        TextView yValue = (TextView) findViewById(R.id.y);
        TextView zValue = (TextView) findViewById(R.id.z);
        TextView magneticFieldValue = (TextView) findViewById(R.id.magneticField);

        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            float x = event.values[0];
            float y = event.values[1];
            float z = -(event.values[2]);
            xValue.setText(String.valueOf(df.format(x)) + magneticMeterUnit);
            yValue.setText(String.valueOf(df.format(y)) + magneticMeterUnit);
            zValue.setText(String.valueOf(df.format(z)) + magneticMeterUnit);
            float magneticField = (float) Math.sqrt((x * x) + (y * y)+ (z * z));
            if (magneticField > 300) {
                Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(vibrationDuration);
            }
            magneticFieldValue.setText(String.valueOf(df.format(magneticField)) + magneticMeterUnit);
        }
    }
}