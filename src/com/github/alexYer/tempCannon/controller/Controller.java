package com.github.alexYer.tempCannon.controller;

import java.util.HashMap;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.shape.Shape;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Controller {
    private ITouchCallback mLeftCallback;

    private VertexBufferObjectManager mVertexBufferObjectManager;
    //private Camera mCamera;
    private HUD hud;

    public Controller() {
        hud = new HUD();
    }

    public void initController(HashMap<String, ControlProperties> buttonList, VertexBufferObjectManager vbo, Camera camera) {
        mVertexBufferObjectManager = vbo;
        //mCamera = camera;

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

        camera.setHUD(hud);
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
}
