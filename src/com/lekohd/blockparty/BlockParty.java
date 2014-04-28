package com.lekohd.blockparty;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.lekohd.blockparty.commands.BlockPartyCommand;
import com.lekohd.blockparty.floor.LoadFloor;
import com.lekohd.blockparty.listeners.BlockPlaceListener;
import com.lekohd.blockparty.listeners.ChangeBlockListener;
import com.lekohd.blockparty.listeners.CommandListener;
import com.lekohd.blockparty.listeners.DamageListener;
import com.lekohd.blockparty.listeners.DisconnectListener;
import com.lekohd.blockparty.listeners.FeedListener;
import com.lekohd.blockparty.listeners.InteractListener;
import com.lekohd.blockparty.listeners.InventoryListener;
import com.lekohd.blockparty.listeners.MoveListener;
import com.lekohd.blockparty.listeners.SignListener;
import com.lekohd.blockparty.music.Songs;
import com.lekohd.blockparty.system.Config;

/*
 * Copyright (C) 2014 Leon167 and XxChxppellxX 
 */

public class BlockParty extends JavaPlugin {

	public static Player p;
	public static HashMap<String, String> inGamePlayers = new HashMap<>();
	public static HashMap<String, String> inLobbyPlayers = new HashMap<>();
	public static HashMap<String, String> onFloorPlayers = new HashMap<>();
	public static HashMap<String, Location> locs = new HashMap<>();
	public static HashMap<String, GameMode> gm = new HashMap<>();
	public static HashMap<String, ItemStack[]> inv = new HashMap<>();
	public static HashMap<String, Config> getArena = new HashMap<>();
	public static ArrayList<LoadFloor> floors = new ArrayList<>();
	public static HashMap<String, ArrayList<LoadFloor>> getFloor = new HashMap<>();
	public static HashMap<String, Sign> signs = new HashMap<>();
	public static BlockParty instance;
	public static int playerAmount = 0;
	public static int lessMinimum = 0;
	public static boolean abort = false;
	public static ArrayList<String> arenaNames = new ArrayList<>();
	private static boolean noteBlockAPI, BarAPI;
	// TODO hoverable text
	// prevent user from placing blocks
	// disable pvp
	// falling blocks
	// TODO Note Block as countdown
	// Heath and Feed regeneration
	
	 public void onEnable()
	  {
		  instance = this;
		  loadConfig();
		  MessageManager.getInstance().log("Plugin by " + this.getDescription().getAuthors());
		  
		  this.getCommand("blockparty").setExecutor(new BlockPartyCommand());
		  
		  PluginManager pm = getServer().getPluginManager();
		  pm.registerEvents(new DisconnectListener(), this);
		  pm.registerEvents(new CommandListener(), this);
		  pm.registerEvents(new MoveListener(), this);
		  pm.registerEvents(new SignListener(), this);
		  pm.registerEvents(new InteractListener(), this);
		  pm.registerEvents(new FeedListener(), this);
		  pm.registerEvents(new ChangeBlockListener(), this);
		  pm.registerEvents(new BlockPlaceListener(), this);
		  pm.registerEvents(new DamageListener(), this);
		  pm.registerEvents(new InventoryListener(), this);
		  
		  noteBlockAPI = pm.isPluginEnabled("NoteBlockAPI");
		  BarAPI = pm.isPluginEnabled("BarAPI");
		  
		  if(noteBlockAPI){
			  	for(Player p : Bukkit.getOnlinePlayers()) Songs.stop(p); 
		  }  
	  }
	 public void onDisable()
	 {
		 MessageManager.getInstance().log("Plugin disabled!");
	 }
	  public static BlockParty getInstance(){
		  return instance;
	  }
	  @SuppressWarnings("unchecked")
	public static void loadConfig(){
		  FileConfiguration cfg = instance.getConfig();
		  cfg.options().copyDefaults(true);
		  instance.saveConfig();
		  arenaNames = (ArrayList<String>) cfg.get("enabledArenas");
		  if(!(arenaNames == null))
		  {
			  if(!(arenaNames.isEmpty()))
			  {
				  for(String name : arenaNames){
					  Config conf = new Config(name);
					  conf.enable();
					  conf.loadCfg();
					  conf.loadGameSpawn();
					  conf.loadLobbySpawn();
					  conf.loadMinPlayers();
					  conf.loadMax();
					  conf.loadMin();
					  conf.load();
					  getArena.put(name, conf);
					  if(!(conf.getFloors()  == null)){
						  floors.clear();
						  for(String floor : conf.getFloors())
						  {
							  floors.add(new LoadFloor(floor));
							  getFloor.put(name, floors);
						  }	
				  		}
					  MessageManager.getInstance().log("Arena " + name + " loaded!");
				  }
			  }  
		  }
	  }
}
