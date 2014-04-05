package com.github.alexYer.tempCannon.resourcemanager;

import android.os.Bundle;
import android.util.Log;

/**
 * Created by Никита on 05.04.14.
 */
public class ResourceManager {
    public ResourceManager(Bundle properties) {
        ResourceConstant.cameraWidth = properties.getInt("cameraWidth");
        ResourceConstant.cameraHeight = properties.getInt("cameraHeigth");
        ResourceConstant.density = properties.getFloat("density");
        ResourceConstant.densityDpi = properties.getInt("densityDpi");
    }
}
