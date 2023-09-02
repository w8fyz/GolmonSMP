package sh.fyz.golmonsmp.managers.scoreboard;

import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import sh.fyz.golmonsmp.account.Account;
import sh.fyz.golmonsmp.managers.scoreboard.libs.FastBoard;
import sh.fyz.golmonsmp.managers.tpa.TPARequest;

public class Board {

	private static int color = 0;
	private static String[] colors = { "§c", "§6", "§e", "§a", "§b", "§9", "§f", "§f", "§f", "§f", "§f", "§f", "§f",
			"§f", "§f", "§f" };

	private static HashMap<Player, FastBoard> scoreboards = new HashMap<>();

	public static FastBoard add(Player p) {
		FastBoard board = new FastBoard(p);
		scoreboards.put(p, board);
		return board;
	}

	public static void update(Player p) {
		FastBoard board = scoreboards.containsKey(p) ? scoreboards.get(p) : add(p);

		board.updateTitle("§7- "+color() + "Golmon§fCraft §7-");

		Account account = Account.get(p.getUniqueId());

		board.updateLines(Arrays.asList("§a", "➤ §e" + p.getName(),
				"TPA : " + canTP(account),
				"Home" + (account.getHomes().size() > 1 ? "s" : "") + ": §b" + account.getHomes().size(), "§c",
				"En ligne" + (Bukkit.getOnlinePlayers().size() > 1 ? "s" : "") + ": §a"
						+ Bukkit.getOnlinePlayers().size(),
				"§f", "§7golmon.need.gf"));

	}

	private static String canTP(Account account) {
		return account.canTeleport() ? "§a✔" : formated(account.getLastTP());
	}

	private static String formated(long lastTP) {
		long milliseconds = TPARequest.TP_COOLDOWN - (System.currentTimeMillis() - lastTP);
		long minutes = (milliseconds / 1000) / 60;
		long seconds = (milliseconds / 1000) % 60;
		return "§e" + (minutes > 9 ? minutes : "0" + minutes) + ":" + (seconds > 9 ? seconds : "0" + seconds);
	}

	private static String color() {
		return colors[color];
	}

	private static void updateColor() {
		color++;
		if (color == colors.length) {
			color = 0;
		}
	}

	public static void updateAll() {
		updateColor();
		for (Player pls : scoreboards.keySet()) {
			update(pls);
		}
	}

}
