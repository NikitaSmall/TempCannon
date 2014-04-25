package com.github.alexYer.tempCannon.resourcemanager;

import java.io.IOException;
import java.io.InputStream;

import org.andengine.extension.tmx.TMXLoader;
import org.andengine.extension.tmx.TMXTiledMap;
import org.andengine.extension.tmx.util.exception.TMXLoadException;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.io.in.IInputStreamOpener;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

/**
 * Created by Никита on 05.04.14.
 */
public class ResourceManager {
    private AssetManager assetManager;
    private TextureManager textureManager;
    private VertexBufferObjectManager vertexBufferObjectManager;


    public ResourceManager(Bundle properties, AssetManager baseAssetManager, TextureManager baseTextureManager, 
            VertexBufferObjectManager baseVertexBufferObjectManager) {
        ResourceConstant.cameraWidth = properties.getInt("cameraWidth");
        ResourceConstant.cameraHeight = properties.getInt("cameraHeigth");
        ResourceConstant.density = properties.getFloat("density");
        ResourceConstant.densityDpi = properties.getInt("densityDpi");

        assetManager = baseAssetManager;
        textureManager = baseTextureManager;
        vertexBufferObjectManager = baseVertexBufferObjectManager;
    }


    public TextureRegion fullPathLoadTexture(final String path) {
        BitmapTexture texture = null;
        TextureRegion region = null;
        try {
            texture = new BitmapTexture(textureManager, new IInputStreamOpener() {
                @Override
                public InputStream open() throws IOException {
                    return assetManager.open(path);
                }
            });

            texture.load();
            region = TextureRegionFactory.extractFromTexture(texture);
        } catch (IOException e) {
            Log.e("TempCannon", "Error on loading texture:");
            Log.e("TempCannon", String.format("   File \"%s\" does not exist.", path));
        }
        return region;
    }

    public TextureRegion loadTexture(String path) {
        return fullPathLoadTexture(this.getPathPrefix() + path);
    }


    private String getPathPrefix() {
        String folder = null;

        switch (ResourceConstant.densityDpi) {
            case DisplayMetrics.DENSITY_LOW:
                folder = "ldpi/";
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                folder = "mdpi/";
                break;
            case DisplayMetrics.DENSITY_HIGH:
                folder = "hdpi/";
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                folder = "xhdpi/";
                break;
            case DisplayMetrics.DENSITY_TV:
                folder = "tv/";
                break;
            default:
                Log.e("TempCannon", "error on choosing dpi, other dpi");
                Log.e("TempCannon", Integer.toString(ResourceConstant.densityDpi));
        }

        return folder;
    }


    /**
     * Detect screen resolution and load map for that resolutuon from file.
     *
     * @param level String "levelname". It'll be transformed in "*dpi/level/levelname.tmx".
     *                      e.g. "xhdpi/level/1.tmx"
     * @return              TMXTiledMap with level.
     */
    public TMXTiledMap loadLevel(String level) {
        TMXTiledMap map = null;

        final TMXLoader tmxLoader = new TMXLoader(assetManager, textureManager,
                vertexBufferObjectManager); 

        try {
            map = tmxLoader.loadFromAsset(getPathPrefix() + "level/" + level + ".tmx");
        } catch(TMXLoadException e) {
            Log.e("TempCannon", e.toString());
        };

        return map;
    }
}
