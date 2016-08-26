package com.kaushal.toolkit.magneto_meter;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.List;

/**
 * Created by xkxd061 on 8/26/16.
 */
public class MagnetoMeterManager {

    private static Sensor sensor;
    private static SensorManager sensorManager;
    // you could use an OrientationListener array instead
    // if you plans to use more than one listener
    private static MagnetoMeterListener listener;

    /** indicates whether or not MagneticField Sensor is supported */
    private static Boolean supported;
    /** indicates whether or not MagneticField Sensor is running */
    private static boolean running = false;

    /**
     * Returns true if the manager is listening to magnetic-field changes
     */
    public static boolean isListening() {
        return running;
    }

    /**
     * Unregisters listeners
     */
    public static void stopListening() {
        running = false;
        try {
            if (sensorManager != null && sensorEventListener != null) {
                sensorManager.unregisterListener(sensorEventListener);
            }
        } catch (Exception e) {}
    }

    /**
     * Returns true if at least one Magnetic-Field sensor is available
     */
    public static boolean isSupported() {
        if (supported == null) {
            if (MagnetoMeterActivity.getContext() != null) {
                sensorManager = (SensorManager) MagnetoMeterActivity.getContext().
                        getSystemService(Context.SENSOR_SERVICE);
                List<Sensor> sensors = sensorManager.getSensorList(
                        Sensor.TYPE_MAGNETIC_FIELD);
                supported = sensors.size() > 0;
            } else {
                supported = Boolean.FALSE;
            }
        }
        return supported;
    }


    /**
     * Registers a listener and start listening
     */
    public static void startListening(MagnetoMeterListener magneticFieldListener) {
        sensorManager = (SensorManager) MagnetoMeterActivity.getContext().
                getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(
                Sensor.TYPE_MAGNETIC_FIELD);
        if (sensors.size() > 0) {
            sensor = sensors.get(0);
            running = sensorManager.registerListener(
                    sensorEventListener, sensor,
                    SensorManager.SENSOR_DELAY_GAME);
            listener = magneticFieldListener;
        }
    }

    /**
     * The listener that listen to events from the magnetic-field listener
     */
    private static SensorEventListener sensorEventListener = new SensorEventListener() {

        public void onAccuracyChanged(Sensor sensor, int accuracy) {}

        public void onSensorChanged(SensorEvent event) {
            // forwards orientation to the MagneticFieldListener
            listener.onCompassChanged(
                    event.values[0], 	// x axis
                    event.values[1], 	// y axis
                    - event.values[2]);	// z axis (inverted)
        }

    };

}
