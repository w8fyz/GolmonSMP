package sh.fyz.golmonsmp.managers.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import sh.fyz.golmonsmp.Main;
import sh.fyz.golmonsmp.managers.discord.listeners.CustomVoiceListener;
import sh.fyz.golmonsmp.managers.discord.listeners.MessageListener;
import sh.fyz.golmonsmp.managers.discord.listeners.ReadyListener;
import sh.fyz.golmonsmp.managers.discord.listeners.VerificationChannelListener;

import javax.security.auth.login.LoginException;
import java.util.EnumSet;

public class Discord {
	
	private static JDA jda;
	
	public static JDA get() {
		return jda;
	}
	
	public static void init() {
		try {
			jda = JDABuilder.createDefault(Main.getInstance().getConfig().getString("discord_token"))
					.setActivity(Activity.playing("golmon.need.gf"))
					.addEventListeners(new ReadyListener(), new CustomVoiceListener(), new VerificationChannelListener(), new MessageListener())
					.enableIntents(EnumSet.allOf(GatewayIntent.class))
					.setMemberCachePolicy(MemberCachePolicy.ALL).build();
		} catch (LoginException e) {
			e.printStackTrace();
		}
	}
	
	public static void sendChatMessage(String message) {
		TextChannel channel = jda.getTextChannelById(Main.getInstance().getConfig().getString("channel_link"));
		channel.sendMessage(protect(message)).queue();
	}

	private static String protect(String message) {
		return message.replaceAll("@", "ⓐ");
		
	}
	
	

}
