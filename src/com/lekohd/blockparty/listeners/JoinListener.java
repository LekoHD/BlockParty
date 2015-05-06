package com.lekohd.blockparty.listeners;

import com.lekohd.blockparty.BlockParty;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by Leon on 05.05.2015.
 * Project BlockParty
 * <p/>
 * Copyright (C) 2014 Leon167 { LekoHD
 */
public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        BlockParty.statsManager.getStats(e.getPlayer());
       // BlockParty.statsManager.storeStats(e.getPlayer());
    }

}
