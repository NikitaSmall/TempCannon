package com.github.alexYer.tempCannon.core;

import java.util.List;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.extension.tmx.TMXObject;
import org.andengine.extension.tmx.TMXObjectGroup;
import org.andengine.extension.tmx.TMXTiledMap;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.github.alexYer.tempCannon.core.entity.AbstractEntity;
import com.github.alexYer.tempCannon.core.entity.TestPlayer;
import com.github.alexYer.tempCannon.physics.PhysicsEngine;
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
    // FIXME: maybe unused
    private TMXObjectGroup objectGroup;
    private PhysicsEngine physicsEngine;
    private EntityList entityList;


    public Core(TextureRegion playerTexture, VertexBufferObjectManager vertexBufferObjectManager, Camera camera, 
            TMXTiledMap map, Scene scene, ResourceManager resourceManager) {
        this.scene = scene;
        this.map = map;
        this.resourceManager = resourceManager;
        this.vertexBufferObjectManager = vertexBufferObjectManager;

        try {
            objectGroup = Level.getObjectGroupByName(map, Constants.PHYSICAL_OBJECT_GROUP_NAME);
        } catch(TempCannonTmxException e) {
            Log.e(e.toString());
        }

        createEntityList(Constants.ENTITY, objectGroup);
        this.physicsEngine = new PhysicsEngine(map, entityList);

        createPlayer();
        //camera.setChaseEntity(player.getSprite());

    }

    public void update(HudState hudState) {
        physicsEngine.update(hudState);
        moveEntities();
    }

    public void moveEntities() {
        for (AbstractEntity e : entityList.getEntityList()) {
            e.move();
        } 
    }

    // FIXME: temporary
    public void createPlayer() {
        player = (TestPlayer) entityList.getEntityById(Constants.PLAYER);
        if (player != null) {
            player.move();
            scene.attachChild(player.getSprite());
        } 
    }

    private void createEntityList(String type, TMXObjectGroup group) {
        List<TMXObject> objectList = Level.getObjectsByType(type, group);
        entityList = new EntityList();

        // TODO: implement for all entity types and fix constants
        for (TMXObject obj : objectList) {
            if (obj.getName().equals(Constants.PLAYER)) {
                entityList.addEntity(new TestPlayer(Constants.PLAYER,
                            obj,
                            resourceManager.loadTexture("face_box.png"),
                            vertexBufferObjectManager,
                            obj.getX(),
                            obj.getY()));
            }
        }  
    }
}
