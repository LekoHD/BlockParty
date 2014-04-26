package com.lekohd.blockparty.system;

import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.lekohd.blockparty.Main;
import com.lekohd.blockparty.level.Period;
import com.lekohd.blockparty.music.Songs;
import com.lekohd.blockparty.sign.Signs;


public class Start {

	public static int number = 31; // TODO Muss in der Config noch anpassbar sein
	public static int cd;
	public static void start(String arenaName){
		//System.out.println(Players.getPlayerAmountInLobby(arenaName));
		
		if(!Main.getArena.get(arenaName).lessThanMinimum())
		{
			if(Main.getArena.get(arenaName).getGameProgress().equalsIgnoreCase("inLobby"))
				countdown(arenaName);
		}
	}
	
	public static void message(String mes, String arenaName){
		for(Player p : Players.getPlayersInLobby(arenaName))
			p.sendMessage(mes);
	}
	
	public static void level(String arenaName, int lev){
		for(Player p : Players.getPlayersInLobby(arenaName))
			p.setLevel(lev);
	}
	
	public static void startGame(String arenaName, Player sender){
		if(Players.getPlayerAmountInLobby(arenaName) == 0 && !(sender == null))
		{
			sender.sendMessage("§3[BlockParty] §8You can not start a game with 0 players in the Lobby");
		}
		else
		{
			String song = Main.getArena.get(arenaName).getMostVotedSong();
			for(Player p : Players.getPlayersInLobby(arenaName))
			{
				p.teleport(Arena.getGameSpawn(arenaName));
				Main.inLobbyPlayers.remove(p);
				Main.inGamePlayers.put(p, arenaName);
				Main.onFloorPlayers.put(p, arenaName);
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
			Period pe = new Period();
			Period.setFloor(arenaName, true);
			pe.delayedStart(arenaName, 0);
		}
		
	}
	
	public static void telOutOfArena(String arenaName){
		for(Player p : Players.getPlayersInLobby(arenaName))
		{
			Main.inLobbyPlayers.remove(p);
			if(Main.locs.containsKey(p))
			{
				p.teleport(Main.locs.get(p));
				Main.locs.remove(p);
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
			public void run(){
				if(number!=0)
				{
					if(number != 1)
					{
						number--;
						if(Bukkit.getPluginManager().isPluginEnabled("BarAPI")){
							if(!(Players.getPlayerAmountInLobby(aName) == 1)){
								for(Player p : Players.getPlayersInLobby(aName))
								{
									BarAPI.setMessage(p, "Game starts soon", (float) number*10/3);
								}
							}
							else
							{
								BarAPI.setMessage(Players.getPlayersInLobby(aName).get(0),"Game starts soon", (float) number*10/3);
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
