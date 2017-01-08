package com.codelackeys.screens;

import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JOptionPane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.codelackeys.entities.HitlerServer;
import com.codelackeys.game.CoreGame;
import com.codelackeys.utils.GLOBALS;

public class CreateLobby implements Screen {
	private CoreGame game;
	
	public CreateLobby(CoreGame game) {
		this.game = game;
	}
	
	@Override
	public void show() {
		/** Use JOptionPane to get lobby password and username from host **/
		String password = JOptionPane.showInputDialog("Enter new lobby password:");
		String username = JOptionPane.showInputDialog("Enter your desired username:");
		
		/** Generate a unique lobbyID **/
		int lobbyID = ThreadLocalRandom.current().nextInt(1000, 2000 + 1);
		
		/** Tell the host the unique ID **/
		JOptionPane.showMessageDialog(null, "Lobby Password: " + password + ", Lobby Code: " + 
					lobbyID + ". Give this information to your friends");
		
		/** Create a new HitlerServer object **/
		HitlerServer server = new HitlerServer(lobbyID, password, username);
		/** Set the IS_HOST flag to true for later **/
		GLOBALS.IS_HOST = true;
		
		/** Move them to the lobby screen to wait for players to join **/
		game.setScreen(new LobbyScreen(server));
	}

	@Override
	public void render(float delta) {}
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
