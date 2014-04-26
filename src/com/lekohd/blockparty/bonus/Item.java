package com.lekohd.blockparty.bonus;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Item {

	public static ItemStack walk, jump, ender;
	public static void loadItems(){
		walk = new ItemStack(Material.DIAMOND_BOOTS, 1);
	    ItemMeta walkmeta = walk.getItemMeta();
	    walkmeta.setDisplayName("§6Walk faster");
	    walk.setItemMeta(walkmeta);
	    
	    jump = new ItemStack(Material.GOLD_BOOTS, 1);
	    ItemMeta jumpmeta = jump.getItemMeta();
	    jumpmeta.setDisplayName("§6Jump higher");
	    jump.setItemMeta(jumpmeta);
	    
	    ender = new ItemStack(Material.ENDER_PEARL, 3);
	    ItemMeta endermeta = ender.getItemMeta();
	    endermeta.setDisplayName("§6Jump further");
	    ender.setItemMeta(endermeta); 
	}
	
	public static void give(Player p){
		loadItems();
		Random r = new Random();
		int fType = r.nextInt(4) + 1;
		switch (fType) {
		default:
		case 1:
			Bonus.playEf(p, "walk");
			break;
		case 2:
			Bonus.playEf(p, "jump");
			break;
		case 0: 
			Bonus.playEf(p, "walk");
			break;
		case 3:
			Bonus.playEf(p, "nausea");
			break;
		case 4:
			Bonus.playEf(p, "blindness");
			break;
		}
	}
	
	public static void setInInv(Player p, ItemStack item){
		//p.getInventory().setItem(0, new ItemStack(Material.ANVIL, 1));
		Inventory inv = p.getInventory();
		inv.addItem(item);
		p.getInventory().setContents(inv.getContents());
		/*if(p.getInventory().getItem(0) == null)
		{
			p.getInventory().addItem(item);
		}
		else
		if(p.getInventory().getItem(1) == null)
		{
			p.getInventory().setItem(1, item);
		}
		else
		if(p.getInventory().getItem(2) == null)
		{
			p.getInventory().setItem(2, item);
		}
		else
		if(p.getInventory().getItem(3) == null)
		{
			p.getInventory().setItem(3, item);
		}
		else
		if(p.getInventory().getItem(4) == null)
		{
			p.getInventory().setItem(4, item);
		}
		else
		if(p.getInventory().getItem(5) == null)
		{
			p.getInventory().setItem(5, item);
		}
		else
		if(p.getInventory().getItem(6) == null)
		{
			p.getInventory().setItem(6, item);
		}
		else
		if(p.getInventory().getItem(7) == null)
		{
			p.getInventory().setItem(7, item);
		}
		else
		if(p.getInventory().getItem(8) == null)
		{
			p.getInventory().setItem(8, item);
		}*/
	}
}
