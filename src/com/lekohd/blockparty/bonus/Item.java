package com.lekohd.blockparty.bonus;
/*
 * Copyright (C) 2014 Leon167, XxChxppellxX and ScriptJunkie 
 */
import java.util.Random;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Item {
	public static ItemStack walk;
	public static ItemStack jump;
	public static ItemStack ender;

	public static void loadItems() {
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

	public static void give(Player p) {
		loadItems();
		Random r = new Random();
		int fType = r.nextInt(5) + 1;
		switch (fType) {
		case 1:
		default:
			p.getInventory().addItem(new ItemStack[] { walk });
			p.updateInventory();
			break;
		case 2:
			p.getInventory().addItem(new ItemStack[] { jump });
			p.updateInventory();
			break;
		case 3:
			p.getInventory().addItem(new ItemStack[] { ender });
			p.updateInventory();
			break;
		case 4:
			Bonus.playEf(p, "nausea");
			break;
		case 5:
			Bonus.playEf(p, "blindness");
		}
	}

	public static void setInInv(Player p, ItemStack item) {
		Inventory inv = p.getInventory();
		inv.addItem(new ItemStack[] { item });
		p.getInventory().setContents(inv.getContents());
	}
}