package com.example.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.example.game.Screens.HomeScreen;
import com.example.game.Screens.PlayScreen;

public class MoveOrCrashGame extends Game {
	public SpriteBatch batch;
	public final ScreenManager manager;

	public MoveOrCrashGame() {
		manager = new ScreenManager(this);
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		//setScreen(new HomeScreen(this));
		manager.nextScreen(new HomeScreen(this));
		//setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
}
