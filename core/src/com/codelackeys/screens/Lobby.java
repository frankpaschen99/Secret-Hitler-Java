package com.codelackeys.screens;

import java.util.ArrayList;

import com.codelackeys.entities.Player;
import com.codelackeys.network.SHRes;
import com.esotericsoftware.kryonet.Connection;

public class Lobby {

	public int lobbyID;
	public ArrayList<Player> players;
	public String password;
	public Player host;
	
	public Lobby(int lobbyID, String password) {
		this.password = password;
		this.lobbyID = lobbyID;
		
		players = new ArrayList<Player>();
	}
	public void startGame() {
		// Notify all clients to switch to the GameBoard screen
		for (Player p : players) {
			if (p == host) continue;
			
			SHRes res = new SHRes();
			res.text = "START";
			p.connection.sendTCP(res);
		}
	}
	public void addPlayer(Player p) {
		this.players.add(p);
	}
	public void playerDisconnect(Connection con) {
		for (Player p : players) {
			if (p.connection == con) {
				players.remove(p);
			}
		}
		
	}
}
