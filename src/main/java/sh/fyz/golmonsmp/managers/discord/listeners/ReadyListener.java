package sh.fyz.golmonsmp.managers.discord.listeners;

import sh.fyz.golmonsmp.managers.discord.Discord;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ReadyListener extends ListenerAdapter{
	
	@Override
	public void onReady(ReadyEvent event ) {
		Discord.sendChatMessage(":green_circle: **Serveur lancé**");
	}

}
