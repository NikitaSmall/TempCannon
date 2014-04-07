package com.github.alexYer.tempCannon.resourcemanager;

import android.content.Context;
import android.content.res.*;
import android.os.Bundle;
import android.util.Log;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.util.adt.io.in.IInputStreamOpener;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Никита on 05.04.14.
 */
public class ResourceManager {
    private AssetManager assetManager;
    private TextureManager textureManager;

    public ResourceManager(Bundle properties, AssetManager baseAssetManager, TextureManager baseTextureManager) {
        ResourceConstant.cameraWidth = properties.getInt("cameraWidth");
        ResourceConstant.cameraHeight = properties.getInt("cameraHeigth");
        ResourceConstant.density = properties.getFloat("density");
        ResourceConstant.densityDpi = properties.getInt("densityDpi");

        assetManager = baseAssetManager;
        textureManager = baseTextureManager;
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
            Log.e("TempCannon", "error on fullPathLoadTexture");
        }
        return region;
    }

/*    public TextureRegion loadTexture(String path) {

        return ;
    }*/
}
