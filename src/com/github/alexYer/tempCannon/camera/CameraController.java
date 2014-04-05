package com.github.alexYer.tempCannon.camera;

import android.view.Display;
import android.view.WindowManager;

/**
 * Created by Никита on 05.04.14.
 */
public class CameraController {

    private int cameraWidth;
    private int cameraHeight;

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
        this.cameraWidth = display.getWidth();
        this.cameraHeight = display.getHeight();
    }

}
