package com.github.alexYer.tempCannon.core;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.tmx.TMXObject;
import org.andengine.extension.tmx.TMXObjectGroup;
import org.andengine.extension.tmx.TMXTiledMap;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.github.alexYer.tempCannon.core.entity.TestPlayer;
import com.github.alexYer.tempCannon.resourcemanager.Level;
import com.github.alexYer.tempCannon.resourcemanager.ResourceManager;
import com.github.alexYer.tempCannon.util.Log;
import com.github.alexYer.tempCannon.util.exception.TempCannonException;
import com.github.alexYer.tempCannon.util.exception.TempCannonTmxException;

/**
 * @author Olexander Yermakov (mannavard1611@gmail.com)
 */
public class Core {
    public TestPlayer player;
    private Scene scene;
    private TMXTiledMap map;
    private ResourceManager resourceManager;
    private VertexBufferObjectManager vertexBufferObjectManager;
    private TMXObjectGroup objectGroup;


    public Core(TextureRegion playerTexture, VertexBufferObjectManager vertexBufferObjectManager, Camera camera, 
            TMXTiledMap map, Scene scene, ResourceManager resourceManager) {
        this.scene = scene;
        this.map = map;
        this.resourceManager = resourceManager;
        this.vertexBufferObjectManager = vertexBufferObjectManager;
        //player = new TestPlayer(playerTexture, vertexBufferObjectManager);

        try {
            objectGroup = Level.getObjectGroupByName(map, Constants.PHYSICAL_OBJECT_GROUP_NAME);
        } catch(TempCannonTmxException e) {
            Log.e(e.toString());
        }

        createPlayer();
        camera.setChaseEntity(player.getSprite());

    }


    public void update(float x, float y) {
        Sprite pSprite = player.getSprite();
        float currentX = pSprite.getX();
        float currentY = pSprite.getY();

        player.getSprite().setPosition(x + currentX, y + currentY);
    }

    // FIXME: temporary
    public void createPlayer() {
        TMXObject playerObject = null;
        try {
            playerObject = Level.getObjectByName(objectGroup, "player");
            player = new TestPlayer(resourceManager.loadTexture("face_box.png"), vertexBufferObjectManager);

            float x = Level.levelToSceneCoordinatesX((float) playerObject.getX(), map);
            float y = Level.levelToSceneCoordinatesY((float) playerObject.getY(), map);
            //Log.i(Integer.toString(playerObject.getX()));
            //Log.i(Float.toString(y));

            player.getSprite().setPosition(x, y);
        } catch (TempCannonTmxException e) {
            Log.e(e.toString());
            return;
        } catch (TempCannonException e) {
            Log.e(e.toString());
            return;
        }


    }
}
