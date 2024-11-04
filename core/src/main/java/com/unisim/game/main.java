package com.unisim.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Timer;

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

    TextButton playButton;
    TextButton.TextButtonStyle playButtonStyle;
    BitmapFont font;
    Skin skin;
    TextureAtlas buttonAtlas;

    @Override
    public void create() {
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
        button playButton = new button("buttons/Play Button.png",
                                (Gdx.graphics.getHeight()/2f)+400f,
                                Gdx.graphics.getWidth()/2f,
                                0.5f);
        menuStage.addActor(playButton);


        mainStage = new Stage();
        mainStage.addActor(new gameMap());
        //Gdx.input.setInputProcessor(this);

        //Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        //config.setWindowedMode(800, 600);



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
        camera.update();

        switch(sceneId){
            case 0:
                Gdx.gl.glClearColor(0f, 0f, 0f, 1);
                Gdx.input.setInputProcessor(menuStage);
                //if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                  //  if (());
                //}
                menuStage.draw();
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
        Vector2 coord = menuStage.screenToStageCoordinates(new Vector2((float) screenX, (float) screenY));
        Actor hitActor = menuStage.hit(coord.x, coord.y, false);

        if (hitActor != null) {
            Gdx.app.exit();
        }
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
