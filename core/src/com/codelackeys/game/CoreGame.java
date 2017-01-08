package com.codelackeys.game;

import com.codelackeys.screens.WelcomeScreen;
import com.badlogic.gdx.Game;

public class CoreGame extends Game {
	
	@Override
	public void create () {
		setScreen(new WelcomeScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
}
