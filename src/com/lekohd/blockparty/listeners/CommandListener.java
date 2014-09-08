package com.lekohd.blockparty.listeners;

import com.lekohd.blockparty.BlockParty;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {
	@EventHandler
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		if ((BlockParty.inGamePlayers.containsKey(p.getName())) || (BlockParty.onFloorPlayers.containsKey(p.getName())) || (BlockParty.inLobbyPlayers.containsKey(p.getName()))) {
			if (!p.isOp()) {
				e.setCancelled(true);
			}
		}
	}
}