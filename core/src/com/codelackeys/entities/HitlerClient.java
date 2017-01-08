package com.codelackeys.entities;

import java.io.IOException;

import com.codelackeys.game.CoreGame;
import com.codelackeys.network.SHReq;
import com.codelackeys.network.SHRes;
import com.codelackeys.screens.GameBoard;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class HitlerClient {
	private Client client;
	private Kryo kryo;
	private CoreGame game;
	
	public HitlerClient(CoreGame game) {
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
	       public void received (Connection connection, Object object) {
	          if (object instanceof SHRes) {
	             SHRes res = (SHRes) object;
	            	 System.out.println("received message");
	            	 // HitlerClient.this.game.setScreen(new GameBoard());
	          }
	       }
	    });
	}
	public void joinGame(int lobbyID, String username, String password) {
		SHReq req = new SHReq();
		req.lobbyID = lobbyID;
		req.username = username;
		req.password = password;
		client.sendTCP(req);
	}
}
