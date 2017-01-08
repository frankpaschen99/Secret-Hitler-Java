package com.codelackeys.screens;

import java.awt.Container;
import java.awt.List;
import java.util.ArrayList;

import com.badlogic.gdx.Screen;
import com.codelackeys.entities.Player;
import com.codelackeys.game.CoreGame;
import com.codelackeys.network.SHReq;
import com.codelackeys.network.SHRes;
import com.codelackeys.utils.GLOBALS;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class Lobby implements Screen {

	private int lobbyID;
	private ArrayList<Player> players;
	private String password;
	private Server server;
	private CoreGame game;
	
	public Lobby(CoreGame game, int lobbyID, String password) {
		// Constructor parameters
		this.password = password;
		this.lobbyID = lobbyID;
		
		// Initialize properties
		players = new ArrayList<Player>();
		this.initializeServer();
	}
	
	@Override
	public void show() {
		// Setup server + listener for host
	}
	
	public void initializeServer() {
		
		if (GLOBALS.IS_HOST) {
			this.server = new Server();
			server.addListener(new Listener() {
				@SuppressWarnings("unused")
			public void received (Connection connection, SHReq request) {
				   if (request.lobbyID == Lobby.this.lobbyID) {
					   // create Player and add them to the game
					   Lobby.this.players.add(new Player(connection, request.username));
					   System.out.println("Server awaiting connections");
				   }
			   }
		    });
		}
	}
	
	public void startGame() {
		// Notify all clients to switch to the GameBoard screen
		for (Player p : players) {
			SHRes res = new SHRes();
			res.text = "START";
			p.connection.sendTCP(res);
		}
		// Move the server host to the game screen
		// game.setScreen(new GameBoard());
	}

	@Override
	public void render(float delta) {
		// Draw the lobby here, showing buttons for the Host ("Start Game") and a 
		// list of players that everyone can see
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
