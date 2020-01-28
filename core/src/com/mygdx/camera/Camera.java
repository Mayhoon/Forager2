package com.mygdx.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Camera extends OrthographicCamera {
    private float cameraSpeed = 60f;
    public boolean left, right, down, up;
    float x;
    float y;

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
}
