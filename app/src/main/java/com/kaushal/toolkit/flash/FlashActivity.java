package com.kaushal.toolkit.flash;

import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ToggleButton;

import com.kaushal.toolkit.R;


public class FlashActivity extends AppCompatActivity {
    ToggleButton lightOnOffToggle;

    private Camera camera;
    private boolean isFlashOn;
    private boolean hasFlash;
    Parameters params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flash_main);
        lightOnOffToggle = (ToggleButton) findViewById(R.id.toggleButton);
        hasFlash = getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        if (!hasFlash) {
            final AlertDialog.Builder infoDialog = new AlertDialog.Builder(FlashActivity.this);
            infoDialog.setTitle("Error");
            infoDialog.setMessage("Sorry, your device doesn't support flash light!");
            infoDialog.setPositiveButton("OK", null);
            infoDialog.show();
            return;
        }
        else {
            getCamera();
        }

        ToggleButton.OnClickListener toggleButtonListener = new ToggleButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lightOnOffToggle.isChecked()) {
                    turnOnFlash();
                } else {
                    turnOffFlash();
                }
            }
        };
        lightOnOffToggle.setOnClickListener(toggleButtonListener);
    }

    private void getCamera() {
        if (camera == null) {
            try {
                camera = Camera.open();
                params = camera.getParameters();
            } catch (RuntimeException e) {
                Log.e("Camera Error.", e.getMessage());
            }
        }
    }

    private void turnOnFlash() {
        if (!isFlashOn) {
            if (camera == null || params == null) {
                return;
            }
            params = camera.getParameters();
            params.setFlashMode(Parameters.FLASH_MODE_TORCH);
            camera.setParameters(params);
            camera.startPreview();
            isFlashOn = true;
        }

    }

    private void turnOffFlash() {
        if (isFlashOn) {
            if (camera == null || params == null) {
                return;
            }
            params = camera.getParameters();
            params.setFlashMode(Parameters.FLASH_MODE_OFF);
            camera.setParameters(params);
            camera.stopPreview();
            isFlashOn = false;
        }
    }
}
