package com.example.game.Screens;
import com.badlogic.gdx.Gdx;
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


public class HomeScreen implements Screen{

    private MoveOrCrashGame game;

    private Texture background;

    BitmapFont font;

    private Stage mStage;
    private TextureAtlas mTextureAtlas;
    private Skin mSkin;


    public HomeScreen(MoveOrCrashGame game){
        this.game = game;
        background =  new Texture("HomeScreenBackground.png");
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

            TextButton button = new TextButton("Play", style);
//            float buttonWidth = 300 * Gdx.graphics.getDensity();
//            float buttonHegiht = 72 * Gdx.graphics.getDensity();


            button.setPosition(Gdx.graphics.getWidth()/2 - button.getWidth()/2, 200);
//            button.setWidth(buttonWidth);       // TODO: remove hardcoded value
//            button.setHeight(buttonHegiht);      // TODO: remove hardcoded value

            button.addListener(new InputListener() {
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    Gdx.app.log("", "Play!"); //** Usually used to start Game, etc. **//
//                    game.setScreen(new PlayScreen(game));
                    game.manager.nextScreen(new PlayScreen(game));
//                    dispose();
                    return true;
                }
            });

            mStage.addActor(button);


            Gdx.input.setInputProcessor(mStage); //** stage set to pass input to button/actors **//

            font = new BitmapFont(Gdx.files.internal("DemolitionCrackBlack.fnt"));

        }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mStage.act();

        game.batch.begin();
        game.batch.draw(background, 0, 0, GameConfiguration.WIDTH, GameConfiguration.HEIGHT);
        font.draw(game.batch,"MOVE\nOR\nCRASH", GameConfiguration.WIDTH/2 - 110,GameConfiguration.HEIGHT/2 + 350);

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
        Gdx.app.log("HomeScreen", "hide!"); //** Usually used to start Game, etc. **//
        mStage.clear();
    }

    @Override
    public void dispose() {
        Gdx.app.log("HomeScreen", "dispose!"); //** Usually used to start Game, etc. **//
        mSkin.dispose();
        mTextureAtlas.dispose();
        mStage.dispose();

    }
}
