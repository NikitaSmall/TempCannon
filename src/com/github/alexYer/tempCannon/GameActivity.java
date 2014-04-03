package com.github.alexYer.tempCannon;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.andengine.engine.Engine;
import org.andengine.engine.LimitedFPSEngine;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.entity.util.FPSCounter;
import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXLoader;
import org.andengine.extension.tmx.TMXTiledMap;
import org.andengine.extension.tmx.util.exception.TMXLoadException;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.adt.io.in.IInputStreamOpener;

import android.graphics.Color;
import android.util.Log;

import com.github.alexYer.tempCannon.controller.ControlProperties;
import com.github.alexYer.tempCannon.controller.Controller;
import com.github.alexYer.tempCannon.controller.ITouchCallback;
import com.github.alexYer.tempCannon.core.Core;

/**
 * (c) 2014 Olexander Yermakov
 * 
 * @author Olexander Yermakov
 */
public class GameActivity extends SimpleBaseGameActivity {
    //Camera settings
    private BoundCamera mCamera;
    private static final int CAMERA_WIDTH = 720;
    private static final int CAMERA_HEIGHT = 480;

    private Font mFont;

    private HashMap<String, ControlProperties> mControlProperties;

    private Core mCore;

    private Scene mScene;

//FIXME: ugly construction
    private float mCurrentX;
    private float mCurrentY;

    private BitmapTexture mTexture;
    private TextureRegion mFaceTextureRegion;

    private TMXTiledMap map;

    @Override
    public EngineOptions onCreateEngineOptions() {
        mCamera = new BoundCamera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);

        return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), mCamera);
    }

    @Override
    public Engine onCreateEngine(final EngineOptions opts) {
        return new LimitedFPSEngine(opts, 60);
    }

    @Override
    public void onCreateResources() {
        initControlResources();
        initFont();

//FIXME: temporary. Implement more advanced resource manager in future.
        try {
            this.mTexture = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
                 @Override
                 public InputStream open() throws IOException {
                     return getAssets().open("face_box.png");
                 }
             });
        
            this.mTexture.load();
            this.mFaceTextureRegion = TextureRegionFactory.extractFromTexture(this.mTexture);
        } catch (IOException e) {
            Log.e("TempCannon", "error");
        }
    }

	
    @Override
    public Scene onCreateScene() {
        mScene = new Scene();

        loadLevel(mScene);
        mScene.setBackground(new Background(255, 255, 255));
        initControl();
        //initFpsCounter();
        initCore();

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
        BitmapTexture textureRight = null;
        BitmapTexture textureLeft = null;
        TextureRegion leftButtonTextureRegion = null;
        TextureRegion rightButtonTextureRegion = null;

        try {
            textureLeft = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
                 @Override
                 public InputStream open() throws IOException {
                     return getAssets().open("gfx/left.png");
                 }
             });
        
            textureLeft.load();
            leftButtonTextureRegion = TextureRegionFactory.extractFromTexture(textureLeft);
        } catch (IOException e) {
            Log.e("TempCannon", "Error loading Left Button texture.");
        }

        try {
            textureRight = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
                 @Override
                 public InputStream open() throws IOException {
                     return getAssets().open("gfx/right.png");
                 }
             });
        
            textureRight.load();
            rightButtonTextureRegion = TextureRegionFactory.extractFromTexture(textureRight);
        } catch (IOException e) {
            Log.e("TempCannon", "Error loading Right Button texture.");
        }

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

    private void initCore() {
        mCore = new Core(mFaceTextureRegion, getVertexBufferObjectManager(), mCamera);
        mScene.attachChild(mCore.player.getSprite());
    }

    private void loadLevel(Scene scene) {
        final TMXLoader tmxLoader = new TMXLoader(this.getAssets(), this.mEngine.getTextureManager(),
                this.getVertexBufferObjectManager());

        try {
            map = tmxLoader.loadFromAsset("level/testLevel.tmx");
        } catch(TMXLoadException e) {
            Log.e("TempCannon", e.toString());
        };

        for (TMXLayer layer : map.getTMXLayers()) {
            mScene.attachChild(layer);
        }

        //mCamera.setBounds(0, 0, map.getHeight(), map.getWidth());
        //mCamera.setBoundsEnabled(true);
    }

//FIXME: delete fps counter
    //private void initFpsCounter() {
        //final FPSCounter fpsCounter = new FPSCounter();
        //this.mEngine.registerUpdateHandler(fpsCounter);

        //final Text fpsText = new Text(CAMERA_WIDTH-200, 0, this.mFont, "FPS:", "FPS:XXXXXX".length(),
                //this.getVertexBufferObjectManager());

        //this.mScene.attachChild(fpsText);

        //this.mScene.registerUpdateHandler(new TimerHandler(1/20.0f, true, new ITimerCallback() {
            //@Override
            //public void onTimePassed(final TimerHandler timeHandler) {
                //fpsText.setText("FPS: " + String.format("%.3g%n", fpsCounter.getFPS()));
            //}
        //}));
    //}

    private void initFont() {
        FontFactory.setAssetBasePath("font/");
        this.mFont = FontFactory.createFromAsset(this.getFontManager(), this.getTextureManager(),
                512, 512, TextureOptions.BILINEAR,  this.getAssets(), "Droid.ttf", 32, true, Color.BLACK);
        this.mFont.load();
    }
}
