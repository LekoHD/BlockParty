package com.lekohd.blockparty;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class MessageManager {
	public static enum MessageType {
		INFO(ChatColor.GRAY, ""), ERROR(ChatColor.RED, "§cError: "), BAD(ChatColor.RED, "");

		private ChatColor color;
		private String prefix;

		private MessageType(ChatColor color, String prefix) {
			this.color = color;
			this.prefix = prefix;
		}

		public ChatColor getColor() {
			return this.color;
		}

		public String getPrefix() {
			return this.prefix;
		}
	}

	private static MessageManager instance = new MessageManager();
	private String messagePrefix = ChatColor.DARK_AQUA + "[BlockParty]" + ChatColor.RESET;

	public static MessageManager getInstance() {
		return instance;
	}

	public void log(String message) {
		System.out.println(this.messagePrefix + " " + message);
	}

	public void msg(CommandSender sender, MessageType type, String message) {
		sender.sendMessage(this.messagePrefix + type.getPrefix() + type.getColor() + message);
	}

	public void broadcast(MessageType type, String message) {
		BlockParty.getInstance().getServer().broadcastMessage(this.messagePrefix + type.getPrefix() + type.getColor() + message);
	}
}

/*
 * Location: C:\_game_servers\minecraft-towncraft\plugins\BlockParty.jar
 * 
 * Qualified Name: com.lekohd.blockparty.MessageManager
 * 
 * JD-Core Version: 0.7.0.1
 */