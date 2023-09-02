package sh.fyz.golmonsmp.managers.tasks;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import sh.fyz.golmonsmp.managers.ingame.VerificationManager;

import java.util.UUID;

public class UnverifiedTask extends BukkitRunnable {
    @Override
    public void run() {
        for(UUID uuid : VerificationManager.getUnverified()) {
            Player p = Bukkit.getPlayer(uuid);
            p.setGameMode(GameMode.SPECTATOR);
            p.teleport(new Location(Bukkit.getWorld("world"), 0, 88, 0));
        }
    }
}
