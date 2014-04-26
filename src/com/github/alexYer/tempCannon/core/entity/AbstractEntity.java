package com.github.alexYer.tempCannon.core.entity;

import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.tmx.TMXObject;

public abstract class AbstractEntity {
    public String id;
    private Sprite sprite;
    private boolean onScene;
    private TMXObject physicsObject;

    public AbstractEntity(String id, TMXObject object) {
        this.id = id;
        this.physicsObject = object;
    }

    /**
     * @return the sprite
     */
    public Sprite getSprite() {
        return sprite;
    }

    /**
     * @param sprite the sprite to set
     */
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    /**
     * @return the onScene
     */
    public boolean isOnScene() {
        return onScene;
    }

    /**
     * @param onScene the onScene to set
     */
    public void setOnScene(boolean onScene) {
        this.onScene = onScene;
    }

    /**
     * @return the physicsObject
     */
    public TMXObject getPhysicsObject() {
        return physicsObject;
    }
}
