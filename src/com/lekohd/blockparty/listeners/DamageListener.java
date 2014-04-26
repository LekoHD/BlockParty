package com.lekohd.blockparty.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import com.lekohd.blockparty.Main;

public class DamageListener implements Listener{
	@EventHandler
    public void onEntityDamageEvent(final EntityDamageEvent e) {
		if(e.getEntity() instanceof Player){
			Player p = (Player) e.getEntity();
			if(Main.inLobbyPlayers.containsKey(p)){
				e.setCancelled(true);
			}
			if(Main.onFloorPlayers.containsKey(p)){
				e.setCancelled(true);
			}
		}
	}
}