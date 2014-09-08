package com.lekohd.blockparty.floor;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class FloorBlock {
	public static String getName(Byte b, Material material) {
		if (material == Material.STAINED_CLAY) {
			if (b.byteValue() == 0) {
				return "White Stained Clay";
			}
			if (b.byteValue() == 1) {
				return "Orange Stained Clay";
			}
			if (b.byteValue() == 2) {
				return "Magenta Stained Clay";
			}
			if (b.byteValue() == 3) {
				return "Light Blue Stained Clay";
			}
			if (b.byteValue() == 4) {
				return "Yellow Stained Clay";
			}
			if (b.byteValue() == 5) {
				return "Lime Stained Clay";
			}
			if (b.byteValue() == 6) {
				return "Pink Stained Clay";
			}
			if (b.byteValue() == 7) {
				return "Gray Stained Clay";
			}
			if (b.byteValue() == 8) {
				return "Light Gray Stained Clay";
			}
			if (b.byteValue() == 9) {
				return "Cyan Stained Clay";
			}
			if (b.byteValue() == 10) {
				return "Purple Stained Clay";
			}
			if (b.byteValue() == 11) {
				return "Blue Stained Clay";
			}
			if (b.byteValue() == 12) {
				return "Brown Stained Clay";
			}
			if (b.byteValue() == 13) {
				return "Green Stained Clay";
			}
			if (b.byteValue() == 14) {
				return "Red Stained Clay";
			}
			if (b.byteValue() == 15) {
				return "Black Stained Clay";
			}
			return null;
		}
		if (material == Material.WOOL) {
			if (b.byteValue() == 0) {
				return "White Wool";
			}
			if (b.byteValue() == 1) {
				return "Orange Wool";
			}
			if (b.byteValue() == 2) {
				return "Magenta Wool";
			}
			if (b.byteValue() == 3) {
				return "Light Blue Wool";
			}
			if (b.byteValue() == 4) {
				return "Yellow Wool";
			}
			if (b.byteValue() == 5) {
				return "Lime Wool";
			}
			if (b.byteValue() == 6) {
				return "Pink Wool";
			}
			if (b.byteValue() == 7) {
				return "Gray Wool";
			}
			if (b.byteValue() == 8) {
				return "Light Gray Wool";
			}
			if (b.byteValue() == 9) {
				return "Cyan Wool";
			}
			if (b.byteValue() == 10) {
				return "Purple Wool";
			}
			if (b.byteValue() == 11) {
				return "Blue Wool";
			}
			if (b.byteValue() == 12) {
				return "Brown Wool";
			}
			if (b.byteValue() == 13) {
				return "Green Wool";
			}
			if (b.byteValue() == 14) {
				return "Red Wool";
			}
			if (b.byteValue() == 15) {
				return "Black Wool";
			}
			return null;
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	public static void givePlayer(Player p, Block b) {
		if (Material.STAINED_CLAY == b.getType() || Material.WOOL == b.getType()) {
			ItemStack block = new ItemStack(b.getType(), 1, (short) 0, b.getData());
			ItemMeta bmeta = block.getItemMeta();
			bmeta.setDisplayName(getName(b.getData(), b.getType()));
			block.setItemMeta(bmeta);
			p.getInventory().setItem(4, block);
		}else{
			ItemStack block = new ItemStack(b.getType(), 1, (short) 0, b.getData());
			ItemMeta bmeta = block.getItemMeta();
			bmeta.setDisplayName(b.getType().name());
			block.setItemMeta(bmeta);
			p.getInventory().setItem(4, block);
		}
		
	}
}