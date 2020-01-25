package com.mygdx.camera;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class PlayerInputProcessor implements InputProcessor {
    Camera camera;
    public PlayerInputProcessor(Camera camera) {
        this.camera = camera;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.W) {
            camera.moveUp();
        }
        if (keycode == Input.Keys.A) {
            camera.moveLeft();
        }
        if (keycode == Input.Keys.D) {
            camera.moveRight();
        }
        if (keycode == Input.Keys.S) {
            camera.moveDown();
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.W) {
            camera.stopMotion("up");
        }
        if (keycode == Input.Keys.A) {
            camera.stopMotion("left");
        }
        if (keycode == Input.Keys.D) {
            camera.stopMotion("right");
        }
        if (keycode == Input.Keys.S) {
            camera.stopMotion("down");
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
