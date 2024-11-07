package com.unisim.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.font.ImageGraphicAttribute;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class main extends ApplicationAdapter implements InputProcessor {
    //private SpriteBatch batch;
    //private Texture image;

    Texture img;
    public static OrthographicCamera camera;

    int sceneId = 0;
    Stage menuStage;
    Stage mainStage;
    Stage pauseStage;
    Stage tutorialStage;
    Stage endTimeStage;

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

    Label buildingsTitle;
    TextField buildingsText;
    Label countersTitle;
    TextField counterText;

    Image accommodationImg;
    Image clubImg;
    Image foodHallImg;
    Image gymImg;
    Image lectureHallImg;
    Image constructionImg;

//    Label accomCounter;
//    Label clubCounter;
//    Label foodCounter;
//    Label gymCounter;
//    Label lectureCounter;

//    ImageButton accommodationButton;
//    ImageButton clubButton;
//    ImageButton foodHallButton;
//    ImageButton gymButton;
//    ImageButton lectureHallButton;

    Image[] buildingImgs;
    Label[] buildingCounters;
    Label[] buildingLabels;
    ImageButton[] buildingButtons;

    ImageButton pauseButton;
    ImageButton tutorialButton;

    int score;
    Label scoreTitleLabel;
    Label scoreTextLabel;

    // to change
    int time;
    Label timeTitleLabel;
    Label timeTextLabel;

    Color mainColour;

    Table buildingsTable;
    Table buttonsTable;
    Table scoreTable;
    Table timerTable;
    Table miscTable;

    ImageButton resumeButtonOnPause;
    ImageButton resumeButtonOnTutorial;

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
        mainColour = new Color(0.09f, 0.41f, 0.22f, 1f);

//        MenuButton playButton = new MenuButton("buttons/Play Button.png",0, 0, 0.5f);
//        playButton.setPosition(Gdx.graphics.getWidth()/2f - playButton.getWidth() / 2,
//                               Gdx.graphics.getHeight()/2f - playButton.getHeight() / 2);


//        playButton.addListener(new ChangeListener() {
//            @Override
//            public void changed(ChangeEvent event, Actor actor) {
//                System.out.println("play button pressed");
//            }
//        });

        // play button on menu stage
        playImgButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/Play Button.png")))));
        playImgButton.setPosition(Gdx.graphics.getWidth()/2 - playImgButton.getWidth() / 2,
                                  Gdx.graphics.getHeight()/2f - 50f);
        menuStage.addActor(playImgButton);

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

        buildingsTitle = new Label("Buildings", skin);
        buildingsText = new TextField("", skin);
        countersTitle = new Label("Counter", skin);
        counterText = new TextField("", skin);

        accommodationImg = new Image(new Texture(Gdx.files.internal("Accommodation.png")));
        clubImg = new Image(new Texture(Gdx.files.internal("Club.png")));
        foodHallImg = new Image(new Texture(Gdx.files.internal("Food Hall.png")));
        gymImg = new Image(new Texture(Gdx.files.internal("Gym.png")));
        lectureHallImg = new Image(new Texture(Gdx.files.internal("lecture hall.png")));
        constructionImg = new Image(new Texture(Gdx.files.internal("Construction.png")));

//        accommodationButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Accommodation.png")))));
//        clubButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Club.png")))));
//        foodHallButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Food Hall.png")))));
//        gymButton = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Gym.png"))));
//        lectureHallButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Lecture hall.png")))));

//        addButton("Accommodation.png", 0);
//        addButton("Club.png", 1);
//        addButton("Food Hall.png", 2);
//        addButton("Gym.png", 3);
//        addButton("Lecture hall.png", 4);

//        accomCounter = new Label("0", skin);
//        clubCounter = new Label("0", skin);
//        foodCounter = new Label("0", skin);
//        gymCounter = new Label("0", skin);
//        lectureCounter = new Label("0", skin);

        String[] filepaths = {"Accommodation.png", "Lecture hall.png", "Food hall.png", "Gym.png", "Club.png"};

        // adding buttons for each building type
        buildingButtons = new ImageButton[5];
        for (int i = 0; i < filepaths.length; i++) {
            buildingButtons[i] = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(filepaths[i]))));
        }

        // adding counters for each building type
        buildingCounters = new Label[5];
        for (int i = 0; i < filepaths.length; i++) {
            buildingCounters[i] = new Label("0", skin);
        }

        // adding labels for each building type
        buildingLabels = new Label[5];
        for (int i = 0; i < filepaths.length; i++) {
            String buildingLabel = filepaths[i];
            buildingLabel = buildingLabel.substring(0, buildingLabel.length()-4);
            buildingLabels[i] = new Label(buildingLabel, skin);
        }

        buildingsTable = new Table();

        buildingsTable.add(buildingsTitle);
        buildingsTable.add(countersTitle);

        // adding buttons and counters to table
        int cellD = 110;
        for (int i = 0; i < buildingCounters.length; i++) {
            buildingsTable.row();
            buildingsTable.add(buildingButtons[i]).width(cellD).height(cellD);
            buildingsTable.add(buildingCounters[i]);
            buildingsTable.row();
            buildingsTable.add(buildingLabels[i]).width(cellD).height(20);
        }

