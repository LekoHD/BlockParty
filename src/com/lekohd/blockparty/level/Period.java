package com.lekohd.blockparty.level;

import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.lekohd.blockparty.Main;
import com.lekohd.blockparty.bonus.Boosts;
import com.lekohd.blockparty.floor.FloorBlock;
import com.lekohd.blockparty.floor.LoadFloor;
import com.lekohd.blockparty.floor.RandomizeFloor;
import com.lekohd.blockparty.floor.RemoveBlocks;
import com.lekohd.blockparty.music.Songs;
import com.lekohd.blockparty.sign.Signs;
import com.lekohd.blockparty.system.Arena;
import com.lekohd.blockparty.system.Players;

public class Period {

	public static double number = 16;  // TODO Muss noch configurierbar sein!
	public static int periods = 15; // TODO Muss noch configurierbar sein!
	public static int counter = 0;
	public static double multiplier = 0.5; // TODO Muss noch configurierbar sein!
	public int cd;
	static int num = 6;
	public int dc;
	public static int cg = 0 ;
	public static boolean setStart;
	
	public int getCd(){
		return cd;
	}
	
	public static void setFloor(String arenaName, boolean fall){
		final String aName = arenaName;
		//System.out.println(Main.getArena.get(aName).getAutoGenerateFloors() + " " + Main.getArena.get(aName).getUseSchematicFloors());
		if(!(Main.getArena.get(aName).getAutoGenerateFloors()) && (Main.getArena.get(aName).getUseSchematicFloors())){
			setShFloor(aName);
		}
		if((Main.getArena.get(aName).getAutoGenerateFloors()) && !(Main.getArena.get(aName).getUseSchematicFloors())){
			RandomizeFloor.place(aName);
		}
		if((Main.getArena.get(aName).getAutoGenerateFloors()) && (Main.getArena.get(aName).getUseSchematicFloors())){
			double id = (Math.random()*(Main.getFloor.get(aName).size()+1));
			if(fall == true){
				setShFloor(aName);
			}
			else
			{
				if(id>1)
				{
					setShFloor(aName);
				}
				else
				{
					RandomizeFloor.place(aName);
				}
			}
		}
	}
	
	public static void setShFloor(String aName){
		if(Main.getFloor.get(aName).isEmpty()){
			System.err.println("Blockparty: There are no floors for Arena " + aName);
		} else
		{
			if(Main.getFloor.get(aName).size() == 1){
				Main.getFloor.get(aName).get(0).placeFloor(aName);
			}
			else
				
			{
				byte id = (byte)(Math.random()*(Main.getFloor.get(aName).size()));
				if(Main.getArena.get(aName).getStart() == true){
					Main.getFloor.get(aName).get(id).placeFloor(aName); // TODO In die Config, dass die nicht zufällig sein müssen!
				}
				if(Main.getArena.get(aName).getStart() == false){
					if(Main.getFloor.get(aName).size() > 1)
					{
						for(LoadFloor l : Main.getFloor.get(aName)){
							if(l.getFloorName().equalsIgnoreCase("start")){
								l.placeFloor(aName);
								Main.getArena.get(aName).setStart(true);
							}
						}
						if(Main.getArena.get(aName).getStart() == false){
							Main.getFloor.get(aName).get(id).placeFloor(aName);
						}
					}
				}
			}
		}
	}
	
