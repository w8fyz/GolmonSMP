package sh.fyz.golmonsmp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sh.fyz.golmonsmp.account.Account;
import sh.fyz.golmonsmp.managers.tpa.TPAQuery;

public class CommandTpyes implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			Player targeter = null;
			if (args.length == 0) {
				targeter = TPAQuery.get(p).getLatestTargeter();
			} else {
				targeter = Bukkit.getPlayer(args[0]);
			}
			if (targeter == null) {
				p.sendMessage("§cImpossible de trouver un compte avec ce pseudo connecté.");
				return false;
			}
			if(!targeter.isOnline()) {
				p.sendMessage("§cLa personne n'est pas connectée.");
				return false;
			}
			if (!TPAQuery.get(p).isRequested(targeter)) {
				p.sendMessage("§cVous n'avez pas de demande de téléportation de cette personne.");
				return false;
			}
			TPAQuery.get(p).getRequested(targeter).delete();
			Account.get(targeter.getUniqueId()).setLastTP(System.currentTimeMillis()).save();
			targeter.teleport(p.getLocation().add(0, 0.5, 0));
			targeter.sendMessage("§6Téléportation vers "+p.getName()+"...");
			p.sendMessage("§6Téléportation acceptée !");
		}
		return false;
	}

}
