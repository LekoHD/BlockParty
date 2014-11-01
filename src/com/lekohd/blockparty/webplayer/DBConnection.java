package com.lekohd.blockparty.webplayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import code.husky.mysql.MySQL;

public class DBConnection {
	
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
		checkTables();
	}
	
	@SuppressWarnings("unused")
	private void checkTables() {
		try {
			Statement statement = dbcon.createStatement();
			String songs = "CREATE TABLE IF NOT EXISTS `songs` (`Index` int(11) NOT NULL AUTO_INCREMENT,`Title` varchar(32) NOT NULL,`Artist` varchar(32) NOT NULL,`Filename` varchar(32) NOT NULL,PRIMARY KEY (`Index`)) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;";
			String info = "CREATE TABLE IF NOT EXISTS `info` (`Object` varchar(16) NOT NULL,`Value` varchar(16) NOT NULL,PRIMARY KEY (`Object`)) ENGINE=InnoDB DEFAULT CHARSET=latin1;";
			ResultSet res = statement.executeQuery(songs + info);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public List<String> getSongs(){
		List<String> songlist = new ArrayList<String>();
		try {
			Statement statement = dbcon.createStatement();
			ResultSet res = statement.executeQuery("SELECT * FROM Songs;");
			while(res.next()){
				String title = res.getString("Title");
				String artist = res.getString("Artist");
				String filename = res.getString("Filename");
				songlist.add(title + ", " + artist + ", " + filename);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return songlist;
	}

}
