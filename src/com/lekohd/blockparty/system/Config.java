package com.lekohd.blockparty.system;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.lekohd.blockparty.BlockParty;
import com.lekohd.blockparty.floor.FloorPoints;
import com.lekohd.blockparty.sign.Signs;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;

/*
 * Copyright (C) 2014 Leon167 and XxChxppellxX 
 */
 
public class Config {

	public File arena;
	public String arenaName;
	public FileConfiguration cfg;
	public Location gameSpawn, lobbySpawn;
	public Location locMax, locMin;
	public int lessMinimum;
	public World world;
	public int floorLength, floorWidth;
	public int minPlayers, maxPlayers, countdown, timeToSearch, level, boostDuration;
	public double timeReductionPerLevel;
	public boolean autoGenerateFloors, useSchematicFloors;
	public boolean isEnabled = false;
	public boolean useBoosts=true;
	public boolean fallingBlock = true;
	public boolean useSongs = true;
	public ArrayList<String> enabledFloors = new ArrayList<>();
	public ArrayList<Integer> rewardItems = new ArrayList<>();
	public ArrayList<String> songs = new ArrayList<>();
	public int outBlock;
	public HashMap<String, Integer> votedSongs = new HashMap<>();

	
	public String getMostVotedSong(){
		String song = null;
		if(votedSongs.size() == 0)
		{
			Random r = new Random();
			int ri = r.nextInt(songs.size());
			votedSongs.clear();
			return songs.get(ri);
		}
		else if(votedSongs.size() == 1)
		{
			if(songs.size()>1){
				for (String name : songs){
					if(votedSongs.containsKey(name))
					{
						votedSongs.clear();
						return name;
					}
				}
			}
			else
			{
				votedSongs.clear();
				return songs.get(0);
			}
		}
		else if(votedSongs.size() > 1)
		{
			for (String name : songs){
				if(votedSongs.containsKey(name))
				{
					if(song == null){
						song = name;
						continue;
					}
					if(votedSongs.get(name) > votedSongs.get(song))
						song = name;
				}
			}
			votedSongs.clear();
			return song;
		}
		votedSongs.clear();
		return null;
	}
	
	public void vote(String song){
		int value = 0;
		if(votedSongs.containsKey(song))
		{
			value = votedSongs.get(song);
			votedSongs.put(song, value + 1);
		}
		else
		{
			votedSongs.put(song, 1);
		}
	}
	
	public Location getGameSpawn(){
		return gameSpawn;
	}
	
	public Location getLobbySpawn(){
		return lobbySpawn;
	}
	
	public Config(String aName){
		arenaName=aName;
		arena = new File("plugins//BlockParty//Arenas//" + arenaName + ".yml");
	}
	
	public void loadCfg(){
		if(!arena.exists())
		{
			System.err.print("ERROR in BlockParty: Arena " + arenaName + " doesn't exists! Please check your configs!");
		}
		else
		{
			cfg = YamlConfiguration.loadConfiguration(arena);
		}
	}
	
