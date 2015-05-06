package com.lekohd.blockparty.scoreboardsystem;
/*
 * Copyright (C) 2014 Leon167, XxChxppellxX, ScriptJunkie and CPx1989
 */
import com.lekohd.blockparty.BlockParty;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class ScoreboardSys {

    Objective ob;
	@SuppressWarnings("deprecation")
	public static void setGameScore(Player p, int dancers, int round, int seconds) {
		Scoreboard playerboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = playerboard.getObjective("Info");
        if(objective == null)
        {
            objective = playerboard.registerNewObjective("Info", "dummy");
        }
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName("§6§lBlockParty");
		Score s1 = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.BLUE + "" + ChatColor.BOLD + "Dancers"));
		Score s2 = objective.getScore(Bukkit.getOfflinePlayer("§1§f" + dancers));
		Score s3 = objective.getScore(Bukkit.getOfflinePlayer("§2§f    "));
		Score s4 = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "" + ChatColor.BOLD + "Round Number"));
		Score s5 = objective.getScore(Bukkit.getOfflinePlayer("§3§f" + round));
		Score s6 = objective.getScore(Bukkit.getOfflinePlayer("§4§f    "));
		Score s7 = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.RED + "" + ChatColor.BOLD + "Seconds Left"));
		Score s8 = objective.getScore(Bukkit.getOfflinePlayer("§5§f" + seconds));
		s1.setScore(8);
		s2.setScore(7);
		s3.setScore(6);
		s4.setScore(5);
		s5.setScore(4);
		s6.setScore(3);
		s7.setScore(2);
		s8.setScore(1);
		p.setScoreboard(playerboard);
	}
    @SuppressWarnings("deprecation")
    public static void setLobbyScore(Player p) {
        Scoreboard playerboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = playerboard.getObjective("Stats");
        if(objective == null)
        {
            objective = playerboard.registerNewObjective("Stats", "dummy");
        }
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.GOLD + "BlockParty");
        Score s1 = objective.getScore(Bukkit.getOfflinePlayer("§3Games Played"));
        Score s2 = objective.getScore(Bukkit.getOfflinePlayer("§1§3Eliminations"));
        Score s3 = objective.getScore(Bukkit.getOfflinePlayer("§2§3Placings"));
        Score s4 = objective.getScore(Bukkit.getOfflinePlayer("§4§3Victories"));
        Score s5 = objective.getScore(Bukkit.getOfflinePlayer("§5§3Points"));
        Score s6 = objective.getScore(Bukkit.getOfflinePlayer("§5§3Rounds Survived"));
        s1.setScore(BlockParty.statsManager.getGamesPlayed(p));
        s2.setScore(BlockParty.statsManager.getEliminations(p));
        s3.setScore(BlockParty.statsManager.getPlacings(p));
        s4.setScore(BlockParty.statsManager.getVictories(p));
        s5.setScore(BlockParty.statsManager.getPoints(p));
        s6.setScore(BlockParty.statsManager.getRoundsSurvived(p));

        p.setScoreboard(playerboard);
    }

    public static void removeScore(Player p)
    {
        /*Scoreboard playerboard = p.getScoreboard();
        Objective objective = playerboard.getObjective("Info");
        if(objective == null)
        {
            objective = playerboard.getObjective("Info");
            if(objective == null)
            {
                return;
            }
            objective.unregister();
            return;
        }
        objective.unregister();*/
        Scoreboard s = Bukkit.getScoreboardManager().getNewScoreboard();
        p.setScoreboard(s);
    }
}