package com.lekohd.blockparty.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.lekohd.blockparty.Main;

/*
 * Copyright (C) 2014 Leon167 and XxChxppellxX 
 */
 
public class CommandListener implements Listener{

	@EventHandler
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e)
	{
		Player p = e.getPlayer();
	        if(Main.inGamePlayers.containsKey(p.getName()) || Main.onFloorPlayers.containsKey(p.getName())){
	            // Hier die Aktion, wenn ein Befehl in einer ArrayList 'ArrayListName' steht.
	        	if(!p.isOp())
	        	{
	        		e.setCancelled(true);
	        	}
	        }
	 }
}
