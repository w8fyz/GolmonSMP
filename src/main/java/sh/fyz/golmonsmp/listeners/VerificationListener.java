package sh.fyz.golmonsmp.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import sh.fyz.golmonsmp.managers.ingame.VerificationManager;

public class VerificationListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if(VerificationManager.isUnverified(e.getPlayer().getUniqueId()))
            e.setCancelled(true);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if(VerificationManager.isUnverified(e.getPlayer().getUniqueId()))
            e.setCancelled(true);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if(VerificationManager.isUnverified(e.getPlayer().getUniqueId()))
            e.setCancelled(true);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if(VerificationManager.isUnverified(e.getEntity().getUniqueId()))
            e.setCancelled(true);
    }

}
