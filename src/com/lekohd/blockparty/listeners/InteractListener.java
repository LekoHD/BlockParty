package com.lekohd.blockparty.listeners;

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
			if(Main.onFloorPlayers.containsKey(p))
			{
				if(e.getPlayer().getItemInHand().getType() == Material.DIAMOND_BOOTS)
					{
						Bonus.playEf(e.getPlayer(), "walk");
						e.getPlayer().getInventory().remove(Material.DIAMOND_BOOTS);
					}
				if(e.getPlayer().getItemInHand().getType() == Material.GOLD_BOOTS)
					{
						Bonus.playEf(e.getPlayer(), "jump");
						e.getPlayer().getInventory().remove(Material.GOLD_BOOTS);
					}
			}
			if(Main.inLobbyPlayers.containsKey(p))
			{
				if(e.getPlayer().getItemInHand().getType() == Material.FIREBALL)
				{
					Vote.openInv(p, Main.inLobbyPlayers.get(p));
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
		    				if(!Main.inLobbyPlayers.containsKey(p) && !Main.inGamePlayers.containsKey(p))
		    				{
		    					if(Main.getArena.containsKey(removeColorCodes(sign.getLine(2))))
			    				{
			    					Main.locs.put(p, p.getLocation());
			    					Main.gm.put(p, p.getGameMode());
			    					Main.inv.put(p, p.getInventory().getContents());
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
			    			if(!Main.inLobbyPlayers.containsKey(p))
			    			{
			    				p.sendMessage("§3[BlockParty] §8You are not in an arena!");
			    			}
			    			if(Main.inGamePlayers.containsKey(p))
			    			{
			    				p.sendMessage("§3[BlockParty] §8You can not leave the current game");
			    			}
			    			if((Main.inLobbyPlayers.containsKey(p)) && !(Main.inGamePlayers.containsKey(p)))
			    			{
			    				if(Players.getPlayerAmountInLobby(Main.inLobbyPlayers.get(p)) <= 1){
									Players.getPlayersInLobby(Main.inLobbyPlayers.get(p)).get(0).sendMessage("§3[BlockParty] §8" + p.getName() + " leaved the game");
								}
								else
								{
									for (Player player : Players.getPlayersInLobby(Main.inLobbyPlayers.get(p))){
										player.sendMessage("§3[BlockParty] §8" + p.getName() + " leaved the game");
									}
								}
			    				Main.inLobbyPlayers.remove(p);
			    				p.teleport(Main.locs.get(p));
			    				Main.locs.remove(p);
			    				p.setGameMode(Main.gm.get(p));
			    				Main.gm.remove(p);
			    				p.getInventory().clear();
			    				p.getInventory().setContents(Main.inv.get(p));
			    				Main.inv.remove(p);
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
			if(Main.onFloorPlayers.containsKey(p))
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
	
	/*public void boost(final Player p){
		duration = Main.getArena.get(Main.onFloorPlayers.get(p)).getBoostDuration() + 1;
		if(duration > 0){
			p.setWalkSpeed(0.4f);
			dc = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
				public void run(){
					if(duration!=0)
					{
						if(!(duration <= 1))
						{
							duration--;
						}
						else
						{
							p.setWalkSpeed(0.25f);
							p.sendMessage("§3[BlockParty] §8Your boost has expired");
							Bukkit.getScheduler().cancelTask(dc);
						}
					}
				}
			}, 0L, 20L);
		}
	}*/
	
}
