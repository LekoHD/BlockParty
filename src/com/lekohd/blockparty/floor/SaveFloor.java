package com.lekohd.blockparty.floor;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.lekohd.blockparty.BlockParty;

/*
 * Copyright (C) 2014 Leon167 and XxChxppellxX 
 */
 
public class SaveFloor {

	public static void setFloor(Player p, String arenaName){
		File arena = new File("plugins//BlockParty//Arenas//" + arenaName + ".yml");
		if(!arena.exists())
		{
			p.sendMessage("§3[BlockParty] §8Arena " + arenaName + " doesn't exists!");
		}
		else
		{
			FileConfiguration cfg = YamlConfiguration.loadConfiguration(arena);
			if(checkConditions(p, arenaName)){
				try {
					cfg.save(arena);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static boolean checkConditions(Player p, String arenaName){
		
		if(!(BlockParty.getArena.get(arenaName) == null)){
		if(BlockParty.getArena.get(arenaName).checkConditions(p))
			return true;
		
		return false;
		}
		return false;
	}
	
}




	
