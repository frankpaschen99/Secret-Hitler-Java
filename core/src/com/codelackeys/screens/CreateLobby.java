package com.codelackeys.screens;

import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JOptionPane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.codelackeys.entities.HitlerServer;
import com.codelackeys.game.CoreGame;

public class CreateLobby implements Screen {
	private CoreGame game;
	
	public CreateLobby(CoreGame game) {
		this.game = game;
	}
	
	@Override
	public void show() {
		String password = JOptionPane.showInputDialog("Enter lobby password");
		String username = JOptionPane.showInputDialog("Enter your desired username:");
		int code = ThreadLocalRandom.current().nextInt(1000, 2000 + 1);
		JOptionPane.showMessageDialog(null, "Lobby Password: " + password + ", Lobby Code: " + code + ". Give"
				+ " this information to your friends");
		HitlerServer server = new HitlerServer(code, password, username);
		game.setScreen(new LobbyScreen(server));
	}

	@Override
	public void render(float delta) {
		// Show text box for password, random generated lobby id
		Gdx.gl.glClearColor(1, 0, 0, 1);
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
