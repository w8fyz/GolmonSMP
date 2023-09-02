package sh.fyz.golmonsmp;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import sh.fyz.golmonsmp.commands.*;
import sh.fyz.golmonsmp.listeners.*;
import sh.fyz.golmonsmp.managers.discord.Discord;
import sh.fyz.golmonsmp.managers.discord.listeners.AdvancementListener;
import sh.fyz.golmonsmp.managers.scoreboard.BoardTask;

public class Main extends JavaPlugin{
	
	private static Main INSTANCE;
	
	public static Main getInstance() {
		return INSTANCE;
	}
	
	@Override
	public void onEnable() {
		INSTANCE = this;
		saveDefaultConfig();
		
		registerListeners();
		registerCommands();
		
		Discord.init();
		
		new BoardTask().runTaskTimer(this, 10, 10);
	}
	
	@Override
	public void onDisable() {
		Discord.sendChatMessage(":red_circle: **Serveur éteint**");
	}

	private void registerCommands() {
		getCommand("home").setExecutor(new CommandHome());
		getCommand("sethome").setExecutor(new CommandSetHome());
		getCommand("delhome").setExecutor(new CommandDelHome());
		getCommand("pronouns").setExecutor(new CommandPronouns());
		getCommand("tpa").setExecutor(new CommandTpa());
		getCommand("tpaccept").setExecutor(new CommandTpyes());
		getCommand("tpyes").setExecutor(new CommandTpyes());
		getCommand("tpdeny").setExecutor(new CommandTpno());
		getCommand("tpno").setExecutor(new CommandTpno());
		getCommand("tpcancel").setExecutor(new CommandTpcancel());
		getCommand("tpundo").setExecutor(new CommandTpcancel());
		getCommand("voterain").setExecutor(new CommandVoteRain());
		getCommand("home").setTabCompleter(new CommandHome());
		getCommand("delhome").setTabCompleter(new CommandDelHome());
	}

	private void registerListeners() {
		Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
		Bukkit.getPluginManager().registerEvents(new RainListener(), this);
		Bukkit.getPluginManager().registerEvents(new InteractListener(), this);
		Bukkit.getPluginManager().registerEvents(new JoinQuitListener(), this);
		Bukkit.getPluginManager().registerEvents(new DeathListener(), this);
		Bukkit.getPluginManager().registerEvents(new AdvancementListener(), this);
	}

}
