package com.mygdx.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.camera.Camera;

public class InputManager {

    private Camera camera;
    private InputMultiplexer inputMultiplexer;

    public InputManager(Camera camera) {
        this.camera = camera;
        inputMultiplexer = new InputMultiplexer();
    }

    public void addInputPorcessor(InputProcessor input) {
        inputMultiplexer.addProcessor(input);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }
}