	public String create(){
		if(arena.exists())
		{
			return "§3[BlockParty] §8Arena " + arenaName + " already exists!";
		}
		else
		{
			cfg = YamlConfiguration.loadConfiguration(arena);
			cfg.set("configuration.MinPlayers", 2);
			cfg.set("configuration.MaxPlayers", 15);
			cfg.set("configuration.Countdown", 30);
			cfg.set("configuration.OutBlock", 7);
			cfg.set("configuration.TimeToSearch", 8);
			cfg.set("configuration.TimeReductionPerLevel", 0.5);
			cfg.set("configuration.Level", 15);
			cfg.set("configuration.UseBoosts", true);
			cfg.set("configuration.BoostDuration", 10);
			cfg.set("configuration.FallingBlocks", true);
			cfg.set("configuration.AutoGenerateFloors", true);
			cfg.set("configuration.UseSchematicFloors", true);
			enabledFloors.add("example");
			cfg.set("configuration.EnabledFloors", enabledFloors);
			rewardItems.add(264);
			cfg.set("configuration.RewardItems", rewardItems);
			cfg.set("configuration.UseSongs", true);
			songs.add("Hold The Line");
			cfg.set("configuration.Songs", songs);
			cfg.set("dontChange.Game", false);
			cfg.set("dontChange.Lobby", false);
			cfg.set("floor.floorPoints", false);
			
			try {
				cfg.save(arena);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.load();
			return "§3[BlockParty] §8Arena " + arenaName + " was successfully created!";
		}
	}
	public String setSpawn(Player p, String pos){
		if(isEnabled){
		if(!arena.exists())
		{
			return "§3[BlockParty] §8Arena " + arenaName + " doesn't exists!";
		}
		else
		{
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
				lobbySpawn=loc;
				world = Bukkit.getWorld(cfg.getString("spawns.Lobby.World"));
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
				gameSpawn=loc;
				world = Bukkit.getWorld(cfg.getString("spawns.Game.World"));
			}
			cfg.set("dontChange." + pos, true);
			try {
				cfg.save(arena);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return ("§3[BlockParty] §8" + pos + " Spawn was set for Arena " + arenaName);
		}
		}
		return ("§3[BlockParty] §8Arena is disabled. /bp enable " + arenaName + " to enable!");
	}
	
	public String delete(){
		if(arena.exists())
		{
			arena.delete();
			return "§3[BlockParty] §8Arena " + arenaName + " was successfully deleted!";
		}
		else
		{
			return "§3[BlockParty] §8Arena " + arenaName + " doesn't exists!";
		}
	}
	
	public boolean exists(Player p)
	{
		if(arena.exists())
		{
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
	}
	
	public void loadGameSpawn(){
		
			if(isEnabled){
				if(arena.exists())
				{
					if (cfg.getBoolean("dontChange.Game")){
					Location loc;
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
					String w = (String) cfg.get("spawns.Game.World");
					loc = new Location(Bukkit.getWorld(w), xPos, yPos, zPos, (float) yaw, (float) pitch);
					world = Bukkit.getWorld(cfg.getString("spawns.Game.World"));
				    gameSpawn=loc;
				}
				else
				{
					gameSpawn=null;
				}
			}
		}
	}

	public void loadLobbySpawn() {
		
			if(isEnabled){
				if(arena.exists())
				{
					if (cfg.getBoolean("dontChange.Lobby")){
					Location loc;
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
					String w = (String) cfg.get("spawns.Lobby.World");
					loc = new Location(Bukkit.getWorld(w), xPos, yPos, zPos, (float) yaw, (float) pitch);
					world = Bukkit.getWorld(cfg.getString("spawns.Game.World"));
				    lobbySpawn=loc;
				}
				else
				{
					lobbySpawn=null;
				}
			}
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ItemStack getVoteItem(){
		ItemStack item = new ItemStack(Material.FIREBALL, 1);
	    ItemMeta meta = item.getItemMeta();
	    meta.setDisplayName("§6Vote for a Song!");
		List lores = new ArrayList();
	    lores.add("Click me!");
	    meta.setLore(lores);
	    item.setItemMeta(meta);
	    return item;
	}
	
	@SuppressWarnings("deprecation")
	public void join(Player p){
		if(isEnabled){
		if(!BlockParty.inLobbyPlayers.containsKey(p.getName()) && !(BlockParty.inGamePlayers.containsKey(p.getName())))
		{
			if(this.exists(p))
			{
				if(!Players.reachedMaxPlayers(arenaName))
				{
					if(gameProgress.equalsIgnoreCase("inLobby") || gameProgress.equalsIgnoreCase("Countdown"))
					{
						p.teleport(lobbySpawn);
						BlockParty.inLobbyPlayers.put(p.getName(), arenaName);
						BlockParty.inv.put(p.getName(), p.getInventory().getContents());
						p.getInventory().clear();
						p.getInventory().addItem(this.getVoteItem());
						p.updateInventory();
						Start.start(arenaName);
						Signs.updateJoin(arenaName, false);
						if(Bukkit.getPluginManager().isPluginEnabled("BarAPI")){
							BarAPI.setMessage(p,"Waiting ...", (float) 100);
						}
						p.sendMessage("§3[BlockParty] §8You have joined Arena " + arenaName);
						if(Players.getPlayerAmountInLobby(arenaName) <= 1){
							Bukkit.getPlayer(Players.getPlayersInLobby(arenaName).get(0)).sendMessage("§3[BlockParty] §8" + p.getName() + " joined the game");
						}
						else
						{
							for (String name : Players.getPlayersInLobby(arenaName)){
								Player player = Bukkit.getPlayer(name);
								player.sendMessage("§3[BlockParty] §8" + p.getName() + " joined the game");
							}
						}
					}
					else
					{
						p.sendMessage("§3[BlockParty] §8You can not join during a game");
					}
				}
				else
				{
					Signs.updateJoin(arenaName, true);
					p.sendMessage("§3[BlockParty] §8The Arena " + arenaName + " is full!");
				}
			}
		} else {
			p.sendMessage("§3[BlockParty] §8You are already in an arena!");
		}
		}
		else
		{
			p.sendMessage("§3[BlockParty] §8Arena is disabled. /bp enable " + arenaName + " to enable!");
		}
	}
	
	  public boolean lessThanMinimum(){
		  if(Players.getPlayerAmountInLobby(arenaName) >= lessMinimum)
		  {
			  return false;
		  }
		  return true;
	  }
	  
	  public void loadMinPlayers(){
		  if(isEnabled){
			if(arena.exists())
			{
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
				lessMinimum = cfg.getInt("configuration.MinPlayers");
			}
		  }
	  }
	  
	  public void loadMin(){
		  
			  if(isEnabled){
				if(!arena.exists())
				{
					System.out.println("ERROR: Arena " + arenaName + " doesn't exists!");
				}
				else
				{
					if (cfg.getBoolean("floor.floorPoints")){
					World world = Bukkit.getWorld("floor.min.World");
					double xPos = (double) cfg.get("floor.min.xPos");
					double yPos = (double) cfg.get("floor.min.yPos");
					double zPos = (double) cfg.get("floor.min.zPos");
					Location loc = new Location(world, xPos, yPos, zPos);
					this.locMin = loc;
				}
			  }
		  }
		}
		public void loadMax(){
			
				if(isEnabled){
					if(!arena.exists())
					{
						System.out.println("ERROR: Arena " + arenaName + " doesn't exists!");
					}
					else
					{
						if (cfg.getBoolean("floor.floorPoints")){
						World world = Bukkit.getWorld("floor.max.World");
						double xPos = (double) cfg.get("floor.max.xPos");
						double yPos = (double) cfg.get("floor.max.yPos");
						double zPos = (double) cfg.get("floor.max.zPos");
						Location loc = new Location(world, xPos, yPos, zPos);
						this.locMax = loc;
					}
				}
			}
		}
		
		public void set(Location min, Location max){
			cfg.set("floor.min.xPos", min.getX());
			cfg.set("floor.min.yPos", min.getY());
			cfg.set("floor.min.zPos", min.getZ());
			cfg.set("floor.min.World", min.getWorld().getName());
			
			cfg.set("floor.max.xPos", max.getX());
			cfg.set("floor.max.yPos", max.getY());
			cfg.set("floor.max.zPos", max.getZ());
			cfg.set("floor.max.World", max.getWorld().getName());
			
			cfg.set("floor.floorPoints", true);
			
			try {
				cfg.save(arena);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.loadMax();
			this.loadMin();
		}
		
		public Location getLocMin(){
			return locMin;
		}
		
		public Location getLocMax(){
			return locMax;
		}
		
		public World getWorld(){
			//World world = Bukkit.getWorld(cfg.getString("spawns.Game.World"));
			return world;
		}
		
		
		public boolean checkConditions(Player p){
			
			WorldEditPlugin worldEdit = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
			Selection selection = worldEdit.getSelection(p);
			 
			if (selection != null) {
			    World world = selection.getWorld();
			    if(selection.getHeight() == 1)
			    {

			    	if(world.equals(this.getWorld()))
					{
						Location min = selection.getMinimumPoint();
						Location max = selection.getMaximumPoint();
						FloorPoints.set(min, max, arenaName);
						cfg.set("configuration.floor.length", selection.getLength());
						cfg.set("configuration.floor.width", selection.getWidth());
						floorLength = selection.getLength();
						floorWidth = selection.getWidth();
						//this.set(min, max);
						p.sendMessage("§3[BlockParty] §8Floor was set for Arena " + arenaName); 
						return true;
					}
			    	else
			    	{
			    		p.sendMessage("§3[BlockParty] §8Arena and Floor must be in the same world");
			    	}
					    	
			    }
				else
				{
				p.sendMessage("§3[BlockParty] §8The Floor must be 1 block high!");
				}
			} else {
				p.sendMessage("§3[BlockParty] §8Select a region with WorldEdit first.");
			}
			return false;
		}
		
		@SuppressWarnings("unchecked")
		public void load(){
			if(isEnabled){
			if(!arena.exists())
			{
				//System.out.println("ERROR: Arena " + arenaName + " doesn't exists!");
			}
			else
			{
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
				enabledFloors.clear();
				rewardItems.clear();
				floorLength = cfg.getInt("configuration.floor.length");
				floorWidth = cfg.getInt("configuration.floor.width");
				minPlayers = cfg.getInt("configuration.MinPlayers");
				maxPlayers = cfg.getInt("configuration.MaxPlayers");
				countdown = cfg.getInt("configuration.Countdown");
				outBlock = cfg.getInt("configuration.OutBlock");
				timeToSearch = cfg.getInt("configuration.TimeToSearch");
				timeReductionPerLevel = cfg.getDouble("configuration.TimeReductionPerLevel");
				level = cfg.getInt("configuration.Level");
				useBoosts = cfg.getBoolean("configuration.UseBoosts");
				boostDuration = cfg.getInt("configuration.BoostDuration");
				fallingBlock = cfg.getBoolean("configuration.FallingBlocks");
				autoGenerateFloors = cfg.getBoolean("configuration.AutoGenerateFloors");
				useSchematicFloors = cfg.getBoolean("configuration.UseSchematicFloors");
				enabledFloors = (ArrayList<String>) cfg.get("configuration.EnabledFloors");
				rewardItems = (ArrayList<Integer>) cfg.get("configuration.RewardItems");
				songs = (ArrayList<String>) cfg.get("configuration.Songs");
				cfg.set("configuration.useSongs", true);
				useSongs = cfg.getBoolean("configuration.UseSongs");
				if(cfg.getString("spawns.Game.World") != null)
					world = Bukkit.getWorld(cfg.getString("spawns.Game.World"));
			}
			}
		}
	
		public boolean getUseSongs(){
			return useSongs;
		}
		
		public ArrayList<String> getSongs(){
			return songs;
		}
		
		public int getOutBlock(){
			return outBlock;
		}
		
		public boolean getFallingBlocks(){
			return fallingBlock;
		}
		
		public boolean getUseBoosts(){
			return useBoosts;
		}
		
		public int getFloorLength(){
			return floorLength;
		}
		
		public int getBoostDuration(){
			return boostDuration;
		}
		
		public int getFloorWidth(){
			return floorWidth;
		}
		
		public int getMaxPlayers(){
			return maxPlayers;
		}
		public int getCountdown(){
			return countdown;
		}
		public int getMinPlayers(){
			return minPlayers;
		}
		public int getTimeToSearch(){
			return timeToSearch;
		}
		public double getTimeReductionPerLevel(){
			return timeReductionPerLevel;
		}
		public int getLevel(){
			return level;
		}
		
		public boolean getAutoGenerateFloors(){
			return autoGenerateFloors;
		}
		
		public boolean getUseSchematicFloors(){
			return useSchematicFloors;
		}
		
		public boolean reachedMaxPlayers(){
			if(arena.exists())
			{
			    if(Players.getPlayerAmountInLobby(arenaName) >= maxPlayers)
			    {
			    	return true;
			    }
			    else
			    {
			    	return false;
			    }
			}
			return true;
		}
		
		public void enable(){
			isEnabled = true;
		}
		
		public void disable(){
			isEnabled = false;
		}
		
		public boolean ex(){
			return arena.exists();
		}
		
		public ArrayList<String> getFloors(){
			return enabledFloors;
		}
		
		public ArrayList<Integer> getRewardItems(){
			return rewardItems;
		}
		
		public void addFloor(String floorName){
			enabledFloors.add(floorName);
			cfg.set("configuration.EnabledFloors", enabledFloors);
			try {
				cfg.save(arena);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void removeFloor(String floorName){
			enabledFloors.remove(floorName);
			cfg.set("configuration.EnabledFloors", enabledFloors);
			try {
				cfg.save(arena);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public boolean aborted(){
			  return abort;
		  }
		  
		  public void abort(){
			  abort = true;
		  }
	    public void unAbort(){
			  abort = false;
		  }
	    public boolean abort = false;
	    public String gameProgress = "inLobby";
	    public String getGameProgress(){
	  	  return gameProgress;
	    }
	    public void setGameProgress (String pro){
	  	  gameProgress = pro;
	    }
	    
	    public boolean getStart(){
	    	return setStart;
	    }
	    public boolean setStart = false;
	    
	    public void setStart(boolean s){
	    	setStart = s;
	    }
}
