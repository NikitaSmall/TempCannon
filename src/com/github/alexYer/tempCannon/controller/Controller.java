package com.github.alexYer.tempCannon.controller;

import java.util.HashMap;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.shape.Shape;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.util.FPSCounter;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Controller {
    private Camera mCamera;
    private Font mFont;
    private VertexBufferObjectManager mVertexBufferObjectManager;
    private HUD hud;

    public Controller(Font font, Camera camera, VertexBufferObjectManager vbo) {
        hud = new HUD();
        mVertexBufferObjectManager = vbo;
        mCamera = camera;
        mFont = font;
    }

    public void initController(HashMap<String, ControlProperties> buttonList) {

        ControlProperties lButtonProrepties = buttonList.get("left");

        Shape leftButton = createButton(lButtonProrepties.controlCoordinates[0],
                lButtonProrepties.controlCoordinates[1], lButtonProrepties.controlTexture,
                lButtonProrepties.controlTouchCallback);

        ControlProperties rButtonProrepties = buttonList.get("right");

        Shape rightButton = createButton(rButtonProrepties.controlCoordinates[0],
                rButtonProrepties.controlCoordinates[1], rButtonProrepties.controlTexture,
                rButtonProrepties.controlTouchCallback);


        hud.registerTouchArea(leftButton);
        hud.registerTouchArea(rightButton);
        hud.attachChild(leftButton);
        hud.attachChild(rightButton);

        initFpsCounter();

        mCamera.setHUD(hud);
    }

    private Shape createButton(float x, float y, TextureRegion texture, ITouchCallback c) {
        final ITouchCallback callback = c;

        final Sprite button = new Sprite(x, y, texture, mVertexBufferObjectManager) {
            @Override
            public boolean onAreaTouched(TouchEvent te, float x, float y) {
                callback.onTouch(te, x, y);
                return true;
            }
        };

        return button;
    }

    private void initFpsCounter() {
        final FPSCounter fpsCounter = new FPSCounter();
        this.hud.registerUpdateHandler(fpsCounter);

        final Text fpsText = new Text(mCamera.getWidth()-200, 0, this.mFont, "FPS:", "FPS:XXXXXX".length(),
                mVertexBufferObjectManager);

        this.hud.attachChild(fpsText);

        this.hud.registerUpdateHandler(new TimerHandler(1/20.0f, true, new ITimerCallback() {
            @Override
            public void onTimePassed(final TimerHandler timeHandler) {
                fpsText.setText("FPS: " + String.format("%.3g%n", fpsCounter.getFPS()));
            }
        }));
    }
}