//        buildingsTable.row();
//        buildingsTable.add(accommodationButton).width(cellW).height(cellH);
//        buildingsTable.add(accomCounter);
//        buildingsTable.row();
//        buildingsTable.add(clubButton).width(cellW).height(cellH);
//        buildingsTable.add(clubCounter);
//        buildingsTable.row();
//        buildingsTable.add(foodHallButton).width(cellW).height(cellH);
//        buildingsTable.add(foodCounter);
//        buildingsTable.row();
//        buildingsTable.add(gymButton).width(cellW).height(cellH);
//        buildingsTable.add(gymCounter);
//        buildingsTable.row();
//        buildingsTable.add(lectureHallButton).width(cellW).height(cellH);
//        buildingsTable.add(lectureCounter);

        // https://stackoverflow.com/questions/39081993/libgdx-scene2d-set-background-color-of-table
        // i tried
        Color backgroundColour = new Color(0.09f, 0.41f, 0.22f, 1f);
        Pixmap backgroundPM = new Pixmap(1,1, Pixmap.Format.RGB565);
        backgroundPM.setColor(backgroundColour);
        backgroundPM.fill();
        buildingsTable.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(backgroundPM))));

        // adding event listeners
        for (int i = 0; i < filepaths.length; i++) {
            int finalI = i;
            buildingButtons[i].addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    buildingCounters[finalI].setText(String.valueOf(Integer.parseInt(buildingCounters[finalI].getText().toString()) + 1));
                }
            });
        }

        // adding score table
        scoreTable = new Table();
        scoreTitleLabel = new Label("score", skin);
        scoreTextLabel = new Label(String.valueOf(score), skin);
        scoreTable.add(scoreTitleLabel);
        scoreTable.row();
        scoreTable.add(scoreTextLabel);

        // adding time table
        timerTable = new Table();
        timeTitleLabel = new Label("time left", skin);
        timeTextLabel = new Label(String.valueOf(time), skin);
        timerTable.add(timeTitleLabel);
        timerTable.row();
        timerTable.add(timeTextLabel);

        // adding pause and tutorial buttons
        // new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(filepaths[i]))));
        pauseButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/Pause Square Button.png")))));
        tutorialButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/Questionmark Square Button.png")))));
        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // changing scene to pause screen
                sceneId = 2;
            }
        });
        tutorialButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // changing scene to tutorial screen
                sceneId = 3;
            }
        });


        miscTable = new Table();
        miscTable.add(pauseButton);
        miscTable.add(tutorialButton);
        miscTable.row();
        miscTable.add(scoreTable);
        miscTable.add(timerTable);

        buttonsTable = new Table();
        buttonsTable.add(miscTable);
        buttonsTable.row();
        buttonsTable.add(buildingsTable);




//        accommodationButton.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                accomCounter.setText(String.valueOf(Integer.parseInt(accomCounter.getText().toString()) + 1));
//            }
//        });
//        clubButton.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                //clubCounter.setText(String.valueOf(Integer.parseInt(clubCounter.getText().toString()) + 1));
//                sceneId = 0;
//            }
//        });

//        buildingsTable.setPosition(150, Gdx.graphics.getHeight()/2);
//        mainStage.addActor(buildingsTable);

        //buttonsTable.setDebug(true);
        buttonsTable.setPosition(150, Gdx.graphics.getHeight() / 2f);
        mainStage.addActor(buttonsTable);

        //table.add(buildingsText).width(100);
        //table.row();
        //table.add(counterText).width(100);

        Gdx.input.setInputProcessor(menuStage);

        pauseStage = new Stage();
        tutorialStage = new Stage();
        endTimeStage = new Stage();

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

    private void addButton(String filepath, int index) {
        buildingButtons[index] = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(filepath)))));
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
                //Gdx.gl.glClearColor(0.45f, 0.52f, 0.98f, 1);
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
                //Gdx.gl.glClearColor(0.45f, 0.52f, 0.98f, 1);
                Gdx.input.setInputProcessor(mainStage);

                mainStage.act(Gdx.graphics.getDeltaTime());
                mainStage.draw();
                break;
            case 2:

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
