package com.lekohd.blockparty.system;

/*
 * Copyright (C) 2014 Leon167, XxChxppellxX and ScriptJunkie 
 */
import com.lekohd.blockparty.BlockParty;
import com.lekohd.blockparty.floor.LoadFloor;
import org.bukkit.Bukkit;
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
		if (Bukkit.getPluginManager().isPluginEnabled("VanishNoPacket")) {
			try {

				if (VanishNoPacket.isVanished(p.getName())) {
					p.sendMessage("§3[BlockParty] §8Cannot join arena when you are in Vanish Mode.");
					return;
				}
			} catch (Exception ex) {
			}
		}

		if (BlockParty.getArena.containsKey(arenaName)) {
			BlockParty.getArena.get(arenaName).join(p);
		} else {
			p.sendMessage("§3[BlockParty] §8Arena " + arenaName + " does not exist at this time!");
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
			p.sendMessage("§3[BlockParty] §8Arena " + arenaName + " loaded!");
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

	public static void reload(Player p) {
		for (String name : BlockParty.arenaNames) {
			p.sendMessage("§3[BlockParty] §8reloading "+name);
			Arena.enable(name, p);
		}
		p.sendMessage("§3[BlockParty] §8configs reloaded!");
	}
}