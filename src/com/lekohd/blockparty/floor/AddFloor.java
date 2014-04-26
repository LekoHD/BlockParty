package com.lekohd.blockparty.floor;

import com.lekohd.blockparty.Main;

/*
 * Copyright (C) 2014 Leon167 and XxChxppellxX 
 */
 
public class AddFloor {

	public static void add(String arenaName, String floorName){
		Main.floors.clear();
		LoadFloor lf = new LoadFloor(floorName);
		Main.floors.add(lf);
		Main.getArena.get(arenaName).addFloor(floorName);
		if(Main.getFloor.get(arenaName).size()>1){
			for(LoadFloor f : Main.getFloor.get(arenaName)){
				Main.floors.add(f);
			}
		}
		if(Main.getFloor.get(arenaName).size()==1){
			Main.floors.add(Main.getFloor.get(arenaName).get(0));
		}
		
		Main.getFloor.put(arenaName, Main.floors);
		Main.getArena.get(arenaName).addFloor(floorName);
	}
	
}
