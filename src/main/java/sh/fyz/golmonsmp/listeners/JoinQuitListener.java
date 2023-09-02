package sh.fyz.golmonsmp.listeners;

import org.bukkit.Bukkit;
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
import sh.fyz.golmonsmp.managers.player.Link;
import sh.fyz.golmonsmp.managers.player.PlayerDecoration;
import sh.fyz.golmonsmp.managers.scoreboard.Board;

public class JoinQuitListener implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		Account account = Account.get(p.getUniqueId());
		if (account == null) {
			p.kickPlayer("§eBienvenue sur le serveur !\n\n§fPour des raisons de sécurité, vous devez lier votre discord à votre compte minecraft.\n\n§e§l"
							+"§7--------\n§e§l"+ Link.getNewVerificationCode(p.getUniqueId())+"\n§7--------"
							+ "\n\n§fest votre §ecode §fà rentrer dans le §esalon link du Discord.§f Vous pourrez en suite vous reconnecter.");
		e.setJoinMessage("§eMdrrr y'a "+p.getName()+" qui a essayé de se co sans avoir lié son compte discord");
			return;
		}
		e.setJoinMessage("§a+ §7"+p.getName());
		if(account.getPlayCount() == 0) {
			Bukkit.getOnlinePlayers().forEach(pls ->  {
				pls.playSound(pls.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
			});
			Bukkit.broadcastMessage("§eBienvenue à §6"+p.getName()+" §esur le serveur !");
			p.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 32));
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
