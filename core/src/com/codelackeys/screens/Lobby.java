package com.codelackeys.screens;

import java.util.ArrayList;

import com.codelackeys.entities.Player;
import com.esotericsoftware.kryonet.Connection;

public class Lobby {

	public int lobbyID;						// Unique lobby ID
	public ArrayList<Player> players;		// ArrayList of all Players
	public String password;					// Password to join the server
	public Player host;						// The Player object of the server host
	
	public Lobby(int lobbyID, String password) {
		/** Initialize fields **/
		this.password = password;
		this.lobbyID = lobbyID;
		this.players = new ArrayList<Player>();
	}
	/** Called by the server. Removes disconnected player from the player ArrayList **/
	public void playerDisconnect(Connection con) {
		for (Player p : players) {
			if (p.connection == con) {
				players.remove(p);
			}
		}
	}
}
