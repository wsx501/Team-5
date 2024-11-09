package com.unisim.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.*;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class main extends ApplicationAdapter implements InputProcessor {

    public static OrthographicCamera camera;

    int sceneId = 0;
    Stage menuStage;
    Stage mainStage;
    Stage pauseStage;
    Stage tutorialStage;
    Stage endTimeStage;

    Skin skin;

    ImageButton playImgButton;

    Label buildingsTitle;
    Label countersTitle;

    Image mainMenuText;
    Image pauseMenuText;
    Image tutorialMenuText;

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

    float time = 300f;
    Label timeTitleLabel;
    Label timeTextLabel;

    Color mainColour;
    ShapeRenderer backgroundColourSR;

    Table buildingsTable;
    Table buttonsTable;
    Table scoreTable;
    Table timerTable;
    Table miscTable;

    LandPlot[] landPlots;

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

    Image buildingImage;

    String[] filepaths;

    @Override
    public void create() {

        // Variables to change size of camera.
        float w = 1260;
        float h = 640;

        // Sets up camera.
        camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);
        camera.translate(-300f, 0f);
        camera.update();

        mainColour = new Color(0f, 0.23f, 0.17f, 1f);
        backgroundColourSR = new ShapeRenderer();

        menuStage = new Stage();

        // play button on menu stage
        playImgButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/Play Button.png")))));
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

        skin = new Skin(Gdx.files.internal("default_skin/uiskin.json"));

        mainStage = new Stage();
        mainStage.addActor(new GameMap());

        buildingsTitle = new Label("Buildings", skin);
        buildingsTitle.setFontScale(1.5f);
        countersTitle = new Label("Counter", skin);
        countersTitle.setFontScale(1.5f);

        buildingImage = new Image(new Texture(Gdx.files.internal("textures/see through.png")));

        filepaths = new String[]{"Accommodation.png", "Lecture hall.png", "Food hall.png", "Gym.png", "Club.png"};

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
                    else if (selectedBuilding == finalI){
                        buildingCounters[finalI].setText(String.valueOf(Integer.parseInt(buildingCounters[finalI].getText().toString()) + - 1));
                        main.selectedBuilding = -1;

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


        //buttonsTable.setDebug(true);
        buttonsTable.setPosition(150, Gdx.graphics.getHeight() / 2f);
        mainStage.addActor(buttonsTable);

        int pxpt = 40;
        int size2 = pxpt * 2;
        int size3 = pxpt * 3;
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

        Gdx.input.setInputProcessor(menuStage);

        pauseStage = new Stage();

        ImageButton quitButtonPM = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/Quit.png")))));
        quitButtonPM.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.exit(0);
            }
        });

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
        pauseMenuTable.row();
        pauseMenuTable.add(quitButtonPM);
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
        Image endTimeText = new Image(new Texture(Gdx.files.internal("text/Time up Text.png")));
        ImageButton quitButtonTS = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("buttons/Quit.png"))));
        quitButtonTS.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.exit(0);
            }
        });
        Table endTimeTable = new Table();
        endTimeTable.add(endTimeText).width(700f). height(200f);
        endTimeTable.row();
        endTimeTable.add(quitButtonTS);
        endTimeTable.setPosition(Gdx.graphics.getWidth() / 2f - endTimeTable.getWidth() / 2,
                                 Gdx.graphics.getHeight() / 2f - endTimeTable.getHeight() / 2);
        endTimeStage.addActor(endTimeTable);

    }

    @Override
    public void render() {
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        backgroundColourSR.begin(ShapeRenderer.ShapeType.Filled);
        backgroundColourSR.setColor(mainColour);
        backgroundColourSR.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        backgroundColourSR.end();

        camera.update();

        switch(sceneId){
            case 0:
                Gdx.gl.glClearColor(0f, 0f, 0f, 1);
                Gdx.input.setInputProcessor(menuStage);
                menuStage.act(Gdx.graphics.getDeltaTime());
                menuStage.draw();
                break;
            case 1:
                Gdx.gl.glClearColor(0f, 0f, 0f, 1);
                Gdx.input.setInputProcessor(mainStage);
                mainStage.act(Gdx.graphics.getDeltaTime());
                time -= Gdx.graphics.getDeltaTime();
                if (time < 0) sceneId = 4;
                timeTextLabel.setText(String.valueOf(Math.round(time)));
                mainStage.draw();
                break;
            case 2:
                // pause screen
                Gdx.gl.glClearColor(0f, 0f, 0f, 1);
                Gdx.input.setInputProcessor(pauseStage);

                pauseStage.act(Gdx.graphics.getDeltaTime());
                pauseStage.draw();
                break;
            case 3:
                // tutorial screen
                Gdx.gl.glClearColor(0f, 0f, 0f, 1);
                Gdx.input.setInputProcessor(tutorialStage);

                tutorialStage.act(Gdx.graphics.getDeltaTime());
                tutorialStage.draw();
                break;
            case 4:
                // end of game screen
                Gdx.gl.glClearColor(0f, 0f, 0f, 1);
                Gdx.input.setInputProcessor(endTimeStage);

                endTimeStage.act(Gdx.graphics.getDeltaTime());
                endTimeStage.draw();
                break;
        }
    }

    @Override
    public void dispose() {
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
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }
}
