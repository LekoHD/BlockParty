package com.lekohd.blockparty.listeners;

/*
 * Copyright (C) 2014 Leon167, XxChxppellxX and ScriptJunkie 
 */
import com.lekohd.blockparty.BlockParty;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {
	@EventHandler
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		String[] commandArgs = e.getMessage().split("");

		if (BlockParty.inGamePlayers.containsKey(p.getName())) {
			if (!p.isOp()) {
				if (!p.hasPermission("blockparty.admin") || !p.hasPermission("blockparty.cmdbypass")) {
					if (!checkCommand(commandArgs[0]))
						e.setCancelled(true);

				}
				return;
			} else {

			}
		}
	}

	//need to put commands in config.
	private boolean checkCommand(String command) {
        if( command.equalsIgnoreCase("/msg") || command.equalsIgnoreCase("/ban") || command.equalsIgnoreCase("/mute")||command.equalsIgnoreCase("/a")||command.equalsIgnoreCase("/kick"))
        {
            return true;
        }
        return false;
	}
}