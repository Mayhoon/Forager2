package com.mygdx.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Camera extends OrthographicCamera implements InputProcessor {
    private boolean moveUp,moveDown,moveLeft,moveRight;
    private float cameraSpeed;
    private float deltaTime;


    public Camera() {
        super.setToOrtho(false, Gdx.graphics.getWidth()*2, Gdx.graphics.getHeight()*2);
        super.update();
        cameraSpeed = 5f;
    }

    public void update() {
        deltaTime = Gdx.graphics.getDeltaTime();

        if(moveUp) {
            super.translate(0f,(1f* cameraSpeed));
        }else if(moveDown) {
            super.translate(0f,(-1f* cameraSpeed));
        }else if(moveLeft) {
            super.translate((-1f* cameraSpeed),0f);
        }else if(moveRight) {
            super.translate((1f* cameraSpeed),0f);
        }
        super.update();
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.W ){
            moveUp = true;
        }
        if(keycode == Input.Keys.A){
            moveLeft = true;
        }
        if(keycode == Input.Keys.D){
            moveRight = true;
        }
        if(keycode == Input.Keys.S){
            moveDown = true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.W){
            moveUp = false;
        }
        if(keycode == Input.Keys.A){
            moveLeft = false;
        }
        if(keycode == Input.Keys.D){
            moveRight = false;
        }
        if(keycode == Input.Keys.S){
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
