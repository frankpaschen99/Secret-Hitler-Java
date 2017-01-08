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
	private Kryo kryo;
	private Server server;
	public Lobby lobby;

	public HitlerServer(int lobbyID, String password, String username) {
		lobby = new Lobby(lobbyID, password);
		Player host = new Player(null, username);
		lobby.addPlayer(host);
		lobby.host = host;

		server = new Server();
		server.start();
		try {
			server.bind(54555, 54777);
		} catch (IOException e) {
		}

		kryo = server.getKryo();
		kryo.register(SHReq.class);
		kryo.register(SHRes.class);

		server.addListener(new Listener() {
			@Override
			public void received(Connection con, Object packet) { // DON'T CHANGE METHOD SIGNATURE
				if (packet instanceof SHReq) {
					SHReq req = (SHReq) packet;
					if (req.password.equals(lobby.password)) {
						lobby.addPlayer(new Player(con, req.username));
						
						/* Give them the OK to start. Normally this won't happen till host presses start game */
						SHRes res = new SHRes();
						res.text = "START";
						con.sendTCP(res);
					} else {
						System.out.println("Incorrect password! TODO: Handle this more elegantly later");
					}
				}
			}
		});
		server.addListener(new Listener() {
			@Override
			public void disconnected(Connection con) {
				lobby.playerDisconnect(con);
			}
		});
		
		/* Send the gamestate to all connected clients */
		Thread sendState = new Thread() {
			public void run() {
				GameState gs = new GameState();
				gs.players = lobby.players;
				for(Player p : lobby.players) {
					try {
						p.connection.sendTCP(gs);
					} catch (NullPointerException e) {
						continue;
					}
				}
			}
		};
		sendState.start();
	}
}
