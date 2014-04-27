package com.lekohd.blockparty.listeners;

import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.lekohd.blockparty.Main;
import com.lekohd.blockparty.music.Songs;
import com.lekohd.blockparty.system.Arena;
import com.lekohd.blockparty.system.Players;

/*
 * Copyright (C) 2014 Leon167 and XxChxppellxX 
 */
 
public class MoveListener implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerMove(final PlayerMoveEvent e) {
		if(Main.onFloorPlayers.containsKey(e.getPlayer().getName())){
			Location loc = e.getTo();
			loc.setY(e.getTo().getBlockY() - 1);
			if(loc.getBlock().getTypeId() == Main.getArena.get(Main.onFloorPlayers.get(e.getPlayer().getName())).getOutBlock())
			{
				if(!(Players.getPlayerAmountOnFloor(Main.onFloorPlayers.get(e.getPlayer().getName())) <= 1)){
					if(Bukkit.getPluginManager().isPluginEnabled("NoteBlockAPI"))
						Songs.stop(e.getPlayer());
					e.getPlayer().getInventory().clear();
					e.getPlayer().getInventory().addItem(Main.getArena.get(Main.onFloorPlayers.get(e.getPlayer().getName())).getVoteItem());
					e.getPlayer().updateInventory();
					Main.inLobbyPlayers.put(e.getPlayer().getName(), Main.onFloorPlayers.get(e.getPlayer().getName()));
					World world = e.getPlayer().getWorld();
					world.strikeLightning(e.getPlayer().getLocation());
					if(!(Players.getPlayerAmountOnFloor(Main.onFloorPlayers.get(e.getPlayer().getName())) <= 1))
					{
						for(String name : Players.getPlayersOnFloor((Main.onFloorPlayers.get(e.getPlayer().getName())))){
							Player p = Bukkit.getPlayer(name);
							p.sendMessage("§3[BlockParty] §8" + e.getPlayer().getName() + " was §4ELIMINATED");
						}
					}
					else
					{
						Bukkit.getPlayer(Players.getPlayersOnFloor((Main.onFloorPlayers.get(e.getPlayer().getName()))).get(0)).sendMessage("§3[BlockParty] §8" + e.getPlayer().getName() + " was §4ELIMINATED");
					}
					e.getPlayer().sendMessage("§3[BlockParty] §8You were §4ELIMINATED");
					e.getPlayer().teleport(Arena.getLobbySpawn(Main.onFloorPlayers.get(e.getPlayer().getName())));
					Main.inGamePlayers.remove(e.getPlayer().getName());
					Main.onFloorPlayers.remove(e.getPlayer().getName());
					if(Bukkit.getPluginManager().isPluginEnabled("BarAPI")){
						BarAPI.setMessage(e.getPlayer(), "Waiting ...", (float)100);
					}
				}
			}
		}
	}

}
