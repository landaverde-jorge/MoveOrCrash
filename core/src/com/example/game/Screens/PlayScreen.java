package com.example.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.example.game.Car;
import com.example.game.MoveOrCrashGame;
import com.example.game.Scenes.HUD;

import java.util.Random;

/**
 * Created by Mirola on 5/21/2016.
 */
public class PlayScreen implements Screen{
    private static final String TAG = PlayScreen.class.getSimpleName();


    private MoveOrCrashGame game;

    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private HUD hud;

    private Vector2 position;
    private Texture bg;

    private Car playerCar;
    private Car enemyCar;

    private boolean isGameOver;

    private Random random;


        public PlayScreen(MoveOrCrashGame game){
            Gdx.app.log(TAG, String.format("width: %d, height: %d", Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
            this.game = game;
            hud = new HUD(game.batch);
            gamecam = new OrthographicCamera();
            //gamePort = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), gamecam);
            gamePort = new FitViewport(MoveOrCrashGame.V_WIDTH, MoveOrCrashGame.V_HEIGHT, gamecam);

//            gamePort = new ScreenViewport(gamecam);
//            gamePort =  new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), gamecam);

            bg = new Texture("newTiledStreet.png");
            position = new Vector2(0,0);

            playerCar = new Car(120f, 20f, 60f, 120f, "CAR.png");
            enemyCar = new Car(240f, Gdx.graphics.getHeight(), 60f, 120f, "BlueCar.png");
            enemyCar.setVelocity(new Vector2(0,-3f));

            random = new Random();
        }


    @Override
    public void show() {
        gamecam.setToOrtho(false, MoveOrCrashGame.V_WIDTH, MoveOrCrashGame.V_HEIGHT);

    }

    public void handleInput(float dt){
        if(Gdx.input.justTouched()) {
            isGameOver = !isGameOver;
            enemyCar.getPosition().y = MoveOrCrashGame.V_HEIGHT;
            enemyCar.getPosition().x = 120 * (1 + random.nextInt(3));
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && !isGameOver) {
            playerCar.moveRight(120f);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && !isGameOver) {
            playerCar.moveLeft(120f);
        }

    }

    public void update(float dt){

        handleInput(dt);

        if(isGameOver) {
            return;
        }

        gamecam.update();

        position.y = position.y - 300*dt;

        if(position.y < -MoveOrCrashGame.V_HEIGHT) {
            position.y = 0;
        }

        if(enemyCar.getPosition().y + 120 < 0) {
            enemyCar.getPosition().y = MoveOrCrashGame.V_HEIGHT;
            enemyCar.getPosition().x = 120 * (1 + random.nextInt(3));
        }

        enemyCar.update(dt);

        isGameOver = playerCar.collidesWith(enemyCar);

    }


    @Override
    public void render(float delta) {

        update(delta);



        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        game.batch.setProjectionMatrix(gamecam.combined);

        game.batch.begin();
        game.batch.draw(bg,position.x,position.y);
        game.batch.draw(bg,position.x,position.y + MoveOrCrashGame.V_HEIGHT);
        game.batch.draw(playerCar,playerCar.getPosition().x, playerCar.getPosition().y);
        game.batch.draw(enemyCar,enemyCar.getPosition().x, enemyCar.getPosition().y);
        game.batch.end();

        hud.stage.draw();


        if(isGameOver) {
            game.batch.begin();
            game.batch.draw(new Texture("GameOver.png"),Gdx.graphics.getWidth()/2 - 190, Gdx.graphics.getHeight()/2);
            game.batch.end();
        }
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);

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
