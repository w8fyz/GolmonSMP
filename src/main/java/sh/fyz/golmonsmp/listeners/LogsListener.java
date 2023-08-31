package sh.fyz.golmonsmp.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import sh.fyz.golmonsmp.managers.logs.Logs;

public class LogsListener implements Listener {


    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Logs.info(event.getPlayer().getName() + " a placé un bloc de type " + event.getBlock().getType().name() + " en " + event.getBlock().getLocation().toString());
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Logs.info(event.getPlayer().getName() + " a cassé un bloc de type " + event.getBlock().getType().name() + " en " + event.getBlock().getLocation().toString());
    }

    @EventHandler
    public void onBucketPlace(PlayerBucketEmptyEvent event) {
        Logs.info(event.getPlayer().getName() + " a placé un bloc de type " + event.getBucket().name() + " en " + event.getBlock().getLocation().toString());
    }

    @EventHandler
    public void onChestOpened(PlayerInteractEvent event) {
        if (event.getClickedBlock() != null && event.getClickedBlock().getType().name().contains("CHEST")) {
            Logs.info(event.getPlayer().getName() + " a ouvert un coffre en " + event.getClickedBlock().getLocation().toString());
        }
    }

}
