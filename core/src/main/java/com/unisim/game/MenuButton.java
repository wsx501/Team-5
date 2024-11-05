package com.unisim.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class MenuButton extends Actor {
    //Texture texture;
    //TextureRegion region;

    Sprite sprite;
    Color colour;

    public MenuButton(String buttonPath, float x, float y, float scale) {
        sprite = new Sprite(new Texture(buttonPath));
        //texture = new Texture(buttonPath);
        //region = new TextureRegion(texture);
        //setBounds(region.getRegionX(), region.getRegionY(), region.getRegionWidth(), region.getRegionHeight());
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        setTouchable(Touchable.enabled);
        setX(x);
        setY(y);
        setWidth(getWidth()*scale);
        setHeight(getHeight()*scale);

        colour = new Color(Color.WHITE);

        //addListener(new InputListener() {

        //});

        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                colour.set(Color.RED);
                return true;
            }
        });

        //MenuButton.setPosition(Gdx.input.getX(), Gdx.input.getY());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(colour);
        batch.draw(sprite, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
