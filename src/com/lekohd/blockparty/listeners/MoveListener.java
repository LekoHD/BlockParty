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

public class MoveListener implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerMove(final PlayerMoveEvent e) {
		if(Main.onFloorPlayers.containsKey(e.getPlayer())){
			Location loc = e.getTo();
			loc.setY(e.getTo().getBlockY() - 1);
			if(loc.getBlock().getTypeId() == Main.getArena.get(Main.onFloorPlayers.get(e.getPlayer())).getOutBlock())
			{
				if(!(Players.getPlayerAmountOnFloor(Main.onFloorPlayers.get(e.getPlayer())) <= 1)){
					if(Bukkit.getPluginManager().isPluginEnabled("NoteBlockAPI"))
						Songs.stop(e.getPlayer());
					e.getPlayer().getInventory().clear();
					Main.inLobbyPlayers.put(e.getPlayer(), Main.onFloorPlayers.get(e.getPlayer()));
					World world = e.getPlayer().getWorld();
					world.strikeLightning(e.getPlayer().getLocation());
					if(!(Players.getPlayerAmountOnFloor(Main.onFloorPlayers.get(e.getPlayer())) <= 1))
					{
						for(Player p : Players.getPlayersOnFloor((Main.onFloorPlayers.get(e.getPlayer())))){
							p.sendMessage("§3[BlockParty] §8" + e.getPlayer().getName() + " was §4ELIMINATED");
						}
					}
					else
					{
						Players.getPlayersOnFloor((Main.onFloorPlayers.get(e.getPlayer()))).get(0).sendMessage("§3[BlockParty] §8" + e.getPlayer().getName() + " was §4ELIMINATED");
					}
					e.getPlayer().sendMessage("§3[BlockParty] §8You were §4ELIMINATED");
					e.getPlayer().teleport(Arena.getLobbySpawn(Main.onFloorPlayers.get(e.getPlayer())));
					Main.inGamePlayers.remove(e.getPlayer());
					Main.onFloorPlayers.remove(e.getPlayer());
					if(Bukkit.getPluginManager().isPluginEnabled("BarAPI")){
						BarAPI.setMessage(e.getPlayer(), "Waiting ...", (float)100);
					}
				}
			}
		}
	}

}
