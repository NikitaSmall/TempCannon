package com.github.alexYer.tempCannon.core;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.github.alexYer.tempCannon.core.entity.TestPlayer;

public class Core {
    public TestPlayer player;

    public Core(TextureRegion playerTexture, VertexBufferObjectManager vertexBufferObjectManager, Camera camera) {
        player = new TestPlayer(playerTexture, vertexBufferObjectManager);
        //CameraController.setChaseEntity(player.getSprite());
    }

    public void update(float x, float y) {
        Sprite pSprite = player.getSprite();
        float currentX = pSprite.getX();
        float currentY = pSprite.getY();

        player.getSprite().setPosition(x + currentX, y + currentY);
    }
}
