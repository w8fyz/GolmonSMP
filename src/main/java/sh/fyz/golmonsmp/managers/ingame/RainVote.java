package sh.fyz.golmonsmp.managers.ingame;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class RainVote {
	
	private static ArrayList<Player> voters = new ArrayList<>();
	
	private static int goal = 0;

	public static int start() {
		goal = (int)Math.floor(Bukkit.getOnlinePlayers().size()/2);
		if(goal == 0) goal = 1;
		voters.clear();
		return goal;
	}
	
	public static boolean isVoteStarted() {
		return goal > 0;
	}
	
	public static boolean vote(Player p) {
		if(voters.contains(p)) return false;
		voters.add(p);
		check();
		return true;
	}

	private static void check() {
		if(voters.size() >= goal) {
			goal = 0;
			voters.clear();
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "weather clear");
			Bukkit.broadcastMessage("§bLe soleil est de retour !");
		}
	}
	
}
