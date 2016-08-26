package com.kaushal.toolkit.level;

/**
 * Created by Kaushal Dontula on 8/5/16.
 */
public interface LevelListener {

    public void onAccelerationChanged(float x, float y, float z);

    public void onShake(float force);

}