	public void delayedStart(String arenaName, int c){
		
		final String aName;
		aName=arenaName;
		cg=c;
		num=6;
		if(Bukkit.getPluginManager().isPluginEnabled("BarAPI")){
			if(!(Players.getPlayerAmountOnFloor(aName) == 1)){
				for(Player p : Players.getPlayersOnFloor(aName))
				{
					BarAPI.setMessage(p, "Waiting ...", (float) 100);
				}
			}
			else
			{
				BarAPI.setMessage(Players.getPlayersOnFloor(aName).get(0),"Waiting ...", (float) 100);
			}
		}
		dc = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
			public void run(){
				if(num!=0)
				{
					if(!(num <= 1))
					{
						num--;
					}
					else
					{
						start(aName, cg, null);
						Bukkit.getScheduler().cancelTask(dc);
					}
				}
			}
		}, 0L, 20L);
	}
	
	public void start(String arenaName, int c, final Boosts bo){
		final String aName = arenaName;
		final Boosts b = new Boosts();
		//counter = 0;
		counter=c;
		number = Main.getArena.get(arenaName).getTimeToSearch() + 10;
		multiplier = Main.getArena.get(arenaName).getTimeReductionPerLevel();
		periods = Main.getArena.get(arenaName).getLevel();
		setStart = Main.getArena.get(arenaName).getStart();
		//setFloor(aName);
		final Byte randomNum =  (byte)RandomizeFloor.randomizedItem(aName);
		for(Player p : Players.getPlayersOnFloor(aName)){
			p.sendMessage("§3[BlockParty] §8Next Block is " + FloorBlock.getName(randomNum) + " !");
			FloorBlock.givePlayer(p, randomNum);
			p.setLevel(counter+1);
		}
		number = number - counter*multiplier;
		final double numb = number;
		if(counter <= periods && number>10)
		{
			
			cd = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
				public void run(){
					if(number!=0)
					{
						if(!(number <= 1))
						{
							number--;
							if(number > 10){
								if(Bukkit.getPluginManager().isPluginEnabled("BarAPI")){
									if(!(Players.getPlayerAmountOnFloor(aName) == 1)){
										for(Player p : Players.getPlayersOnFloor(aName))
										{
											BarAPI.setMessage(p, "Dance", (float) ((number-10)/(numb-10)*100));
										}
									}
									else
									{
										BarAPI.setMessage(Players.getPlayersOnFloor(aName).get(0), "Dance", (float) ((number-10)/(numb-10)*100));
									}
								}
							}
							if(number<=10 && number>9)
								{
									if(Bukkit.getPluginManager().isPluginEnabled("BarAPI")){
										BarAPI.setMessage("STOP", 5);
									}
									if(Bukkit.getPluginManager().isPluginEnabled("BarAPI")){
										if(!(Players.getPlayerAmountOnFloor(aName) == 1)){
											for(Player p : Players.getPlayersOnFloor(aName))
											{
												BarAPI.setMessage(p, "STOP", 5);
											}
										}
										else
										{
											BarAPI.setMessage(Players.getPlayersOnFloor(aName).get(0), "STOP", 5);
										}
									}
									RemoveBlocks.remove(aName, randomNum);
									if(Main.getArena.get(aName).getUseBoosts()){
										if(bo != null)
											bo.remove();
									}
								}
							if(number<=5 && number>4)
							{
								setFloor(aName, false);
								for(Player p : Players.getPlayersOnFloor(aName)){
									p.getInventory().remove(Material.STAINED_CLAY);
								}
								if(Bukkit.getPluginManager().isPluginEnabled("BarAPI")){
									if(!(Players.getPlayerAmountOnFloor(aName) == 1)){
										for(Player p : Players.getPlayersOnFloor(aName))
										{
											BarAPI.setMessage(p, "Waiting ...", (float) 100);
										}
									}
									else
									{
										BarAPI.setMessage(Players.getPlayersOnFloor(aName).get(0),"Waiting ...", (float) 100);
									}
								}
								if(Main.getArena.get(aName).getUseBoosts()){
									if(counter == 3 || counter == 7 || counter == 11 || counter == 15)
									{
										b.place(aName);
										if(!(Players.getPlayerAmountOnFloor(aName) == 1)){
											for(Player p : Players.getPlayersOnFloor(aName))
											{
												p.sendMessage("§3[BlockParty] §8A Boost have been summoned!");
											}
										}
									}
								}
							}
							if(Players.getPlayerAmountOnFloor(aName) == 1){
								Bukkit.getScheduler().cancelTask(cd);
								Main.getArena.get(aName).setStart(false);
								Main.getArena.get(aName).setGameProgress("inLobby");
								Signs.updateGameProgress(aName, true);
								setFloor(aName, true);
								Players.getPlayersOnFloor(aName).get(0).teleport(Arena.getGameSpawn(aName));
								if(Bukkit.getPluginManager().isPluginEnabled("NoteBlockAPI"))
									Songs.stop(Players.getPlayersOnFloor(aName).get(0));
								if(Main.getArena.get(aName).getUseBoosts()){
									if(bo != null)
										bo.remove();
								}
								Players.getPlayersOnFloor(aName).get(0).sendMessage("§3[BlockParty] §8Congratulations! You won the game.");   // TODO set old spawn Floor
								ItemStack[] is;
								if(Main.getArena.get(aName).getRewardItems().size() > 1)
								{
									is = Main.inv.get(Players.getPlayersOnFloor(aName).get(0));
									Inventory inv = Players.getPlayersOnFloor(aName).get(0).getInventory();
									inv.setContents(is);
									for(int item : (Main.getArena.get(aName).getRewardItems()))
									{
										if(is!= null){}
											inv.addItem(getItem(item));
									}
									is = inv.getContents();
									Main.inv.put(Players.getPlayersOnFloor(aName).get(0), is);
									Players.getPlayersOnFloor(aName).get(0).getInventory().clear();
									Players.getPlayersOnFloor(aName).get(0).sendMessage("§3[BlockParty] §8You will get you reward when you leave the arena!");
								}
								else if(Main.getArena.get(aName).getRewardItems().size() == 1){
									is = Main.inv.get(Players.getPlayersOnFloor(aName).get(0));
									if(is!= null)
										{
											Inventory inv = Players.getPlayersOnFloor(aName).get(0).getInventory();
											inv.setContents(is);
											inv.addItem(getItem(Main.getArena.get(aName).getRewardItems().get(0)));
											is = inv.getContents();
											Main.inv.put(Players.getPlayersOnFloor(aName).get(0), is);
											Players.getPlayersOnFloor(aName).get(0).getInventory().clear();
											Players.getPlayersOnFloor(aName).get(0).sendMessage("§3[BlockParty] §8You will get you reward when you leave the arena!");
										}
									
								}
								else
								{
									
								}
								WinnerCountdown.start(aName);
								if(!(Players.getPlayerAmountInLobby(aName) <= 1)){
									for(Player p : Players.getPlayersInLobby(aName))
									{
										p.sendMessage("§3[BlockParty] §8Player " + Players.getPlayersInGame(aName).get(0).getName() + " won the game.");
									}
								}
								else if (Players.getPlayerAmountInLobby(aName)!=0)
								{				
									Players.getPlayersInLobby(aName).get(0).sendMessage("§3[BlockParty] §8Player " + Players.getPlayersInGame(aName).get(0).getName() + " won the game.");
								}
							}
						}
						else
						{
							counter++;
							//Main.getArena.get(aName).setStart(false);
							Bukkit.getScheduler().cancelTask(cd);
							start(aName, counter, b);
						}
					}
					else
					{
						Bukkit.getScheduler().cancelTask(cd);
						System.out.println("[BlockParty] ERROR: The countdown is less than 1");
					}
					//Bukkit.getScheduler().cancelTask(cd);
				}
			}, 0L, 20L);
	        
		}
		else if(Players.getPlayerAmountOnFloor(aName) > 1)
		{
			Main.getArena.get(aName).setStart(false);
			setFloor(aName, true);
			if(Main.getArena.get(aName).getUseBoosts()){
				if(bo != null)
					bo.remove();
			}
			Main.getArena.get(aName).setGameProgress("inLobby");
			Signs.updateGameProgress(aName, true);
			for(Player p : Players.getPlayersOnFloor(aName))
			{
				p.sendMessage("§3[BlockParty] §8Congratulations! You won the game.");
				if(Bukkit.getPluginManager().isPluginEnabled("NoteBlockAPI"))
					Songs.stop(p);
				ItemStack[] is;
				if(Main.getArena.get(aName).getRewardItems().size() > 1)
				{
					is = Main.inv.get(p);
					Inventory inv = p.getInventory();
					inv.setContents(is);
					//inv.addItem(getItem(Main.getArena.get(aName).getRewardItems().get(0)));
					//is = inv.getContents();
					//Main.inv.put(Players.getPlayersOnFloor(aName).get(0), is);
					for(int item : (Main.getArena.get(aName).getRewardItems()))
					{
						inv.addItem(getItem(item));
					}
					is = inv.getContents();
					Main.inv.put(p, is);
					p.getInventory().clear();
					p.sendMessage("§3[BlockParty] §8You will get you reward when you leave the arena!");
				}
				else if(Main.getArena.get(aName).getRewardItems().size() == 1){
					is = Main.inv.get(p);
					Inventory inv = p.getInventory();
					inv.setContents(is);
					inv.addItem(getItem(Main.getArena.get(aName).getRewardItems().get(0)));
					is = inv.getContents();
					Main.inv.put(p, is);
					p.getInventory().clear();
					p.sendMessage("§3[BlockParty] §8You will get you reward when you leave the arena!");
				}
				else
				{
					
				}
			}
			if(!(Players.getPlayerAmountInLobby(aName) == 1)){
				for(Player p : Players.getPlayersInLobby(aName))
				{
					p.sendMessage("§3[BlockParty] §8Players" + getWinners(aName) + " won the game.");
				}
			}
			else
			{
				Players.getPlayersInLobby(aName).get(0).sendMessage("§3[BlockParty] §8Players" + getWinners(aName) + " won the game.");
			}
			WinnerCountdown.start(aName);
		}
	}
	
	
	public String getWinners(String arenaName){
		String names = "";
		for(Player p : Players.getPlayersInGame(arenaName))
		{
			names = names + " " + p.getName();
		}
		return names;
	}
	
	@SuppressWarnings("deprecation")
	public ItemStack getItem(int id){
		    ItemStack item = new ItemStack(id, 1);
		    return item;
	}
	/*public static void telBackToLobby(String arenaName){ // TODO Nicht doch PlayersOnLobby
		//if(Players.getPlayersInGame(arenaName).)
		for(Player p : Players.getPlayersInGame(arenaName))
		{
			if(!Players.getPlayersInGame(arenaName).contains(p))
			{
				p.teleport(Arena.getLobbySpawn(arenaName));
				Main.inGamePlayers.remove(p);
				Main.inLobbyPlayers.put(p, arenaName);
					
			}
		}
		
	}*/
}
