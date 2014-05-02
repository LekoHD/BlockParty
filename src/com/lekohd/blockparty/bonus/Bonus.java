package com.lekohd.blockparty.bonus;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.lekohd.blockparty.BlockParty;

/*
 * Copyright (C) 2014 Leon167 and XxChxppellxX 
 */

public class Bonus {

	public static int dc;
	public static int duration = 10;
	
	public static void playEf(final Player p, final String ef){
		duration = BlockParty.getArena.get(BlockParty.onFloorPlayers.get(p.getName())).getBoostDuration() + 1;
		if(duration > 0){
			if(ef.equalsIgnoreCase("walk"))
				{
					p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, duration*20, 2));
					p.sendMessage("§3[BlockParty] §8You walk speed has increased.");
				}
			if(ef.equalsIgnoreCase("jump"))
				{
					p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, duration*20, 3));
					p.sendMessage("§3[BlockParty] §8You jump hight has increased.");
				}
			if(ef.equalsIgnoreCase("nausea"))
				{
					p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, duration*20, 1));
					p.sendMessage("§3[BlockParty] §8Ohh... This was a bad effect.");
					return;
				}
			if(ef.equalsIgnoreCase("blindness"))
				{
					p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, duration*20, 1));
					p.sendMessage("§3[BlockParty] §8Ohh... This was a bad effect.");
					return;
				}
			dc = Bukkit.getScheduler().scheduleSyncRepeatingTask(BlockParty.getInstance(), new Runnable() {
				public void run(){
					if(duration!=0)
					{
						if(!(duration <= 1))
						{
							duration--;
						}
						else
						{
							if(duration > -1)
							{
								p.sendMessage("§3[BlockParty] §8Your effect has expired");
								Bukkit.getScheduler().cancelTask(dc);
							}
							else
								Bukkit.getScheduler().cancelTask(dc);
						}
					}
				}
			}, 0L, 20L);
		}
	}
	
}
