package com.kaushal.toolkit.light_meter;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.kaushal.toolkit.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LightMeterActivity extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor lightSensor;
    TextView lux;
    TextView maxLux;
    TextView minLux;
    TextView avgLux;
    List<Integer> listValues = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.light_main);
        lux = (TextView) findViewById(R.id.lux);
        maxLux = (TextView) findViewById(R.id.maxLux);
        minLux = (TextView) findViewById(R.id.minLux);
        avgLux = (TextView) findViewById(R.id.avgLux);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    protected void onResume() {
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(this);
        super.onPause();
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        if (sensor.getType() == Sensor.TYPE_LIGHT) {
            Log.i("Sensor Changed", "Accuracy :" + accuracy);
        }

    }

    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            int luxValue = (int) event.values[0];
            lux.setText(String.valueOf(luxValue));
            listValues.add(luxValue);
            maxLux.setText(String.valueOf(Collections.max(listValues)));
            minLux.setText(String.valueOf(Collections.min(listValues)));
            avgLux.setText(String.valueOf(average(listValues)));
        }
    }

    public static int average(List<Integer> list) {
        if (list == null || list.isEmpty())
            return 0;
        int sum = 0;
        int n = list.size();
        for (int i = 0; i < n; i++)
            sum += list.get(i);
        return sum / n;
    }
}
