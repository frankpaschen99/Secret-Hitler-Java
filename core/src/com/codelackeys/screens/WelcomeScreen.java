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
	private SpriteBatch batch;
	private CoreGame game;
	private Sprite createButton, joinButton;
	
	public WelcomeScreen(CoreGame game) {
		/** Initialize fields **/
		this.game = game;
		this.createButton = new Sprite(new Texture(Gdx.files.internal("interface/create_lobby.png")));
		this.joinButton = new Sprite(new Texture(Gdx.files.internal("interface/join_lobby.png")));
	}
	@Override
	public void show() {
		batch = new SpriteBatch();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		/** Draw buttons **/
		batch.begin();
		batch.draw(createButton, 0, Gdx.graphics.getHeight()-createButton.getHeight());
		batch.draw(joinButton, 0, Gdx.graphics.getHeight()-createButton.getHeight()*2);
		batch.end();

		/** Handle input & Button pressing **/
		// TODO: Use Scene2d for UI in the future
		if (Gdx.input.isKeyPressed(Keys.K)) {
			game.setScreen(new JoinLobby(game));
		} else if (Gdx.input.isKeyPressed(Keys.L)) {
			game.setScreen(new CreateLobby(game));
		}
	}
	
	@Override
	public void resize(int width, int height) {}
	@Override
	public void pause() {}
	@Override
	public void resume() {}
	@Override
	public void hide() {}
	@Override
	public void dispose() {}
}
