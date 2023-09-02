package sh.fyz.golmonsmp.managers.discord.listeners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import sh.fyz.golmonsmp.Main;
import sh.fyz.golmonsmp.account.Account;
import sh.fyz.golmonsmp.managers.discord.Discord;
import sh.fyz.golmonsmp.managers.ingame.VerificationManager;
import sh.fyz.golmonsmp.managers.player.Link;
import sh.fyz.golmonsmp.managers.player.PlayerDecoration;
import sh.fyz.golmonsmp.managers.scoreboard.Board;

import java.util.ArrayList;
import java.util.UUID;

public class VerificationChannelListener extends ListenerAdapter{
	
	@Override
	public void onMessageReceived(MessageReceivedEvent e) {
		if(e.getChannel().getId().equals(Main.getInstance().getConfig().getString("channel_verification"))) {
			UUID uuid = Link.isValidCode(e.getMessage().getContentRaw());
			e.getMessage().delete().queue();
			if(uuid != null) {
				new Account(uuid, "", new ArrayList<>(), 0, 0, 2, "", 0, true).save();
				e.getAuthor().openPrivateChannel().flatMap(channel -> channel.sendMessage("**Tu peux désormais accéder au serveur Minecraft de la drum et de la bass !**")) .queue();
				Link.remove(e.getMessage().getContentRaw());
				Account.get(uuid).setDiscordID(e.getAuthor().getId()).save();
				VerificationManager.removeUnverified(uuid);
				if(Bukkit.getPlayer(uuid).isOnline()) {
					Player p = Bukkit.getPlayer(uuid);
					Bukkit.getOnlinePlayers().forEach(pls ->  {
						pls.playSound(pls.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
					});
					Bukkit.broadcastMessage("§eBienvenue à §6"+p.getName()+" §esur le serveur !");
					p.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 32));
					Account.get(uuid).addPlayCount().save();
					Board.update(p);
					PlayerDecoration.update(p);
					Discord.sendChatMessage("**+** *"+p.getName()+"*");
					p.setGameMode(GameMode.SURVIVAL);
				}
			}
		}
	}

}
