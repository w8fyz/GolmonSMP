package sh.fyz.golmonsmp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import org.bukkit.event.player.PlayerChatEvent;
import sh.fyz.golmonsmp.account.Account;
import sh.fyz.golmonsmp.managers.discord.Discord;

public class ChatListener implements Listener {

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Account account = Account.get(e.getPlayer().getUniqueId());
		e.setCancelled(true);
		Bukkit.broadcastMessage(account.getFormatedColor()+e.getPlayer().getName()+"§7: "+e.getMessage());
		Discord.sendChatMessage("**"+e.getPlayer().getName()+"**: "+e.getMessage());
	}
	
}
