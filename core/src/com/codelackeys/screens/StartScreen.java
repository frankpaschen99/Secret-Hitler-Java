package com.codelackeys.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.codelackeys.game.CoreGame;

public class StartScreen implements Screen {
	private SpriteBatch batch;
	private CoreGame game;
	
	private FreeTypeFontGenerator generator;
	private FreeTypeFontParameter parameter;
	BitmapFont font12;

	public StartScreen(CoreGame game) {
		/** Initialize fields **/
		this.game = game;
		
		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/roboto.ttf"));
		parameter = new FreeTypeFontParameter();
		parameter.size = 24;
		font12 = generator.generateFont(parameter);
		generator.dispose();
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
		font12.draw(batch, "Press [K] to join a lobby. Press [L] to create a lobby.", 0, 580);
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
