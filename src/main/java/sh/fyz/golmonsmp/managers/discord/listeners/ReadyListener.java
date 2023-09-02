package sh.fyz.golmonsmp.managers.discord.listeners;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import sh.fyz.golmonsmp.managers.discord.Discord;

public class ReadyListener extends ListenerAdapter{
	
	@Override
	public void onReady(ReadyEvent event ) {
		Discord.sendChatMessage(":green_circle: **Serveur lancé**");
	}

}
