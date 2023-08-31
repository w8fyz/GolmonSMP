package sh.fyz.golmonsmp.managers.discord.listeners;

import org.bukkit.Bukkit;

import sh.fyz.golmonsmp.Main;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter{

	@Override
	public void onMessageReceived(MessageReceivedEvent e) {
		if(e.getChannel().getId().equals(Main.getInstance().getConfig().getString("channel_link")) && !e.getAuthor().isBot()) {
			if(e.getMessage().getContentRaw().length() > 1) Bukkit.broadcastMessage("§7[D]§b"+e.getMember().getEffectiveName()+"§7: "+e.getMessage().getContentRaw());
		}
	}
	
}
