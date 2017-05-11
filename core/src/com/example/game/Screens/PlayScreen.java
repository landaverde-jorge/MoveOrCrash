package com.example.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.example.game.Car;
import com.example.game.GameConfiguration;
import com.example.game.MoveOrCrashGame;
import com.example.game.Scenes.HUD;

import java.util.ArrayList;

/**
 * Created by Jorge on 5/21/2016.
 */
public class PlayScreen implements Screen{
    private static final String TAG = PlayScreen.class.getSimpleName();


    private MoveOrCrashGame game;


    private static  final float LEFT_LANE = 120f;
    private static  final float MID_LANE = 240f;
    private static  final float RIGHT_LANE = 360f;

    private static final int MAX_LVL = 8;
    private static final int MIN_CAR_SPACING = 300;
    private static final int LVL_DURATION = 10;

    private ShapeRenderer renderer;  // TODO: temporaty

    private OrthographicCamera camera;
    private Viewport viewport;
    private HUD hud;

    private Vector2 position;
    private Vector2 position2;  // TODO: better var name

    private Texture bg;
    private Texture bg2;

    private Car playerCar;
    private float playerSpeed;

    private int distanceUntilNextWave;
    private ArrayList<Car> enemies;
    private float enemyVelocity;

    public static final String[] GAME_AREAS = {"Area1-50x27.png","50x27.png","Area2.png"};

    private float time;
    private int level;
    private int area;
    private float paddingTime;
    private int carSpacing = 0;


    private boolean isGameOver;

    private RandomXS128 random;
    private TextureAtlas mTextureAtlas;
    private Skin mSkin;
    private Stage mStage;


    public PlayScreen(MoveOrCrashGame game){
            this.game = game;
            hud = new HUD(game.batch);
            camera = new OrthographicCamera();
            viewport = new FitViewport(GameConfiguration.WIDTH, GameConfiguration.HEIGHT, camera);

            bg = new Texture(GAME_AREAS[0]);
            bg2 =  new Texture(GAME_AREAS[0]);;
            position = new Vector2(0,-20);
            position2 = new Vector2(0, GameConfiguration.HEIGHT - 20);

            playerCar = new Car(new Texture("RedSportsCar.png"),120f, 20f, 60f, 120f);   //TODO: Remove magic numbers!

            enemies = new ArrayList<Car>();

            random = new RandomXS128();

            renderer = new ShapeRenderer();

            mStage = new Stage();

        }


