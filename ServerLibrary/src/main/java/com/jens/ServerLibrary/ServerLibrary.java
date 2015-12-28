package com.jens.ServerLibrary;

import org.bukkit.plugin.java.JavaPlugin;

public class ServerLibrary extends JavaPlugin {
	
	protected String tag = "[ServerLibrary] ";
	
	@Override
	public void onEnable() {
		System.out.println(tag + "The Library now started.");
	}
	
	@Override
	public void onDisable() {
		System.out.println(tag + "The Library was disabled.");
	}

}
