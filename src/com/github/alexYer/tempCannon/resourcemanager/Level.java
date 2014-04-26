package com.github.alexYer.tempCannon.resourcemanager;

import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXLayerProperty;
import org.andengine.extension.tmx.TMXObject;
import org.andengine.extension.tmx.TMXObjectGroup;
import org.andengine.extension.tmx.TMXTiledMap;

import com.github.alexYer.tempCannon.util.Log;
import com.github.alexYer.tempCannon.util.exception.TempCannonException;
import com.github.alexYer.tempCannon.util.exception.TempCannonTmxException;


/**
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
        for (TMXObjectGroup obj : map.getTMXObjectGroups()) {
            if (obj.getName().equals(name)) {
                return obj;
            }
        }
        throw new TempCannonTmxException(String.format("No such group object: %s", name));
    }

    /**
     * @param map   Group from where we want to get Object.
     * @param name  Object name.
     * @return      Object.
     */
    public static TMXObject getObjectByName(TMXObjectGroup group, String name) throws TempCannonTmxException {
        for (TMXObject obj : group.getTMXObjects()) {
            if (obj.getName().equals(name)) {
                return obj;
            }
        }
        throw new TempCannonTmxException(String.format("No such object: %s", name));
    }

    public static TMXLayerProperty getTmxLayerProperty(TMXLayer layer, String propertyName) throws TempCannonTmxException {
        for (TMXLayerProperty property : layer.getTMXLayerProperties()) {
            if (property.getName().equals(propertyName)) {
                return property;
            }
        } 
        throw new TempCannonTmxException(String.format("No such layer property: %s", propertyName));
    }

    public static float levelToSceneCoordinatesX(float x, TMXTiledMap map) throws TempCannonException {
        try {
            TMXLayerProperty property = getTmxLayerProperty(getLayerByName(map, "Map"), "originalTileSize");
            return x / Integer.parseInt(property.getValue()) * map.getTileWidth();
        } catch(TempCannonTmxException e) {
            Log.e(e.toString());
        }
        throw new TempCannonException(String.format("Can't convert coordinates."));
    }

    public static float levelToSceneCoordinatesY(float y, TMXTiledMap map) throws TempCannonException {
        try {
            TMXLayerProperty property = getTmxLayerProperty(getLayerByName(map, "Map"), "originalTileSize");
            return y / Integer.parseInt(property.getValue()) * map.getTileHeight();
        } catch(TempCannonTmxException e) {
            Log.e(e.toString());
        }
        throw new TempCannonException(String.format("Can't convert coordinates."));
    }
}
