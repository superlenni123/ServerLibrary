package com.jens.ServerLibrary.Protocol;

public enum AuthentificationConnect {
	
	UUID("https://api.mojang.com/users/profiles/minecraft/%s?at=%d"),
	GAMEPROFILE("https://sessionserver.mojang.com/session/minecraft/profile/%s?unsigned=false");
	
	protected String url;
	
	private AuthentificationConnect(String url){
		this.url = url;
	}

	public String getURL(){
		return url;
	}
}
