package com.lekohd.blockparty.commands;

/*
 * Copyright (C) 2014 Leon167, XxChxppellxX, ScriptJunkie and CPx1989
 */
import com.lekohd.blockparty.BlockParty;
import com.lekohd.blockparty.floor.AddFloor;
import com.lekohd.blockparty.floor.RemoveFloor;
import com.lekohd.blockparty.floor.SaveFloor;
import com.lekohd.blockparty.level.Period;
import com.lekohd.blockparty.system.Arena;
import com.lekohd.blockparty.system.Config;
import com.lekohd.blockparty.system.Players;
import com.lekohd.blockparty.system.Start;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class BlockPartyCommand implements CommandExecutor {
	public static String lobby = "Lobby";
	public static String game = "Game";

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if ((sender instanceof Player)) {
			Player p = (Player) sender;
			if (args.length == 0) {
				p.sendMessage("");
				p.sendMessage("§7BlockParty §6" + BlockParty.getInstance().getDescription().getVersion());
				p.sendMessage("");
				p.sendMessage("§8Developers: §3" + BlockParty.pdfFile.getAuthors());
				p.sendMessage("§8Commands: §3/blockparty help");
				return true;
			}
			if (args[0].equalsIgnoreCase("help")) {
				p.sendMessage(ChatColor.GREEN + "----" + " §6BlockParty " + ChatColor.AQUA + "Commands " + ChatColor.GREEN + "----");
				p.sendMessage("§3/blockparty info  §8Get all informations about the plugin");
				p.sendMessage("§3/blockparty join <arenaName>  §8Join the arena");
				p.sendMessage("§3/blockparty leave  §8Leave the arena");
                p.sendMessage("§3/blockparty stats <points|gamesplayed|roundssurvived|eliminations|victories|placings>  §8Get stats");
				if (p.hasPermission("blockparty.admin")) {
					p.sendMessage("§3/blockparty admin  §8Show the admin commands");
				}
				return true;
			}
			if (args[0].equalsIgnoreCase("admin")) {
				if (p.hasPermission("blockparty.admin")) {
					p.sendMessage(ChatColor.GREEN + "----" + " §6BlockParty " + ChatColor.AQUA + "Admin-Commands " + ChatColor.GREEN + "----");
					p.sendMessage("§3/blockparty start <arenaName>  §8Starts the game");
					p.sendMessage("§3/blockparty create <arenaName>  §8Creates an arena");
					p.sendMessage("§3/blockparty delete <arenaName>  §8Deletes an arena");
					p.sendMessage("§3/blockparty setSpawn <arenaName> <lobby|game>  §8Set the spawns for lobby|game");
					p.sendMessage("§3/blockparty setFloor <arenaName>  §8Set the floor for an arena");
					p.sendMessage("§3/blockparty addFloor <arenaName> <floorName>  §8Add a schematic floor to an arena");
					p.sendMessage("§3/blockparty removeFloor <arenaName> <floorName>  §8Remove a schematic floor of an arena");
					p.sendMessage("§3/blockparty enable <arenaName>  §8To enable an arena");
					p.sendMessage("§3/blockparty disable <arenaName>  §8To disable an arena");
					p.sendMessage("§3/blockparty reload  §8Reload the configs");
					p.sendMessage("§3/blockparty tutorial  §8Shows a tutorial of How to setup an arena");
				}
				return true;
			}

			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("status")) {
					if (p.hasPermission("blockparty.admin")) {
						String response = "";
						if (!BlockParty.arenaNames.isEmpty()) {
							for (String arenaName : BlockParty.arenaNames) {
								if (Players.getPlayerAmountInGame(arenaName) >= 1) {
									response += "§3[BlockParty] §8Arena §e" + arenaName + "§8 is running!\n\n";

									if (Players.getPlayerAmountOnFloor(arenaName) >= 1) {
										response += "§6Current Players On Floor:\n";
										for (String name : Players.getPlayersOnFloor(arenaName)) {
											response += "§4" + name + " - ";
										}
									}

									if (Players.getPlayerAmountInLobby(arenaName) >= 1) {
										response += "\n§6Current Players In Lobby:\n";
										for (String name : Players.getPlayersInLobby(arenaName)) {
											response += "§4" + name + " - ";
										}
									}
								} else {
									response += "§3[BlockParty] §8Arena " + arenaName + " is not currently active!";
								}

								p.sendMessage(response);
								return true;
							}
						} else {
							p.sendMessage("§3[BlockParty] §8No Arena's enabled!");
						}
					} else {
						p.sendMessage("§4You don't have the permissions to do that");
						return true;
					}
				}

				if (args[0].equalsIgnoreCase("leave")) {
					if (p.hasPermission("blockparty.user")) {
						Arena.leave(p);
					}
				}
				
				if ((args[0].equalsIgnoreCase("reload")) && (p.hasPermission("blockparty.admin"))) {
					Arena.reload(p);
					return true;
				}
				
				if ((args[0].equalsIgnoreCase("tutorial")) && (p.hasPermission("blockparty.admin"))) {
					p.sendMessage(ChatColor.GREEN + "----" + " §6BlockParty " + ChatColor.AQUA + "Tutorial " + ChatColor.GREEN + "----");
					p.sendMessage("§8 - Use §3/bp create <arenaName> §8to create your arena");
					p.sendMessage("§8 - Type in §3/bp enable <arenaName> §8to enable your arena");
					p.sendMessage("§8 - Go to the point, where you want to have the §3Lobby§8 spawn");
					p.sendMessage("§8 - And type in §3/bp setSpawn <arenaName> lobby");
					p.sendMessage("§8 - Select two points with §3WorldEdit§8, where you want to have the floor");
					p.sendMessage("§8 - Use §3/bp setFloor <arenaName> §8to set the floor");
					p.sendMessage("§8 - Go to the point, where you want to have the §3Game§8 spawn (on the floor)");
					p.sendMessage("§8 - And type in §3/bp setSpawn <arenaName> game");
					p.sendMessage("§8 - You can now start playing in your arena");
					p.sendMessage("§8 - If you want to use Schematics: /bp tutorial schematics");
				}
			}

			if (args.length == 2) {
				if (args[0].equalsIgnoreCase("join")) {
					if (p.hasPermission("blockparty.user")) {
						Arena.join(p, args[1]);
					} else {
						p.sendMessage("§4You don't have the permissions to do that");
					}
				}

                if(args[0].equalsIgnoreCase("stats"))
                {
                    if(args[1].equalsIgnoreCase("points"))
                    {
                        ArrayList<String> pList = BlockParty.statsManager.getSortedPlayersByPoints();
                        int i = 1;
                        p.sendMessage(ChatColor.GREEN + "----" + " §6BlockParty " + ChatColor.AQUA + "Stats " + ChatColor.GREEN + "----");
                        for(String name : pList)
                        {
                            p.sendMessage("§3§l" + i + ". §8" + name + ": §5" + BlockParty.statsManager.getPoints(name) + " §8Points");
                            i++;
                        }
                        p.sendMessage(" ");
                        p.sendMessage("§8Your Points: §5" + BlockParty.statsManager.getPoints(p.getName()));
                    }

                    if(args[1].equalsIgnoreCase("eliminations"))
                    {
                        ArrayList<String> pList = BlockParty.statsManager.getSortedPlayersByEliminations();
                        int i = 1;
                        p.sendMessage(ChatColor.GREEN + "----" + " §6BlockParty " + ChatColor.AQUA + "Stats " + ChatColor.GREEN + "----");
                        for(String name : pList)
                        {
                            p.sendMessage("§3§l" + i + ". §8" + name + ": §5" + BlockParty.statsManager.getEliminations(name) + " §8Eliminations");
                            i++;
                        }
                        p.sendMessage(" ");
                        p.sendMessage("§8Your Eliminations: §5" + BlockParty.statsManager.getEliminations(p.getName()));
                    }

                    if(args[1].equalsIgnoreCase("gamesplayed"))
                    {
                        ArrayList<String> pList = BlockParty.statsManager.getSortedPlayersByGamesPlayed();
                        int i = 1;
                        p.sendMessage(ChatColor.GREEN + "----" + " §6BlockParty " + ChatColor.AQUA + "Stats " + ChatColor.GREEN + "----");
                        for(String name : pList)
                        {
                            p.sendMessage("§3§l" + i + ". §8" + name + ": §5" + BlockParty.statsManager.getGamesPlayed(name) + " §8Games played");
                            i++;
                        }
                        p.sendMessage(" ");
                        p.sendMessage("§8You played: §5" + BlockParty.statsManager.getGamesPlayed(p.getName()) + " §8games");
                    }

                    if(args[1].equalsIgnoreCase("roundssurvived"))
                    {
                        ArrayList<String> pList = BlockParty.statsManager.getSortedPlayersByRoundsSurvived();
                        int i = 1;
                        p.sendMessage(ChatColor.GREEN + "----" + " §6BlockParty " + ChatColor.AQUA + "Stats " + ChatColor.GREEN + "----");
                        for(String name : pList)
                        {
                            p.sendMessage("§3§l" + i + ". §8" + name + ": §5" + BlockParty.statsManager.getRoundsSurvived(name) + " §8Rounds survived");
                            i++;
                        }
                        p.sendMessage(" ");
                        p.sendMessage("§8You survived: §5" + BlockParty.statsManager.getRoundsSurvived(p.getName()) + " §8rounds");
                    }

                    if(args[1].equalsIgnoreCase("victories"))
                    {
                        ArrayList<String> pList = BlockParty.statsManager.getSortedPlayersByVictories();
                        int i = 1;
                        p.sendMessage(ChatColor.GREEN + "----" + " §6BlockParty " + ChatColor.AQUA + "Stats " + ChatColor.GREEN + "----");
                        for(String name : pList)
                        {
                            p.sendMessage("§3§l" + i + ". §8" + name + ": §5" + BlockParty.statsManager.getVictories(name) + " §8Victories");
                            i++;
                        }
                        p.sendMessage(" ");
                        p.sendMessage("§8Your Victories: §5" + BlockParty.statsManager.getVictories(p.getName()));
                    }

                    if(args[1].equalsIgnoreCase("placings"))
                    {
                        ArrayList<String> pList = BlockParty.statsManager.getSortedPlayersByPlacings();
                        int i = 1;
                        p.sendMessage(ChatColor.GREEN + "----" + " §6BlockParty " + ChatColor.AQUA + "Stats " + ChatColor.GREEN + "----");
                        for(String name : pList)
                        {
                            p.sendMessage("§3§l" + i + ". §8" + name + ": §5" + BlockParty.statsManager.getPlacings(name) + " §8Placings");
                            i++;
                        }
                        p.sendMessage(" ");
                        p.sendMessage("§8Your Placings: §5" + BlockParty.statsManager.getPlacings(p.getName()));
                    }
                }

				if ((args[0].equalsIgnoreCase("tutorial")) && (args[1].equalsIgnoreCase("schematics")) && (p.hasPermission("blockparty.admin"))) {
					p.sendMessage(ChatColor.GREEN + "----" + " §6BlockParty " + ChatColor.AQUA + "Schematics-Tutorial " + ChatColor.GREEN + "----");
					p.sendMessage("§8 - Create a floor Schematic, using WorldEdit, or take the example");
					p.sendMessage("§8 - Copy the Schematic from the WorldEdit folder to the BlockParty Floors folder");
					p.sendMessage("§8 - If there is no Floors folder, create one");
					p.sendMessage("§8 - Use §3/bp addFloor <arenaName> <floorName>");
					p.sendMessage("§8 - <floorName> must be the schematic name");
					p.sendMessage("§8 - Go into your Arena config and change UseSchematicFloors to true");
					p.sendMessage("§8 - Reload or Restart your Server");
					p.sendMessage("§8 - Make sure your floor appears in the Arena config (enabledFloors)");
					p.sendMessage("§8 - Now you can start your game");
					p.sendMessage("§8 - Name one schematic 'start'. Than this schematic will load first");
				}
				if (args[0].equalsIgnoreCase("create")) {
					if (p.hasPermission("blockparty.admin")) {
						Arena.create(p, args[1]);
					} else {
						p.sendMessage("§4You don't have the permissions to do that");
					}
					return true;
				}
				if (args[0].equalsIgnoreCase("start")) {
					if (p.hasPermission("blockparty.admin")) {
						if (BlockParty.getArena.containsKey(args[1])) {
							((Config) BlockParty.getArena.get(args[1])).abort();
							Start.startGame(args[1], p);
						} else {
							p.sendMessage("§3[BlockParty] §8Arena " + args[1] + " isn't enabled or doesn't exists!");
						}
					} else {
						p.sendMessage("§4You don't have the permissions to do that");
					}
				}
				if (args[0].equalsIgnoreCase("stop")) {
					if (p.hasPermission("blockparty.admin")) {
						if (BlockParty.getArena.containsKey(args[1])) {
							((Config) BlockParty.getArena.get(args[1])).abort();
							// stop timers and cleanup here
							Period.stop(args[1]);
							Config.stopGameInProgress(args[1], true);
						} else {
							p.sendMessage("§3[BlockParty] §8Arena " + args[1] + " isn't enabled or doesn't exists!");
						}
					} else {
						p.sendMessage("§4You don't have the permissions to do that");
					}
				}

				if (args[0].equalsIgnoreCase("delete")) {
					if (p.hasPermission("blockparty.admin")) {
						Arena.delete(args[1], p);
					}
				}
				if (args[0].equalsIgnoreCase("setFloor")) {
					if (p.hasPermission("blockparty.admin")) {
						if (BlockParty.getArena.containsKey(args[1])) {
							SaveFloor.setFloor(p, args[1]);
						} else {
							p.sendMessage("§3[BlockParty] §8Arena " + args[1] + " isn't enabled or doesn't exists!");
						}
					}
				}
				if (args[0].equalsIgnoreCase("enable")) {
					if (p.hasPermission("blockparty.admin")) {
						Arena.enable(args[1], p);
					}
				}
				if (args[0].equalsIgnoreCase("disable")) {
					if (p.hasPermission("blockparty.admin")) {
						if (BlockParty.getArena.containsKey(args[1])) {
							Arena.disable(args[1], p);
						} else {
							p.sendMessage("§3[BlockParty] §8Arena " + args[1] + " isn't enabled or doesn't exists!");
						}
					}
				}

			}
			if (args.length == 3) {
				if (args[0].equalsIgnoreCase("setspawn")) {
					if (p.hasPermission("blockparty.admin")) {
						if (BlockParty.getArena.containsKey(args[1])) {
							if (args[2].equalsIgnoreCase(lobby)) {
								Arena.setSpawn(p, args[1], lobby);
							}
							if (args[2].equalsIgnoreCase(game)) {
								Arena.setSpawn(p, args[1], game);
							}
							return true;
						}
						p.sendMessage("§3[BlockParty] §8Arena " + args[1] + " isn't enabled or doesn't exists!");
					} else {
						p.sendMessage("§4You don't have the permissions to do that");
						return true;
					}
				}
				if (args[0].equalsIgnoreCase("addFloor")) {
					if (p.hasPermission("blockparty.admin")) {
						if (BlockParty.getArena.containsKey(args[1])) {
							AddFloor.add(args[1], args[2]);
							p.sendMessage("§3[BlockParty] §8Floor " + args[2] + " was added to Arena " + args[1]);
							p.sendMessage("§3[BlockParty] §8Be sure, that the floor exists!");
							p.sendMessage("§3[BlockParty] §8It is recommended, that the floors have the same size!");
							p.sendMessage("§3[BlockParty] §7Maybe the Floor will not be added. Take a look in you arena config!");
						} else {
							p.sendMessage("§3[BlockParty] §8Arena " + args[1] + " isn't enabled or doesn't exists!");
						}
					}
				}
				if (args[0].equalsIgnoreCase("removeFloor")) {
					if (p.hasPermission("blockparty.admin")) {
						if (BlockParty.getArena.containsKey(args[1])) {
							RemoveFloor.add(args[1], args[2]);
							p.sendMessage("§3[BlockParty] §7Maybe the Floor will not be removed. Take a look in you arena config!");
						} else {
							p.sendMessage("§3[BlockParty] §8Arena " + args[1] + " isn't enabled or doesn't exists!");
						}
					}
				}
			}
		} else {
			ConsoleCommandSender cs = (ConsoleCommandSender) sender;
			cs.sendMessage("");
			cs.sendMessage("§7BlockParty §6" + BlockParty.getInstance().getDescription().getVersion());
			cs.sendMessage("");
			cs.sendMessage("§8Developer: §3LekoHD, ScriptJunkie");
			cs.sendMessage("§8Commands: §3/blockparty help");
			return true;
		}
		return true;
	}
}