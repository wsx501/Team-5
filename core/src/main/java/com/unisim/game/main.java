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

    Image mainMenuText;
    Image pauseMenuText;
    Image tutorialMenuText;


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

    ImageButton playButtonPM;
    ImageButton backButtonTM;

    int score;
    Label scoreTitleLabel;
    Label scoreTextLabel;

    // to change
    float time = 300f;
    Label timeTitleLabel;
    Label timeTextLabel;

    Color mainColour;

    Table buildingsTable;
    Table buttonsTable;
    Table scoreTable;
    Table timerTable;
    Table miscTable;

    LandPlot[] landPlots;

    // filepaths = {"Accommodation.png", "Lecture hall.png", "Food hall.png", "Gym.png", "Club.png"};
    static Building[] buildingTypes = new Building[]{
        new Building("Accommodation.png", 3),
        new Building("Lecture hall.png", 3 ),
        new Building("Food hall.png", 3),
        new Building("Gym.png", 2),
        new Building("Club.png", 2)
    };
    // Represents corresponding building types from the selection menu
    // -1 by default, no building selected
    public static int selectedBuilding = -1;

//    ImageButton resumeButtonOnPause;
//    ImageButton resumeButtonOnTutorial;

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



        Image PauseMenuText;
        Image TutorialText;

        // play button on menu stage
        playImgButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/Play Button.png")))));
        //playImgButton.setPosition(Gdx.graphics.getWidth()/2 - playImgButton.getWidth() / 2,
        //                          Gdx.graphics.getHeight()/2f - 50f);
        playImgButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sceneId = 1;
            }
        });

        mainMenuText = new Image(new Texture(Gdx.files.internal("text/Home Text.png")));

        Table mainMenuTable = new Table();
        mainMenuTable.add(mainMenuText).width(700f).height(300f);
        mainMenuTable.row();
        mainMenuTable.add(playImgButton);
        mainMenuTable.setPosition(Gdx.graphics.getWidth() / 2f - mainMenuTable.getWidth() / 2,
                                  Gdx.graphics.getHeight()/2f - mainMenuTable.getHeight() / 2);

        menuStage.addActor(mainMenuTable);

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
        buildingsTitle.setFontScale(1.5f);
        buildingsText = new TextField("", skin);
        countersTitle = new Label("Counter", skin);
        countersTitle.setFontScale(1.5f);
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

//        // https://stackoverflow.com/questions/39081993/libgdx-scene2d-set-background-color-of-table
//        // i tried
        Color backgroundColour = new Color(0.09f, 0.41f, 0.22f, 1f);
        Pixmap backgroundPM = new Pixmap(1,1, Pixmap.Format.RGB565);
        backgroundPM.setColor(backgroundColour);
        backgroundPM.fill();

        // adding event listeners
        for (int i = 0; i < filepaths.length; i++) {
            int finalI = i;
            buildingButtons[i].addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (selectedBuilding < 0) {
                        buildingCounters[finalI].setText(String.valueOf(Integer.parseInt(buildingCounters[finalI].getText().toString()) + 1));
                        main.selectedBuilding = finalI;
                    }
                }
            });
        }

        // adding score table
        scoreTable = new Table();
        scoreTitleLabel = new Label("Score", skin);
        scoreTitleLabel.setFontScale(1.5f);
        scoreTextLabel = new Label(String.valueOf(score), skin);
        scoreTable.add(scoreTitleLabel);
        scoreTable.row();
        scoreTable.add(scoreTextLabel);

        // adding timer table
        timerTable = new Table();
        timeTitleLabel = new Label("Time Left", skin);
        timeTextLabel = new Label(String.valueOf(time), skin);
        timeTitleLabel.setFontScale(1.5f);
        timerTable.add(timeTitleLabel);
        timerTable.row();
        timerTable.add(timeTextLabel);

        // adding pause and tutorial buttons
        // new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(filepaths[i]))));
        pauseButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/Pause Square Button.png")))));
        tutorialButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/Question Mark.png")))));
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

