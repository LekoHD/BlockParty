package com.lekohd.blockparty.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import com.lekohd.blockparty.BlockParty;

/*
 * Copyright (C) 2014 Leon167 and XxChxppellxX 
 */
 
public class DamageListener implements Listener{
	@EventHandler
    public void onEntityDamageEvent(final EntityDamageEvent e) {
		if(e.getEntity() instanceof Player){
			Player p = (Player) e.getEntity();
			if(BlockParty.inLobbyPlayers.containsKey(p.getName())){
				e.setCancelled(true);
			}
			if(BlockParty.onFloorPlayers.containsKey(p.getName())){
				e.setCancelled(true);
			}
		}
	}
}
