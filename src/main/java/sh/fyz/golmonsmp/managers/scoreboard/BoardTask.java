package sh.fyz.golmonsmp.managers.scoreboard;

import org.bukkit.scheduler.BukkitRunnable;

import sh.fyz.golmonsmp.managers.ingame.DeathTracker;

public class BoardTask extends BukkitRunnable{

	@Override
	public void run() {
		Board.updateAll();
		DeathTracker.track();
	}

}
