package com.github.alexYer.tempCannon.physics;

import org.andengine.extension.tmx.TMXTiledMap;

import com.github.alexYer.tempCannon.core.Constants;
import com.github.alexYer.tempCannon.core.EntityList;
import com.github.alexYer.tempCannon.core.HudState;
import com.github.alexYer.tempCannon.core.entity.AbstractEntity;
import com.github.alexYer.tempCannon.util.Log;

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
        PhysicsConstants.playerAcceleration = PhysicsConstants.maxPlayerSpeed / 6;
        PhysicsConstants.playerDeceleration = PhysicsConstants.maxPlayerSpeed / 12;
    }

    public void update(HudState hudState) {
        movePlayer(hudState);
    }

    public void movePlayer(HudState hudState) {
        float currentSpeed = player.getSpeed();
        if (hudState.rightButton) {
            if (currentSpeed < PhysicsConstants.maxPlayerSpeed) {
                currentSpeed += PhysicsConstants.playerAcceleration;
                if (currentSpeed > PhysicsConstants.maxPlayerSpeed) {
                    currentSpeed = PhysicsConstants.maxPlayerSpeed;
                }
            }
        } else {
            if (currentSpeed > 0) {
                currentSpeed -= PhysicsConstants.playerDeceleration;
                if (currentSpeed < 0) {
                    currentSpeed = 0;
                }
            }
        }

        if (hudState.leftButton) {
            if (currentSpeed > -PhysicsConstants.maxPlayerSpeed) {
                currentSpeed -= PhysicsConstants.playerAcceleration;
                if (currentSpeed < -PhysicsConstants.maxPlayerSpeed) {
                    currentSpeed = -PhysicsConstants.maxPlayerSpeed;
                }
            }
        } else {
            if (currentSpeed < 0) {
                currentSpeed += PhysicsConstants.playerDeceleration;
                if (currentSpeed > 0) {
                    currentSpeed = 0;
                }
            }
        }
        player.setX(player.getX() + currentSpeed);
        player.setSpeed(currentSpeed);
    }
}