    @Override
    public void show() {
        camera.setToOrtho(false, GameConfiguration.WIDTH, GameConfiguration.HEIGHT);

        Gdx.app.log("HomeScreen", "show");

//            mStage.clear();

        mTextureAtlas = new TextureAtlas("buttons.pack");
        mSkin = new Skin(mTextureAtlas);

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(); //** Button properties **//
        style.up = mSkin.getDrawable("PauseButton40pixls");
        //style.down = mSkin.getDrawable("GreenButton");
        style.font = new BitmapFont();

        TextButton button = new TextButton("", style);
//            float buttonWidth = 300 * Gdx.graphics.getDensity();
//            float buttonHegiht = 72 * Gdx.graphics.getDensity();


        button.setPosition(GameConfiguration.WIDTH - 60, GameConfiguration.HEIGHT - 60);
//            button.setWidth(buttonWidth);       // TODO: remove hardcoded value
//            button.setHeight(buttonHegiht);      // TODO: remove hardcoded value

        button.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("", "Play!"); //** Usually used to start Game, etc. **//
//                    game.setScreen(new PlayScreen(game));
                game.manager.nextScreen(new PauseScreen(game));
//                    dispose();
                return true;
            }
        });

        mStage.addActor(button);


        Gdx.input.setInputProcessor(mStage); //** stage set to pass input to button/actors **//


    }

    public void handleInput(float dt){
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER) && isGameOver) {
            enemies.clear();
            distanceUntilNextWave = 0;
            isGameOver = !isGameOver;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && !isGameOver) {
            playerCar.moveRight(120f);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && !isGameOver) {
            playerCar.moveLeft(120f);
        }

    }

    public void update(float dt) {

        camera.update();
        handleInput(dt);

        // check if game is over and reset everything
        if (isGameOver) {
            enemies.clear();
            distanceUntilNextWave = 0;
            return;
        }

        //hud.updateTimer(dt);


        // bg speed (aka player speed)
        float dy =  playerSpeed * dt;
        position.y = position.y - dy;
        position2.y = position2.y - dy;




//        //bg loop stuff
        if (position.y <= -GameConfiguration.HEIGHT) {
            position.y = GameConfiguration.HEIGHT - 20;
            bg.dispose();
            bg = new Texture(GAME_AREAS[area]);
        }

        if (position2.y <= -GameConfiguration.HEIGHT ) {
            position2.y = GameConfiguration.HEIGHT - 20;
            bg2.dispose();
            bg2 = new Texture(GAME_AREAS[area]);
        }

        time += dt;

        level = ((int) time / LVL_DURATION) % MAX_LVL + 1;
//        area = ((int) time / (LVL_DURATION * MAX_LVL)) + 1;

        enemyVelocity = -(2 * area + 3);

        if(time < LVL_DURATION * MAX_LVL) {
            createNextWave();
        } else {
            paddingTime += dt;

               if (paddingTime > 5f && position2.y == GameConfiguration.HEIGHT - 20){
                   time = 0;
                   paddingTime = 0;
                   bg2.dispose();
                   bg2 = new Texture("TiledStreetBridge.png");
                   area++;
                }
        }



        playerSpeed = -100f * enemyVelocity;


        for(int i = 0; i < enemies.size() && !isGameOver; i++) {

            Car car = enemies.get(i);
            car.update(dt);
            isGameOver = playerCar.collidesWith(car);
            if(car.getPosition().y + 120 < 0) {
                enemies.remove(car);
                car.dispose();
            }
        }

        String log = String.format("Time: %f Area: %d Level: %d Speed: %f CarSpacing: %d, distanceUntilNextWave: %d", time, area, level, enemyVelocity, carSpacing, distanceUntilNextWave);
        Gdx.app.log(TAG, log);
    }


    @Override
    public void render(float delta) {

        update(delta);



        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(bg,position.x,position.y);
        game.batch.draw(bg2,position2.x,position2.y);

        game.batch.draw(playerCar,playerCar.getPosition().x, playerCar.getPosition().y);

//        Gdx.app.log("", "" + (position.y - position2.y));
//
//
//        //Draws all the enemy cars in the enemies array list.
        for(Car car : enemies) {
            game.batch.draw(car, car.getPosition().x, car.getPosition().y);
        }

        game.batch.end();

        hud.stage.draw();
        mStage.act();

        mStage.draw();

        if(isGameOver) {
            game.batch.begin();
            game.batch.draw(new Texture("GameOver.png"),Gdx.graphics.getWidth()/2 - 190, Gdx.graphics.getHeight()/2);
            game.batch.end();
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);

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

    public void createNextWave(){

        carSpacing = MIN_CAR_SPACING + (int)playerCar.getLength()*(MAX_LVL - level);

        if(distanceUntilNextWave <= 0) {

            int waveType = random.nextInt(6); //TODO: remove magic number!

            float y = GameConfiguration.HEIGHT;


            //TODO: remove all magic number!
            switch (waveType) {
                case 0: {
                    Car car = null;

                    car = Car.makeRandom(LEFT_LANE, y, 60f, 120f);
                    car.setVelocity(new Vector2(0, enemyVelocity));
                    enemies.add(car);
                    break;
                }
                case 1: {
                    Car car = null;

                    car = Car.makeRandom(MID_LANE, y, 60f, 120f);
                    car.setVelocity(new Vector2(0, enemyVelocity));
                    enemies.add(car);
                    break;
                }
                case 2: {
                    Car car = null;

                    car = Car.makeRandom(RIGHT_LANE, y, 60f, 120f);
                    car.setVelocity(new Vector2(0, enemyVelocity));
                    enemies.add(car);
                    break;
                }
                case 3: {
                    Car car = null;

                    car = Car.makeRandom(MID_LANE, y, 60f, 120f);
                    car.setVelocity(new Vector2(0, enemyVelocity));
                    enemies.add(car);

                    car = Car.makeRandom(RIGHT_LANE, y, 60f, 120f);
                    car.setVelocity(new Vector2(0, enemyVelocity));
                    enemies.add(car);
                    break;
                }
                case 4: {
                    Car car = null;
                    car = Car.makeRandom(MID_LANE, y, 60f, 120f);
                    car.setVelocity(new Vector2(0, enemyVelocity));
                    enemies.add(car);

                    car = Car.makeRandom(LEFT_LANE, y, 60f, 120f);
                    car.setVelocity(new Vector2(0, enemyVelocity));
                    enemies.add(car);
                    break;
                }
                case 5: {
                    Car car = null;
                    car = Car.makeRandom(RIGHT_LANE, y, 60f, 120f);
                    car.setVelocity(new Vector2(0, enemyVelocity));
                    enemies.add(car);

                    car = Car.makeRandom(LEFT_LANE, y, 60f, 120f);
                    car.setVelocity(new Vector2(0, enemyVelocity));
                    enemies.add(car);
                    break;
                }
            }
            distanceUntilNextWave = carSpacing;
        } else {
            distanceUntilNextWave += enemyVelocity;
        }

    }
}
