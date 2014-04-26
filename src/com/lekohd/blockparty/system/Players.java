package com.lekohd.blockparty.system;


import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.lekohd.blockparty.Main;

/*
 * Copyright (C) 2014 Leon167 and XxChxppellxX 
 */
 
public class Players {

	public static ArrayList<Player> playersInGame = new ArrayList<>();
	public static ArrayList<Player> playersInLobby = new ArrayList<>();
	public static ArrayList<Player> playersOnFloor = new ArrayList<>();
	public static int playerAmountInGame = 0;
	public static int playerAmountInLobby = 0;
	public static int playerAmountOnFloor = 0;

	
	public static void getAmount(String arenaName, String loc){
		playerAmountInGame = 0;
		playerAmountInLobby = 0;
		playerAmountOnFloor = 0;
		for (Player p : Bukkit.getOnlinePlayers())
		{
			if(loc.equals("Lobby"))
			{
				if(Main.inLobbyPlayers.containsKey(p))
				{
					if(Main.inLobbyPlayers.get(p).equalsIgnoreCase(arenaName))
					{
						playerAmountInLobby++;
					}
				}
			}else if(loc.equals("Game"))
			{
				if(Main.inGamePlayers.containsKey(p))
				{
					if(Main.inGamePlayers.get(p).equalsIgnoreCase(arenaName))
					{
						playerAmountInGame++;
					}
				}
			}else if(loc.equals("Floor"))
			{
				if(Main.onFloorPlayers.containsKey(p))
				{
					if(Main.onFloorPlayers.get(p).equalsIgnoreCase(arenaName))
					{
						playerAmountOnFloor++;
					}
				}
			}
		}
	}
	
	public static void getPlayers(String arenaName, String loc){
		playersInGame.clear();
		playersInLobby.clear();
		playersOnFloor.clear();
		for (Player p : Bukkit.getOnlinePlayers())
		{
			if(Main.inLobbyPlayers.containsKey(p))
			{
				if(Main.inLobbyPlayers.get(p).equalsIgnoreCase(arenaName))
				{
					if(loc.equals("Lobby"))
					{
						if(!playersInLobby.contains(p))
							playersInLobby.add(p);
					}
				}
			}
			if(Main.inGamePlayers.containsKey(p))
			{
				if(Main.inGamePlayers.get(p).equalsIgnoreCase(arenaName))
				{
				    if(loc.equals("Game"))
					{
				    	if(!playersInGame.contains(p))
				    		playersInGame.add(p);
					}
				}
			}
			if(Main.onFloorPlayers.containsKey(p))
			{
				if(Main.onFloorPlayers.get(p).equalsIgnoreCase(arenaName))
				{
				    if(loc.equals("Floor"))
					{
				    	if(!playersOnFloor.contains(p))
				    		playersOnFloor.add(p);
					}
				}
			}
		}
	}
	public static ArrayList<Player> getPlayersInLobby(String arenaName){
		getPlayers(arenaName, "Lobby");
		return playersInLobby;
	}
	public static ArrayList<Player> getPlayersInGame(String arenaName){
		getPlayers(arenaName, "Game");
		return playersInGame;
	}
	public static ArrayList<Player> getPlayersOnFloor(String arenaName){
		getPlayers(arenaName, "Floor");
		return playersOnFloor;
	}
	
	public static int getPlayerAmountInLobby(String arenaName){
		getAmount(arenaName, "Lobby");
		return playerAmountInLobby;
	}
	public static int getPlayerAmountInGame(String arenaName){
		getAmount(arenaName, "Game");
		return playerAmountInGame;
	}
	public static int getPlayerAmountOnFloor(String arenaName){
		getAmount(arenaName, "Floor");
		return playerAmountOnFloor;
	}
	public static boolean reachedMaxPlayers(String arenaName){
		/*File arena = new File("plugins//BlockParty//Arenas//" + arenaName + ".yml");
		if(arena.exists())
		{
			FileConfiguration cfg = YamlConfiguration.loadConfiguration(arena);
			try {
				cfg.load(arena);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    if(playerAmountInLobby >= cfg.getInt("configuration.MaxPlayers"))
		    {
		    	return true;
		    }
		    else
		    {
		    	return false;
		    }
		}
		return true;*/
		return Main.getArena.get(arenaName).reachedMaxPlayers();
	}
}
