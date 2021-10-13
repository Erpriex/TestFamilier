package fr.erpriex.familier;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.EulerAngle;

import fr.erpriex.familier.commands.CommandReloadpet;
import fr.erpriex.familier.listeners.FamilierListeners;

public class Familier extends JavaPlugin {
	
	public Map<String, String> names = new HashMap<>();
	public Map<Player, ArmorStand> familiers = new HashMap<>();
	
	@Override
	public void onEnable() {
		
		names.put("Erpriex", "§aMiniPriex :o");
		
		getServer().getPluginManager().registerEvents(new FamilierListeners(this), this);
		
		getCommand("reloadpet").setExecutor(new CommandReloadpet(this));
	}
	
	public void spawnFamilier(Player player, String petName) {
		ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(getFamilierLoc(player), EntityType.ARMOR_STAND);
		
		armorStand.setCustomName(petName);
		armorStand.setCustomNameVisible(true);
		armorStand.setArms(true);
		armorStand.setSmall(true);
		
		armorStand.setHelmet(getHead(player.getName()));
		
		ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
		LeatherArmorMeta chestplateM = (LeatherArmorMeta) chestplate.getItemMeta();
		chestplateM.setColor(Color.GRAY);
		chestplate.setItemMeta(chestplateM);
		armorStand.setChestplate(chestplate);
		
		ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS, 1);
		LeatherArmorMeta leggingsM = (LeatherArmorMeta) leggings.getItemMeta();
		leggingsM.setColor(Color.GRAY);
		leggings.setItemMeta(leggingsM);
		armorStand.setLeggings(leggings);
		
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS, 1);
		LeatherArmorMeta bootsM = (LeatherArmorMeta) boots.getItemMeta();
		bootsM.setColor(Color.GRAY);
		boots.setItemMeta(bootsM);
		armorStand.setBoots(boots);
		
		ItemStack cookie = new ItemStack(Material.COOKIE, 1);
		armorStand.setItemInHand(cookie);
		
		armorStand.setRightArmPose(new EulerAngle(5.5, 0, 0));
		
		armorStand.setGravity(false);
		armorStand.setBasePlate(false);
		armorStand.setVisible(true);
		
		armorStand.setMetadata("pet", new FixedMetadataValue(this, player.getName()));
		
		familiers.put(player, armorStand);
	}
	
	public Location getFamilierLoc(Player player) {
		Location familierLoc = player.getLocation().add(player.getEyeLocation().getDirection().setY(0).multiply(-0.5));
		double distance = 1.0D;
		double x = -Math.cos(player.getEyeLocation().getYaw()/180.0F*Math.PI)*distance;
		double z = -Math.sin(player.getEyeLocation().getYaw()/180.0F*Math.PI)*distance;
		familierLoc.add(x, 0.7, z);
		familierLoc.setYaw(player.getLocation().getYaw());
		familierLoc.setPitch(player.getLocation().getPitch());
		return familierLoc;
	}
	
	private ItemStack getHead(String playerName) {
		ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (byte)3);
		SkullMeta headM = (SkullMeta) head.getItemMeta();
		headM.setOwner(playerName);
		head.setItemMeta(headM);
		return head;
	}

}
