package com.mygdx.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Camera extends OrthographicCamera {
    private float cameraSpeed = 60f;
    boolean left, right, down, up;

    public Camera() {
        super.setToOrtho(false, Gdx.graphics.getWidth() * 2, Gdx.graphics.getHeight() * 2);
        super.update();
    }

    public void update() {
        if (left) {
            super.translate(-(Gdx.graphics.getDeltaTime() * cameraSpeed), 0f);
            super.update();
        }

        if (right) {
            super.translate(Gdx.graphics.getDeltaTime() * cameraSpeed, 0f);
            super.update();
        }

        if (up) {
            super.translate(0f, Gdx.graphics.getDeltaTime() * cameraSpeed);
            super.update();
        }

        if (down) {
            super.translate(0f, -(Gdx.graphics.getDeltaTime() * cameraSpeed));
            super.update();
        }

        super.update();
    }

    public void moveUp() {
        up = true;
        update();
    }

    public void moveDown() {
        down = true;
        update();
    }

    public void moveLeft() {
        left = true;
        update();
    }

    public void moveRight() {
        right = true;
        update();
    }

    public void stopMotion(String dir){
        switch(dir) {
            case "left": left = false;
                break;
            case "right": right = false;
                break;
            case "up": up = false;
                break;
            case "down": down = false;
                break;
            default: System.out.println("ERROR");
        }
    }

    public void moveDirect(float x, float y) {
        super.translate(Gdx.graphics.getDeltaTime() * (x  * cameraSpeed), Gdx.graphics.getDeltaTime() * (y * cameraSpeed));
    }
}
