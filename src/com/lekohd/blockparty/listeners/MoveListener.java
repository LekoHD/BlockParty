package com.lekohd.blockparty.listeners;

/*
 * Copyright (C) 2014 Leon167, XxChxppellxX, ScriptJunkie and CPx1989
 */

import com.lekohd.blockparty.BlockParty;
import com.lekohd.blockparty.music.Songs;
import com.lekohd.blockparty.scoreboardsystem.ScoreboardSys;
import com.lekohd.blockparty.system.Arena;
import com.lekohd.blockparty.system.Config;
import com.lekohd.blockparty.system.Players;
import me.confuser.barapi.BarAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class MoveListener implements Listener {

    public Location loc;
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		if (BlockParty.onFloorPlayers.containsKey(e.getPlayer().getName())) {
			loc = e.getPlayer().getLocation();
			loc.setY(loc.getBlockY() - 1);
			try {
				if (loc.getBlock().getTypeId() == (BlockParty.getArena.get(BlockParty.onFloorPlayers.get(e.getPlayer().getName()))).getOutBlock()) {
					if (Players.getPlayerAmountOnFloor((String) BlockParty.onFloorPlayers.get(e.getPlayer().getName())) > 1) {
						if (Bukkit.getPluginManager().isPluginEnabled("NoteBlockAPI")) {
							Songs.stop(e.getPlayer());
						}
						e.getPlayer().getInventory().clear();
						e.getPlayer()
								.getInventory()
								.addItem(
										new ItemStack[] { ((Config) BlockParty.getArena.get(BlockParty.onFloorPlayers.get(e.getPlayer().getName())))
												.getVoteItem() });
						e.getPlayer().updateInventory();
						BlockParty.inLobbyPlayers.put(e.getPlayer().getName(), (String) BlockParty.onFloorPlayers.get(e.getPlayer().getName()));

                        //Stats
                        BlockParty.statsManager.setEliminations(e.getPlayer(), (BlockParty.statsManager.getEliminations(e.getPlayer()) + 1));
                        if(Players.getPlayerAmountOnFloor((String) BlockParty.onFloorPlayers.get(e.getPlayer().getName())) <= 3)
                        {
                            BlockParty.statsManager.setPlacings(e.getPlayer(), (BlockParty.statsManager.getPlacings(e.getPlayer()) + 1));
                        }
                        if(Players.getPlayerAmountOnFloor((String) BlockParty.onFloorPlayers.get(e.getPlayer().getName())) == 3)
                        {
                            BlockParty.statsManager.setPoints(e.getPlayer(), (BlockParty.statsManager.getPoints(e.getPlayer()) + 5));
                            e.getPlayer().sendMessage(BlockParty.messageManager.PERIOD_ANNOUNCE_POINTS_PLUS_FIVE);
                        }
                        if(Players.getPlayerAmountOnFloor((String) BlockParty.onFloorPlayers.get(e.getPlayer().getName())) == 2)
                        {
                            BlockParty.statsManager.setPoints(e.getPlayer(), (BlockParty.statsManager.getPoints(e.getPlayer()) + 10));
                            e.getPlayer().sendMessage(BlockParty.messageManager.PERIOD_ANNOUNCE_POINTS_PLUS_TEN);
                        }
                        BlockParty.statsManager.storeStats(e.getPlayer());

                        if(BlockParty.getArena.get((String) BlockParty.onFloorPlayers.get(e.getPlayer().getName())).getEnableScoreboard())
                            ScoreboardSys.setLobbyScore(e.getPlayer());
						World world = e.getPlayer().getWorld();
						world.strikeLightning(e.getPlayer().getLocation());
						Config.broadcastInGame(BlockParty.messageManager.PERIOD_ELIMINATED.replace("$PLAYER$", e.getPlayer().getName()), Config.arenaName);

						// e.getPlayer().sendMessage("�3[BlockParty] �8You were �4ELIMINATED");
						e.getPlayer().teleport(Arena.getLobbySpawn((String) BlockParty.onFloorPlayers.get(e.getPlayer().getName())));
						BlockParty.onFloorPlayers.remove(e.getPlayer().getName());
						if (Bukkit.getPluginManager().isPluginEnabled("BarAPI")) {
							BarAPI.setMessage(e.getPlayer(), BlockParty.messageManager.BAR_WAITING, 100.0F);
						}
					}
				}
			} catch (Exception ex) {
				Config.broadcastInGame(BlockParty.messageManager.PERIOD_ELIMINATED.replace("$PLAYER$", e.getPlayer().getName()), Config.arenaName);

				// e.getPlayer().sendMessage("�3[BlockParty] �8You were �4ELIMINATED");
				if (BlockParty.onFloorPlayers.containsKey(e.getPlayer().getName())) {
					BlockParty.onFloorPlayers.remove(e.getPlayer().getName());
				}

				BlockParty.inLobbyPlayers.put(e.getPlayer().getName(), Config.arenaName);
				Arena.leave(e.getPlayer());
			}
		}
	}
}