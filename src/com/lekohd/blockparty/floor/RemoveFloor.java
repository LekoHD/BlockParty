package com.lekohd.blockparty.floor;

import com.lekohd.blockparty.BlockParty;

/*
 * Copyright (C) 2014 Leon167 and XxChxppellxX 
 */
 
public class RemoveFloor {
	public static void add(String arenaName, String floorName){
		BlockParty.floors.clear();
		if(BlockParty.getFloor.get(arenaName).size()==1){
			BlockParty.floors.add(BlockParty.getFloor.get(arenaName).get(0));
		}
		if(BlockParty.getFloor.get(arenaName).size()>1){
			for(LoadFloor f : BlockParty.getFloor.get(arenaName)){
				BlockParty.floors.add(f);
			}
		}
		BlockParty.floors.remove(floorName);
		BlockParty.getFloor.put(arenaName, BlockParty.floors);
		BlockParty.getArena.get(arenaName).removeFloor(floorName);
	}
}
