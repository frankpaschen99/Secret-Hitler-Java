package com.codelackeys.entities;

import java.io.IOException;

import com.codelackeys.network.SHReq;
import com.codelackeys.network.SHRes;
import com.codelackeys.screens.Lobby;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class HitlerServer {
	private Kryo kryo;				// serialization
	private Server server;			// Kryonet Server object
	public Lobby lobby;				// Lobby that will actually run the game

	public HitlerServer(int lobbyID, String password, String username) {
		lobby = new Lobby(lobbyID, password);			// create the lobby with the supplied ID and pw
		Player host = new Player(null, username);		// Create & add the host to the players ArrayList
		lobby.players.add(host);
		lobby.host = host;								// Let the lobby know who the host is

		server = new Server();
		server.start();
		try {
			server.bind(54555, 54777);
		} catch (IOException e) {
		}

		kryo = server.getKryo();
		kryo.register(SHReq.class);
		kryo.register(SHRes.class);
		
		/** Await a request from the Client. Validate their password **/
		server.addListener(new Listener() {
			@Override
			public void received(Connection con, Object packet) { // DON'T CHANGE METHOD SIGNATURE
				if (packet instanceof SHReq) {
					SHReq req = (SHReq) packet;
					// Test if their password is correct
					if (req.password.equals(lobby.password)) {
						
						// Add them to the players ArrayList
						lobby.players.add(new Player(con, req.username));
						
						/* Give them the OK to start. Normally this won't happen till host presses start game */
						SHRes res = new SHRes("START");
						con.sendTCP(res);
						
					} else {
						System.out.println("Incorrect password! TODO: Handle this more elegantly later");
					}
				}
			}
		});
		/** Listen for a client's disconnect. Inform the lobby when it happens. **/
		server.addListener(new Listener() {
			@Override
			public void disconnected(Connection con) {
				lobby.playerDisconnect(con);
			}
		});
	}
}
