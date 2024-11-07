package com.unisim.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.awt.*;
import java.awt.event.ActionListener;

public class LandPlot extends Actor {
    private Building buildingPlaced;
    private int maxSize;
    private int x, y, width, height;
    private boolean occupied;

    ImageButton button;
    TextButton txtButton;

    SpriteBatch batch;
    Skin skin;
    Color backgroundColour = new Color(0.09f, 0.41f, 0.22f, 1f);
    Pixmap backgroundPM;


    public LandPlot(int maxSize, int x, int y, int width, int height) {
        this.maxSize = maxSize;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        buildingPlaced = null;
        occupied = false;

        button = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files
            .internal("Gym.png")))));
        button.setPosition(x, y);
        button.setBounds(x, y, width, height);
        button.setSize(width, height);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });
        skin = new Skin(Gdx.files.internal("default_skin/uiskin.json"));
        button.setStyle(skin);
    }

    @Override
    public void act(float delta) {
        backgroundColour = new Color(0.09f, 0.41f, 0.22f, 1f);
        backgroundPM = new Pixmap(1,1, Pixmap.Format.RGB565);
        backgroundPM.setColor(backgroundColour);
        backgroundPM.fill();
        super.act(delta);
        if (main.selectedBuilding > -1 && !isOccupied()) {
            if (main.buildingTypes[main.selectedBuilding].getSize() <= maxSize) {
                // needs to green
                button.setColor(0, 1, 0, 1);

            }
            else {
                // needs to go red
                button.setColor(1, 0, 0, 1);
            }
        }
        else if (isOccupied()) {
            // show building
        }
        else {
            // show nothing
        }
        // otherwise : has a building placed
    }

    public void setBuilding(Building buildingPlaced) {
        this.buildingPlaced = buildingPlaced;
    }

    public void setOccupied() {
        occupied = true;
    }

    public boolean isOccupied() {
        return occupied;
    }


//    public int getMaxSize() {
//        return maxSize;
//    }
//
//    public int getX() {
//        return x;
//    }
//
//    public int getY() {
//        return y;
//    }
//
//    public int getWidth() {
//        return width;
//    }
//
//    public int getHeight() {
//        return height;
//    }


}
