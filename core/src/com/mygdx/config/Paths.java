package com.mygdx.config;

public final class Paths {

    //Basic configuration
    public final static int INVENTORY_SCROLLBAR_SIZE = 6;
    public final static String PNG = ".png";
    public final static String OTF = ".OTF";
    public final static String TTF = ".TTF";


    //Base paths to the images, skins and maps
    public final static String CHARACTERS_PATH = "characters/";
    public final static String ANIMATIONS_PATH = "animations/";
    public final static String BUTTONS_PATH = "buttons/";
    public final static String FONTS_SKINS_PATH = "fonts/";
    public final static String INVENTORY_PATH = "inventory/";
    public final static String ITEMS_PATH = "items/";
    public final static String MAPS_PATH = "maps/";

    //Fonts, skins and ui
    public final static String ITEM_COUNT_FONT = FONTS_SKINS_PATH + "B8.ttf";
    public final static String HOST_BUTTON = BUTTONS_PATH + "/host" + PNG;
    public final static String JOIN_BUTTON = BUTTONS_PATH + "/join" + PNG;
    public final static String HOST_HOVERED_BUTTON = BUTTONS_PATH + "/host_hovered" + PNG;
    public final static String JOIN_HOVERED_BUTTON = BUTTONS_PATH + "/join_hovered" + PNG;
    public final static String TEXTINPUT_SERVER_IP = FONTS_SKINS_PATH + "3dventure/3Dventure" + TTF;

    //Inventory
    public final static String MISSING_ITEM = INVENTORY_PATH + "missing" + PNG;
    public final static String Inventory_INGAME_SCROLLBAR = INVENTORY_PATH + "inv" + PNG;

    //Player related
    public final static String PLAYER_PATH = CHARACTERS_PATH + "/player/player.png";
    public final static String PLAYER_IDLE = ANIMATIONS_PATH + "/player/skeleton/idle" + PNG;
    public final static String PLAYER_RUN = ANIMATIONS_PATH + "/player/skeleton/run" + PNG;

    //Maps
    public final static String TILESET_PATH = MAPS_PATH + "Tileset.png";
}
