package com.lekohd.blockparty;

/*
 * Copyright (C) 2014 Leon167, XxChxppellxX and ScriptJunkie 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

//Usage
//MessageManager.BLOCKPARTY_PREFIX + " " + MessageManager.EFFECTS_JUMPING
public class MessageManager {
	public String PLUGIN_NAME = "[BlockParty]";
	// #Essentially is the color for §3[BlockParty] for all messages
	public String BLOCKPARTY_COLOR = "§3";

	public String BLOCKPARTY_PREFIX = "§3[BlockParty]";

	public String BOOST_NAME_WALK = "§6Walk Faster";
	public String BOOST_NAME_JUMP = "§6Jump Higher";
	public String BOOST_NAME_ENDERPEARL = "§6Jump Further";

	public String EFFECTS_WALKING = "§8Your walking speed has been increased!";
	public String EFFECTS_JUMPING = "§8Your jumping height has been increased!";
	public String EFFECTS_NAUSEA = "§8Ohh... This was a bad effect!";
	public String EFFECTS_BLINDNESS = "§8Ohh... This was a bad effect!";
	public String EFFECTS_EXPIRED = "§8Your effect has expired!";

	public String ARENA_EXISTS_ALREADY = "§8Arena $ARENANAME$ already exists!";
	public String ARENT_DOES_NOT_EXIST = "§8Arena $ARENANAME$ does not exists!";
	public String ARENA_CREATED = "§8Arena $ARENANAME$ was successfully created!";
	public String ARENA_FULL = "§8Arena $ARENANAME$ is full!";
	public String ARENA_DISABLED = "§8Arena $ARENANAME$ disabled!";
	public String ARENA_ENABLED = "§8Arena $ARENANAME$ loaded!";
	public String ARENA_RELOADING = "§8reloading $ARENANAME$";
	public String ARENA_CONFIGS_RELOADED = "§8Arena configs reloaded successfully!";
	public String ARENA_DELETED = "§8Arena $ARENANAME$ was successfully deleted!";
	public String ARENA_FLOOR_ERROR = "§8You have to set the floor first!";
	public String ARENA_SPAWN_ERROR = "§8You have to set all spawns first!";

	public String SETUP_SPAWN_SET = "The spawn point was set for the arena";
	public String SETUP_FLOOR_SET = "§8Floor was set for Arena $ARENANAME$";
	public String SETUP_FLOOR_ERROR_SAME_WORLD = "§8Arena and Floor must be in the same world";
	public String SETUP_FLOOR_ERROR_MIN_HEIGHT = "§8The Floor must be 1 block high!";
	public String SETUP_FLOOR_ERROR_WORLD_EDIT_SELECT = "§8Select a region with WorldEdit first.";
	public String JOIN_VANISH = "§8Cannot join arena when you are in Vanish Mode.";
	public String JOIN_SUCCESS_BROADCAST = "§8$PLAYER$ joined the game!";
	public String JOIN_SUCCESS_PLAYER = "§8You have joined Arena $ARENANAME$";
	public String JOIN_ERROR_FULL = "§8The Arena $ARENANAME$ is full!";
	public String JOIN_ERROR_IN_GAME = "§8You are already in a game!";
	public String JOIN_ARENA_IS_DISABLED = "§8Arena $ARENANAME$ is currently disabled!";

	public String START_SUCCESS = "§8The game has started!";
	public String START_ERROR_ZERO_PAYERS = "§8You cannot start a game with 0 players in the Lobby";
	public String START_ERROR_LESS_THEN_MIN_PLAYERS = "§8Not enough players to start the game!";
	public String STOP_GAME_FORCED = "§8The game has forced stopped by staff!";

	public String LEAVE_LOCATION_NOT_FOUND = "§8Cannot load your old location. Please type /spawn";
	public String LEAVE_CANNOT = "§8You can not leave the current game.";
	public String LEAVE_NOT_IN_ARENA = "§8You are not in an arena!";
	public String LEAVE_ARENA_BROADCAST = "§8$PLAYER$ has left the game";
	public String LEAVE_ARENA_PLAYER = "§8You left the arena!";

	public String BAR_WAITING = "Waiting ...";
	public String BAR_DANCE = "Dance";
	public String BAR_STOP = "STOP";
	public String BAR_STARTS_SOON = "Game starts soon";
	public String BAR_TIMER = "§8$TIMER$ seconds left!";

    public String ACTIONBAR_WAITING = "Waiting ...";
    public String ACTIONBAR_DANCE = "Block:$BLOCKCOLORCODE$ &l$BLOCKNAME$";
    public String ACTIONBAR_STOP = "STOP";

	public String PERIOD_WINNER_ANNOUNCE_SELF = "§8Congratulations! You won the game.";
	public String PERIOD_WINNER_ANNOUNCE_ALL = "§8$PLAYER$ won the game.";
	public String PERIOD_WINNER_ANNOUNCE_REWARD = "§8You will get you reward when you leave the arena!";
	public String PERIOD_NEXT_BLOCK_IS = "§8Next Block is $BLOCKNAME$";
	public String PERIOD_BOOSTS_SUMMONED = "§8A Boost has been summoned!";
	public String PERIOD_ELIMINATED = "§8$PLAYER$ was §4ELIMINATED!";
	public String PERIOD_ERROR_NO_FLOORS = "There are no floors for Arena $ARENANAME$.";

	public String SONG_NOT_EXIST = "§8$SONG$ does not exist.";
	public String SONG_NOW_PLAYING = "§8Playing song $SONG$";

	public String VOTE_ITEM_INVENTORY_HEADER = "Click to Vote!";
	public String VOTE_ITEM_FIREBALL_DISPLAY_NAME = "§6Vote for a Song!";
	public String VOTE_ITEM_FIREBALL_LORE = "Click me!";
	public String VOTE_FOR_SONG = "§8You have voted for $SONG$.";

	public String SIGN_JOIN = "§2Join";
	public String SIGN_FULL = "§4Full";
	public String SIGN_IN_LOBBY = "§2In Lobby";
	public String SIGN_IN_GAME = "§4In Game";
	public String SIGN_PLACEMENT_NOT_EXIST = "§8Arena $ARENANAME$ is not enabled or does not exists!";

	public String NO_PERMISSION = "§4You do not have the permissions to do that.";
	public String CONSOLE_OUTPUT_WINNER = "$PLAYER$ won block party @ $ARENANAME$.";

	public void loadLocale(String language) {
		try {
			File file = new File("plugins//BlockParty//locale_" + language + ".yml");
			YamlConfiguration config = new YamlConfiguration();
			if (file.exists()) {
				config.load(file);
				PLUGIN_NAME = "[BlockParty]";
				BLOCKPARTY_COLOR = colorizeString(config.getString("BLOCKPARTY_COLOR"));
				BLOCKPARTY_PREFIX = colorizeString(BLOCKPARTY_COLOR + PLUGIN_NAME);
				
				BOOST_NAME_WALK = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("BOOST_NAME_WALK"));
				BOOST_NAME_JUMP = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("BOOST_NAME_JUMP"));
				BOOST_NAME_ENDERPEARL = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("BOOST_NAME_ENDERPEARL"));

				EFFECTS_WALKING = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("EFFECTS_WALKING"));
				EFFECTS_JUMPING = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("EFFECTS_JUMPING"));
				EFFECTS_NAUSEA = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("EFFECTS_NAUSEA"));
				EFFECTS_BLINDNESS = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("EFFECTS_BLINDNESS"));
				EFFECTS_EXPIRED = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("EFFECTS_EXPIRED"));

				ARENA_EXISTS_ALREADY = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("ARENA_EXISTS_ALREADY"));
				ARENT_DOES_NOT_EXIST = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("ARENT_DOES_NOT_EXIST"));
				ARENA_CREATED = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("ARENA_CREATED"));
				ARENA_FULL = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("ARENA_FULL"));
				ARENA_DISABLED = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("ARENA_DISABLED"));
				ARENA_ENABLED = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("ARENA_ENABLED"));
				ARENA_RELOADING = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("ARENA_RELOADING"));
				ARENA_CONFIGS_RELOADED = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("ARENA_CONFIGS_RELOADED"));
				ARENA_DELETED = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("ARENA_DELETED"));
				ARENA_FLOOR_ERROR = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("ARENA_FLOOR_ERROR"));
				ARENA_SPAWN_ERROR = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("ARENA_SPAWN_ERROR"));

				SETUP_SPAWN_SET = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("SETUP_SPAWN_SET"));
				SETUP_FLOOR_SET = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("SETUP_FLOOR_SET"));
				SETUP_FLOOR_ERROR_SAME_WORLD = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("SETUP_FLOOR_ERROR_SAME_WORLD"));
				SETUP_FLOOR_ERROR_MIN_HEIGHT = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("SETUP_FLOOR_ERROR_MIN_HEIGHT"));
				SETUP_FLOOR_ERROR_WORLD_EDIT_SELECT = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("SETUP_FLOOR_ERROR_WORLD_EDIT_SELECT"));

				JOIN_VANISH = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("JOIN_VANISH"));
				JOIN_SUCCESS_BROADCAST = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("JOIN_SUCCESS_BROADCAST"));
				JOIN_SUCCESS_PLAYER = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("JOIN_SUCCESS_PLAYER"));
				JOIN_ERROR_FULL = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("JOIN_ERROR_FULL"));
				JOIN_ERROR_IN_GAME = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("JOIN_ERROR_IN_GAME"));
				JOIN_ARENA_IS_DISABLED = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("JOIN_ARENA_IS_DISABLED"));

				START_SUCCESS = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("START_SUCCESS"));
				START_ERROR_ZERO_PAYERS = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("START_ERROR_ZERO_PAYERS"));
				START_ERROR_LESS_THEN_MIN_PLAYERS = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("START_ERROR_LESS_THEN_MIN_PLAYERS"));
				STOP_GAME_FORCED = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("STOP_GAME_FORCED"));

				LEAVE_LOCATION_NOT_FOUND = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("LEAVE_LOCATION_NOT_FOUND"));
				LEAVE_CANNOT = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("LEAVE_CANNOT"));
				LEAVE_NOT_IN_ARENA = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("LEAVE_NOT_IN_ARENA"));
				LEAVE_ARENA_BROADCAST = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("LEAVE_ARENA_BROADCAST"));
				LEAVE_ARENA_PLAYER = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("LEAVE_ARENA_PLAYER"));

				BAR_WAITING = colorizeString(config.getString("BAR_WAITING"));
				BAR_DANCE = colorizeString(config.getString("BAR_DANCE"));
				BAR_STOP = colorizeString(config.getString("BAR_STOP"));
				BAR_STARTS_SOON = colorizeString(config.getString("BAR_STARTS_SOON"));
				BAR_TIMER = colorizeString(config.getString("BAR_TIMER"));

                ACTIONBAR_WAITING = colorizeString(config.getString("ACTIONBAR_WAITING"));
                ACTIONBAR_DANCE = colorizeString(config.getString("ACTIONBAR_DANCE"));
                ACTIONBAR_STOP = colorizeString(config.getString("ACTIONBAR_STOP"));

				PERIOD_WINNER_ANNOUNCE_SELF = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("PERIOD_WINNER_ANNOUNCE_SELF"));
				PERIOD_WINNER_ANNOUNCE_ALL = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("PERIOD_WINNER_ANNOUNCE_ALL"));
				PERIOD_WINNER_ANNOUNCE_REWARD = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("PERIOD_WINNER_ANNOUNCE_REWARD"));
				PERIOD_NEXT_BLOCK_IS = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("PERIOD_NEXT_BLOCK_IS"));
				PERIOD_BOOSTS_SUMMONED = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("PERIOD_BOOSTS_SUMMONED"));
				PERIOD_ELIMINATED = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("PERIOD_ELIMINATED"));
				PERIOD_ERROR_NO_FLOORS = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("PERIOD_ERROR_NO_FLOORS"));

				SONG_NOT_EXIST = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("SONG_NOT_EXIST"));
				SONG_NOW_PLAYING = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("SONG_NOW_PLAYING"));

				VOTE_ITEM_INVENTORY_HEADER = colorizeString(config.getString("VOTE_ITEM_INVENTORY_HEADER"));
				VOTE_ITEM_FIREBALL_DISPLAY_NAME = colorizeString(config.getString("VOTE_ITEM_FIREBALL_DISPLAY_NAME"));
				VOTE_ITEM_FIREBALL_LORE = colorizeString(config.getString("VOTE_ITEM_FIREBALL_LORE"));
				VOTE_FOR_SONG = colorizeString(config.getString("VOTE_FOR_SONG"));

				SIGN_JOIN = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("SIGN_JOIN"));
				SIGN_FULL = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("SIGN_FULL"));
				SIGN_IN_LOBBY = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("SIGN_IN_LOBBY"));
				SIGN_IN_GAME = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("SIGN_IN_GAME"));
				SIGN_PLACEMENT_NOT_EXIST = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("SIGN_PLACEMENT_NOT_EXIST"));

				NO_PERMISSION = colorizeString(BLOCKPARTY_PREFIX + " " + config.getString("NO_PERMISSION"));
				CONSOLE_OUTPUT_WINNER = colorizeString(PLUGIN_NAME + " " + config.getString("CONSOLE_OUTPUT_WINNER"));
			} else {
				System.out.print("[BlockParty] No locale file found. Assuming default is English (en)");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// this.cfg.getInt("configuration.MinPlayers")
	}

	public static String colorizeString(String input){
		return ChatColor.translateAlternateColorCodes('&', input);
	}
	
	
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