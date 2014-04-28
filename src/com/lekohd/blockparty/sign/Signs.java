package com.lekohd.blockparty.sign;

import com.lekohd.blockparty.BlockParty;

public class Signs {

/*
 * Copyright (C) 2014 Leon167 and XxChxppellxX 
 */
 
	public static void updateJoin(String arenaName, boolean full){
		if(BlockParty.signs.containsKey(arenaName)){
			if(full)
				{
					BlockParty.signs.get(arenaName).setLine(3, "§4Full");
				}
			if(!full)
				{
					BlockParty.signs.get(arenaName).setLine(3, "§2Join");
				}
		}
		
	}
	public static void updateGameProgress(String arenaName, boolean inLobby){
		if(BlockParty.signs.containsKey(arenaName)){
			if(inLobby)
				{
					BlockParty.signs.get(arenaName).setLine(1, "§2In Lobby");
				}
			if(!inLobby)
				{
					BlockParty.signs.get(arenaName).setLine(1, "§4In Game");
				}
		}
	}
}
