package com.lekohd.blockparty.floor;

import com.lekohd.blockparty.BlockParty;

/*
 * Copyright (C) 2014 Leon167 and XxChxppellxX 
 */
 
public class AddFloor {

	public static void add(String arenaName, String floorName){
		BlockParty.floors.clear();
		LoadFloor lf = new LoadFloor(floorName);
		BlockParty.floors.add(lf);
		BlockParty.getArena.get(arenaName).addFloor(floorName);
		if(BlockParty.getFloor.get(arenaName).size()>1){
			for(LoadFloor f : BlockParty.getFloor.get(arenaName)){
				BlockParty.floors.add(f);
			}
		}
		if(BlockParty.getFloor.get(arenaName).size()==1){
			BlockParty.floors.add(BlockParty.getFloor.get(arenaName).get(0));
		}
		
		BlockParty.getFloor.put(arenaName, BlockParty.floors);
		BlockParty.getArena.get(arenaName).addFloor(floorName);
	}
	
}
