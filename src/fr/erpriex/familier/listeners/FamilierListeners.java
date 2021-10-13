package fr.erpriex.familier.listeners;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.erpriex.familier.Familier;
import fr.erpriex.familier.tasks.FamilierSpawnTask;

public class FamilierListeners implements Listener {
	
	private Familier main;
	
	public FamilierListeners(Familier main) {
		this.main = main;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		FamilierSpawnTask spawnTask = new FamilierSpawnTask(main, player);
		spawnTask.runTaskTimer(main, 20, 20);
		
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		if(main.familiers.containsKey(player)) {
			ArmorStand armorStand = main.familiers.get(player);
			armorStand.remove();
			main.familiers.remove(player);
		}
	}
	
	
	@EventHandler
	public void onDamage(EntityDamageEvent event) {
		if(event.getEntityType() == EntityType.ARMOR_STAND) {
			ArmorStand armorStand = (ArmorStand) event.getEntity();
			if(armorStand.hasMetadata("pet")) {
				event.setCancelled(true);
			}
		}
	}
	
	
	@EventHandler
	public void onInteractAtEnity(PlayerInteractAtEntityEvent event) {
		if(event.getRightClicked().getType() == EntityType.ARMOR_STAND) {
			ArmorStand armorStand = (ArmorStand) event.getRightClicked();
			if(armorStand.hasMetadata("pet")) {
				event.setCancelled(true);
			}
		}
	}
	

	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		
		if(main.familiers.containsKey(player)) {
			ArmorStand armorStand = main.familiers.get(player);
			armorStand.teleport(main.getFamilierLoc(player));
		}
	}

}
