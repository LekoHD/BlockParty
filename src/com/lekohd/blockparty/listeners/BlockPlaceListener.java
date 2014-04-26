package com.lekohd.blockparty.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.lekohd.blockparty.Main;

/*
 * Copyright (C) 2014 Leon167 and XxChxppellxX 
 */
 
public class BlockPlaceListener implements Listener{
	
	@EventHandler
    public void onBlockPlaceEvent(final BlockPlaceEvent e) {
		Player p = e.getPlayer();
		if(Main.inLobbyPlayers.containsKey(p)){
			e.setCancelled(true);
		}
		if(Main.onFloorPlayers.containsKey(p)){
			e.setCancelled(true);
		}
	}

}
