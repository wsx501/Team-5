package com.unisim.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Represents an area of land where a building can be placed. LandPlot extends {@link Actor} and primarily contains an
 * image and a button.
 * <p>The image is used to represent the availability of the LandPlot, and then to display the building once placed. The
 * button manages the click actions of LandPlot, enabling a player to place a building.</p>
 */
public class LandPlot extends Actor {
    private Building buildingPlaced;
    /**The maximum allowed size of buildings.*/
    private final int maxSize;
    private final int x, y, width, height;
    private boolean occupied;

    Button button;
    Image image;
    Skin skin;

    // Different textures that image can be set to.
    Texture greenTexture;
    Texture redTexture;
    Texture imageTexture;
    Texture seeThroughTexture;

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

//        final int[] finalX = {x, y};
//        button.addListener(new InputListener() {
//            @Override
//            public boolean keyDown(InputEvent event, int keycode) {
//                if (keycode == Keys.RIGHT) {
//                    finalX[0] += 1;
//                }
//                if (keycode == Keys.UP) {
//                    finalX[1] += 1;
//                }
//                return true;
//            }
//        });
//        x = finalX[0];
//        y = finalX[1];

        button.setPosition(x, y);
        button.setSize(width, height);
        button.setBounds(x, y, width, height);
        image.setPosition(x, y);
        image.setSize(width, height);

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // If a building is selected for placement, the LandPlot is unoccupied, and the building selected is
                // within the size limit.
                if (main.selectedBuilding > -1
                    && !isOccupied()
                    && main.buildingTypes[main.selectedBuilding].getSize() <= maxSize)  {
                    // Setting the building of the LandPlot, and the texture needed for the image.
                    buildingPlaced = main.buildingTypes[main.selectedBuilding].deepCopy();
                    imageTexture = new Texture(Gdx.files.internal(buildingPlaced.getName()));

                    // Disabling the state of a building being selected for placement.
                    main.selectedBuilding = -1;
                    setOccupied();
                }
            }
        });
    }

    @Override
    public void act(float delta) {

        super.act(delta);
        //image.setPosition(x, y);
        //image.setSize(width,width);

        // If the LandPlot doesn't contain a building, and a building has been selected for placement.
        if (main.selectedBuilding > -1 && !isOccupied()) {
            // If the building selected is within size constraints.
            if (main.buildingTypes[main.selectedBuilding].getSize() <= maxSize) {
                // needs to go green
                image.setDrawable(new TextureRegionDrawable(new TextureRegion(greenTexture)));

            }
            else {
                // needs to go red
                image.setDrawable(new TextureRegionDrawable(new TextureRegion(redTexture)));
            }
        }
        // If the LandPlot contains a building.
        else if (isOccupied()) {
            // show building
            image.setDrawable(new TextureRegionDrawable(new TextureRegion(imageTexture)));
        }
        // If the LandPlot is empty, and there is no building selected for placement.
        else {
            // show nothing
            image.setDrawable(new TextureRegionDrawable(new TextureRegion(seeThroughTexture)));
        }
        // otherwise : has a building placed
    }

//    public void setBuilding(Building buildingPlaced) {
//        this.buildingPlaced = buildingPlaced;
//    }

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
