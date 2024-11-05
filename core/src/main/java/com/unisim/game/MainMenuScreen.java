package com.unisim.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {
    UniGame game;

    Stage menuStage;
    public static OrthographicCamera camera;

    SpriteBatch batch;
    Skin skin;

    public MainMenuScreen(UniGame game) {
        this.game = game;

        float w = 1260;
        float h = 640;

        camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);
        camera.translate(-300f, 0f);
        camera.update();

        menuStage = new Stage();

        TextButton playButton = new TextButton("Play", skin, "default");
        playButton.setWidth(200f);
        playButton.setHeight(20f);
        playButton.setPosition(Gdx.graphics.getWidth() / 2 - playButton.getWidth() / 2,
                               Gdx.graphics.getHeight() / 2 - playButton.getHeight() / 2);
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                playButton.setText("clicked");
            }
        });

        menuStage.addActor(playButton);
        Gdx.input.setInputProcessor(menuStage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(Color.BLACK);

        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);

        game.batch.begin();
        game.font.draw(game.batch, "Welcome to uni sim!", 1, 1.5f);
        game.batch.end();



    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
