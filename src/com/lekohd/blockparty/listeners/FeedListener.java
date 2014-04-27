package com.lekohd.blockparty.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import com.lekohd.blockparty.Main;

/*
 * Copyright (C) 2014 Leon167 and XxChxppellxX 
 */
 
public class FeedListener implements Listener{

	@EventHandler
	public void onFeed(final FoodLevelChangeEvent e){
		if(Main.onFloorPlayers.containsKey(e.getEntity().getName()))
			e.setFoodLevel(20);
	}
	
}
