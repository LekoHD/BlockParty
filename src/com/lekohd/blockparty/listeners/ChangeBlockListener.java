package com.lekohd.blockparty.listeners;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

/*
 * Copyright (C) 2014 Leon167 and XxChxppellxX 
 */
 
public class ChangeBlockListener implements Listener{

	@EventHandler
    public void onEntityChangeBlockEvent(EntityChangeBlockEvent event) {
        if (event.getEntityType() == EntityType.FALLING_BLOCK) {
            FallingBlock fallingBlock = (FallingBlock) event.getEntity();
            if (fallingBlock.getMaterial() == Material.STAINED_CLAY) {
                event.setCancelled(true);
                //event.getBlock().setType(Material.AIR);
            }
        }
    }
}
