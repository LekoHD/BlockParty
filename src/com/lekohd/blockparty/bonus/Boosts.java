package com.lekohd.blockparty.bonus;

import java.util.Random;


import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import com.lekohd.blockparty.BlockParty;

/*
 * Copyright (C) 2014 Leon167 and XxChxppellxX 
 */

public class Boosts {
	
	public Block b;

	public void place(String arenaName){
		Random r = new Random();
		int x = r.nextInt(BlockParty.getArena.get(arenaName).getFloorLength());
		int z = r.nextInt(BlockParty.getArena.get(arenaName).getFloorWidth());
		int y = BlockParty.getArena.get(arenaName).getLocMin().getBlockY()+1;
		World world = BlockParty.getArena.get(arenaName).getWorld();
		b = world.getBlockAt(BlockParty.getArena.get(arenaName).getLocMin().getBlockX() + x, y, BlockParty.getArena.get(arenaName).getLocMin().getBlockZ() + z);
		b.setType(Material.BEACON);
	}
	
	public void remove(){
		if(b!=null)
		{
			b.setType(Material.AIR);
		}
	}
	
}
