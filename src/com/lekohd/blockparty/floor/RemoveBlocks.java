package com.lekohd.blockparty.floor;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import com.lekohd.blockparty.BlockParty;

/*
 * Copyright (C) 2014 Leon167 and XxChxppellxX 
 */
 
public class RemoveBlocks {

	
	@SuppressWarnings("deprecation")
	public static void remove(String arenaName, Byte value) {
		//System.out.println("In progress " + Main.getGameProgress());
		Location locMax = FloorPoints.getMax(arenaName);
		Location locMin = FloorPoints.getMin(arenaName);
		boolean fall = BlockParty.getArena.get(arenaName).getFallingBlocks();
       for(int x = RandomizeFloor.getxMin(arenaName, locMax, locMin); x <= RandomizeFloor.getxMax(arenaName, locMax, locMin); x++) {
    	   //System.out.println("xPos: "+ x);
    	   for(int z = RandomizeFloor.getzMin(arenaName, locMax, locMin); z <= RandomizeFloor.getzMax(arenaName, locMax, locMin); z++) {
    		 //  System.out.println("xPos: "+ x + "zPos: " + z);
    		   int y = RandomizeFloor.getyMax(arenaName, locMax, locMin);
    		  // for(int z = getzMin(arenaName, locMax, locMin); z < getzMax(arenaName, locMax, locMin); z++) {
    			  // System.out.println("xPos: "+ x + "yPos: " + y + "zPos: " + z);
                    World world = FloorPoints.getWorld(arenaName);
                    if(world == null)
                    	continue;
                    if(!(world.getBlockAt(x, y, z).getData()==(value)))
                    {
	                    Location location = new Location(world, x, y, z);
	                    Material ma = world.getBlockAt(x, y, z).getType();
                    	world.getBlockAt(x, y, z).setType(Material.AIR);
                    	Byte blockData = world.getBlockAt(x, y, z).getData();
                    	if(fall)
                    		world.spawnFallingBlock(location, ma, blockData).setFallDistance(7);
                    }
            }
        }
    }
}
