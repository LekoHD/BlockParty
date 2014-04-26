package com.lekohd.blockparty.floor;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/*
 * Copyright (C) 2014 Leon167 and XxChxppellxX 
 */
 
public class FloorBlock {

	
	public static String getName(Byte b){
		if(b == 0)
			return "White";
		if(b == 1)
			return "Orange";
		if(b == 2)
			return "Magenta";
		if(b == 3)
			return "Light Blue";
		if(b == 4)
			return "Yellow";
		if(b == 5)
			return "Lime";
		if(b == 6)
			return "Pink";
		if(b == 7)
			return "Gray";
		if(b == 8)
			return "Light Gray";
		if(b == 9)
			return "Cyan";
		if(b == 10)
			return "Purple";
		if(b == 11)
			return "Blue";
		if(b == 12)
			return "Brown";
		if(b == 13)
			return "Green";
		if(b == 14)
			return "Red";
		if(b == 15)
			return "Black";
		return null;
	}
	
	public static void givePlayer(Player p, Byte b){
		 @SuppressWarnings("deprecation")
		 ItemStack block = new ItemStack(Material.STAINED_CLAY, 1, (short) 0, b);
		 ItemMeta bmeta = block.getItemMeta();
		 bmeta.setDisplayName(getName(b));
		 block.setItemMeta(bmeta);
		 p.getInventory().setItem(4, block);
	}
	
}
