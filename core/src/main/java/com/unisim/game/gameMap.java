package com.unisim.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class gameMap extends Actor {
    TiledMap tiledMap;
    TiledMapRenderer tiledMapRenderer;

    public gameMap() {
        tiledMap = new TmxMapLoader().load("TEAM5ENG1map.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();
        tiledMapRenderer.setView(main.camera);
        tiledMapRenderer.render();
        batch.begin();
    }
}
