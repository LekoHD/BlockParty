package com.lekohd.blockparty.floor;

import org.bukkit.Location;
import org.bukkit.World;
import com.lekohd.blockparty.BlockParty;

/*
 * Copyright (C) 2014 Leon167 and XxChxppellxX 
 */
 
public class FloorPoints {

	public static Location getMin(String arenaName){
		return BlockParty.getArena.get(arenaName).getLocMin();
	}
	public static Location getMax(String arenaName){
		return BlockParty.getArena.get(arenaName).getLocMax();
	}
	
	public static void set(Location min, Location max, String arenaName){
		BlockParty.getArena.get(arenaName).set(min, max);
		
	}
	
	public static World getWorld(String arenaName){
		return BlockParty.getArena.get(arenaName).getWorld();
	}
	
}
