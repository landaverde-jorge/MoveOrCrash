package com.example.game.Screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
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


public class HomeScreen implements Screen{

    private MoveOrCrashGame game;

    private Texture background;

    BitmapFont font;

    private Stage mStage;
    private TextureAtlas mTextureAtlas;
    private Skin mSkin;
//    private Music homeMusic;


    public HomeScreen(MoveOrCrashGame game){
        this.game = game;
        background =  new Texture("HomeScreenTest.png");
        mStage = new Stage();
//        homeMusic = MoveOrCrashGame.assetManager.get("Audio/Music/HomeScreenSong2.mp3",Music.class); TODO: Make homeScreen Music not overlap with inGame music
//        homeMusic.setLooping(true);
//        homeMusic.play();

    }
    @Override
    public void show() {
            Gdx.app.log("HomeScreen", "show");

//            mStage.clear();

            mTextureAtlas = new TextureAtlas("HomeScreenButtons.atlas");
            mSkin = new Skin(mTextureAtlas);

            TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();//** Button properties **//
            TextButton.TextButtonStyle style2 = new TextButton.TextButtonStyle();
            style.up = mSkin.getDrawable("BlackCarButtonGO");
            style2.up = mSkin.getDrawable("BlackCarButtonQuit");
            style.font = new BitmapFont();
            style2.font = new BitmapFont();

            TextButton button = new TextButton("", style);
            TextButton button2 = new TextButton("", style2);


            button.setPosition(Gdx.graphics.getWidth()/4 - button.getWidth()/2, 450); // TODO: remove hardcoded value
            button2.setPosition(Gdx.graphics.getWidth()/4 - button2.getWidth()/2, 350);// TODO: remove hardcoded value

            button.addListener(new InputListener() {
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    Gdx.app.log("", "Play!"); //** Usually used to start Game, etc. **//
                    game.manager.nextScreen(new PlayScreen(game));
                    return true;
                }
            });
            button2.addListener(new InputListener() {
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    Gdx.app.log("", "QUIT!"); //** Usually used to End Game **//
                    Gdx.app.exit();
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
        Gdx.app.log("HomeScreen", "hide!");
        mStage.clear();
    }

    @Override
    public void dispose() {
        Gdx.app.log("HomeScreen", "dispose!");
        mSkin.dispose();
        mTextureAtlas.dispose();
        mStage.dispose();

    }
}
