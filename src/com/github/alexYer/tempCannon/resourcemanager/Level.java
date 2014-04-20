package com.github.alexYer.tempCannon.resourcemanager;

import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXTiledMap;


/**
 *
 * @author Olexander Yermakov (mannavard1611@gmail.com)
 */
public class Level {
    /**
     * @param map   Map from where we want to get layer.
     * @param name  Layer name.
     * @return      Layer or null.
     */
    public static TMXLayer getLayerByName(TMXTiledMap map, String name) {
        for (TMXLayer layer : map.getTMXLayers()) {
            if (layer.getName().equals(name)) {
                return layer;
            }
        }
        return null;
    }
}
