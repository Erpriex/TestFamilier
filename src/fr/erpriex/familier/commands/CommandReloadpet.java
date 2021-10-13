package fr.erpriex.familier.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import fr.erpriex.familier.Familier;

public class CommandReloadpet implements CommandExecutor {
	
	private Familier main;
	
	public CommandReloadpet(Familier main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("reloadpet")) {
				
				if(!main.names.containsKey(player.getName())) {
					player.sendMessage("§cVous n'avez pas de familier enregistré !");
					return true;
				}
				
				if(main.familiers.containsKey(player)) {
					ArmorStand armorStand = main.familiers.get(player);
					armorStand.remove();
					main.familiers.remove(player);
				}
				
				main.spawnFamilier(player, main.names.get(player.getName()));
				
				player.sendMessage("§aFamilier rechargé !");
				
				
				return true;
			}
		}
		return false;
	}

}
