package com.unisim.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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

    Button button;
    Texture greenTexture;
    Texture redTexture;
    Texture imageTexture;
    Texture seeThroughTexture;
    Image image;
    Skin skin;

    public LandPlot(int maxSize, int x, int y, int width, int height) {
        this.maxSize = maxSize;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        buildingPlaced = null;
        occupied = false;

        greenTexture = new Texture(Gdx.files.internal("textures/green.png"));
        redTexture = new Texture(Gdx.files.internal("textures/red.png"));
        seeThroughTexture = new Texture(Gdx.files.internal("textures/see through.png"));
        imageTexture = new Texture(Gdx.files.internal("Gym.png"));
        skin = new Skin(Gdx.files.internal("default_skin/uiskin.json"));
        button = new Button(new TextureRegionDrawable(new TextureRegion(seeThroughTexture)));
        image = new Image(new TextureRegionDrawable(new TextureRegion(seeThroughTexture)));
        image.setTouchable(Touchable.disabled);

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (main.selectedBuilding > -1
                    && !isOccupied()
                    && main.buildingTypes[main.selectedBuilding].getSize() <= maxSize) {
                    buildingPlaced = main.buildingTypes[main.selectedBuilding].deepCopy();
                    imageTexture = new Texture(Gdx.files.internal(buildingPlaced.getName()));
                    main.selectedBuilding = -1;
                    setOccupied();
                }
            }
        });
    }

    @Override
    public void act(float delta) {

        super.act(delta);
        if (main.selectedBuilding > -1 && !isOccupied()) {
            if (main.buildingTypes[main.selectedBuilding].getSize() <= maxSize) {
                // needs to green
                image.setDrawable(new TextureRegionDrawable(new TextureRegion(greenTexture)));

            }
            else {
                // needs to go red
                image.setDrawable(new TextureRegionDrawable(new TextureRegion(redTexture)));
            }
        }
        else if (isOccupied()) {
            // show building
            image.setDrawable(new TextureRegionDrawable(new TextureRegion(imageTexture)));
        }
        else {
            // show nothing
            image.setDrawable(new TextureRegionDrawable(new TextureRegion(seeThroughTexture)));
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
