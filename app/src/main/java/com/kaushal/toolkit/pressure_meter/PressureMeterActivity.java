package com.kaushal.toolkit.pressure_meter;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.kaushal.toolkit.R;

/**
 * Created by Kaushal Dontula on 8/26/16.
 */
public class PressureMeterActivity extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor pressureSensor;
    TextView pressure;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pressure_meter_main);
        pressure = (TextView) findViewById(R.id.pressure);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
    }

    @Override
    protected void onResume() {
        sensorManager.registerListener(this, pressureSensor, SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(this);
        super.onPause();
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        if (sensor.getType() == Sensor.TYPE_PRESSURE) {
            Log.i("Sensor Changed", "Accuracy :" + accuracy);
        }

    }

    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PRESSURE) {
            int pressureValue = (int) event.values[0];
            pressure.setText(String.valueOf(pressureValue));
        }
    }
}
