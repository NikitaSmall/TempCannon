package com.github.alexYer.tempCannon.physics;

import org.andengine.extension.tmx.TMXTiledMap;

import com.github.alexYer.tempCannon.core.HudState;
import com.github.alexYer.tempCannon.util.Log;

/**
 * @author Olexander Yermakov (mannavard1611@gmail.com)
 */
public class PhysicsEngine {
    public PhysicsEngine(TMXTiledMap map) {
    }

    public void update(HudState hudState) {
        Log.i(Boolean.toString(hudState.leftButton));
    }
}
