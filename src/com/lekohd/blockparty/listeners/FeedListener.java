package com.lekohd.blockparty.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import com.lekohd.blockparty.Main;

public class FeedListener implements Listener{

	@EventHandler
	public void onFeed(final FoodLevelChangeEvent e){
		if(Main.onFloorPlayers.containsKey(e.getEntity()))
			e.setFoodLevel(20);
	}
	
}
