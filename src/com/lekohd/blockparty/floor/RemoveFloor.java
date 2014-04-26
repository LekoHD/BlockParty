package com.lekohd.blockparty.floor;

import com.lekohd.blockparty.Main;

public class RemoveFloor {
	public static void add(String arenaName, String floorName){
		Main.floors.clear();
		if(Main.getFloor.get(arenaName).size()==1){
			Main.floors.add(Main.getFloor.get(arenaName).get(0));
		}
		if(Main.getFloor.get(arenaName).size()>1){
			for(LoadFloor f : Main.getFloor.get(arenaName)){
				Main.floors.add(f);
			}
		}
		Main.floors.remove(floorName);
		Main.getFloor.put(arenaName, Main.floors);
		Main.getArena.get(arenaName).removeFloor(floorName);
	}
}
