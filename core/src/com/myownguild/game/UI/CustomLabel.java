package com.myownguild.game.UI;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class CustomLabel extends Label {
    private String text;

    public CustomLabel(CharSequence text, LabelStyle style) {
        super(text, style);
        this.text = text.toString();
    }

    @Override
    public void act(float delta) {
        this.setText(text);
        super.act(delta);
    }

    public void updateText(String text){
        this.text = text;
    }
}