//        LandPlot gb1;
//        gb1 = new LandPlot(2, 200, 200, 100, 100);
////        gb1.setBuilding(buildingTypes[0].deepCopy());
////        gb1.button.setBounds(0, 0, 100, 100);
////        gb1.button.setSize(100, 100);
////        gb1.button.setPosition(0,0);
//        mainStage.addActor(gb1.image);
//        mainStage.addActor(gb1.button);
//        mainStage.addActor(gb1);

        int pxpt = 40;
        int size2 = pxpt * 2;
        int size3 = pxpt * 3;
        int tilePX = 41;
        int bw = 360;


        landPlots = new LandPlot[9];
        landPlots[0] = new LandPlot(2, bw + (int)(8.5 * pxpt), 2 * pxpt, size2, size2);
        landPlots[1] = new LandPlot(2, bw + (int)(3.5 * pxpt), 2 * pxpt, size2, size2);
        landPlots[2] = new LandPlot(3, bw + 3 * pxpt, (int)(5.5 * pxpt), size3, size3);
        landPlots[3] = new LandPlot(3, bw + 3 * pxpt, (int)(8.6 * pxpt), size3, size3);
        landPlots[4] = new LandPlot(3, bw + (int)(12.5 * pxpt), (13 * pxpt), size3, size3);
        landPlots[5] = new LandPlot(3, bw + (int)(16.7 * pxpt), (11 * pxpt), size3, size3);
        landPlots[6] = new LandPlot(3, bw + (int)(20.7 * pxpt), 11 * pxpt, size3, size3);
        landPlots[7] = new LandPlot(3, bw + (int)(24.2 * pxpt), 11 * pxpt, size3, size3);
        landPlots[8] = new LandPlot(2, bw + 24 * pxpt, (int)(6.5 * pxpt), size2, size2);



        for (LandPlot landPlot : landPlots) {

            mainStage.addActor(landPlot.image);
            mainStage.addActor(landPlot.button);
            mainStage.addActor(landPlot);

        }


        //table.add(buildingsText).width(100);
        //table.row();
        //table.add(counterText).width(100);

        Gdx.input.setInputProcessor(menuStage);

        pauseStage = new Stage();

        playButtonPM = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/Resume Button.png")))));
        playButtonPM.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sceneId = 1;
            }
        });
        playButtonPM.setPosition(Gdx.graphics.getWidth() / 2f - playButtonPM.getWidth() / 2,
                                 Gdx.graphics.getHeight() / 2f - playButtonPM.getHeight() / 2);
        pauseMenuText = new Image(new Texture(Gdx.files.internal("text/Paused Text.png")));
        Table pauseMenuTable = new Table();
        pauseMenuTable.add(pauseMenuText).width(700f).height(300f);
        pauseMenuTable.row();
        pauseMenuTable.add(playButtonPM);
        pauseMenuTable.setPosition(Gdx.graphics.getWidth() / 2f - pauseMenuTable.getWidth() / 2,
                                   Gdx.graphics.getHeight()/2f - pauseMenuTable.getHeight() / 2);
        pauseStage.addActor(pauseMenuTable);

        tutorialStage = new Stage();

        backButtonTM = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/Back Button.png")))));
        backButtonTM.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sceneId = 1;
            }
        });
        backButtonTM.setPosition(Gdx.graphics.getWidth() / 2f - backButtonTM.getWidth() / 2,
                                 Gdx.graphics.getHeight() / 2f - backButtonTM.getHeight() / 2);
        tutorialMenuText = new Image(new Texture(Gdx.files.internal("text/How To Play.png")));
        Table tutorialMenuTable = new Table();
        tutorialMenuTable.add(tutorialMenuText).width(1000f).height(700f);
        tutorialMenuTable.row();
        tutorialMenuTable.add(backButtonTM);
        tutorialMenuTable.setPosition(Gdx.graphics.getWidth() / 2f - tutorialMenuTable.getWidth() / 2,
            Gdx.graphics.getHeight()/2f - tutorialMenuTable.getHeight() / 2);
        tutorialStage.addActor(tutorialMenuTable);

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

        endTimeStage = new Stage();
        Image endTimeText = new Image(new Texture(Gdx.files.internal("text/Time up Text.png")));
        Table endTimeTable = new Table();
        endTimeTable.add(endTimeText).width(700f). height(200f);
        endTimeTable.setPosition(Gdx.graphics.getWidth() / 2f - endTimeTable.getWidth() / 2,
                                 Gdx.graphics.getHeight() / 2f - endTimeTable.getHeight() / 2);
        endTimeStage.addActor(endTimeTable);

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
                time -= Gdx.graphics.getDeltaTime();
                if (time < 0) sceneId = 4;
                timeTextLabel.setText(String.valueOf(Math.round(time)));
                mainStage.draw();
                break;
            case 2:
                // pause screen
                Gdx.gl.glClearColor(0f, 0.23f, 0.17f, 1);
                Gdx.input.setInputProcessor(pauseStage);

                pauseStage.act(Gdx.graphics.getDeltaTime());
                pauseStage.draw();
                break;
            case 3:
                // tutorial screen
                Gdx.gl.glClearColor(0f, 0.23f, 0.17f, 1);
                Gdx.input.setInputProcessor(tutorialStage);

                tutorialStage.act(Gdx.graphics.getDeltaTime());
                tutorialStage.draw();
                break;
            case 4:
                // end of game screen
                Gdx.gl.glClearColor(0f, 0.23f, 0.17f, 1);
                Gdx.input.setInputProcessor(endTimeStage);

                endTimeStage.act(Gdx.graphics.getDeltaTime());
                endTimeStage.draw();
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
