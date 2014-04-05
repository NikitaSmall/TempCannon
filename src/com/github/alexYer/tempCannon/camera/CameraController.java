package com.github.alexYer.tempCannon.camera;

import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by Никита on 05.04.14.
 */
public class CameraController {

    private int cameraWidth;
    private int cameraHeight;
    private float density;
    private int densityDpi;

    public int getDensityDpi() {
        return densityDpi;
    }

    public void setDensityDpi(int densityDpi) {
        this.densityDpi = densityDpi;
    }

    public float getDensity() {
        return density;
    }

    public void setDensity(float density) {
        this.density = density;
    }

    public int getCameraWidth() {
        return cameraWidth;
    }

    public void setCameraWidth(int cameraWidth) {
        this.cameraWidth = cameraWidth;
    }

    public int getCameraHeight() {
        return cameraHeight;
    }

    public void setCameraHeight(int cameraHeight) {
        this.cameraHeight = cameraHeight;
    }

    public CameraController(Display display) {
        /*this.cameraWidth = display.getWidth();
        this.cameraHeight = display.getHeight();
        this.density = display.*/

        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);

        this.cameraHeight = metrics.heightPixels;
        this.cameraWidth = metrics.widthPixels;
        this.density = metrics.density;
        this.densityDpi = metrics.densityDpi;
    }

}
