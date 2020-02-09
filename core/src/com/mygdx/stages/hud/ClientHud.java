package com.mygdx.stages.hud;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.stages.ownStage;

public class ClientHud extends ownStage {
    private Table table;

    public ClientHud() {
        table = new Table();

//        BitmapFont font = new BitmapFont(Gdx.files.internal(Paths.TEXTINPUT_SERVER_IP));
//        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle(font, null, null, null, null);
//
//        TextField textField = new TextField("HI", textFieldStyle);
//        table.add(textField);

        addActor(table);
    }
}
