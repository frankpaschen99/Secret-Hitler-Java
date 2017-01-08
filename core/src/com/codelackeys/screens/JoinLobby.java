package com.codelackeys.screens;

import javax.swing.JOptionPane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.codelackeys.entities.HitlerClient;
import com.codelackeys.game.CoreGame;

public class JoinLobby implements Screen {
	private CoreGame game;
	
	public JoinLobby(CoreGame game) {
		this.game = game;
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		System.out.println("show() called");
		int lobbyID = Integer.parseInt(JOptionPane.showInputDialog("Enter lobby code"));
		String username = JOptionPane.showInputDialog("Desired username?");
		String password = JOptionPane.showInputDialog("Enter password; leave blank if none");
		HitlerClient client = new HitlerClient(game);
		client.joinGame(lobbyID, username, password);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
