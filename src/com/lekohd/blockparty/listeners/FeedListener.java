package com.lekohd.blockparty.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import com.lekohd.blockparty.BlockParty;

/*
 * Copyright (C) 2014 Leon167 and XxChxppellxX 
 */
 
public class FeedListener implements Listener{

	@EventHandler
	public void onFeed(final FoodLevelChangeEvent e){
		if(BlockParty.onFloorPlayers.containsKey(e.getEntity().getName()))
			e.setFoodLevel(20);
		if(BlockParty.inLobbyPlayers.containsKey(e.getEntity().getName()))
			e.setFoodLevel(20);
	}
	
}
