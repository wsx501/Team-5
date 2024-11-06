package com.unisim.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.TimerTask;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class main extends ApplicationAdapter implements InputProcessor {
    //private SpriteBatch batch;
    //private Texture image;

    Texture img;
    public static OrthographicCamera camera;

    int sceneId = 0;
    Stage menuStage;
    Stage mainStage;

    //TextButton testButton;
    TextButton.TextButtonStyle playButtonStyle;
    BitmapFont font;
    Viewport viewport;
    TextureAtlas buttonAtlas;

    SpriteBatch testBatch;
    Skin skin;

    //Texture testImg;
    //Sprite testSprite;

    //MenuButton colourChangingButton;

    ImageButton playImgButton;

    Label buildingsLabel;
    TextField buildingsText;
    Label counterLabel;
    TextField counterText;

    Image accommodationImg;
    Image clubImg;
    Image foodHallImg;
    Image gymImg;
    Image lectureHallImg;
    Image constructionImg;

    Label accomCounter;
    Label clubCounter;
    Label foodCounter;
    Label gymCounter;
    Label lectureCounter;

    ImageButton accommodationButton;
    ImageButton clubButton;
    ImageButton foodHallButton;
    ImageButton gymButton;
    ImageButton lectureHallButton;


    Table buildingsTable;
    Table buttonsTable;
    Table scoreTable;
    Table timerTable;

    @Override
    public void create() {
        //testImg = new Texture("buttons/Resume Button.png");
        //testSprite = new Sprite(testImg);
        //testSprite.setPosition(Gdx.graphics.getWidth()/2 - testSprite.getWidth() / 2, Gdx.graphics.getHeight() / 2 - testSprite.getHeight() / 2);

        //batch = new SpriteBatch();
        //image = new Texture("libgdx.png");
        //float w = Gdx.graphics.getWidth();
        float w = 1260;
        //float h = Gdx.graphics.getHeight();
        float h = 640;

        camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);
        camera.translate(-300f, 0f);
        camera.update();

        //font.setUseIntegerPositions(false);
        //font.getData().setScale(viewport.getWorldHeight() / Gdx.graphics.getHeight());

        menuStage = new Stage();

//        MenuButton playButton = new MenuButton("buttons/Play Button.png",0, 0, 0.5f);
//        playButton.setPosition(Gdx.graphics.getWidth()/2f - playButton.getWidth() / 2,
//                               Gdx.graphics.getHeight()/2f - playButton.getHeight() / 2);


//        playButton.addListener(new ChangeListener() {
//            @Override
//            public void changed(ChangeEvent event, Actor actor) {
//                System.out.println("play button pressed");
//            }
//        });

        // testing image button
        playImgButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/Play Button.png")))));
        playImgButton.setPosition(Gdx.graphics.getWidth()/2 - playImgButton.getWidth() / 2,
                                  Gdx.graphics.getHeight()/2f - 50f);
        menuStage.addActor(playImgButton);

        // testing new button out
        testBatch = new SpriteBatch();
        skin = new Skin(Gdx.files.internal("default_skin/uiskin.json"));

//        testButton = new TextButton("Play", skin, "default");
//        testButton.setWidth(200f);
//        testButton.setHeight(20f);
//        testButton.setPosition(Gdx.graphics.getWidth() / 2 - 100f, Gdx.graphics.getHeight() / 2 - 200f);



//        testButton.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                testButton.setText("you've clicked the button.");
//            }
//        });

//        menuStage.addActor(testButton);

//        playButton.setTouchable(Touchable.enabled);
//        menuStage.addActor(playButton);



        mainStage = new Stage();
        mainStage.addActor(new gameMap());

        buildingsLabel = new Label("Buildings", skin);
        buildingsText = new TextField("", skin);
        counterLabel = new Label("Counter", skin);
        counterText = new TextField("", skin);

        accommodationImg = new Image(new Texture(Gdx.files.internal("Accommodation.png")));
        clubImg = new Image(new Texture(Gdx.files.internal("Club.png")));
        foodHallImg = new Image(new Texture(Gdx.files.internal("Food Hall.png")));
        gymImg = new Image(new Texture(Gdx.files.internal("Gym.png")));
        lectureHallImg = new Image(new Texture(Gdx.files.internal("lecture hall.png")));
        constructionImg = new Image(new Texture(Gdx.files.internal("Construction.png")));

        accommodationButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Accommodation.png")))));
        clubButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Club.png")))));
        foodHallButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Food Hall.png")))));
        gymButton = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Gym.png"))));
        lectureHallButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Lecture hall.png")))));


        accomCounter = new Label("0", skin);
        clubCounter = new Label("0", skin);
        foodCounter = new Label("0", skin);
        gymCounter = new Label("0", skin);
        lectureCounter = new Label("0", skin);

        buildingsTable = new Table();
        int cellW = 100;
        int cellH = 100;

        buildingsTable.add(buildingsLabel);
        buildingsTable.add(counterLabel);
        buildingsTable.row();
        buildingsTable.add(accommodationButton).width(cellW).height(cellH);
        buildingsTable.add(accomCounter);
        buildingsTable.row();
        buildingsTable.add(clubButton).width(cellW).height(cellH);
        buildingsTable.add(clubCounter);
        buildingsTable.row();
        buildingsTable.add(foodHallButton).width(cellW).height(cellH);
        buildingsTable.add(foodCounter);
        buildingsTable.row();
        buildingsTable.add(gymButton).width(cellW).height(cellH);
        buildingsTable.add(gymCounter);
        buildingsTable.row();
        buildingsTable.add(lectureHallButton).width(cellW).height(cellH);
        buildingsTable.add(lectureCounter);

        // https://stackoverflow.com/questions/39081993/libgdx-scene2d-set-background-color-of-table
        // i tried
        Color backgroundColour = new Color(0.09f, 0.41f, 0.22f, 1f);
        Pixmap backgroundPM = new Pixmap(1,1, Pixmap.Format.RGB565);
        backgroundPM.setColor(backgroundColour);
        backgroundPM.fill();
        buildingsTable.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(backgroundPM))));

        accommodationButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                accomCounter.setText(String.valueOf(Integer.parseInt(accomCounter.getText().toString()) + 1));
            }
        });
        clubButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clubCounter.setText(String.valueOf(Integer.parseInt(clubCounter.getText().toString()) + 1));
            }
        });

        buildingsTable.setPosition(100, Gdx.graphics.getHeight()/2);
        mainStage.addActor(buildingsTable);

        //table.add(buildingsText).width(100);
        //table.row();
        //table.add(counterText).width(100);

        Gdx.input.setInputProcessor(menuStage);

        //colourChangingButton = new MenuButton("buttons/Back Square Button.png",
        //    (Gdx.graphics.getHeight()/2f),
        //    Gdx.graphics.getWidth()/2f,
        //    0.5f);

        //colourChangingButton.setPosition(Gdx.graphics.getWidth()/2 - colourChangingButton.getWidth() / 2,
        //    Gdx.graphics.getHeight()/2 - colourChangingButton.getHeight() / 2);
        //menuStage.addActor(colourChangingButton);




        //Gdx.input.setInputProcessor(this);

        //Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        //config.setWindowedMode(800, 600);

        //needs to be removed
        //Gdx.input.setInputProcessor(this);

    }

    @Override
    public void render() {
        /*ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        batch.draw(image, 140, 210);
        batch.end();*/

        //Gdx.gl.glClearColor(0f, 0.23f, 0.17f, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // makes sprite follow mouse (on left click)
        // or changes scene to mainStage
        //if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            //testSprite.setPosition(Gdx.input.getX() - testSprite.getWidth() / 2,
            //    Gdx.graphics.getHeight() - Gdx.input.getY() - testSprite.getHeight() / 2);
            //sceneId = 1;
        //}
        // resets sprite to centre screen (on right click)
        //if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
        //    testSprite.setPosition(Gdx.graphics.getWidth() / 2 - testSprite.getWidth() / 2,
        //        Gdx.graphics.getHeight() / 2 - testSprite.getHeight() / 2);
        //}

        //testBatch.begin();
        //testSprite.draw(testBatch);
        //testBatch.end();

        camera.update();

        switch(sceneId){
            case 0:
                Gdx.gl.glClearColor(0f, 0.23f, 0.17f, 1);
                Gdx.input.setInputProcessor(menuStage);
                //if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                  //  if (());
                //}

//                testButton.addListener(new ClickListener() {
//                    @Override
//                    public void clicked(InputEvent event, float x, float y) {
//                        testButton.setText("you've clicked the button.");
//                        sceneId = 1;
//                    }
//                });
                playImgButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        sceneId = 1;
                    }
                });

                menuStage.act(Gdx.graphics.getDeltaTime());
                testBatch.begin(); // to remove
                menuStage.draw();
                testBatch.end(); // to remove
                break;
            case 1:
                Gdx.gl.glClearColor(0f, 0.23f, 0.17f, 1);
                Gdx.input.setInputProcessor(mainStage);

                mainStage.act(Gdx.graphics.getDeltaTime());
                mainStage.draw();
                break;
        }
    }

    @Override
    public void dispose() {
        //batch.dispose();
        //image.dispose();
    }

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;

    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }
}
