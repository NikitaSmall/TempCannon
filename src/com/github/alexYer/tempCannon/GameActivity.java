package com.github.alexYer.tempCannon;

import java.io.IOException;
import java.io.InputStream;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl.IOnScreenControlListener;
import org.andengine.engine.camera.hud.controls.DigitalOnScreenControl;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.adt.io.in.IInputStreamOpener;

import android.opengl.GLES20;
import android.util.Log;

import com.github.alexYer.tempCannon.core.Core;

/**
 * (c) 2014 Olexander Yermakov
 * 
 * @author Olexander Yermakov
 */
public class GameActivity extends SimpleBaseGameActivity {
    //Camera settings
    private Camera mCamera;
    private static final int CAMERA_WIDTH = 720;
    private static final int CAMERA_HEIGHT = 480;

    //Control settings
	private BitmapTextureAtlas mOnScreenControlTexture;
	private ITextureRegion mOnScreenControlBaseTextureRegion;
	private ITextureRegion mOnScreenControlKnobTextureRegion;
    private DigitalOnScreenControl mDigitalOnScreenControl;

    private Core mCore;

    private Scene mScene;

//FIXME: ugly construction
    private float mCurrentX;
    private float mCurrentY;

    private BitmapTexture mTexture;
    private TextureRegion mFaceTextureRegion;

    @Override
    public EngineOptions onCreateEngineOptions() {
        mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);

        return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), mCamera);
    }

    @Override
    public void onCreateResources() {
        initControlResources();
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
        mScene.setBackground(new Background(0.09804f, 0.6274f, 0.8784f));

        initControl();
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
		this.mDigitalOnScreenControl = new DigitalOnScreenControl(0, CAMERA_HEIGHT - this.mOnScreenControlBaseTextureRegion.getHeight(),
                this.mCamera, this.mOnScreenControlBaseTextureRegion, this.mOnScreenControlKnobTextureRegion,
                0.1f, this.getVertexBufferObjectManager(), new IOnScreenControlListener() {
            @Override
            public void onControlChange(final BaseOnScreenControl pBaseOnScreenControl, final float pValueX, final float pValueY) {
                mCurrentX = pValueX;
                mCurrentY = pValueY;
            }

		});

		this.mDigitalOnScreenControl.getControlBase().setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		this.mDigitalOnScreenControl.getControlBase().setAlpha(0.5f);
		this.mDigitalOnScreenControl.getControlBase().setScaleCenter(0, 128);
		this.mDigitalOnScreenControl.getControlBase().setScale(1.25f);
		this.mDigitalOnScreenControl.getControlKnob().setScale(1.25f);
		this.mDigitalOnScreenControl.refreshControlKnobPosition();

		mScene.setChildScene(this.mDigitalOnScreenControl);
    }

    private void initControlResources() {
		this.mOnScreenControlTexture = new BitmapTextureAtlas(this.getTextureManager(), 256, 128, TextureOptions.BILINEAR);
		this.mOnScreenControlBaseTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mOnScreenControlTexture,
                this, "onscreen_control_base.png", 0, 0);
		this.mOnScreenControlKnobTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mOnScreenControlTexture,
                this, "onscreen_control_knob.png", 128, 0);
		this.mOnScreenControlTexture.load();
    }

    private void initCore() {
        mCore = new Core(mFaceTextureRegion, getVertexBufferObjectManager());
        mScene.attachChild(mCore.player.getSprite());
    }
}
