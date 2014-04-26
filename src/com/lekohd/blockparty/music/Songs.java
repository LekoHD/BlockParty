package com.lekohd.blockparty.music;

import java.io.File;

import org.bukkit.entity.Player;

import com.xxmicloxx.NoteBlockAPI.NBSDecoder;
import com.xxmicloxx.NoteBlockAPI.NoteBlockPlayerMain;
import com.xxmicloxx.NoteBlockAPI.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.Song;
import com.xxmicloxx.NoteBlockAPI.SongPlayer;

/*
 * Copyright (C) 2014 Leon167 and XxChxppellxX 
 */
 
public class Songs {
	public static void play(Player p, String song){
        Player target = p;
        File file = new File("plugins/BlockParty/Songs/", song + ".nbs");
        if (!file.exists()) {
          p.sendMessage("§3[BlockParty] §8" + song + " does not exist ..");
        }
        else
        {
	        playSong(target, song + ".nbs");
	        p.sendMessage("§3[BlockParty] §8Playing song " + song);
        }
	}
	public static void playSong(Player p, String song) {
	    Song s = NBSDecoder.parse(new File("plugins/BlockParty/Songs/" + song));
	    SongPlayer sp = new RadioSongPlayer(s);
	    sp.setAutoDestroy(true);
	    sp.addPlayer(p);
	    sp.setPlaying(true);
	  }
	public static void stop(Player p){
		NoteBlockPlayerMain.stopPlaying(p);
	}
}
