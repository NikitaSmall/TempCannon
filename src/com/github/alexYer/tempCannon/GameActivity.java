package com.github.alexYer.tempCannon;

import java.util.HashMap;

import org.andengine.engine.Engine;
import org.andengine.engine.LimitedFPSEngine;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXTiledMap;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.color.Color;

import android.os.Bundle;

import com.github.alexYer.tempCannon.camera.CameraController;
import com.github.alexYer.tempCannon.controller.ControlProperties;
import com.github.alexYer.tempCannon.controller.Controller;
import com.github.alexYer.tempCannon.controller.ITouchCallback;
import com.github.alexYer.tempCannon.core.Constants;
import com.github.alexYer.tempCannon.core.Core;
import com.github.alexYer.tempCannon.resourcemanager.Level;
import com.github.alexYer.tempCannon.resourcemanager.ResourceManager;
import com.github.alexYer.tempCannon.util.Log;
import com.github.alexYer.tempCannon.util.exception.TempCannonTmxException;

/**
 * (c) 2014 Olexander Yermakov
 * 
 * @author Olexander Yermakov
 */
public class GameActivity extends SimpleBaseGameActivity {
    //CameraController settings
    private BoundCamera mCamera;
    private CameraController cameraController;

    //FIXME: make cooler
    private int CAMERA_WIDTH;
    private int CAMERA_HEIGHT;

    private Font mFont;

    private HashMap<String, ControlProperties> mControlProperties;

    private Core mCore;

    private Scene mScene;
    private ResourceManager resourceManager;

//FIXME: ugly construction
    private float mCurrentX;
    private float mCurrentY;

    private TextureRegion mFaceTextureRegion;

    private TMXTiledMap map;


    @Override
    public EngineOptions onCreateEngineOptions() {
        cameraController = new CameraController(getWindowManager().getDefaultDisplay());
        mCamera = new BoundCamera(0, 0, cameraController.getCameraWidth(), cameraController.getCameraHeight());

        CAMERA_HEIGHT = cameraController.getCameraHeight();
        CAMERA_WIDTH = cameraController.getCameraWidth();

        return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(cameraController.getCameraWidth(), cameraController.getCameraHeight()), mCamera);
    }


    @Override
    public Engine onCreateEngine(final EngineOptions opts) {
        return new LimitedFPSEngine(opts, 60);
    }


    @Override
    public void onCreateResources() {
        initResourceManager();
        initControlResources();
        initFont();

        this.mFaceTextureRegion = resourceManager.loadTexture("face_box.png");
    }

	
    @Override
    public Scene onCreateScene() {
        mScene = new Scene();

        TMXTiledMap map = loadLevel(mScene);
        initControl();
        initCore(map);
        mScene.setBackground(new Background(255, 255, 255));

        // Main game circle
        mScene.registerUpdateHandler(new IUpdateHandler() {
            @Override
            public void onUpdate(final float secElapsed) {
                mCore.update(mCurrentX, mCurrentY);
            }

            @Override
            public void reset() {}
        });

        return mScene;
    }


    @Override
    public void onGameCreated() {
    }


    @Override
    public void onResumeGame() {
        super.onResumeGame();
    }


    private void initControl() {
        Controller controller = new Controller(mFont, mCamera, this.getVertexBufferObjectManager());
        controller.initController(mControlProperties);
    }


    private void initControlResources() {
        TextureRegion leftButtonTextureRegion = null;
        TextureRegion rightButtonTextureRegion = null;

        leftButtonTextureRegion = resourceManager.loadTexture("gfx/left.png");
        rightButtonTextureRegion = resourceManager.loadTexture("gfx/right.png");

        float[] coordinatesLeft = {0, CAMERA_HEIGHT - 100};
        float[] coordinatesRight = {100, CAMERA_HEIGHT - 100};

        ITouchCallback leftCallback = new ITouchCallback() {
            @Override
            public void onTouch(TouchEvent te, float x, float y) {
                if (te.isActionUp()) {
                    mCurrentX = 0;
                } else {
                    if (te.isActionDown()) {
                    mCurrentX = -1;
                    }
                }
            }
        };

        ITouchCallback rightCallback = new ITouchCallback() {
            @Override
            public void onTouch(TouchEvent te, float x, float y) {
                if (te.isActionUp()) {
                    mCurrentX = 0;
                } else {
                    if (te.isActionDown()) {
                        mCurrentX = 1;
                    }
                }
            }
        };

        ControlProperties lButtonProperties = new ControlProperties("Left Button", leftButtonTextureRegion,
                coordinatesLeft, leftCallback);
        ControlProperties rButtonProperties = new ControlProperties("Right Button", rightButtonTextureRegion,
                coordinatesRight, rightCallback);

        this.mControlProperties = new HashMap<String, ControlProperties>();

        this.mControlProperties.put("left", lButtonProperties);
        this.mControlProperties.put("right", rButtonProperties);
    }


    private void initCore(TMXTiledMap map) {
        mCore = new Core(mFaceTextureRegion, getVertexBufferObjectManager(), mCamera, map, mScene);
        mScene.attachChild(mCore.player.getSprite());
    }


    private TMXTiledMap loadLevel(Scene scene) {
        map = resourceManager.loadLevel("testLevel2");
        try {
            TMXLayer layer = Level.getLayerByName(map, Constants.MAP_LAYER_NAME);

            if (layer != null) {
                mScene.attachChild(layer);
            }
        } catch(TempCannonTmxException e) {
            Log.e(e.toString());
        }


        mCamera.setBounds(0, 0, map.getTileColumns() * map.getTileHeight(), map.getTileRows() * map.getTileWidth());
        mCamera.setBoundsEnabled(true);

        return map;
    }


    private void initFont() {
        this.mFont = resourceManager.loadFont("Droid.ttf", Color.BLACK);
    }


    private void initResourceManager() {
        Bundle properties = new Bundle();
        properties.putInt("cameraWidth", cameraController.getCameraWidth());
        properties.putInt("cameraHeigth", cameraController.getCameraHeight());
        properties.putFloat("density", cameraController.getDensity());
        properties.putInt("densityDpi", cameraController.getDensityDpi());

        resourceManager = new ResourceManager(properties, getAssets(), this.getTextureManager(),
                this.getVertexBufferObjectManager(), this.getFontManager());
    }
}
