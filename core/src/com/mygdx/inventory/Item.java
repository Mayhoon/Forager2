package com.mygdx.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.config.CFG;

public class Item extends Actor {
    private String type;
    private int amount;
    private int durability;
    private Image image;
    private boolean stackable;

    public Item(String type, String itemName, boolean stackable, int amount, int durability) {
        this.type = type;
        Texture texture = new Texture(Gdx.files.internal(CFG.ITEMS_PATH + itemName + ".png"));
        image = new Image(texture);
        this.stackable = stackable;
        this.amount = amount;
        this.durability = durability;
    }

    public String getType() {
        return type;
    }

    public void increaseWear(int amount) {
        durability -= amount;
    }

    public Image getImage() {
        return image;
    }

    public boolean destroy() {
        amount -= 1;
        if (amount == 0) {
            return true;
        }
        return false;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount += amount;
        System.out.println("New Amount for Item " + type + " : " + this.amount);
    }
}
