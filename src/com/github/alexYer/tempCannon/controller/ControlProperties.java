package com.github.alexYer.tempCannon.controller;

import org.andengine.opengl.texture.region.TextureRegion;

public class ControlProperties {
    public String controlId;
    public TextureRegion controlTexture;
    public float[] controlCoordinates;
    public ITouchCallback controlTouchCallback;

    public ControlProperties(String id, TextureRegion texture, float[] coordinates, 
            ITouchCallback touchCallback) {
        this.controlId = id;
        this.controlTexture = texture;
        this.controlCoordinates = coordinates;
        this.controlTouchCallback = touchCallback;
    }
}
