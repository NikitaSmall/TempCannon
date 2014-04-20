package com.github.alexYer.tempCannon.core;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.tmx.TMXTiledMap;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.github.alexYer.tempCannon.core.entity.TestPlayer;

/**
 * @author Olexander Yermakov (mannavard1611@gmail.com)
 */
public class Core {
    public TestPlayer player;
    private Scene scene;
    private TMXTiledMap map;

    public Core(TextureRegion playerTexture, VertexBufferObjectManager vertexBufferObjectManager, Camera camera, 
            TMXTiledMap map, Scene scene) {
        this.scene = scene;
        this.map = map;
        player = new TestPlayer(playerTexture, vertexBufferObjectManager);
        camera.setChaseEntity(player.getSprite());
    }

    public void update(float x, float y) {
        Sprite pSprite = player.getSprite();
        float currentX = pSprite.getX();
        float currentY = pSprite.getY();

        player.getSprite().setPosition(x + currentX, y + currentY);
    }
}
