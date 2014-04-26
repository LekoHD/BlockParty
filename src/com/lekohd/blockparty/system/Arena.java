package com.lekohd.blockparty.system;

import java.util.ArrayList;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.lekohd.blockparty.Main;
import com.lekohd.blockparty.floor.LoadFloor;

public class Arena {
	public static void create(Player p, String arenaName){
		/*File arena = new File("plugins//BlockParty//Arenas//" + arenaName + ".yml");
		if(arena.exists())
		{
			p.sendMessage("§3[BlockParty] §8Arena " + arenaName + " already exists!");
		}
		else
		{
			FileConfiguration cfg = YamlConfiguration.loadConfiguration(arena);
			cfg.set("configuration.MinPlayers", 4);
			cfg.set("configuration.MaxPlayers", 15);
			cfg.set("configuration.Countdown", 30);
			cfg.set("configuration.AutoGenerateFloors", true);
			cfg.set("configuration.UseSchematicFloors", false);
			cfg.set("configuration.floor.length", 2);
			cfg.set("configuration.floor.width", 2);
			cfg.set("configuration.EnabledFloors", Main.floors);
			try {
				cfg.save(arena);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			p.sendMessage("§3[BlockParty] §8Arena " + arenaName + " was successfully created!");
		}*/
		if(!Main.getArena.containsKey(arenaName))
		{
			Config conf = new Config(arenaName);
			p.sendMessage(conf.create());
			Main.getArena.put(arenaName, conf);
		}
		else
		{
			p.sendMessage("§3[BlockParty] §8Arena " + arenaName + " already exists!");
		}
	}
	
	public static void setSpawn(Player p, String arenaName, String pos){
		/*File arena = new File("plugins//BlockParty//Arenas//" + arenaName + ".yml");
		if(!arena.exists())
		{
			p.sendMessage("§3[BlockParty] §8Arena " + arenaName + " doesn't exists!");
		}
		else
		{
			FileConfiguration cfg = YamlConfiguration.loadConfiguration(arena);
			Location loc = p.getLocation();
			cfg.set("spawns." + pos + ".xPos", loc.getX());
			cfg.set("spawns." + pos + ".yPos", loc.getY());
			cfg.set("spawns." + pos + ".zPos", loc.getZ());
			cfg.set("spawns." + pos + ".Yaw", loc.getYaw());
			cfg.set("spawns." + pos + ".Pitch", loc.getPitch());
			cfg.set("spawns." + pos + ".World", loc.getWorld().getName());
			if(pos.equalsIgnoreCase("Lobby")){
				if(cfg.getBoolean("dontChange.Game")){
					cfg.set("dontChange." + pos, true);
					cfg.set("dontChange.Game", true);
				}
				else
				{
					cfg.set("dontChange." + pos, true);
					cfg.set("dontChange.Game", false);
				}
			}
			else
			{
				if(cfg.getBoolean("dontChange.Lobby")){
					cfg.set("dontChange." + pos, true);
					cfg.set("dontChange.Lobby", true);
				}
				else
				{
					cfg.set("dontChange." + pos, true);
					cfg.set("dontChange.Lobby", false);
				}
			}
			cfg.set("dontChange." + pos, true);
			try {
				cfg.save(arena);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			p.sendMessage("§3[BlockParty] §8" + pos + " Spawn was set for Arena " + arenaName);
		}*/
		if(Main.getArena.containsKey(arenaName))
		{
			p.sendMessage(Main.getArena.get(arenaName).setSpawn(p, pos));
		}
		else
		{
			p.sendMessage("§3[BlockParty] §8Arena " + arenaName + " doesn't exists!");
		}
	}
	public static void delete(String arenaName, Player p){
		/*File arena = new File("plugins//BlockParty//Arenas//" + arenaName + ".yml");
		if(arena.exists())
		{
			arena.delete();
			p.sendMessage("§3[BlockParty] §8Arena " + arenaName + " was successfully deleted!");
		}
		else
		{
			p.sendMessage("§3[BlockParty] §8Arena " + arenaName + " doesn't exists!");
		}*/
		if(Main.getArena.containsKey(arenaName))
		{
			p.sendMessage(Main.getArena.get(arenaName).delete());
			Main.getArena.remove(arenaName);
		}
		else
		{
			p.sendMessage("§3[BlockParty] §8Arena " + arenaName + " doesn't exists!");
		}
	}
	
