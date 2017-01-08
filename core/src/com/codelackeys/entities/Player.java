package com.codelackeys.entities;

import com.esotericsoftware.kryonet.Connection;

public class Player {
	public Connection connection;
	public String username;
	
	public Player(Connection connection, String username) {
		this.connection = connection;
		this.username = username;
	}
}
