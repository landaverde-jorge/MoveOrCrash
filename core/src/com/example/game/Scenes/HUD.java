package com.example.game.Scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.example.game.GameConfiguration;
import com.example.game.MoveOrCrashGame;

public class HUD {
    private  final static String TAG = HUD.class.getSimpleName();

    public Stage stage;
    private Viewport viewport;
    private float worldTimer;
    private float timeCount;
    private Integer score;
    private String player;
    private Integer hScore;

    Label timerLabel;
    Label scoreLabel;
    Label timeLabel;
    Label sceneLabel;
    Label areaLabel;
    Label playerLabel;

    public HUD(SpriteBatch sb){

        worldTimer = 0;
        timeCount = 0;
        score = 0;
        player = "Player1";
        viewport = new FitViewport(GameConfiguration.WIDTH, GameConfiguration.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport,sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

       // timerLabel = new Label(String.format("%f",worldTimer),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%06d",score),new Label.LabelStyle(new BitmapFont(Gdx.files.internal("mentha-rapture.fnt")), Color.BLACK));
       // timeLabel = new Label("TIME",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //sceneLabel = new Label("City",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //areaLabel = new Label("AREA",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        playerLabel = new Label(player,new Label.LabelStyle(new BitmapFont(Gdx.files.internal("mentha-rapture.fnt")), Color.BLACK));

        //playerLabel =  new Label("Player1",new Label.LabelStyle(new BitmapFont(), Color.BLACK));

        table.add(playerLabel).expandX();
        table.add(scoreLabel).expandX();

        table.add(areaLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.row();
        table.add(sceneLabel).expandX();
        table.add(timerLabel).expandX();

        stage.addActor(table);

    }

    public void updateTimer(float dt) {

        worldTimer += dt;
        int minutes;
        int seconds;

        minutes = (int)worldTimer/60;
        seconds = (int)worldTimer%60;


        timerLabel.setText(String.format("%02d:%02d",minutes,seconds));
//
//        int innerCycle;
//        int outerCycle;
//
//
//        innerCycle = (int)(worldTimer/30)%8 + 1;
//        level = ((int)time/LVL_DURATION) % MAX_LVL + 1;
//        outerCycle = (int)worldTimer/240 + 1
//
//    String log = String.format("stage: %d level: %d",outerCycle,innerCycle);
//
//    //  Gdx.app.log(TAG ,log);
}
    public void updateScore(int newScore)
    {
        score += newScore;
        scoreLabel.setText(String.format("%06d",score));
    }


}