	/*public static boolean exists(String arenaName, Player p, File arena)
	{
		if(arena.exists())
		{
			FileConfiguration cfg = YamlConfiguration.loadConfiguration(arena);
			if((cfg.getBoolean("dontChange.Lobby")) && (cfg.getBoolean("dontChange.Game")))
			{
				if(cfg.getBoolean("floor.floorPoints"))
				{
					return true;
				}
				else
				{
					p.sendMessage("§3[BlockParty] §8You have to set the floor first!");
					return false;
				}
				
			}
			else
			{
				p.sendMessage("§3[BlockParty] §8You have to set all spawns first!");
				return false;
			}
		}
		else
		{
			p.sendMessage("§3[BlockParty] §8Arena " + arenaName + " doesn't exists!");
			return false;
		}
	}*/
	
	public static void join(Player p, String arenaName){
		/*if(!Main.inLobbyPlayers.containsKey(p) && !(Main.inGamePlayers.containsKey(p)))
		{
			File arena = new File("plugins//BlockParty//Arenas//" + arenaName + ".yml");
			if(Arena.exists(arenaName, p, arena))
			{
				Location loc;
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
				if(!Players.reachedMaxPlayers(arenaName))
				{
					double xPos = (double) cfg.get("spawns.Lobby.xPos");
					double yPos = (double) cfg.get("spawns.Lobby.yPos");
					double zPos = (double) cfg.get("spawns.Lobby.zPos");
					double yaw = (double) cfg.get("spawns.Lobby.Yaw");
					double pitch = (double) cfg.get("spawns.Lobby.Pitch");
					String world = (String) cfg.get("spawns.Lobby.World");
					loc = new Location(Bukkit.getWorld(world), xPos, yPos, zPos, (float) yaw, (float) pitch);
					p.teleport(loc);
					Main.inLobbyPlayers.put(p, arenaName);
					p.sendMessage("§3[BlockParty] §8You have joined Arena " + arenaName);
				}
				else
				{
					p.sendMessage("§3[BlockParty] §8The Arena " + arenaName + " is full!");
				}
			}
		} else {
			p.sendMessage("§3[BlockParty] §8You are already in an arena!");
		}*/
		if(Main.getArena.containsKey(arenaName))
		{
			Main.getArena.get(arenaName).join(p);
			p.setGameMode(GameMode.ADVENTURE);
		}
		else
		{
			p.sendMessage("§3[BlockParty] §8Arena " + arenaName + " doesn't exists!");
		}
	}
	
	public static Location getGameSpawn(String arenaName){
		/*File arena = new File("plugins//BlockParty//Arenas//" + arenaName + ".yml");
		if(arena.exists())
		{
			Location loc;
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
			double xPos = (double) cfg.get("spawns.Game.xPos");
			double yPos = (double) cfg.get("spawns.Game.yPos");
			double zPos = (double) cfg.get("spawns.Game.zPos");
			double yaw = (double) cfg.get("spawns.Game.Yaw");
			double pitch = (double) cfg.get("spawns.Game.Pitch");
			String world = (String) cfg.get("spawns.Game.World");
			loc = new Location(Bukkit.getWorld(world), xPos, yPos, zPos, (float) yaw, (float) pitch);
		    return loc;
		}
		return null;*/
		if(Main.getArena.containsKey(arenaName))
		{
			return Main.getArena.get(arenaName).getGameSpawn();
		}
		else
		{
			return null;
		}
	}

