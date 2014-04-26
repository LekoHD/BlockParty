package com.lekohd.blockparty.floor;

import org.bukkit.Location;
import org.bukkit.World;

/*
 * Copyright (C) 2014 Leon167 and XxChxppellxX 
 */
 
public class RandomizeFloor {
	public static int xMax, yMax, zMax;
	public static int xMin, yMin, zMin;
	
	@SuppressWarnings("deprecation")
	public static void place(String arenaName) {
		//System.out.println("In progress " + Main.getArena.get(arenaName).);
		Location locMax = FloorPoints.getMax(arenaName);
		Location locMin = FloorPoints.getMin(arenaName);
       for(int x = getxMin(arenaName, locMax, locMin); x <= getxMax(arenaName, locMax, locMin); x++) {
    	   //System.out.println("xPos: "+ x);
    	   for(int z = getzMin(arenaName, locMax, locMin); z <= getzMax(arenaName, locMax, locMin); z++) {
    		 //  System.out.println("xPos: "+ x + "zPos: " + z);
    		   int y = locMax.getBlockY();
    		  // for(int z = getzMin(arenaName, locMax, locMin); z < getzMax(arenaName, locMax, locMin); z++) {
    			  // System.out.println("xPos: "+ x + "yPos: " + y + "zPos: " + z);
                    World world = FloorPoints.getWorld(arenaName);
                    if(world == null)
                    	continue;
                    world.getBlockAt(x, y, z).setTypeId(159);
					world.getBlockAt(x, y, z).setData(randomizedData(arenaName));;
                //}
            }
        }
    }
	
	public static byte randomizedData(String arenaName){
		byte id = (byte)(Math.random()*16); 
		return id;
	}
	
	@SuppressWarnings("deprecation")
	public static byte randomizedItem(String arenaName){
		Location locMax = FloorPoints.getMax(arenaName);
		Location locMin = FloorPoints.getMin(arenaName);
		boolean found = false;
		while(found == false){
			byte id = (byte)(Math.random()*16); 
	        for(int x = getxMin(arenaName, locMax, locMin); x <= getxMax(arenaName, locMax, locMin); x++) {
	    	   //System.out.println("xPos: "+ x);
	    	   for(int z = getzMin(arenaName, locMax, locMin); z <= getzMax(arenaName, locMax, locMin); z++) {
	    		 //  System.out.println("xPos: "+ x + "zPos: " + z);
	    		   int y = locMax.getBlockY();// TODO Maybe configurable
	    		   World world = FloorPoints.getWorld(arenaName);
	    		   if(world.getBlockAt(x, y, z).getData() == id){
	    			   found = true;
	    			   return id;
	    		   }
	    	   }
	        }
		}
		
		return randomizedItem(arenaName);
		
	}
	
	/*public static void setValues(String arenaName){
		Location locMax = FloorPoints.getMax(arenaName);
		Location locMin = FloorPoints.getMin(arenaName);
		xMax = locMax.getBlockX();
		yMax = locMax.getBlockY();
		zMax = locMax.getBlockZ();
		xMin = locMin.getBlockX();
		yMin = locMin.getBlockY();
		zMin = locMin.getBlockZ();
		
		if(xMax < xMin)
		{
			xMin = locMax.getBlockX();
			xMax = locMin.getBlockX();
		}
		if(yMax < yMin)
		{
			yMin = locMax.getBlockY();
			yMax = locMin.getBlockY();
		}
		if(zMax < zMin)
		{
			zMin = locMax.getBlockZ();
			zMax = locMin.getBlockZ();
		}
	}*/
	
	public static int getxMax(String arenaName, Location locMax, Location locMin){
		if(locMax.getBlockX() < locMin.getBlockX())
		{
			return locMin.getBlockX();
		}
		else
		{
			return locMax.getBlockX();
		}
	}
	public static int getyMax(String arenaName, Location locMax, Location locMin){
		if(locMax.getBlockY() < locMin.getBlockY())
		{
			return locMin.getBlockY();
		}
		else
		{
			return locMax.getBlockY();
		}
	}
	public static int getzMax(String arenaName, Location locMax, Location locMin){
		if(locMax.getBlockZ() < locMin.getBlockZ())
		{
			return locMin.getBlockZ();
		}
		else
		{
			return locMax.getBlockZ();
		}
	}
	public static int getxMin(String arenaName, Location locMax, Location locMin){
		if(locMin.getBlockX() < locMax.getBlockX())
		{
			return locMin.getBlockX();
		}
		else
		{
			return locMax.getBlockX();
		}
	}
	public static int getyMin(String arenaName, Location locMax, Location locMin){
		if(locMin.getBlockY() < locMax.getBlockY())
		{
			return locMin.getBlockY();
		}
		else
		{
			return locMax.getBlockY();
		}
	}
	public static int getzMin(String arenaName, Location locMax, Location locMin){
		if(locMin.getBlockZ() < locMax.getBlockZ())
		{
			return locMin.getBlockZ();
		}
		else
		{
			return locMax.getBlockZ();
		}
	}

}
