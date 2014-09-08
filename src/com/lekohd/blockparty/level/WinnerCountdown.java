package com.lekohd.blockparty.level;
/*
 * Copyright (C) 2014 Leon167, XxChxppellxX and ScriptJunkie 
 */
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
	
	/*public static void shootFireworks(String arenaName){
	Location max = BlockParty.getArena.get(arenaName).getLocMax();
	Location min = BlockParty.getArena.get(arenaName).getLocMin();
	max.setY(BlockParty.getArena.get(arenaName).getLocMax().getBlockX() + 10);
	min.setY(BlockParty.getArena.get(arenaName).getLocMax().getBlockX() + 10);
	Firework fwMax = (Firework) BlockParty.getArena.get(arenaName).getWorld().spawn(max, Firework.class);
	Firework fwMin = (Firework) BlockParty.getArena.get(arenaName).getWorld().spawn(min, Firework.class);
	FireworkMeta fmMax = fwMax.getFireworkMeta();
	FireworkMeta fmMin = fwMax.getFireworkMeta();
	Random r = new Random();
	int fType = r.nextInt(4) + 1;
	Type type = null;
	switch (fType) {
	default:
	case 1:
		type = Type.BALL;
		break;
	case 2:
		type = Type.BALL_LARGE;
		break;
	case 3: 
		type = Type.BURST;
		break;
	case 4:
		type = Type.STAR;
		break;
	}
	int c1 = r.nextInt(17) + 1;
	int c2 = r.nextInt(17) + 1;
	FireworkEffect efMin = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(getColor(c1)).withFade(getColor(c2)).with(type).trail(r.nextBoolean()).build();
	FireworkEffect efMax = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(getColor(c1)).withFade(getColor(c2)).with(type).trail(r.nextBoolean()).build();
	fmMax.addEffect(efMax);
	fmMin.addEffect(efMin);
	int power = r.nextInt(3) + 1;
	fmMax.setPower(power);
	fmMin.setPower(power);
	fwMax.setFireworkMeta(fmMax);
	fwMin.setFireworkMeta(fmMin);
}

public static Color getColor(int c){
	switch(c){
	default:
	case 1: return Color.AQUA;
	case 2: return Color.BLACK;
	case 3: return Color.BLUE;
	case 4: return Color.FUCHSIA;
	case 5: return Color.GRAY;
	case 6: return Color.GREEN;
	case 7: return Color.LIME;
	case 8: return Color.MAROON;
	case 9: return Color.NAVY;
	case 10: return Color.OLIVE;
	case 11: return Color.ORANGE;
	case 12: return Color.PURPLE;
	case 13: return Color.RED;
	case 14: return Color.SILVER;
	case 15: return Color.TEAL;
	case 16: return Color.WHITE;
	case 17: return Color.YELLOW;
	}
}*/
}