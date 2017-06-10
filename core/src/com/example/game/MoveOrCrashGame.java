package com.example.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.example.game.Screens.HomeScreen;
import com.example.game.Screens.PlayScreen;

public class MoveOrCrashGame extends Game {
	public SpriteBatch batch;
	public final ScreenManager manager;
	public static AssetManager assetManager;

	public MoveOrCrashGame() {
		manager = new ScreenManager(this);
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		assetManager = new AssetManager();
		assetManager.load("Audio/Music/Home_InGameSong.mp3", Music.class);
		assetManager.finishLoading();
		manager.nextScreen(new HomeScreen(this));


//		assetManager.load("Audio/Music/HomeScreenSong2.mp3", Music.class); TODO: Get all the sounds to play properly
//		assetManager.load("Audio/Music/InGameMusic.mp3", Music.class);
//		assetManager.load("Audio/Sounds/ButtonSound.wav", Sound.class);
//		assetManager.load("Audio/Sounds/GoSkid.wav", Sound.class);
//		assetManager.load("Audio/Sounds/PauseSkid.wav", Sound.class);
		//setScreen(new PlayScreen(this));
		//setScreen(new HomeScreen(this));
	}

	@Override
	public void render ()
	{
		super.render();

	}
}
