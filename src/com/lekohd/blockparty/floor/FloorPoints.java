package com.lekohd.blockparty.floor;

import org.bukkit.Location;
import org.bukkit.World;
import com.lekohd.blockparty.Main;

/*
 * Copyright (C) 2014 Leon167 and XxChxppellxX 
 */
 
public class FloorPoints {

	public static Location getMin(String arenaName){
		return Main.getArena.get(arenaName).getLocMin();
	}
	public static Location getMax(String arenaName){
		return Main.getArena.get(arenaName).getLocMax();
	}
	
	public static void set(Location min, Location max, String arenaName){
		Main.getArena.get(arenaName).set(min, max);
		
	}
	
	public static World getWorld(String arenaName){
		return Main.getArena.get(arenaName).getWorld();
	}
	
}
