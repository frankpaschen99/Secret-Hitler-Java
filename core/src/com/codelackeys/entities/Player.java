package com.codelackeys.entities;

import com.esotericsoftware.kryonet.Connection;

/** This class represents a player in a game. It stores their username and their
 * Connection object so we can send them stuff later **/
public class Player {
	public Connection connection;
	public String username;
	
	public Player(Connection connection, String username) {
		this.connection = connection;
		this.username = username;
	}
}
