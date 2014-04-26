package com.lekohd.blockparty.floor;

import org.bukkit.Location;
import org.bukkit.World;
import com.lekohd.blockparty.Main;

public class FloorPoints {

	public static Location getMin(String arenaName){
		/*File arena = new File("plugins//BlockParty//Arenas//" + arenaName + ".yml");
		if(!arena.exists())
		{
			System.out.println("ERROR: Arena " + arenaName + " doesn't exists!");
		}
		else
		{
			FileConfiguration cfg = YamlConfiguration.loadConfiguration(arena);
			World world = Bukkit.getWorld("floor.min.World");
			double xPos = (double) cfg.get("floor.min.xPos");
			double yPos = (double) cfg.get("floor.min.yPos");
			double zPos = (double) cfg.get("floor.min.zPos");
			Location loc = new Location(world, xPos, yPos, zPos);
			return loc;
		}
		return null;*/
		
		return Main.getArena.get(arenaName).getLocMin();
	}
	public static Location getMax(String arenaName){
		/*File arena = new File("plugins//BlockParty//Arenas//" + arenaName + ".yml");
		if(!arena.exists())
		{
			System.out.println("ERROR: Arena " + arenaName + " doesn't exists!");
		}
		else
		{
			FileConfiguration cfg = YamlConfiguration.loadConfiguration(arena);
			World world = Bukkit.getWorld("floor.max.World");
			double xPos = (double) cfg.get("floor.max.xPos");
			double yPos = (double) cfg.get("floor.max.yPos");
			double zPos = (double) cfg.get("floor.max.zPos");
			Location loc = new Location(world, xPos, yPos, zPos);
			return loc;
		}
		return null;*/
		return Main.getArena.get(arenaName).getLocMax();
	}
	
	public static void set(Location min, Location max, String arenaName){
		/*cfg.set("floor.min.xPos", min.getX());
		cfg.set("floor.min.yPos", min.getY());
		cfg.set("floor.min.zPos", min.getZ());
		cfg.set("floor.min.World", min.getWorld().getName());
		
		cfg.set("floor.max.xPos", max.getX());
		cfg.set("floor.max.yPos", max.getY());
		cfg.set("floor.max.zPos", max.getZ());
		cfg.set("floor.max.World", max.getWorld().getName());
		
		cfg.set("floor.floorPoints", true);*/
		Main.getArena.get(arenaName).set(min, max);
		
	}
	
	public static World getWorld(String arenaName){
		/*File arena = new File("plugins//BlockParty//Arenas//" + arenaName + ".yml");
		if(!arena.exists())
		{
			System.err.println("ERROR: Arena " + arenaName + " doesn't exists!");
			return null;
		}
		else
		{
			FileConfiguration cfg = YamlConfiguration.loadConfiguration(arena);
			World world = Bukkit.getWorld(cfg.getString("spawns.Game.World"));
			return world;
		}*/
		return Main.getArena.get(arenaName).getWorld();
	}
	
}
