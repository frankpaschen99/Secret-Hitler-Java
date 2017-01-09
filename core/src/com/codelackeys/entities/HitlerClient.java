package com.codelackeys.entities;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.codelackeys.game.CoreGame;
import com.codelackeys.network.SHReq;
import com.codelackeys.network.SHRes;
import com.codelackeys.screens.LobbyScreen;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

/** User client. One for each client program **/
public class HitlerClient {
	private Client client;				// Kryonet Client object
	private Kryo kryo;					// has to do with serialization idfk
	private CoreGame game;				// Reference to the CoreGame
	public GameState gameState;			// TODO: make gamestates work
	
	public HitlerClient(final CoreGame game) {
		this.game = game;
		client = new Client();
		client.start();

		/** Register Request and Response classes to allow them to be sent **/
		kryo = client.getKryo();
		kryo.register(SHReq.class);
		kryo.register(SHRes.class);

		try {
			client.connect(5000, "10.0.0.4", 54555, 54777);
		} catch (IOException e) {
			e.printStackTrace();
		}

		/** Wait for a response from the server to move to the lobby screen. **/
		client.addListener(new Listener() {
			@Override
			public void received(Connection connection, Object object) {
				if (object instanceof SHRes) {
					SHRes res = (SHRes) object;
					if (res.text != "START") return;
					
					// Run setScreen on the main thread, not the Client thread that is created
					Gdx.app.postRunnable(new Runnable() {
						public void run() {
							// Move the client's screen to the Lobby
							HitlerClient.this.game.setScreen(new LobbyScreen());
						}
					});
				}
			}
		});
		/** If a client disconnects, close the client object **/
		client.addListener(new Listener() {
			@Override
			public void disconnected(Connection connection) {
				client.close();
			}
		});
	}

	/** Send the join request to the server. **/
	public void joinGame(int lobbyID, String username, String password) {
		SHReq req = new SHReq(lobbyID, username, password);
		client.sendTCP(req);
	}
}
