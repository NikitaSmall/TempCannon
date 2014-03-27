package com.github.alexYer.tempCannon.core.entity;

import org.andengine.entity.sprite.Sprite;

public abstract class AbstractEntity {
    private Sprite sprite;
    private boolean onScene;

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
}
