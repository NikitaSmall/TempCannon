package com.github.alexYer.tempCannon.core.entity;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;


public class TestPlayer extends Player {
    public TestPlayer(TextureRegion texture, VertexBufferObjectManager vertexBufferObjectManager) {
        loadSprite(texture, vertexBufferObjectManager);
    }

    private void loadSprite(TextureRegion texture, VertexBufferObjectManager vertexBufferObjectManager) {
        setSprite(new Sprite(100, 300, texture, vertexBufferObjectManager));
    }
}
