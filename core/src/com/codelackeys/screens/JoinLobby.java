package com.codelackeys.screens;

import javax.swing.JOptionPane;
import com.badlogic.gdx.Screen;
import com.codelackeys.entities.HitlerClient;
import com.codelackeys.game.CoreGame;

public class JoinLobby implements Screen {
	private CoreGame game;
	
	public JoinLobby(CoreGame game) {
		this.game = game;
	}
	
	@Override
	public void show() {
		/** Query the user for the lobby id, lobby password, and their username **/
		int lobbyID = Integer.parseInt(JOptionPane.showInputDialog("Enter the lobby code:"));
		String password = JOptionPane.showInputDialog("Enter lobby password: (leave blank if none)");
		String username = JOptionPane.showInputDialog("Enter your desired username:");
		
		/** Create a new HitlerClient object **/
		HitlerClient client = new HitlerClient(game);
		
		/** Call joinGame() on the client to send the join request to the host **/
		client.joinGame(lobbyID, username, password);
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
