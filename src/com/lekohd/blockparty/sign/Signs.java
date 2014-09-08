package com.lekohd.blockparty.sign;

import com.lekohd.blockparty.BlockParty;
import org.bukkit.block.Sign;

public class Signs {
	public static void updateJoin(String arenaName, boolean full) {
		if (BlockParty.signs.containsKey(arenaName)) {
			if (full) {
				((Sign) BlockParty.signs.get(arenaName)).setLine(3, "§4Full");
			}
			if (!full) {
				((Sign) BlockParty.signs.get(arenaName)).setLine(3, "§2Join");
			}
		}
	}

	public static void updateGameProgress(String arenaName, boolean inLobby) {
		if (BlockParty.signs.containsKey(arenaName)) {
			if (inLobby) {
				((Sign) BlockParty.signs.get(arenaName)).setLine(1, "§2In Lobby");
			}
			if (!inLobby) {
				((Sign) BlockParty.signs.get(arenaName)).setLine(1, "§4In Game");
			}
		}
	}
}