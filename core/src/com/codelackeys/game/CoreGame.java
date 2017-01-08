package com.codelackeys.game;

import com.codelackeys.screens.StartScreen;
import com.badlogic.gdx.Game;

public class CoreGame extends Game {
	
	@Override
	public void create () {
		setScreen(new StartScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
}
