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

	private HitlerServer server;					// reference to the server, used for the host to get information
	private BitmapFont font = new BitmapFont();		// used to draw text, will soon be replaced with FreetypeFont
	private SpriteBatch batch;
	
	public LobbyScreen(HitlerServer server) {		// Constructor for the host
		this.server = server;
	}
	public LobbyScreen() {}			// Constructor for clients
	
	@Override
	public void show() {
		this.batch = new SpriteBatch();
	}

	@Override
	public void render(float delta) {
		if (GLOBALS.IS_HOST) {
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			
			String playerString = "";
			// Generate string of players, putting a comma only if there are > 1 players. Ex: frank, pedro, davi
			for (Player x : server.lobby.players) playerString += x.username + (server.lobby.players.size() == 1 ? ' ' : ", ");
			
			// Draw lobby information.
			batch.begin();
			font.draw(batch, "Currently in lobby #" + server.lobby.lobbyID, 0, 600);
			font.draw(batch, "Player list: " + playerString, 0, 500);
			batch.end();
		} else {
			// Fetch the player list from the server somehow
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
