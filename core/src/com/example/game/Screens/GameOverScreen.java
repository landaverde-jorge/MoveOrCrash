package com.example.game.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.example.game.GameConfiguration;
import com.example.game.MoveOrCrashGame;

/**
 * Created by Mirola on 6/9/2017.
 */
public class GameOverScreen implements Screen
{
    public Stage stage;
    private Viewport viewport;
    private MoveOrCrashGame game;

    public GameOverScreen(MoveOrCrashGame game){
        this.game = game;
        viewport = new FitViewport(GameConfiguration.WIDTH, GameConfiguration.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((MoveOrCrashGame) game).batch);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

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
