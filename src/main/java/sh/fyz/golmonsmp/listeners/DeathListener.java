package sh.fyz.golmonsmp.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import sh.fyz.golmonsmp.account.Account;
import sh.fyz.golmonsmp.managers.discord.Discord;
import sh.fyz.golmonsmp.managers.ingame.DeathTracker;
import sh.fyz.golmonsmp.managers.player.PlayerDecoration;

public class DeathListener implements Listener {
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Account account = Account.get(e.getEntity().getUniqueId());
		account.setDeaths(account.getDeaths()+1);
		account.save();
		
		PlayerDecoration.update(e.getEntity());
		
		Discord.sendChatMessage("💀 **"+e.getDeathMessage()+"**");
		e.setDeathMessage("§7"+e.getDeathMessage());
		DeathTracker.deaths.put(e.getEntity(), e.getEntity().getLocation());
		
	}

}
