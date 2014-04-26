package com.lekohd.blockparty.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.lekohd.blockparty.Main;

public class CommandListener implements Listener{

	@EventHandler
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e)
	{
		Player p = e.getPlayer();
	        if(Main.inGamePlayers.containsKey(p) || Main.onFloorPlayers.containsKey(p)){
	            // Hier die Aktion, wenn ein Befehl in einer ArrayList 'ArrayListName' steht.
	        	if(!p.isOp())
	        	{
	        		e.setCancelled(true);
	        	}
	        }
	 }
}
