package com.unisim.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class main extends ApplicationAdapter implements InputProcessor {
    //private SpriteBatch batch;
    //private Texture image;

    Texture img;
    public static OrthographicCamera camera;

    int sceneId = 0;
    Stage menuStage;
    Stage mainStage;

    TextButton playButton;
    TextButton.TextButtonStyle playButtonStyle;
    BitmapFont font;
    Skin skin;
    TextureAtlas buttonAtlas;

    SpriteBatch testBatch;
    Skin testSkin;

    //Texture testImg;
    //Sprite testSprite;

    //MenuButton colourChangingButton;

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

        menuStage = new Stage();
        MenuButton playButton = new MenuButton("buttons/Play Button.png",0, 0, 0.5f);
        playButton.setPosition(Gdx.graphics.getWidth()/2f - playButton.getWidth() / 2,
                               Gdx.graphics.getHeight()/2f - playButton.getHeight() / 2);


        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("play button pressed");
            }
        });

        // testing new button out
        testBatch = new SpriteBatch();
        testSkin = new Skin(Gdx.files.internal("default_skin/uiskin.json"));

        TextButton testButton = new TextButton("Play", testSkin, "default");
        testButton.setWidth(200f);
        testButton.setHeight(20f);
        testButton.setPosition(Gdx.graphics.getWidth() / 2 - 100f, Gdx.graphics.getHeight() / 2 - 200f);

        testButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                testButton.setText("you've clicked the button.");
            }
        });

        menuStage.addActor(testButton);

        playButton.setTouchable(Touchable.enabled);
        menuStage.addActor(playButton);


        mainStage = new Stage();
        mainStage.addActor(new gameMap());

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
                Gdx.gl.glClearColor(0f, 0f, 0f, 1);
                Gdx.input.setInputProcessor(menuStage);
                //if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                  //  if (());
                //}

                menuStage.act(Gdx.graphics.getDeltaTime());
                testBatch.begin(); // to remove
                menuStage.draw();
                testBatch.end(); // to remove
                break;
            case 1:
                Gdx.gl.glClearColor(0f, 0.23f, 0.17f, 1);
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
