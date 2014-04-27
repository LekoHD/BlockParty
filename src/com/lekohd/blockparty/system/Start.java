package com.lekohd.blockparty.system;

import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.lekohd.blockparty.Main;
import com.lekohd.blockparty.level.Period;
import com.lekohd.blockparty.music.Songs;
import com.lekohd.blockparty.sign.Signs;

/*
 * Copyright (C) 2014 Leon167 and XxChxppellxX 
 */
 
public class Start {

	public static int number = 31; 
	public static int cd;
	public static void start(String arenaName){
		
		if(!Main.getArena.get(arenaName).lessThanMinimum())
		{
			if(Main.getArena.get(arenaName).getGameProgress().equalsIgnoreCase("inLobby"))
				countdown(arenaName);
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void message(String mes, String arenaName){
		for(String name : Players.getPlayersInLobby(arenaName))
		{
			Player p = Bukkit.getPlayer(name);
			p.sendMessage(mes);
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void level(String arenaName, int lev){
		for(String name : Players.getPlayersInLobby(arenaName))
		{
			Player p = Bukkit.getPlayer(name);
			p.setLevel(lev);
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void startGame(String arenaName, Player sender){
		if(Players.getPlayerAmountInLobby(arenaName) == 0 && !(sender == null))
		{
			sender.sendMessage("§3[BlockParty] §8You can not start a game with 0 players in the Lobby");
		}
		else
		{
			String song = Main.getArena.get(arenaName).getMostVotedSong();
			for(String name : Players.getPlayersInLobby(arenaName))
			{
				Player p = Bukkit.getPlayer(name);
				p.teleport(Arena.getGameSpawn(arenaName));
				Main.inLobbyPlayers.remove(p.getName());
				Main.inGamePlayers.put(name, arenaName);
				Main.onFloorPlayers.put(name, arenaName);
				p.sendMessage("§3[BlockParty] §8The game has started!");
				if(Bukkit.getPluginManager().isPluginEnabled("NoteBlockAPI") && Main.getArena.get(arenaName).getUseSongs())
				{
					Songs.stop(p);
					Songs.play(p, song); // TODO Voteable
				}
				Bukkit.getScheduler().cancelTask(cd);
			}
			Main.getArena.get(arenaName).setGameProgress("inGame");
			Signs.updateGameProgress(arenaName, false);
			Main.getArena.get(arenaName).setStart(false);
			Main.getArena.get(arenaName).unAbort();
			Period pe = new Period();
			Period.setFloor(arenaName, true);
			pe.delayedStart(arenaName, 0);
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public static void telOutOfArena(String arenaName){
		for(String name : Players.getPlayersInLobby(arenaName))
		{
			Player p = Bukkit.getPlayer(name);
			Main.inLobbyPlayers.remove(p.getName());
			if(Main.locs.containsKey(p.getName()))
			{
				p.teleport(Main.locs.get(p.getName()));
				Main.locs.remove(p.getName());
			}
			else
			{
				p.sendMessage("§3[BlockParty] §8Cannot load your old location. Please type /spawn");
			}
		}
	}
	
	public static void countdown(String arenaName){
		final String aName = arenaName;
		number = Main.getArena.get(arenaName).getCountdown();
		level(aName, number);
        cd = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
			@SuppressWarnings("deprecation")
			public void run(){
				if(number!=0)
				{
					if(number != 1)
					{
						number--;
						if(Bukkit.getPluginManager().isPluginEnabled("BarAPI")){
							if(!(Players.getPlayerAmountInLobby(aName) == 1)){
								for(String name : Players.getPlayersInLobby(aName))
								{
									Player p = Bukkit.getPlayer(name);
									BarAPI.setMessage(p, "Game starts soon", (float) number*10/3);
								}
							}
							else
							{
								BarAPI.setMessage(Bukkit.getPlayer(Players.getPlayersInLobby(aName).get(0)),"Game starts soon", (float) number*10/3);
							}
						}
						if(number == 20 || number == 30)
							message("§3[BlockParty] §8" + number + " seconds left!", aName);
						level(aName, number);
						if(number < 11)
							message("§3[BlockParty] §8" + number + " seconds left!", aName);
						if(Main.getArena.get(aName).lessThanMinimum())
						{
							message("§3[BlockParty] §8Less Players than the minimum!", aName);
							//telOutOfArena(aName);
							start(aName);
							Bukkit.getScheduler().cancelTask(cd);
						}
						if(Main.getArena.get(aName).aborted())
						{
							Bukkit.getScheduler().cancelTask(cd);
							Main.getArena.get(aName).unAbort();
							startGame(aName, null);
						}
					}
					else
					{
						Bukkit.getScheduler().cancelTask(cd);
						startGame(aName, null);
					}
				}
				else
				{
					Bukkit.getScheduler().cancelTask(cd);
					System.err.println("[BlockParty] ERROR: The countdown is less than 1");
				}
			}
		}, 0L, 20L);
	}
	
}
