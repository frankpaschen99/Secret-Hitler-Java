package com.codelackeys.network;

/** Generic response class. Used to send any messages between clients & the server **/
public class SHRes {
	public String text;
	
	public SHRes(){}
	public SHRes(String t) {
		this.text = t;
	}
}
