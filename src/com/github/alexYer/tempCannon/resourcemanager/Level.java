package com.github.alexYer.tempCannon.resourcemanager;

import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXObjectGroup;
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

    
    /**
     * @param map   Map from where we want to get GroupObject.
     * @param name  GroupObject name.
     * @return      GroupObject or null.
     */
    public static TMXObjectGroup getObjectGroupByName(TMXTiledMap map, String name) {
        for (TMXObjectGroup obj: map.getTMXObjectGroups()) {
            if (obj.getName().equals(name)) {
                return obj;
            }
        }
        return null;
    }
}
