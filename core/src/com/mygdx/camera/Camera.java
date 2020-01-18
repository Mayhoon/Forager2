package com.mygdx.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Camera extends OrthographicCamera implements InputProcessor {
    public boolean moveUp, moveDown, moveLeft, moveRight;
    private float cameraSpeed;
    private float deltaTime;


    public Camera() {
        super.setToOrtho(false, Gdx.graphics.getWidth() * 2, Gdx.graphics.getHeight() * 2);
        super.update();
        cameraSpeed = 60f;
    }

    public void moveUp() {
        //super.translate(0f, Gdx.graphics.getDeltaTime() * cameraSpeed);
        moveUp = true;
    }

    public void moveDown() {
       // super.translate(0f, Gdx.graphics.getDeltaTime() * (-cameraSpeed));
        moveDown = true;
    }

    public void moveLeft() {
        //super.translate(Gdx.graphics.getDeltaTime() * (-cameraSpeed), 0f);
        moveLeft = true;
    }

    public void moveRight() {
        // super.translate(Gdx.graphics.getDeltaTime() * cameraSpeed, 0f);
        moveRight = true;
    }

    public void stopMotion(int axisCode, boolean left) {
        if (axisCode == 1 && left) {
            moveLeft = false;

        }if(axisCode == 1 && !left){
            moveRight = false;
        }
        if(axisCode == 0){
            moveDown = false;
            moveUp = false;
        }
    }

    public void update() {
        deltaTime = Gdx.graphics.getDeltaTime();

        if (moveUp) {
            super.translate(0f, (deltaTime * cameraSpeed) );
        }
        if (moveDown) {
            super.translate(0f, - (deltaTime * cameraSpeed) );
        }
        if (moveLeft) {
            super.translate(- (deltaTime * cameraSpeed), 0f );
        }
        if (moveRight) {
            super.translate( (deltaTime * cameraSpeed), 0f );
        }
        super.update();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.W) {
            moveUp = true;
        }
        if (keycode == Input.Keys.A) {
            moveLeft = true;
        }
        if (keycode == Input.Keys.D) {
            moveRight = true;
        }
        if (keycode == Input.Keys.S) {
            moveDown = true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.W) {
            moveUp = false;
        }
        if (keycode == Input.Keys.A) {
            moveLeft = false;
        }
        if (keycode == Input.Keys.D) {
            moveRight = false;
        }
        if (keycode == Input.Keys.S) {
            moveDown = false;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
