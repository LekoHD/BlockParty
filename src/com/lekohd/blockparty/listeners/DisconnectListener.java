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
		if(Main.inGamePlayers.containsKey(e.getPlayer()))
			Main.inGamePlayers.remove(e.getPlayer());
		if(Main.inLobbyPlayers.containsKey(e.getPlayer()))
			Main.inLobbyPlayers.remove(e.getPlayer());
		if(Main.onFloorPlayers.containsKey(e.getPlayer()))
			Main.onFloorPlayers.remove(e.getPlayer());
	}
	
	@EventHandler
	public void onKick(PlayerKickEvent e) {
		if(Main.inGamePlayers.containsKey(e.getPlayer()))
			Main.inGamePlayers.remove(e.getPlayer());
		if(Main.inLobbyPlayers.containsKey(e.getPlayer()))
			Main.inLobbyPlayers.remove(e.getPlayer());
		if(Main.onFloorPlayers.containsKey(e.getPlayer()))
			Main.onFloorPlayers.remove(e.getPlayer());
	}

}
