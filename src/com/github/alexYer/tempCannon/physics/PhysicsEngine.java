package com.github.alexYer.tempCannon.physics;

import org.andengine.extension.tmx.TMXTiledMap;

import com.github.alexYer.tempCannon.core.Constants;
import com.github.alexYer.tempCannon.core.EntityList;
import com.github.alexYer.tempCannon.core.HudState;

/**
 * @author Olexander Yermakov (mannavard1611@gmail.com)
 */
public class PhysicsEngine {
    private EntityList entityList;

    public PhysicsEngine(TMXTiledMap map, EntityList entityList) {
        this.entityList = entityList;
    }

    public void update(HudState hudState) {
    }
}
