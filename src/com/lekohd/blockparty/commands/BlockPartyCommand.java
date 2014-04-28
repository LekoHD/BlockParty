package com.lekohd.blockparty.commands;

import me.confuser.barapi.BarAPI;



import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import com.lekohd.blockparty.BlockParty;
import com.lekohd.blockparty.floor.AddFloor;
import com.lekohd.blockparty.floor.RemoveFloor;
import com.lekohd.blockparty.floor.SaveFloor;
import com.lekohd.blockparty.system.Arena;
import com.lekohd.blockparty.system.Players;
import com.lekohd.blockparty.system.Start;

/*
 * Copyright (C) 2014 Leon167 and XxChxppellxX 
 */

public class BlockPartyCommand implements CommandExecutor{
	
	  public static String lobby = "Lobby";
	  public static String game = "Game";
	  
	  @SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
	    if(sender instanceof Player){
	    	final Player p = (Player)sender;
	    	if(args.length == 0)
	    	{
	    		p.sendMessage("");
	    		p.sendMessage("§7BlockParty indev §6" + BlockParty.getInstance().getDescription().getVersion());
	    		p.sendMessage("");
	    		p.sendMessage("§8Developer: §3LekoHD");
	    		p.sendMessage("§8Commands: §3/blockparty help");
	    		return true;
	    	}
	    	if(args[0].equalsIgnoreCase("help"))
	    	{
	    		p.sendMessage(ChatColor.GREEN+ "----" +" §6BlockParty " + ChatColor.AQUA + "Commands " + ChatColor.GREEN+ "----");
	    		p.sendMessage("§3/blockparty info  §8Get all informations about the plugin");
	    		p.sendMessage("§3/blockparty join <arenaName>  §8Join the arena");
	    		p.sendMessage("§3/blockparty leave <arenaName>  §8Leave the arena");
	    		if(p.hasPermission("blockparty.admin"))
	    		{
	    			p.sendMessage("§3/blockparty admin  §8Show the admin commands");
	    		}
	    		return true;
	    	}
	    	if(args[0].equalsIgnoreCase("admin"))
	    	{
	    		if(p.hasPermission("blockparty.admin"))
	    		{
	    			p.sendMessage(ChatColor.GREEN+ "----" +" §6BlockParty " + ChatColor.AQUA + "Admin-Commands " + ChatColor.GREEN+ "----");
	    			p.sendMessage("§3/blockparty start <arenaName>  §8Starts the game");
		    		p.sendMessage("§3/blockparty create <arenaName>  §8Creates an arena");
		    		p.sendMessage("§3/blockparty delete <arenaName>  §8Creates an arena");
		    		p.sendMessage("§3/blockparty setSpawn <arenaName> <lobby|game>  §8Set the spawns for lobby|game");
		    		p.sendMessage("§3/blockparty setFloor <arenaName>  §8Set the floor for an arena");
		    		p.sendMessage("§3/blockparty addFloor <arenaName> <floorName>  §8Add a schematic floor to an arena");
		    		p.sendMessage("§3/blockparty removeFloor <arenaName> <floorName>  §8Remove a schematic floor of an arena");
		    		p.sendMessage("§3/blockparty enable <arenaName>  §8To enable an arena");
		    		p.sendMessage("§3/blockparty disable <arenaName>  §8To disable an arena");
		    		p.sendMessage("§3/blockparty reload  §8Reload the configs");
		    		p.sendMessage("§3/blockparty tutorial  §8Shows a tutorial of How to setup an arena");
	    		}
	    		return true;
	    	}
	    	if(args.length == 1)
	    	{
	    		if(args[0].equalsIgnoreCase("leave"))
		    	{
	    			if(p.hasPermission("blockparty.user"))
		    		{
		    			if(!BlockParty.inLobbyPlayers.containsKey(p.getName()))
		    			{
		    				p.sendMessage("§3[BlockParty] §8You are not in an arena!");
		    				return true;
		    			}
		    			if(BlockParty.inGamePlayers.containsKey(p.getName()))
		    			{
		    				p.sendMessage("§3[BlockParty] §8You can not leave the current game");
		    				return true;
		    			}
		    			if((BlockParty.inLobbyPlayers.containsKey(p.getName())) && !(BlockParty.inGamePlayers.containsKey(p.getName())))
		    			{
		    				if(Players.getPlayerAmountInLobby(BlockParty.inLobbyPlayers.get(p.getName())) <= 1){
								Bukkit.getPlayer(Players.getPlayersInLobby(BlockParty.inLobbyPlayers.get(p.getName())).get(0)).sendMessage("§3[BlockParty] §8" + p.getName() + " leaved the game");
							}
							else
							{
								for (String name : Players.getPlayersInLobby(BlockParty.inLobbyPlayers.get(p.getName()))){
									Bukkit.getPlayer(name).sendMessage("§3[BlockParty] §8" + p.getName() + " left the game");
								}
							}
		    				BlockParty.inLobbyPlayers.remove(p.getName());
		    				p.teleport(BlockParty.locs.get(p.getName()));
		    				BlockParty.locs.remove(p.getName());
		    				p.setGameMode(BlockParty.gm.get(p.getName()));
		    				BlockParty.gm.remove(p.getName());
		    				p.getInventory().clear();
		    				p.getInventory().setContents(BlockParty.inv.get(p.getName()));
		    				if(Bukkit.getPluginManager().isPluginEnabled("BarAPI"))
		    					BarAPI.removeBar(p);
			    			p.sendMessage("§3[BlockParty] §8You left the arena!");
			    			return true;
		    			}
		    		}
		    	}
	    		
	    		if(args[0].equalsIgnoreCase("reload")){
	    			if(p.hasPermission("blockparty.admin")){
	    				Arena.reload(p);
	    				return true;
	    			}
	    		}
	    		if(args[0].equalsIgnoreCase("tutorial")){
	    			if(p.hasPermission("blockparty.admin")){
	    				p.sendMessage(ChatColor.GREEN+ "----" +" §6BlockParty " + ChatColor.AQUA + "Tutorial " + ChatColor.GREEN+ "----");
	    				p.sendMessage("§8 - Use §3/bp create <arenaName> §8to create your arena");
	    				p.sendMessage("§8 - Type in §3/bp enable <arenaName> §8to enable your arena");
	    				p.sendMessage("§8 - Go to the point, where you want to have the §3Lobby§8 spawn");
	    				p.sendMessage("§8 - And type in §3/bp setSpawn <arenaName> lobby");
	    				p.sendMessage("§8 - Select two points with §3WorldEdit§8, where you want to have the floor");
	    				p.sendMessage("§8 - Use §3/bp setFloor <arenaName> §8to set the floor");
	    				p.sendMessage("§8 - Go to the point, where you want to have the §3Game§8 spawn (on the floor)");
	    				p.sendMessage("§8 - And type in §3/bp setSpawn <arenaName> game");
	    				p.sendMessage("§8 - You can now start playing in your arena");
	    				p.sendMessage("§8 - If you want to use Schematics: /bp tutorial schematics");
	    			}
	    		}
	    	}
	    	if(args.length == 2)
	    	{
	    		if(args[0].equalsIgnoreCase("tutorial")){
	    			if(args[1].equalsIgnoreCase("schematics")){
		    			if(p.hasPermission("blockparty.admin")){
		    				p.sendMessage(ChatColor.GREEN+ "----" +" §6BlockParty " + ChatColor.AQUA + "Schematics-Tutorial " + ChatColor.GREEN+ "----");
		    				p.sendMessage("§8 - Create a floor Schematic, using WorldEdit, or take the example");
		    				p.sendMessage("§8 - Copy the Schematic from the WorldEdit folder to the BlockParty Floors folder");
		    				p.sendMessage("§8 - If there is no Floors folder, create one");
		    				p.sendMessage("§8 - Use §3/bp addFloor <arenaName> <floorName>");
		    				p.sendMessage("§8 - <floorName> must be the schematic name");
		    				p.sendMessage("§8 - Go into your Arena config and change UseSchematicFloors to true");
		    				p.sendMessage("§8 - Reload or Restart your Server");
		    				p.sendMessage("§8 - Make sure your floor appears in the Arena config (enabledFloors)");
		    				p.sendMessage("§8 - Now you can start your game");
		    				p.sendMessage("§8 - Name one schematic 'start'. Than this schematic will load first");
		    			}
	    			}
	    		}
	    		if(args[0].equalsIgnoreCase("create"))
	    		{
	    			if(p.hasPermission("blockparty.admin"))
		    		{
	    				Arena.create(p, args[1]);
		    		}
	    			else
	    			{
	    				p.sendMessage("§4You don't have the permissions to do that");
	    			}
	    			return true;
	    		}
	    		if(args[0].equalsIgnoreCase("start"))
	    		{
	    			if(p.hasPermission("blockparty.admin"))
		    		{
	    				if(BlockParty.getArena.containsKey(args[1]))
	    				{
	    					BlockParty.getArena.get(args[1]).abort();
		    				Start.startGame(args[1], p);
	    				}
	    				else
	    				{
	    					p.sendMessage("§3[BlockParty] §8Arena " + args[1] + " isn't enabled or doesn't exists!");
	    				}
	    				
		    		}
	    			else
	    			{
	    				p.sendMessage("§4You don't have the permissions to do that");
	    			}
	    		}
	    		if(args[0].equalsIgnoreCase("delete"))
		    	{
	    			if(p.hasPermission("blockparty.admin"))
		    		{
	    				Arena.delete(args[1], p);
		    		}
		    	}
	    		if(args[0].equalsIgnoreCase("setFloor"))
		    	{
	    			if(p.hasPermission("blockparty.admin"))
		    		{
	    				if(BlockParty.getArena.containsKey(args[1]))
	    				{
	    					SaveFloor.setFloor(p, args[1]);
	    				}
	    				else
	    				{
	    					p.sendMessage("§3[BlockParty] §8Arena " + args[1] + " isn't enabled or doesn't exists!");
	    				}
		    		}
		    	}
	    		if(args[0].equalsIgnoreCase("enable"))
		    	{
	    			if(p.hasPermission("blockparty.admin"))
		    		{
	    				Arena.enable(args[1], p);
		    		}
		    	}
	    		if(args[0].equalsIgnoreCase("disable"))
		    	{
	    			if(p.hasPermission("blockparty.admin"))
		    		{
	    				if(BlockParty.getArena.containsKey(args[1]))
	    				{
	    					Arena.disable(args[1], p);
	    				}
	    				else
	    				{
	    					p.sendMessage("§3[BlockParty] §8Arena " + args[1] + " isn't enabled or doesn't exists!");
	    				}
		    		}
		    	}
	    		if(args[0].equalsIgnoreCase("join"))
	    		{
	    			if(p.hasPermission("blockparty.user"))
		    		{
	    				if(!BlockParty.inLobbyPlayers.containsKey(p.getName()) && !BlockParty.inGamePlayers.containsKey(p.getName()))
	    				{
	    					if(BlockParty.getArena.containsKey(args[1]))
		    				{
		    					BlockParty.locs.put(p.getName(), p.getLocation());
		    					BlockParty.gm.put(p.getName(), p.getGameMode());
		    					Inventory inv = p.getInventory();
		    					BlockParty.inv.put(p.getName(), inv.getContents());
		    					Arena.join(p, args[1]);
			    				//Start.start(args[1]);
		    				}
		    				else
		    				{
		    					p.sendMessage("§3[BlockParty] §8Arena " + args[1] + " isn't enabled or doesn't exists!");
		    				}
	    				}
	    				else
	    				{
	    					p.sendMessage("§3[BlockParty] §8You are already in a game!");
	    				}
		    		}
	    			else
	    			{
	    				p.sendMessage("§4You don't have the permissions to do that");
	    			}
	    		}
	    		
	    	}
	    	if(args.length == 3)
	    	{
	    		if(args[0].equalsIgnoreCase("setspawn"))
	    		{
	    			if(p.hasPermission("blockparty.admin"))
		    		{
	    				if(BlockParty.getArena.containsKey(args[1]))
	    				{
		    				if(args[2].equalsIgnoreCase(lobby))
		    					Arena.setSpawn(p, args[1], lobby);
		    				if(args[2].equalsIgnoreCase(game))
		    					Arena.setSpawn(p, args[1], game);
		    				return true;
	    				}
	    				else
	    				{
	    					p.sendMessage("§3[BlockParty] §8Arena " + args[1] + " isn't enabled or doesn't exists!");
	    				}
		    		}
	    			else
	    			{
	    				p.sendMessage("§4You don't have the permissions to do that");
	    				return true;
	    			}
	    		}
	    		if(args[0].equalsIgnoreCase("addFloor"))
	    		{
	    			if(p.hasPermission("blockparty.admin"))
		    		{
	    				if(BlockParty.getArena.containsKey(args[1]))
	    				{
	    					AddFloor.add(args[1], args[2]);
	    					p.sendMessage("§3[BlockParty] §8Floor " + args[2] + " was added to Arena " + args[1]);
	    					p.sendMessage("§3[BlockParty] §8Be sure, that the floor exists!");
	    					p.sendMessage("§3[BlockParty] §8It is recommended, that the floors have the same size!");
	    					p.sendMessage("§3[BlockParty] §7Maybe the Floor will not be added. Take a look in you arena config!");
	    				}
	    				else
	    				{
	    					p.sendMessage("§3[BlockParty] §8Arena " + args[1] + " isn't enabled or doesn't exists!");
	    				}
		    		}
		    	}
	    		if(args[0].equalsIgnoreCase("removeFloor"))
	    		{
	    			if(p.hasPermission("blockparty.admin"))
		    		{
	    				if(BlockParty.getArena.containsKey(args[1]))
	    				{
	    					RemoveFloor.add(args[1], args[2]);
	    					p.sendMessage("§3[BlockParty] §7Maybe the Floor will not be removed. Take a look in you arena config!");
	    				}
	    				else
	    				{
	    					p.sendMessage("§3[BlockParty] §8Arena " + args[1] + " isn't enabled or doesn't exists!");
	    				}
		    		}
		    	}
	    	}
	    } 
	    else
	    {
	    	ConsoleCommandSender cs = (ConsoleCommandSender) sender;
	    	cs.sendMessage("");
    		cs.sendMessage("§7BlockParty indev §6" + BlockParty.getInstance().getDescription().getVersion());
    		cs.sendMessage("");
    		cs.sendMessage("§8Developer: §3LekoHD");
    		cs.sendMessage("§8Commands: §3/blockparty help");
    		return true;
	    }
		return true;
	  }

}