	public static Location getLobbySpawn(String arenaName) {
		/*File arena = new File("plugins//BlockParty//Arenas//" + arenaName + ".yml");
		if(arena.exists())
		{
			Location loc;
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
			double xPos = (double) cfg.get("spawns.Lobby.xPos");
			double yPos = (double) cfg.get("spawns.Lobby.yPos");
			double zPos = (double) cfg.get("spawns.Lobby.zPos");
			double yaw = (double) cfg.get("spawns.Lobby.Yaw");
			double pitch = (double) cfg.get("spawns.Lobby.Pitch");
			String world = (String) cfg.get("spawns.Lobby.World");
			loc = new Location(Bukkit.getWorld(world), xPos, yPos, zPos, (float) yaw, (float) pitch);
		    return loc;
		}
		return null;*/
		if(Main.getArena.containsKey(arenaName))
		{
			return Main.getArena.get(arenaName).getLobbySpawn();
		}
		else
		{
			return null;
		}
	}
	
	
	public static void enable(String arenaName, Player p){
		  Config conf = new Config(arenaName);
		  if(conf.ex()){
			  conf.enable();
			  conf.loadCfg();
			  conf.loadGameSpawn();
			  conf.loadLobbySpawn();
			  conf.loadMinPlayers();
			  conf.loadMax();
			  conf.loadMin();
			  conf.load();
			  conf.enable();
			  Main.getArena.put(arenaName, conf);
			  if(!(conf.getFloors()  == null)){
				  for(String floor : conf.getFloors())
				  {
					  Main.floors.clear();
					  Main.floors.add(new LoadFloor(floor));
					  Main.getFloor.put(arenaName, Main.floors);
				  }	
		  		}
			  FileConfiguration cfg = Main.getInstance().getConfig();
			  cfg.options().copyDefaults(true);
			  if(!Main.arenaNames.contains(arenaName))
				  {
				  	Main.arenaNames.add(arenaName);
				  	cfg.set("enabledArenas", Main.arenaNames);
				  }
			  Main.getInstance().saveConfig();
			  p.sendMessage("§3[BlockParty] §8Arena " + arenaName + " enabled!");
		  }
		  else
		  {
			  p.sendMessage("§3[BlockParty] §8Arena " + arenaName + " doesn't exists!");
		  }
	}
	
	public static void disable(String arenaName, Player p){
		if(Main.getArena.containsKey(arenaName)){
		  if(Main.getArena.get(arenaName).ex()){
			  Main.getArena.get(arenaName).disable();
			  Main.getArena.remove(arenaName);
			  FileConfiguration cfg = Main.getInstance().getConfig();
			  cfg.options().copyDefaults(true);
			  if(Main.arenaNames.contains(arenaName))
				  {
				  	Main.arenaNames.remove(arenaName);
				  	cfg.set("enabledArenas", Main.arenaNames);
				  }
			  Main.getInstance().saveConfig();
			  p.sendMessage("§3[BlockParty] §8Arena " + arenaName + " disabled!");
		  }
		  else
		  {
			  p.sendMessage("§3[BlockParty] §8Arena " + arenaName + " doesn't exists!");
		  }
		}
		else
		{
			p.sendMessage("§3[BlockParty] §8Arena " + arenaName + " doesn't exists!");
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void reload(Player p){
		  Main.loadConfig();
		  FileConfiguration cfg = Main.getInstance().getConfig();
		  Main.arenaNames.clear();
		  Main.arenaNames = (ArrayList<String>) cfg.get("enabledArenas");
			  if(!(Main.arenaNames.isEmpty()))
			  {
				  for(String name : Main.arenaNames){
					  Config conf = new Config(name);
					  conf.enable();
					  conf.loadCfg();
					  conf.loadGameSpawn();
					  conf.loadLobbySpawn();
					  conf.loadMinPlayers();
					  conf.loadMax();
					  conf.loadMin();
					  conf.load();
					  Main.getArena.put(name, conf);
					  if(!(conf.getFloors()  == null)){
						  for(String floor : conf.getFloors())
						  {
							  Main.floors.clear();
							  Main.floors.add(new LoadFloor(floor));
							  Main.getFloor.put(name, Main.floors);
						  }	
				  		}
				 }
			  }
				  p.sendMessage("§3[BlockParty] §8reloaded!");
		  
	}
	
}
