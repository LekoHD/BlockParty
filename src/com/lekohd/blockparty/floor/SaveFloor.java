package com.lekohd.blockparty.floor;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.lekohd.blockparty.Main;

public class SaveFloor {

	public static void setFloor(Player p, String arenaName){
		File arena = new File("plugins//BlockParty//Arenas//" + arenaName + ".yml");
		if(!arena.exists())
		{
			p.sendMessage("§3[BlockParty] §8Arena " + arenaName + " doesn't exists!");
		}
		else
		{
			FileConfiguration cfg = YamlConfiguration.loadConfiguration(arena);
			if(checkConditions(p, arenaName)){
				try {
					cfg.save(arena);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static boolean checkConditions(Player p, String arenaName){
		
		/*WorldEditPlugin worldEdit = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
		Selection selection = worldEdit.getSelection(p);
		 
		if (selection != null) {
		    World world = selection.getWorld();
		    if(selection.getHeight() == 1)
		    {
		    	if(selection.getLength() == cfg.getInt("configuration.floor.length"))
			    {
				    if(selection.getWidth() == cfg.getInt("configuration.floor.width"))
				    {
				    	if(selection.getHeight() == 1){
				    		if(world.getName().equals("world"))
				    		{
				    			Location min = selection.getMinimumPoint();
				    		    Location max = selection.getMaximumPoint();
				    		    FloorPoints.set(min, max, arenaName);
				    		    p.sendMessage("§3[BlockParty] §8Floor was set for Arena " + arenaName); 
				    		    return true;
				    		}
				    		else
				    		{
				    			p.sendMessage("§3[BlockParty] §8Arena and Floor must be in the same world");
				    		}
				    	}
				    	else
				    	{
				    		p.sendMessage("§3[BlockParty] §8The Floor must be one block high!");
				    	}
				    }
				    else
				    {
				    	p.sendMessage("§3[BlockParty] §8The Floor must be " + cfg.getInt("configuration.floor.width") + " blocks wide!");
				    	p.sendMessage("§3[BlockParty] §8You can change it in config.yml");
				    }
			    }
			    else
			    {
			    	p.sendMessage("§3[BlockParty] §8The Floor must be " + cfg.getInt("configuration.floor.length") + " blocks long!");
			    	p.sendMessage("§3[BlockParty] §8You can change it in config.yml");
			    }
		    } else {
		    	p.sendMessage("§3[BlockParty] §8The Floor must be one block high!");
		    }
		} else {
			p.sendMessage("§3[BlockParty] §8Select a region with WorldEdit first.");
		}
		return false;
	}*/
		
		if(!(Main.getArena.get(arenaName) == null)){
		if(Main.getArena.get(arenaName).checkConditions(p))
			return true;
		
		return false;
		}
		return false;
	}
	
}




	
