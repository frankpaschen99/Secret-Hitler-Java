package com.codelackeys.game;

import com.codelackeys.screens.WelcomeScreen;
import com.badlogic.gdx.Game;

public class CoreGame extends Game {
	
	@Override
	public void create () {
		setScreen(new WelcomeScreen());
	}

	@Override
	public void render () {
		/*Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		
		batch.end();*/
		super.render();
	}
}
