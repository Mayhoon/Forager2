package com.mygdx.inventory;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.config.CFG;
import com.mygdx.items.Sword;

public class Inventory implements InputProcessor {
    private Item[] itemList;

    public Inventory() {
        itemList = new Item[CFG.INVENTORY_SCROLLBAR_SIZE];
        itemList[0] = new Sword(0);
    }

    public void addItem(Item item) {
        itemList[0].setAmount(item.getAmount());
    }

    public Item getItem(int index) {
        if (index < itemList.length && index >= 0 && itemList[index] != null) {
            return itemList[index];
        } else {
            //Failed to retrieve item, using placeholder instead
            return new Item("error", "missing", false, 0, 100);
        }
    }

    public Sword get(int index) {
        return (Sword) itemList[index];
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.E) {
//            Image inventoryGrid = new Image();
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
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
