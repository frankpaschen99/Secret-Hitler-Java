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

public class HitlerClient {
	private Client client;
	private Kryo kryo;
	private CoreGame game;
	public GameState gameState;
	
	public HitlerClient(final CoreGame game) {
		this.game = game;
		client = new Client();
		client.start();

		kryo = client.getKryo();
		kryo.register(SHReq.class);
		kryo.register(SHRes.class);

		try {
			client.connect(5000, "10.0.0.4", 54555, 54777);
		} catch (IOException e) {
			e.printStackTrace();
		}

		client.addListener(new Listener() {
			@Override
			public void received(Connection connection, Object object) {
				if (object instanceof SHRes) {
					SHRes res = (SHRes) object;
					final CoreGame g = HitlerClient.this.game;
					
					// run g.setScreen on the main thread, not the Client thread that is created
					Gdx.app.postRunnable(new Runnable() {
						public void run() {
							g.setScreen(new LobbyScreen());
						}
					});
				} else if (object instanceof GameState) {
					System.out.println("GameState recieved on the client");
					GameState gs = (GameState) object;
					HitlerClient.this.gameState = gs;
				}
			}
		});
	}

	public void moveScreens() {
		game.setScreen(new LobbyScreen());
	}

	public void joinGame(int lobbyID, String username, String password) {
		SHReq req = new SHReq();
		req.lobbyID = lobbyID;
		req.username = username;
		req.password = password;
		client.sendTCP(req);
	}
}
