package sh.fyz.golmonsmp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import sh.fyz.golmonsmp.account.Account;
import sh.fyz.golmonsmp.account.Home;

import java.util.ArrayList;
import java.util.List;

public class CommandSB implements CommandExecutor, TabExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			Account account = Account.get(p.getUniqueId());

		}
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		ArrayList<String> result = new ArrayList<>();
		if(sender instanceof Player && args.length == 1) {
			Player p = ((Player) sender);
			Account account = Account.get(p.getUniqueId());
			for(Home home : account.getHomes()) {
				if(home.getName().startsWith(args[0])) {
					result.add(home.getName());
				}
			}
		}
		return result;
	}
	
}
