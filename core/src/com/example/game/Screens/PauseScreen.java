package com.example.game.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.example.game.GameConfiguration;
import com.example.game.MoveOrCrashGame;

public class PauseScreen implements Screen{

    public MoveOrCrashGame game;
    private TextureAtlas mTextureAtlas;
    private Skin mSkin;
    private Stage mStage;

    public PauseScreen (MoveOrCrashGame game){
        this.game = game;

        mStage = new Stage();

    }



    @Override
    public void show() {
        Gdx.app.log("HomeScreen", "show");

//            mStage.clear();

        mTextureAtlas = new TextureAtlas("plain_buttons.pack");
        mSkin = new Skin(mTextureAtlas);

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(); //** Button properties **//
        style.up = mSkin.getDrawable("RedButton");
        style.down = mSkin.getDrawable("GreenButton");
        style.font = new BitmapFont();

        TextButton button = new TextButton("Resume", style);
//            float buttonWidth = 300 * Gdx.graphics.getDensity();
//            float buttonHegiht = 72 * Gdx.graphics.getDensity();


        button.setPosition(GameConfiguration.WIDTH/2 - button.getWidth()/2, GameConfiguration.HEIGHT/2 - button.getHeight()/2);
//            button.setWidth(buttonWidth);       // TODO: remove hardcoded value
//            button.setHeight(buttonHegiht);      // TODO: remove hardcoded value

        button.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("", "Play!"); //** Usually used to start Game, etc. **//
//                    game.setScreen(new PlayScreen(game));
                game.manager.previousScreen();
//                    dispose();
                return true;
            }
        });

        mStage.addActor(button);


        Gdx.input.setInputProcessor(mStage); //** stage set to pass input to button/actors **//

    }

    @Override
    public void render(float delta) {

//        Gdx.gl.glClearColor(1, 0, 0, 0.5f);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT |  GL20.GL_ALPHA );
        mStage.act();
        mStage.draw();

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
