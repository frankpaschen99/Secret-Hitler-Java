package com.codelackeys.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.codelackeys.entities.HitlerServer;
import com.codelackeys.entities.Player;
import com.codelackeys.utils.GLOBALS;

public class LobbyScreen implements Screen {

	private HitlerServer server;
	private BitmapFont font = new BitmapFont();
	private SpriteBatch batch;
	
	public LobbyScreen(HitlerServer server) {
		this.server = server;
	}
	public LobbyScreen() {
		
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(float delta) {
		this.batch = new SpriteBatch();
		
		if (GLOBALS.IS_HOST) {
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			
			String playerString = "";
			for (Player x : server.lobby.players) playerString += x.username + (server.lobby.players.size() == 1 ? ' ' : ', ');
			
			batch.begin();
			font.draw(batch, "Currently in lobby #" + server.lobby.lobbyID, 0, 600);
			font.draw(batch, "Player list: " + playerString, 0, 500);
			batch.end();
		} else {
			// Fetch the player list from the server somehow
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
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
