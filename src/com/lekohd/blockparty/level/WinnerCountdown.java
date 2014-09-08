package com.lekohd.blockparty.level;

import com.lekohd.blockparty.BlockParty;
import com.lekohd.blockparty.system.Arena;
import com.lekohd.blockparty.system.Config;
import com.lekohd.blockparty.system.Players;
import com.lekohd.blockparty.system.Start;
import me.confuser.barapi.BarAPI;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class WinnerCountdown {
	public static int cd;
	public static double number = 6.0D;

	public static void start(final String arenaName) {
		number = 6.0D;
		cd = Bukkit.getScheduler().scheduleSyncRepeatingTask(BlockParty.getInstance(), new Runnable() {
			public void run() {
				if (WinnerCountdown.number != 0.0D) {
					if (WinnerCountdown.number != 1.0D) {
						WinnerCountdown.number -= 1.0D;
					} else {
						// Notify players of who won
						if (Players.getPlayerAmountOnFloor(arenaName) >= 1) {
							for (String winner : Players.getPlayersOnFloor(arenaName)) {
								for (String name : Players.getPlayersOnFloor(arenaName)) {
									Player p = Bukkit.getPlayer(name);
									p.sendMessage("§3[BlockParty] §8Player " + winner + " won the game.");
								}
								Player p = Bukkit.getPlayer(winner);
								p.sendMessage("§3[BlockParty] §8Player " + winner + " won the game.");
							}
						}

						Bukkit.getScheduler().cancelTask(WinnerCountdown.cd);
						if (Players.getPlayerAmountOnFloor(arenaName) == 1) {
							if (Bukkit.getPluginManager().isPluginEnabled("BarAPI")) {
								BarAPI.setMessage(Bukkit.getPlayer((String) Players.getPlayersOnFloor(arenaName).get(0)), "Waiting ...", 100.0F);
							}
							Bukkit.getPlayer((String) Players.getPlayersOnFloor(arenaName).get(0)).teleport(Arena.getLobbySpawn(arenaName));
							BlockParty.inLobbyPlayers.put((String) Players.getPlayersOnFloor(arenaName).get(0), arenaName);
							Bukkit.getPlayer((String) Players.getPlayersOnFloor(arenaName).get(0)).getInventory()
									.addItem(new ItemStack[] { ((Config) BlockParty.getArena.get(arenaName)).getVoteItem() });
							Bukkit.getPlayer((String) Players.getPlayersOnFloor(arenaName).get(0)).updateInventory();
							BlockParty.inGamePlayers.remove(Players.getPlayersOnFloor(arenaName).get(0));
							BlockParty.onFloorPlayers.remove(Players.getPlayersOnFloor(arenaName).get(0));
							((Config) BlockParty.getArena.get(arenaName)).setGameProgress("inLobby");
							if (((Config) BlockParty.getArena.get(arenaName)).getAutoKick()) {
								WinnerCountdown.kickPlayers(arenaName);
							} else if (((Config) BlockParty.getArena.get(arenaName)).getAutoRestart()) {
								Start.start(arenaName);
							}
						} else if (Players.getPlayerAmountOnFloor(arenaName) > 1) {
							for (String name : Players.getPlayersOnFloor(arenaName)) {
								Player p = Bukkit.getPlayer(name);
								if (Bukkit.getPluginManager().isPluginEnabled("BarAPI")) {
									BarAPI.setMessage(p, "Waiting ...", 100.0F);
								}
								p.teleport(Arena.getLobbySpawn(arenaName));
								BlockParty.inGamePlayers.remove(p.getName());
								BlockParty.onFloorPlayers.remove(p.getName());
								BlockParty.inLobbyPlayers.put(p.getName(), arenaName);
								p.getInventory().addItem(new ItemStack[] { ((Config) BlockParty.getArena.get(arenaName)).getVoteItem() });
								p.updateInventory();
								((Config) BlockParty.getArena.get(arenaName)).setGameProgress("inLobby");
								if (((Config) BlockParty.getArena.get(arenaName)).getAutoKick()) {
									WinnerCountdown.kickPlayers(arenaName);
								} else if (((Config) BlockParty.getArena.get(arenaName)).getAutoRestart()) {
									Start.start(arenaName);
								}
							}
						}
					}
				} else {
					Bukkit.getScheduler().cancelTask(WinnerCountdown.cd);
					System.err.println("[BlockParty] ERROR: The countdown is less than 1");
				}
			}
		}, 0L, 20L);
	}

	public static void kickPlayers(String arenaName) {
		Player p;
		if (Players.getPlayerAmountInLobby(arenaName) == 1) {
			String name = (String) Players.getPlayersInLobby(arenaName).get(0);
			p = Bukkit.getPlayer(name);
			BlockParty.inLobbyPlayers.remove(name);
			p.teleport((Location) BlockParty.locs.get(name));
			BlockParty.locs.remove(name);
			p.setGameMode((GameMode) BlockParty.gm.get(name));
			BlockParty.gm.remove(name);
			BlockParty.inventoryManager.restoreInv(p);
			BlockParty.inventoriesToRestore.remove(p.getPlayer().getName());
			p.getInventory().setContents((ItemStack[]) BlockParty.awards.get(name));
			if (Bukkit.getPluginManager().isPluginEnabled("BarAPI")) {
				BarAPI.removeBar(p);
			}
			p.sendMessage("§3[BlockParty] §8You left the arena!");
		} else if (Players.getPlayerAmountInLobby(arenaName) > 1) {
			for (String name : Players.getPlayersInLobby(arenaName)) {
				p = Bukkit.getPlayer(name);
				BlockParty.inLobbyPlayers.remove(p.getName());
				p.teleport((Location) BlockParty.locs.get(p.getName()));
				BlockParty.locs.remove(p.getName());
				p.setGameMode((GameMode) BlockParty.gm.get(p.getName()));
				BlockParty.gm.remove(p.getName());
				BlockParty.inventoryManager.restoreInv(p);
				BlockParty.inventoriesToRestore.remove(p.getPlayer().getName());
				p.getInventory().setContents((ItemStack[]) BlockParty.awards.get(p.getName()));
				if (Bukkit.getPluginManager().isPluginEnabled("BarAPI")) {
					BarAPI.removeBar(p);
				}
				p.sendMessage("§3[BlockParty] §8You left the arena!");
			}
		}
	}
}