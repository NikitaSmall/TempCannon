package com.github.alexYer.tempCannon.physics;

import org.andengine.extension.tmx.TMXTiledMap;

import com.github.alexYer.tempCannon.core.Constants;
import com.github.alexYer.tempCannon.core.EntityList;
import com.github.alexYer.tempCannon.core.HudState;
import com.github.alexYer.tempCannon.core.entity.AbstractEntity;

/**
 * @author Olexander Yermakov (mannavard1611@gmail.com)
 */
public class PhysicsEngine {
    private EntityList entityList;
    private AbstractEntity player;

    public PhysicsEngine(TMXTiledMap map, EntityList entityList) {
        this.entityList = entityList;

        player = entityList.getEntityById(Constants.PLAYER);
        PhysicsConstants.maxPlayerSpeed = PhysicsConstants.maxPlayerTilePerSecondSpeed * map.getTileWidth() / PhysicsConstants.FPS;
    }

    public void update(HudState hudState) {
        movePlayer(hudState);
    }

    public void movePlayer(HudState hudState) {
        if (hudState.rightButton) {
            player.setX(player.getX() + PhysicsConstants.maxPlayerSpeed);
        }
    }
}
