package com.example.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import java.util.Stack;

/**
 * Created by Mirola on 6/18/2016.
 */
public class ScreenManager {
    Stack<Screen> stack = new Stack<Screen>();
    Screen current;
    Game game;

    public ScreenManager(MoveOrCrashGame game) {
        this.game = game;
    }

    public void previousScreen() {
        current.dispose();
        current = stack.pop();
        game.setScreen(current);
    }


    public void nextScreen(Screen screen) {

        if(current != null) {
            stack.push(current);
        }

        current = screen;
        game.setScreen(current);
    }

    public void setScreen(Screen screen){

    }
}
