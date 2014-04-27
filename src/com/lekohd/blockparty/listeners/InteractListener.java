package com.lekohd.blockparty.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.lekohd.blockparty.Main;
import com.lekohd.blockparty.bonus.Bonus;
import com.lekohd.blockparty.bonus.Item;
import com.lekohd.blockparty.music.Vote;
import com.lekohd.blockparty.system.Arena;
import com.lekohd.blockparty.system.Players;

/*
 * Copyright (C) 2014 Leon167 and XxChxppellxX 
 */
 
public class InteractListener implements Listener{

	public int duration = 10;
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(final PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
			Player p = e.getPlayer();
			if(Main.onFloorPlayers.containsKey(p.getName()))
			{
				if(p.getItemInHand().getType() == Material.DIAMOND_BOOTS)
					{
						Bonus.playEf(p, "walk");
						p.getInventory().remove(Material.DIAMOND_BOOTS);
					}
				if(e.getPlayer().getItemInHand().getType() == Material.GOLD_BOOTS)
					{
						Bonus.playEf(p, "jump");
						p.getInventory().remove(Material.GOLD_BOOTS);
					}
			}
			if(Main.inLobbyPlayers.containsKey(p.getName()))
			{
				if(e.getPlayer().getItemInHand().getType() == Material.FIREBALL)
				{
					Vote.openInv(p, Main.inLobbyPlayers.get(p.getName()));
				}
			}
		}
		if(e.getClickedBlock() == null) return;
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
			if(e.getClickedBlock().getType() == Material.SIGN || e.getClickedBlock().getType() == Material.SIGN_POST)
			{
				Sign sign = (Sign) e.getClickedBlock().getState();
				if(sign.getLine(0).equals("§6[BlockParty]"))
				{
					if(sign.getLine(3).equalsIgnoreCase("§2Join"))
					{
						Player p = e.getPlayer();
						if(p.hasPermission("blockparty.user"))
			    		{
		    				if(!Main.inLobbyPlayers.containsKey(p.getName()) && !Main.inGamePlayers.containsKey(p.getName()))
		    				{
		    					if(Main.getArena.containsKey(removeColorCodes(sign.getLine(2))))
			    				{
			    					Main.locs.put(p.getName(), p.getLocation());
			    					Main.gm.put(p.getName(), p.getGameMode());
			    					Main.inv.put(p.getName(), p.getInventory().getContents());
			    					Arena.join(p, removeColorCodes(sign.getLine(2)));
				    				//Start.start(args[1]);
			    				}
			    				else
			    				{
			    					p.sendMessage("§3[BlockParty] §8Arena " + removeColorCodes(sign.getLine(1)) + " isn't enabled or doesn't exists!");
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
				if(sign.getLine(0).equalsIgnoreCase("§6[BlockParty]"))
				{
					if(sign.getLine(1).equalsIgnoreCase("§4Leave"))
					{
						Player p = e.getPlayer();
						if(p.hasPermission("blockparty.user"))
			    		{
			    			if(!Main.inLobbyPlayers.containsKey(p.getName()))
			    			{
			    				p.sendMessage("§3[BlockParty] §8You are not in an arena!");
			    			}
			    			if(Main.inGamePlayers.containsKey(p.getName()))
			    			{
			    				p.sendMessage("§3[BlockParty] §8You can not leave the current game");
			    			}
			    			if((Main.inLobbyPlayers.containsKey(p.getName())) && !(Main.inGamePlayers.containsKey(p.getName())))
			    			{
			    				if(Players.getPlayerAmountInLobby(Main.inLobbyPlayers.get(p.getName())) <= 1){
									Bukkit.getPlayer(Players.getPlayersInLobby(Main.inLobbyPlayers.get(p.getName())).get(0)).sendMessage("§3[BlockParty] §8" + p.getName() + " leaved the game");
								}
								else
								{
									for (String name : Players.getPlayersInLobby(Main.inLobbyPlayers.get(p.getName()))){
										Player player = Bukkit.getPlayer(name);
										player.sendMessage("§3[BlockParty] §8" + p.getName() + " leaved the game");
									}
								}
			    				Main.inLobbyPlayers.remove(p.getName());
			    				p.teleport(Main.locs.get(p.getName()));
			    				Main.locs.remove(p.getName());
			    				p.setGameMode(Main.gm.get(p.getName()));
			    				Main.gm.remove(p.getName());
			    				p.getInventory().clear();
			    				p.getInventory().setContents(Main.inv.get(p.getName()));
			    				Main.inv.remove(p.getName());
				    			p.sendMessage("§3[BlockParty] §8You leaved the arena!");
			    			}
			    		}
					}
				}
			}
		}
		if(e.getClickedBlock().getType() == Material.BEACON)
		{
			Player p = e.getPlayer();
			if(Main.onFloorPlayers.containsKey(p.getName()))
			{
				//Bonus.playEf(p);
				Item.give(p);
				p.playEffect(e.getClickedBlock().getLocation(), Effect.MOBSPAWNER_FLAMES, 5);
				e.getClickedBlock().setType(Material.AIR);
			}
		}
		//if(e.getPlayer().getItemInHand())
	}
	
	
	public String removeColorCodes(String str){
		String h = "";
		for(int i = 0; i < str.length();i++){
			if(str.charAt(i) == '§'){
				i++;
			}
			else
			{
				h = h + str.charAt(i);
			}
		}
		return h;
	}
	
}
