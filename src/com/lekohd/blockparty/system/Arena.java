package com.lekohd.blockparty.system;

import com.lekohd.blockparty.BlockParty;
import com.lekohd.blockparty.floor.LoadFloor;
import java.util.ArrayList;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.kitteh.vanish.staticaccess.VanishNoPacket;

@SuppressWarnings("deprecation")
public class Arena {
	
	
	public static void create(Player p, String arenaName) {
		if (!BlockParty.getArena.containsKey(arenaName)) {
			Config conf = new Config(arenaName);
			p.sendMessage(conf.create());
			BlockParty.getArena.put(arenaName, conf);
		} else {
			p.sendMessage("§3[BlockParty] §8Arena " + arenaName + " already exists!");
		}
	}

	public static void setSpawn(Player p, String arenaName, String pos) {
		if (BlockParty.getArena.containsKey(arenaName)) {
			p.sendMessage(BlockParty.getArena.get(arenaName).setSpawn(p, pos));
		} else {
			p.sendMessage("§3[BlockParty] §8Arena " + arenaName + " doesn't exists!");
		}
	}

	public static void delete(String arenaName, Player p) {
		if (BlockParty.getArena.containsKey(arenaName)) {
			p.sendMessage(BlockParty.getArena.get(arenaName).delete());
			BlockParty.getArena.remove(arenaName);
		} else {
			p.sendMessage("§3[BlockParty] §8Arena " + arenaName + " doesn't exists!");
		}
	}

	public static void leave(Player p) {
		Config.leave(p);
	}

	public static void join(Player p, String arenaName) {
		//Alert if in vanish mode
		try {
			if (VanishNoPacket.isVanished(p.getName())) {
				p.sendMessage("§3[BlockParty] §8Cannot join arena when you are in Vanish Mode.");
				return;
			}
		} catch (Exception ex){}

		if ((!BlockParty.inLobbyPlayers.containsKey(p.getName())) && (!BlockParty.inGamePlayers.containsKey(p.getName()))) {
			if (BlockParty.getArena.containsKey(arenaName)) {
				BlockParty.locs.put(p.getName(), p.getLocation());
				BlockParty.gm.put(p.getName(), p.getGameMode());
				p.setGameMode(GameMode.ADVENTURE);
				BlockParty.getArena.get(arenaName).join(p);
			} else {
				p.sendMessage("§3[BlockParty] §8Arena " + arenaName + " isn't enabled or doesn't exists!");
			}
		} else {
			p.sendMessage("§3[BlockParty] §8You are already in a game!");
		}
	}

	public static Location getGameSpawn(String arenaName) {
		if (BlockParty.getArena.containsKey(arenaName)) {
			return BlockParty.getArena.get(arenaName).getGameSpawn();
		}
		return null;
	}

	public static Location getLobbySpawn(String arenaName) {
		if (BlockParty.getArena.containsKey(arenaName)) {
			return BlockParty.getArena.get(arenaName).getLobbySpawn();
		}
		return null;
	}

	public static void enable(String arenaName, Player p) {
		Config conf = new Config(arenaName);
		if (conf.ex()) {
			conf.enable();
			conf.loadCfg();
			conf.loadGameSpawn();
			conf.loadLobbySpawn();
			conf.loadMinPlayers();
			conf.loadMax();
			conf.loadMin();
			conf.load();
			conf.enable();
			BlockParty.getArena.put(arenaName, conf);
			if (conf.getFloors() != null) {
				for (String floor : conf.getFloors()) {
					BlockParty.floors.clear();
					BlockParty.floors.add(new LoadFloor(floor));
					BlockParty.getFloor.put(arenaName, BlockParty.floors);
				}
			}
			FileConfiguration cfg = BlockParty.getInstance().getConfig();
			cfg.options().copyDefaults(true);
			if (!BlockParty.arenaNames.contains(arenaName)) {
				BlockParty.arenaNames.add(arenaName);
				cfg.set("enabledArenas", BlockParty.arenaNames);
			}
			BlockParty.getInstance().saveConfig();
			p.sendMessage("§3[BlockParty] §8Arena " + arenaName + " enabled!");
		} else {
			p.sendMessage("§3[BlockParty] §8Arena " + arenaName + " doesn't exists!");
		}
	}

	public static void disable(String arenaName, Player p) {
		if (BlockParty.getArena.containsKey(arenaName)) {
			if (BlockParty.getArena.get(arenaName).ex()) {
				BlockParty.getArena.get(arenaName).disable();
				BlockParty.getArena.remove(arenaName);
				FileConfiguration cfg = BlockParty.getInstance().getConfig();
				cfg.options().copyDefaults(true);
				if (BlockParty.arenaNames.contains(arenaName)) {
					BlockParty.arenaNames.remove(arenaName);
					cfg.set("enabledArenas", BlockParty.arenaNames);
				}
				BlockParty.getInstance().saveConfig();
				p.sendMessage("§3[BlockParty] §8Arena " + arenaName + " disabled!");
			} else {
				p.sendMessage("§3[BlockParty] §8Arena " + arenaName + " doesn't exists!");
			}
		} else {
			p.sendMessage("§3[BlockParty] §8Arena " + arenaName + " doesn't exists!");
		}
	}

	@SuppressWarnings("unchecked")
	public static void reload(Player p) {
		BlockParty.loadConfig();
		FileConfiguration cfg = BlockParty.getInstance().getConfig();
		BlockParty.arenaNames.clear();
		BlockParty.arenaNames = (ArrayList<String>) cfg.get("enabledArenas");
		if (!BlockParty.arenaNames.isEmpty()) {
			for (String name : BlockParty.arenaNames) {
				Config conf = new Config(name);
				conf.enable();
				conf.loadCfg();
				conf.loadGameSpawn();
				conf.loadLobbySpawn();
				conf.loadMinPlayers();
				conf.loadMax();
				conf.loadMin();
				conf.load();
				BlockParty.getArena.put(name, conf);
				if (conf.getFloors() != null) {
					for (String floor : conf.getFloors()) {
						BlockParty.floors.clear();
						BlockParty.floors.add(new LoadFloor(floor));
						BlockParty.getFloor.put(name, BlockParty.floors);
					}
				}
			}
		}
		p.sendMessage("§3[BlockParty] §8reloaded!");
	}
}