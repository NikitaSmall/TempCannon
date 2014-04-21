package com.github.alexYer.tempCannon.resourcemanager;

import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXObjectGroup;
import org.andengine.extension.tmx.TMXTiledMap;

import com.github.alexYer.tempCannon.util.exception.TempCannonTmxException;


/**
 *
 * @author Olexander Yermakov (mannavard1611@gmail.com)
 */
public class Level {
    /**
     * @param map   Map from where we want to get layer.
     * @param name  Layer name.
     * @return      Layer.
     */
    public static TMXLayer getLayerByName(TMXTiledMap map, String name) throws TempCannonTmxException {
        for (TMXLayer layer : map.getTMXLayers()) {
            if (layer.getName().equals(name)) {
                return layer;
            }
        }
        throw new TempCannonTmxException(String.format("No such layer: %s", name));
    }

    
    /**
     * @param map   Map from where we want to get GroupObject.
     * @param name  GroupObject name.
     * @return      GroupObject.
     */
    public static TMXObjectGroup getObjectGroupByName(TMXTiledMap map, String name) throws TempCannonTmxException {
        for (TMXObjectGroup obj: map.getTMXObjectGroups()) {
            if (obj.getName().equals(name)) {
                return obj;
            }
        }
        throw new TempCannonTmxException(String.format("No such group Object: %s", name));
    }
}
