package com.example.game.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
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

    private MoveOrCrashGame game;

    private Texture background;

    BitmapFont font;

    private Stage mStage;
    private TextureAtlas mTextureAtlas;
    private Skin mSkin;

    public PauseScreen (MoveOrCrashGame game){
        this.game = game;
        background =  new Texture("PauseScreenTest.png");
        mStage = new Stage();
    }



    @Override
    public void show() {
        Gdx.app.log("PauseScreen", "show");

        mTextureAtlas = new TextureAtlas("HomeScreenButtons.atlas");
        mSkin = new Skin(mTextureAtlas);

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(); //** Button properties **//
        TextButton.TextButtonStyle style2 = new TextButton.TextButtonStyle(); //** Button properties **//
        style.up = mSkin.getDrawable("BlackCarButtonResume");
        style2.up = mSkin.getDrawable("BlackCarButtonQuit");
        style.font = new BitmapFont();
        style2.font = new BitmapFont();

        TextButton button = new TextButton("", style);
        TextButton button2 = new TextButton("", style2);



        button.setPosition(GameConfiguration.WIDTH/2 - button.getWidth()/2, GameConfiguration.HEIGHT/2);          // TODO: remove hardcoded value
        button2.setPosition(GameConfiguration.WIDTH/2 - button.getWidth()/2, (GameConfiguration.HEIGHT/2) - 60);  // TODO: remove hardcoded value

        button.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("", "Resume!"); //** Usually used to start Game, etc. **//
                game.manager.previousScreen();
                return true;
            }
        });
        button2.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("", "Quit!"); //** Usually used to start Game, etc. **//
                game.setScreen(new HomeScreen((MoveOrCrashGame) game));
                dispose();
                return true;
            }
        });

        mStage.addActor(button);
        mStage.addActor(button2);


        Gdx.input.setInputProcessor(mStage); //** stage set to pass input to button/actors **//

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mStage.act();

        game.batch.begin();
        game.batch.draw(background, 0, 0, GameConfiguration.WIDTH, GameConfiguration.HEIGHT);

        game.batch.end();

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
        mStage.dispose();
        PlayScreen.playMusic.dispose();
    }
}
