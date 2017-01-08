package com.codelackeys.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.codelackeys.game.CoreGame;

public class WelcomeScreen implements Screen {

	/* The Welcome screen will include two buttons: Create Lobby and Join Lobby. */
	
	private SpriteBatch batch;
	private CoreGame game;
	private Sprite createButton;
	private Sprite joinButton;
	
	public WelcomeScreen(CoreGame game) {
		this.game = game;
		this.createButton = new Sprite(new Texture(Gdx.files.internal("interface/create_lobby.png")));
		this.joinButton = new Sprite(new Texture(Gdx.files.internal("interface/join_lobby.png")));
	}
	@Override
	public void show() {
		// This is called once, when setScreen() is called
		batch = new SpriteBatch();
	}

	@Override
	public void render(float delta) {
		// called every iteration of the game loop
		// used for drawing and handling input
		
		// Must clear the buffer every iteration or we get that weird windows xp bug
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		batch.draw(createButton, 0, Gdx.graphics.getHeight()-createButton.getHeight());
		batch.draw(joinButton, 0, Gdx.graphics.getHeight()-createButton.getHeight()*2);
		batch.end();

		if (Gdx.input.justTouched()) {
			if (createButton.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.input.getY())) {
				game.setScreen(new CreateLobby(game));
			}/* else if (joinButton.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.input.getY())) {
				game.setScreen(new JoinLobby(game));
			}*/
		}
		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			game.setScreen(new JoinLobby(game));
		}
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
