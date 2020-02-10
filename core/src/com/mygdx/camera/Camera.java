package com.mygdx.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Camera extends OrthographicCamera {
    private float cameraSpeed = 60f;
    private float x,y;

    public Camera() {
        super.setToOrtho(false, Gdx.graphics.getWidth() * 2, Gdx.graphics.getHeight() * 2);
        super.update();
    }

    public void update() {
        super.translate(x * Gdx.graphics.getDeltaTime(),y * Gdx.graphics.getDeltaTime());
        super.update();
    }

    public void moveByController(float x, float y) {
        this.x = x * cameraSpeed;
        this.y = y * cameraSpeed;
    }

    public void moveWithKeyboard(float x, float y){
        super.translate(x, y);
    }
}
