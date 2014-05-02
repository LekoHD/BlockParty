package com.lekohd.blockparty.level;

import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.lekohd.blockparty.BlockParty;
import com.lekohd.blockparty.system.Arena;
import com.lekohd.blockparty.system.Players;
import com.lekohd.blockparty.system.Start;

/*
 * Copyright (C) 2014 Leon167 and XxChxppellxX 
 */
 
//import org.bukkit.craftbukkit.v1_7_R2.CraftWorld;

public class WinnerCountdown {
	
	public static int cd;
	public static double number = 6;

	public static void start(String arenaName){
		final String aName = arenaName;
		number = 6;
        cd = Bukkit.getScheduler().scheduleSyncRepeatingTask(BlockParty.getInstance(), new Runnable() {
			@SuppressWarnings("deprecation")
			public void run(){
				if(number!=0)
				{
					if(number != 1)
					{
						number--;
					}
					else
					{
						Bukkit.getScheduler().cancelTask(cd);
						if(Players.getPlayerAmountOnFloor(aName) == 1)
						{
							if(Bukkit.getPluginManager().isPluginEnabled("BarAPI")){
								BarAPI.setMessage(Bukkit.getPlayer(Players.getPlayersOnFloor(aName).get(0)), "Waiting ...", (float) 100);
							}
							Bukkit.getPlayer(Players.getPlayersOnFloor(aName).get(0)).teleport(Arena.getLobbySpawn(aName));
							BlockParty.inLobbyPlayers.put(Players.getPlayersOnFloor(aName).get(0), aName);
							Bukkit.getPlayer(Players.getPlayersOnFloor(aName).get(0)).getInventory().addItem(BlockParty.getArena.get(aName).getVoteItem());
							Bukkit.getPlayer(Players.getPlayersOnFloor(aName).get(0)).updateInventory();
							BlockParty.inGamePlayers.remove(Players.getPlayersOnFloor(aName).get(0));
							BlockParty.onFloorPlayers.remove(Players.getPlayersOnFloor(aName).get(0));
							BlockParty.getArena.get(aName).setGameProgress("inLobby");
							Start.start(aName);
						}
						else if(Players.getPlayerAmountOnFloor(aName) > 1)
						{
							for(String name : Players.getPlayersOnFloor(aName))
							{
								Player p = Bukkit.getPlayer(name);
								if(Bukkit.getPluginManager().isPluginEnabled("BarAPI")){
									BarAPI.setMessage(p, "Waiting ...", (float) 100);
								}
								//((CraftWorld) p.getWorld()).getHandle().a("magicCrit", p.getLocation().getX(), p.getLocation().getY() + 2, p.getLocation().getZ(), 5, 0.2, 0.2, 0.2, 0.2);
								p.teleport(Arena.getLobbySpawn(aName));
								BlockParty.inGamePlayers.remove(p.getName());
								BlockParty.onFloorPlayers.remove(p.getName());
								BlockParty.inLobbyPlayers.put(p.getName(), aName);
								p.getInventory().addItem(BlockParty.getArena.get(aName).getVoteItem());
								p.updateInventory();
								BlockParty.getArena.get(aName).setGameProgress("inLobby");
								Start.start(aName);
							}
						}
					}
				}
				else
				{
					Bukkit.getScheduler().cancelTask(cd);
					System.err.println("[BlockParty] ERROR: The countdown is less than 1");
				}
			}
		}, 0L, 20L);
	}
	
	/*public static void shootFireworks(String arenaName){
		Location max = BlockParty.getArena.get(arenaName).getLocMax();
		Location min = BlockParty.getArena.get(arenaName).getLocMin();
		max.setY(BlockParty.getArena.get(arenaName).getLocMax().getBlockX() + 10);
		min.setY(BlockParty.getArena.get(arenaName).getLocMax().getBlockX() + 10);
		Firework fwMax = (Firework) BlockParty.getArena.get(arenaName).getWorld().spawn(max, Firework.class);
		Firework fwMin = (Firework) BlockParty.getArena.get(arenaName).getWorld().spawn(min, Firework.class);
		FireworkMeta fmMax = fwMax.getFireworkMeta();
		FireworkMeta fmMin = fwMax.getFireworkMeta();
		Random r = new Random();
		int fType = r.nextInt(4) + 1;
		Type type = null;
		switch (fType) {
		default:
		case 1:
			type = Type.BALL;
			break;
		case 2:
			type = Type.BALL_LARGE;
			break;
		case 3: 
			type = Type.BURST;
			break;
		case 4:
			type = Type.STAR;
			break;
		}
		int c1 = r.nextInt(17) + 1;
		int c2 = r.nextInt(17) + 1;
		FireworkEffect efMin = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(getColor(c1)).withFade(getColor(c2)).with(type).trail(r.nextBoolean()).build();
		FireworkEffect efMax = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(getColor(c1)).withFade(getColor(c2)).with(type).trail(r.nextBoolean()).build();
		fmMax.addEffect(efMax);
		fmMin.addEffect(efMin);
		int power = r.nextInt(3) + 1;
		fmMax.setPower(power);
		fmMin.setPower(power);
		fwMax.setFireworkMeta(fmMax);
		fwMin.setFireworkMeta(fmMin);
	}
	
	public static Color getColor(int c){
		switch(c){
		default:
		case 1: return Color.AQUA;
		case 2: return Color.BLACK;
		case 3: return Color.BLUE;
		case 4: return Color.FUCHSIA;
		case 5: return Color.GRAY;
		case 6: return Color.GREEN;
		case 7: return Color.LIME;
		case 8: return Color.MAROON;
		case 9: return Color.NAVY;
		case 10: return Color.OLIVE;
		case 11: return Color.ORANGE;
		case 12: return Color.PURPLE;
		case 13: return Color.RED;
		case 14: return Color.SILVER;
		case 15: return Color.TEAL;
		case 16: return Color.WHITE;
		case 17: return Color.YELLOW;
		}
	}*/
	
}
