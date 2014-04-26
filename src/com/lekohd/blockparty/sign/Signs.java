package com.lekohd.blockparty.sign;

import com.lekohd.blockparty.Main;

public class Signs {

/*
 * Copyright (C) 2014 Leon167 and XxChxppellxX 
 */
 
	public static void updateJoin(String arenaName, boolean full){
		if(Main.signs.containsKey(arenaName)){
			if(full)
				{
					Main.signs.get(arenaName).setLine(3, "§4Full");
				}
			if(!full)
				{
					Main.signs.get(arenaName).setLine(3, "§2Join");
				}
		}
		
	}
	public static void updateGameProgress(String arenaName, boolean inLobby){
		if(Main.signs.containsKey(arenaName)){
			if(inLobby)
				{
					Main.signs.get(arenaName).setLine(1, "§2In Lobby");
				}
			if(!inLobby)
				{
					Main.signs.get(arenaName).setLine(1, "§4In Game");
				}
		}
	}
}
