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
        }

        if (right) {
            super.translate(Gdx.graphics.getDeltaTime() * cameraSpeed, 0f);
        }

        if (up) {
            super.translate(0f, Gdx.graphics.getDeltaTime() * cameraSpeed);
        }

        if (down) {
            super.translate(0f, -(Gdx.graphics.getDeltaTime() * cameraSpeed));
        }
        super.update();
    }

    public void stopMotion(int axisCode, boolean left) {
        if (axisCode == 1 && left) {
            left = false;
        }
        if(axisCode == 1 && !left){
            right = false;
        }
        if(axisCode == 0 && !left){
            down = false;
        }
        if(axisCode == 0 && left) {
            up = false;
        }
    }

    public void moveUp() {
        System.out.println("MOVED UP");
        up = true;
        update();
    }

    public void moveDown() {
        System.out.println("MOVED Down");
        down = true;
        update();
    }

    public void moveLeft() {
        System.out.println("MOVED UP");
        left = true;
        update();
    }

    public void moveRight() {
        System.out.println("MOVED UP");
        right = true;
        update();
    }

    public void stopAllMotion(){
        left = false;
        right = false;
        up = false;
        down = false;
    }

}
