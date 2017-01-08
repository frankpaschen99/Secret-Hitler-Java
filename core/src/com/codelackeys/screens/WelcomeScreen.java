package com.codelackeys.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.codelackeys.game.CoreGame;
import com.esotericsoftware.kryonet.Server;

public class WelcomeScreen implements Screen {

	/* The Welcome screen will include two buttons: Create Lobby and Join Lobby. */
	
	private SpriteBatch batch;
	private CoreGame game;
	
	public WelcomeScreen(CoreGame game) {
		this.game = game;
	}
	@Override
	public void show() {
		// This is called once, when setScreen() is called
		batch = new SpriteBatch();
		game.setScreen(new Lobby(123, "siduck"));
	}

	@Override
	public void render(float delta) {
		// called every iteration of the game loop
		// used for drawing and handling input
		
		// Must clear the buffer every iteration or we get that weird windows xp bug
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		// draw welcome screen here
		
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		// Probably not going to allow this
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
