package com.lekohd.blockparty.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.lekohd.blockparty.Main;

/*
 * Copyright (C) 2014 Leon167 and XxChxppellxX 
 */
 
public class DisconnectListener implements Listener {

	@EventHandler
	public void onDisconnect(PlayerQuitEvent e) {
		if(Main.inGamePlayers.containsKey(e.getPlayer().getName()))
			Main.inGamePlayers.remove(e.getPlayer().getName());
		if(Main.inLobbyPlayers.containsKey(e.getPlayer().getName()))
			Main.inLobbyPlayers.remove(e.getPlayer().getName());
		if(Main.onFloorPlayers.containsKey(e.getPlayer().getName()))
			Main.onFloorPlayers.remove(e.getPlayer().getName());
	}
	
	@EventHandler
	public void onKick(PlayerKickEvent e) {
		if(Main.inGamePlayers.containsKey(e.getPlayer().getName()))
			Main.inGamePlayers.remove(e.getPlayer().getName());
		if(Main.inLobbyPlayers.containsKey(e.getPlayer().getName()))
			Main.inLobbyPlayers.remove(e.getPlayer().getName());
		if(Main.onFloorPlayers.containsKey(e.getPlayer().getName()))
			Main.onFloorPlayers.remove(e.getPlayer().getName());
	}

}
