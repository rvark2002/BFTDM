package com.vark.BFTDM.listeners;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.vark.BFTDM.ChatUtilities;
import com.vark.BFTDM.Game;
import com.vark.BFTDM.Main;
import com.vark.BFTDM.PlayerManager;
import com.vark.BFTDM.Team;
import com.vark.BFTDM.kits.KitManager;

import net.md_5.bungee.api.ChatColor;

public class KillListener extends GameListener
{

	public KillListener(Main pl) {
		super(pl);
		// TODO Auto-generated constructor stub
	}

	
	//On Player Damage
	@EventHandler
	public void onPlayerDamage(EntityDamageByEntityEvent event)
	{
		if(event.getDamager() instanceof Player)
		{
			if(event.getEntity() instanceof Player)
			{
				Player damager = (Player) event.getDamager();
				Player hit = (Player) event.getEntity();
				
				if(PlayerManager.isInGame(damager.getName()) && PlayerManager.isInGame(hit.getName()))
				{
					if(Team.getTeam(damager) == Team.getTeam(hit))
					{
						event.setCancelled(true);
						damager.sendMessage(ChatColor.RED+""+ChatColor.BOLD+"Don't Attack Teammates!");
					}
					else {
						
						double dam = event.getDamage();
						
						if(isSaber(damager.getInventory().getItemInMainHand()))
						{
							dam = 4;
							event.setDamage(dam);
						}
						if(isSaber(hit.getInventory().getItemInMainHand()))
						{
							
							if((hit.getLocation().getDirection().dot(damager.getLocation().getDirection()) < -0.5))
							{
								dam = 0;
								event.setCancelled(true);

							}
					
						}
						
						if(dam >= hit.getHealth())
						{
						
						hit.getWorld().spawnParticle(Particle.REDSTONE, hit.getLocation(),20,1.2,1.2,1.2,1,new Particle.DustOptions(Color.RED, 5));
						Team a = Team.getTeam(hit);
						a.addLives(-1);
						ChatUtilities.broadcast(hit.getName()+ " was killed by "+damager.getName());

						respawn(hit);
						Game.checkOver();
						
						}
						
						
						
						
						
					}
				}
				
				if(!(PlayerManager.isInGame(damager.getName())) && PlayerManager.isInGame(hit.getName()))
				{
					if(Team.getTeam(damager) == Team.getTeam(hit))
					{
						event.setCancelled(true);
						damager.sendMessage(ChatColor.RED+""+ChatColor.BOLD+"Can't Attack This Player!");
					}
				}
				
				if(PlayerManager.isInGame(damager.getName()) && (!(PlayerManager.isInGame(hit.getName()))))
				{
					if(Team.getTeam(damager) == Team.getTeam(hit))
					{
						event.setCancelled(true);
						damager.sendMessage(ChatColor.RED+""+ChatColor.BOLD+"Can't Attack This Player!");
					}
				}
				
			}
		}
	}
	
	
	
	
	public boolean isSaber(ItemStack is)
	{
		if(is.getType() == Material.STICK)
		{
			if(is.getItemMeta().hasCustomModelData())
				if(is.getItemMeta().getCustomModelData() > 100 && is.getItemMeta().getCustomModelData() <= 110)
					return true;
		}
		return false;
	}
	
	

	
	public void respawn(Player p)
	{
		ChatUtilities.broadcast("Heroes Lives Left: "+Team.getTeam("heroes").getLives());
		ChatUtilities.broadcast("Villains Lives Left: "+Team.getTeam("villains").getLives());
		int n1 = (int) ((Math.random()*8)+1);
		File file = new File(plugin.getDataFolder()+"/data/"+"respawnloc"+".yml");
		
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		

		ConfigurationSection cs = config.getConfigurationSection(Game.getMapName());
		
		Location loc = (Location) cs.get("path"+n1);
		
	
		System.out.println("path"+n1);
		
		p.teleport(loc);
		p.getInventory().clear();
		
		p.setGameMode(GameMode.ADVENTURE);
		p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
		p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 100));
		if(p.getFireTicks() != 0)
		{
			p.setFireTicks(0);
		}
		p.setFoodLevel(20);
		KitManager.giveKit(p);
		
		for (PotionEffect effect : p.getActivePotionEffects())
	        p.removePotionEffect(effect.getType());
		
		
	}
	
	

	
	
	
	@EventHandler
	public void onDamage(EntityDamageEvent event)
	{
		
		if(event.getCause() == DamageCause.ENTITY_EXPLOSION)
		{
			if(event.getEntity() instanceof Player)
			{
				Player p = (Player)event.getEntity();
				if(PlayerManager.isInGame(p.getName()))
					event.setCancelled(true);
			}
			
		}
		if(event.getCause() == DamageCause.LIGHTNING)
		{
			if(event.getEntity() instanceof Player)
			{
				Player hit = (Player)event.getEntity();
				if(PlayerManager.isInGame(hit.getName()))
				{
					if(Team.getTeam(hit) == Team.getTeam("heroes")) {
					event.setDamage(3);
					double dam = event.getDamage();
					
					if(dam >= hit.getHealth())
					{
					
					hit.getWorld().spawnParticle(Particle.REDSTONE, hit.getLocation(),20,1.2,1.2,1.2,1,new Particle.DustOptions(Color.RED, 5));
					Team a = Team.getTeam(hit);
					a.addLives(-1);
					ChatUtilities.broadcast(hit.getName()+ " was killed by lightning");
					respawn(hit);
					Game.checkOver();
					
					}
					
					}else {
						event.setCancelled(true);
					}
				}
			}
			
		}
		
		if(event.getCause() == DamageCause.WITHER)
		{
			if(event.getEntity() instanceof Player)
			{
				Player hit = (Player)event.getEntity();
				if(PlayerManager.isInGame(hit.getName()))
				{
					double dam = event.getDamage();
					if(dam >= hit.getHealth())
					{
					
					hit.getWorld().spawnParticle(Particle.REDSTONE, hit.getLocation(),20,1.2,1.2,1.2,1,new Particle.DustOptions(Color.RED, 5));
					Team a = Team.getTeam(hit);
					a.addLives(-1);
					ChatUtilities.broadcast(hit.getName()+ " was killed by Sith Magic");
					respawn(hit);
					Game.checkOver();
					
					}
				}
			}
			
		}
		
		
		if(event.getCause() == DamageCause.MAGIC)
		{
			if(event.getEntity() instanceof Player)
			{
				Player hit = (Player)event.getEntity();
				if(PlayerManager.isInGame(hit.getName()))
				{
					double dam = event.getDamage();
					if(dam >= hit.getHealth())
					{
					
					hit.getWorld().spawnParticle(Particle.REDSTONE, hit.getLocation(),20,1.2,1.2,1.2,1,new Particle.DustOptions(Color.RED, 5));
					Team a = Team.getTeam(hit);
					a.addLives(-1);
					ChatUtilities.broadcast(hit.getName()+ " was killed by enviormental forces");
					respawn(hit);
					Game.checkOver();
					
					}
				}
			}
			
		}
		
		
		if(event.getCause() == DamageCause.FIRE)
		{
			if(event.getEntity() instanceof Player)
			{
				Player hit = (Player)event.getEntity();
				if(PlayerManager.isInGame(hit.getName()))
				{
					double dam = event.getDamage();
					if(dam >= hit.getHealth())
					{
					
					hit.getWorld().spawnParticle(Particle.REDSTONE, hit.getLocation(),20,1.2,1.2,1.2,1,new Particle.DustOptions(Color.RED, 5));
					Team a = Team.getTeam(hit);
					a.addLives(-1);
					ChatUtilities.broadcast(hit.getName()+ " was killed by Fire!");
					respawn(hit);
					Game.checkOver();
					
					}
				}
			}
			
		}
		
		if(event.getCause() == DamageCause.FIRE_TICK)
		{
			if(event.getEntity() instanceof Player)
			{
				Player hit = (Player)event.getEntity();
				if(PlayerManager.isInGame(hit.getName()))
				{
					double dam = event.getDamage();
					if(dam >= hit.getHealth())
					{
					
					hit.getWorld().spawnParticle(Particle.REDSTONE, hit.getLocation(),20,1.2,1.2,1.2,1,new Particle.DustOptions(Color.RED, 5));
					Team a = Team.getTeam(hit);
					a.addLives(-1);
					ChatUtilities.broadcast(hit.getName()+ " was killed by Fire!");
					respawn(hit);
					Game.checkOver();
					
					}
				}
			}
			
		}
		
		if(event.getCause() == DamageCause.LAVA)
		{
			if(event.getEntity() instanceof Player)
			{
				Player hit = (Player)event.getEntity();
				if(PlayerManager.isInGame(hit.getName()))
				{
					double dam = event.getDamage();
					if(dam >= hit.getHealth())
					{
					
					hit.getWorld().spawnParticle(Particle.REDSTONE, hit.getLocation(),20,1.2,1.2,1.2,1,new Particle.DustOptions(Color.RED, 5));
					Team a = Team.getTeam(hit);
					a.addLives(-1);
					ChatUtilities.broadcast(hit.getName()+ " fell into lava!");
					respawn(hit);
					Game.checkOver();
					
					}
				}
			}
			
		}
		
		if(event.getCause() == DamageCause.DROWNING)
		{
			if(event.getEntity() instanceof Player)
			{
				Player hit = (Player)event.getEntity();
				if(PlayerManager.isInGame(hit.getName()))
				{
					double dam = event.getDamage();
					if(dam >= hit.getHealth())
					{
					
					hit.getWorld().spawnParticle(Particle.REDSTONE, hit.getLocation(),20,1.2,1.2,1.2,1,new Particle.DustOptions(Color.RED, 5));
					Team a = Team.getTeam(hit);
					a.addLives(-1);
					ChatUtilities.broadcast(hit.getName()+ " Drowned!");
					respawn(hit);
					Game.checkOver();
					
					}
				}
			}
			
		}
		
		if(event.getCause() == DamageCause.HOT_FLOOR)
		{
			if(event.getEntity() instanceof Player)
			{
				Player hit = (Player)event.getEntity();
				if(PlayerManager.isInGame(hit.getName()))
				{
					double dam = event.getDamage();
					if(dam >= hit.getHealth())
					{
					
					hit.getWorld().spawnParticle(Particle.REDSTONE, hit.getLocation(),20,1.2,1.2,1.2,1,new Particle.DustOptions(Color.RED, 5));
					Team a = Team.getTeam(hit);
					a.addLives(-1);
					ChatUtilities.broadcast(hit.getName()+ " was killed by Magma!");
					respawn(hit);
					Game.checkOver();
					
					}
				}
			}
			
		}
		
		
		
		if(event.getCause() == DamageCause.FALL)
		{
			if(event.getEntity() instanceof Player)
			{
			Player p = (Player)event.getEntity();
			
			if(PlayerManager.isInGame(p.getName()))
				event.setCancelled(true);
			}
			
			
		}
		
		
		
		
		
		
	}
	
	
	
	@EventHandler
	public void antiDrop(PlayerDropItemEvent event)	
	{
		
		Player p = event.getPlayer();
		
		if(PlayerManager.isInGame(p.getName()))
		{
			
			event.setCancelled(true);
			
		}
		
		
		
		
		
	}
	
	
	
	
	
	
}
