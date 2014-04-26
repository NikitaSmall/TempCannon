package com.github.alexYer.tempCannon.core;

import java.util.ArrayList;
import java.util.List;

import com.github.alexYer.tempCannon.core.entity.AbstractEntity;

/**
 * @author Olexander Yermakov (mannavard1611@gmail.com)
 */
public class EntityList {
    private List<AbstractEntity> entityList;

    public EntityList() {
        entityList = new ArrayList<AbstractEntity>();
    }

    /**
     * @return the entityList
     */
    public List<AbstractEntity> getEntityList() {
        return entityList;
    }

    public List<AbstractEntity> addEntity(AbstractEntity entity)  {
        entityList.add(entity);
        return entityList;
    }

    public AbstractEntity removeEntity(String id) {
        for (AbstractEntity entity : entityList) {
            if (entity.id.equals(id)) {
                entityList.remove(entity);
                return entity;
            }
        } 
        return null;
    }
}
