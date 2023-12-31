package sh.fyz.golmonsmp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import sh.fyz.golmonsmp.account.Account;
import sh.fyz.golmonsmp.managers.discord.Discord;
import sh.fyz.golmonsmp.managers.ingame.VerificationManager;
import sh.fyz.golmonsmp.managers.player.Link;
import sh.fyz.golmonsmp.managers.player.PlayerDecoration;
import sh.fyz.golmonsmp.managers.scoreboard.Board;

public class JoinQuitListener implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		Account account = Account.get(p.getUniqueId());
		if (account == null) {
			VerificationManager.addUnverified(p.getUniqueId());
			e.setJoinMessage(null);
			return;
		}
		e.setJoinMessage("§a+ §7"+p.getName());
		if(account.getPlayCount() == 0) {
			Bukkit.getOnlinePlayers().forEach(pls ->  {
				pls.playSound(pls.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
			});
			Bukkit.broadcastMessage("§eBienvenue à §6"+p.getName()+" §esur le serveur !");
			p.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 32));
			p.setGameMode(GameMode.SURVIVAL);
		}
		account.addPlayCount().save();
		Board.update(p);
		PlayerDecoration.update(p);
		Discord.sendChatMessage("**+** *"+p.getName()+"*");
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		e.setQuitMessage("§c- §7"+p.getName());
		
		Discord.sendChatMessage("**-** *"+p.getName()+"*");
	}

}
