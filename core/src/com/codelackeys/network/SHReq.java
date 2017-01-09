package com.codelackeys.network;

/** This class represents a join request from the client to the server **/
public class SHReq {
	public int lobbyID;
	public String username;
	public String password;
	public String text;
	
	public SHReq() {}
	public SHReq(int lID, String username, String pw) {
		this.lobbyID = lID;
		this.username = username;
		this.password = pw;
	}
}
