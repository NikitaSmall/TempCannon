package com.github.alexYer.tempCannon.core.entity;

import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.tmx.TMXObject;

public abstract class AbstractEntity {
    public String id;
    private Sprite sprite;
    private boolean onScene;
    private TMXObject physicsObject;
    private float x;
    private float y;

    public AbstractEntity(String id, TMXObject object, float x, float y) {
        this.id = id;
        this.physicsObject = object;
        this.x = x;
        this.y = y;
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

    /**
     * @return the x
     */
    public float getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public float getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(float y) {
        this.y = y;
    }

    public void move() {
        sprite.setPosition(x, y);
    }
}
