package sh.fyz.golmonsmp.managers.discord.listeners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import sh.fyz.golmonsmp.Main;
import sh.fyz.golmonsmp.account.Account;
import sh.fyz.golmonsmp.managers.player.Link;

import java.util.ArrayList;
import java.util.UUID;

public class VerificationChannelListener extends ListenerAdapter{
	
	@Override
	public void onMessageReceived(MessageReceivedEvent e) {
		if(e.getChannel().getId().equals(Main.getInstance().getConfig().getString("channel_verification"))) {
			UUID uuid = Link.isValidCode(e.getMessage().getContentRaw());
			e.getMessage().delete().queue();
			if(uuid != null) {
				new Account(uuid, "", new ArrayList<>(), 0, 0, 2, "", 0).save();
				e.getAuthor().openPrivateChannel().flatMap(channel -> channel.sendMessage("**Tu peux désormais accéder au serveur Minecraft de la drum et de la basse !**")) .queue();
				Link.remove(e.getMessage().getContentRaw());
				Account.get(uuid).setDiscordID(e.getAuthor().getId()).save();
			}
		}
	}

}
