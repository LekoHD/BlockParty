package com.lekohd.blockparty.webplayer;

import java.sql.Connection;
import java.sql.SQLException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import code.husky.mysql.MySQL;

public class Database {
	
	MySQL mysql; 
	Connection dbcon;
	
	public void initialize(Plugin plugin){
		FileConfiguration cfg = plugin.getConfig();
		String host = cfg.getString("webPlayer.mysql.host");
		String port = cfg.getString("webPlayer.mysql.port");
		String db = cfg.getString("webPlayer.mysql.database");
		String user = cfg.getString("webPlayer.mysql.user");
		String pass = cfg.getString("webPlayer.mysql.password");
		mysql = new MySQL(plugin, host, port, db, user, pass);
		try {
			dbcon = mysql.openConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
