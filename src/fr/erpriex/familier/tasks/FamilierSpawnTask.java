package fr.erpriex.familier.tasks;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.erpriex.familier.Familier;

public class FamilierSpawnTask extends BukkitRunnable {
	
	private Familier main;
	private Player player;

	private int timer = 2;
	
	public FamilierSpawnTask(Familier main, Player player) {
		this.player = player;
		this.main = main;
	}

	@Override
	public void run() {
		
		if(timer == 0) {
			cancel();
			if(main.names.containsKey(player.getName())) {
				main.spawnFamilier(player, main.names.get(player.getName()));
			}
		}
		
		timer--;
		
	}

}
