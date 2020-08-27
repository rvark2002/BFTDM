package com.vark.BFTDM.listeners;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.vark.BFTDM.ChatUtilities;
import com.vark.BFTDM.Game;
import com.vark.BFTDM.Main;
import com.vark.BFTDM.PlayerManager;
import com.vark.BFTDM.Team;
import com.vark.BFTDM.kits.KitManager;

import net.md_5.bungee.api.ChatColor;

public class BlasterListener extends GameListener
{
	Plugin plugin = Main.getPlugin(Main.class);	
	
	
	
	public BlasterListener(Main pl) {
		super(pl);
		
	}




	

	
	
	@EventHandler
	public void onBlasterFire(PlayerInteractEvent event)
	{
		
		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
			
			Player p = event.getPlayer();
			
			if(p.getInventory().getItemInMainHand().getType() == Material.MUSIC_DISC_CAT)
			{
				ItemStack is = p.getInventory().getItemInMainHand();
				
					if(PlayerManager.isInGame(p.getName()))
						fireBlaster(p);
					
				
			}
			
			
			
			if(p.getInventory().getItemInMainHand().getType() == Material.MUSIC_DISC_11)
			{
				ItemStack is = p.getInventory().getItemInMainHand();
				
					if(PlayerManager.isInGame(p.getName()))
						fireDH(p);
					
				
			}
			
			
			if(p.getInventory().getItemInMainHand().getType() == Material.MUSIC_DISC_MELLOHI)
			{
				
				ItemStack is = p.getInventory().getItemInMainHand();
				
				if(PlayerManager.isInGame(p.getName())) {
					
					fireSniper(p);
				}
				
				
			}
			
			
			
		}
		
		
		
		
		
	}
	
	
	@EventHandler
	public void onZoom(PlayerInteractEvent event)
	{
		
		if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK)
		{
			
			Player p = event.getPlayer();
			
			if(p.getInventory().getItemInMainHand().getType() == Material.MUSIC_DISC_CAT || p.getInventory().getItemInMainHand().getType() == Material.MUSIC_DISC_MELLOHI)
			{
				ItemStack is = p.getInventory().getItemInMainHand();
				
				
					
					if(PlayerManager.isInGame(p.getName()))
					{
						zoom(p);
						event.setCancelled(true);
					}
					
				
			}
			if(p.getInventory().getItemInMainHand().getType() == Material.MUSIC_DISC_11)
			{
				ItemStack is = p.getInventory().getItemInMainHand();
				
				
					
					if(PlayerManager.isInGame(p.getName()))
					{
						fireDH2(p);
						
					}
					
				
			}
			
			
			
		}
		
		
		
		
		
	}
	
	
	
	public void fireSniper(Player p)
	{
		
		
		if(RM.checkAmmos(p) && RM.checkCooldowns(p))
		{
		
			
				
				
				
					
					
					Snowball a = p.launchProjectile(Snowball.class);
					a.setShooter(p);
					a.setVelocity(a.getVelocity().multiply(5));
					

		
		p.getWorld().spawnParticle(Particle.SMOKE_NORMAL, p.getLocation(),3,.2,.2,.2,.1,null);
		
		
		
		Double d = RM.getAmmos(p);
		
		Double newammo = d-1;
		if(newammo > 0)
		{
			RM.setAmmos(p, newammo);
		}else {
			
			RM.setCooldowns(p, 7);
		}
		
		
		
		
		
		}else {
			RM.setAmmos(p, 3);
			p.sendMessage(ChatColor.RED+""+ChatColor.BOLD+"Reloading...");
		}
		
		
		
		
	}

	
	
	public void fireBlaster(Player p)
	{
		
		
		if(RM.checkAmmo(p) && RM.checkCooldown(p))
		{
		
		Snowball a = p.launchProjectile(Snowball.class);
		a.setVelocity(a.getVelocity().multiply(2));
		p.getWorld().spawnParticle(Particle.SMOKE_NORMAL, p.getLocation(),3,.2,.2,.2,.1,null);
		
		Double d = RM.getAmmo(p);
		
		Double newammo = d-1;
		if(newammo > 0)
		{
			RM.setAmmo(p, newammo);
		}else {
			
			RM.setCooldown(p, 5);
		}
		
		
		
		
		
		}else {
			RM.setAmmo(p, 30);
			p.sendMessage(ChatColor.RED+""+ChatColor.BOLD+"Reloading...");
		}
		
		
		
		
	}
	
	
	
	public void zoom(Player p)
	{
		
		if(!(p.hasPotionEffect(PotionEffectType.SPEED)))
		{
			if(p.getInventory().getItemInMainHand().getType() == Material.MUSIC_DISC_CAT || p.getInventory().getItemInMainHand().getType() == Material.MUSIC_DISC_MELLOHI)
			{
			
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,10000,-4));
				//give zoom
			}
			
		}else {
			p.removePotionEffect(PotionEffectType.SPEED);
		}
		
		
		new BukkitRunnable() {


			@Override
			public void run() {
				
				
				if(p.getInventory().getItemInMainHand().getType() == Material.MUSIC_DISC_CAT || p.getInventory().getItemInMainHand().getType() == Material.MUSIC_DISC_MELLOHI)
				{
					
				}else {
					p.removePotionEffect(PotionEffectType.SPEED);
					this.cancel();
					
				}
			}

		}.runTaskTimer(plugin, 0L, 20L);
			
		
	}
	
	
	
	

	
	
	
	public void fireDH(Player p)
	{
		
		
		if(RM.checkAmmodh(p) && RM.checkCooldowndh(p))
		{
		
		Snowball a = p.launchProjectile(Snowball.class);
		
		a.setVelocity(a.getVelocity().multiply(2));
		p.getWorld().spawnParticle(Particle.SMOKE_NORMAL, p.getLocation(),3,.2,.2,.2,.1,null);
		
		Double d = RM.getAmmodh(p);
		
		Double newammo = d-1;
		if(newammo > 0)
		{
			RM.setAmmodh(p, newammo);
		}else {
			
			RM.setCooldowndh(p, 5);
		}
		
		
		
		
		
		}else {
			RM.setAmmodh(p, 30);
			p.sendMessage(ChatColor.RED+""+ChatColor.BOLD+"Reloading...");
		}
		
		
		
		
	}
	
	
	
	
	
	
	
	
	public void fireDH2(Player p)
	{
		
		
		if(RM.checkAmmodh2(p) && RM.checkCooldowndh2(p))
		{
		
			
		Egg a = p.launchProjectile(Egg.class);
		
		p.getWorld().spawnParticle(Particle.SMOKE_NORMAL, p.getLocation(),3,.2,.2,.2,.1,null);
		
		Double d = RM.getAmmodh2(p);
		
		Double newammo = d-1;
		if(newammo > 0)
		{
			RM.setAmmodh2(p, newammo);
		}else {
			
			RM.setCooldowndh2(p, 10);
		}
		
		
		
		
		
		}else {
			RM.setAmmodh2(p, 1);
			p.sendMessage(ChatColor.RED+""+ChatColor.BOLD+"Reloading...");
		}
		
		
		
		
	}

	
	
	
	
	
	
	
	
	@EventHandler
	public void BlasterHit(ProjectileHitEvent event)
	{
		if(event.getEntity() instanceof Snowball)
		{
			Snowball snowball = (Snowball) event.getEntity();
			if(event.getHitEntity() instanceof Player)
			{
				Player p2 = (Player) event.getHitEntity();
				Player p = (Player)snowball.getShooter();
				
				if((PlayerManager.isInGame(p.getName()) && PlayerManager.isInGame(p2.getName())))
				{
				
				 
				
					
				
						if(!(p2.equals(p)))
						{
							if(Team.getTeam(p) == Team.getTeam(p2))
							{
								
								p.sendMessage(ChatColor.RED+""+ChatColor.BOLD+"Don't Attack Teammates!");
								
							}else {
							double dam = 3;
							
							if(Team.getTeam(p) == Team.getTeam("villains"))
							{
								dam = 5;
								
							}
							if((p2.getLocation().getDirection().dot(p.getLocation().getDirection()) < -0.5) && isSaber(p2.getInventory().getItemInMainHand()))
							{
								dam = 0;
								

							}else {
					
				
							if(dam >= p2.getHealth())
							{
				
								p2.getWorld().spawnParticle(Particle.REDSTONE, p2.getLocation(),20,1.2,1.2,1.2,1,new Particle.DustOptions(Color.RED, 5));
								Team a = Team.getTeam(p2);
								a.addLives(-1);
								ChatUtilities.broadcast(p2.getName()+ " was killed by "+p.getName());
								respawn(p2);
								Game.checkOver();
				
							}
							else 
							{
								p2.damage(dam);
							}
							
							
							}
				
				
						}
							
						}
				}
			}
			
			
			
			
			
		}
		
		
		if(event.getEntity() instanceof Egg)
		{
			
			
			Egg egg = (Egg) event.getEntity();
			
			
			
			//Block
			
			
			if(event.getHitBlock() != null)
			{
				Location loc = event.getHitBlock().getLocation();
				if(egg.getShooter() instanceof Player)
				{
				Player p = (Player) egg.getShooter();
				if(PlayerManager.isInGame(p.getName()))
				{
				event.getHitBlock().getWorld().spawnParticle(Particle.EXPLOSION_HUGE, loc.getX() ,loc.getY(), loc.getZ(), 1, null);
				
				for(Entity e: event.getHitBlock().getWorld().getNearbyEntities(loc, 10, 10, 10))
				{
					
					
					if(e instanceof Player)
					{
						
						Player p2 = (Player)e;
						
						
						
						if((PlayerManager.isInGame(p.getName()) && PlayerManager.isInGame(p2.getName())))
						{
						
						 
						
							
						
								if(!(p2.equals(p)))
								{
									if(Team.getTeam(p) == Team.getTeam(p2))
									{
										
										p.sendMessage(ChatColor.RED+""+ChatColor.BOLD+"Don't Attack Teammates!");
										
									}else 
									{
									
									int dam = 4;	

								
							
						
									if(dam >= p2.getHealth())
									{
						
										p2.getWorld().spawnParticle(Particle.REDSTONE, p2.getLocation(),20,1.2,1.2,1.2,1,new Particle.DustOptions(Color.RED, 5));
										Team a = Team.getTeam(p2);
										a.addLives(-1);
										ChatUtilities.broadcast(p2.getName()+ " was killed by "+p.getName());
										respawn(p2);
										Game.checkOver();
						
									}
									else 
									{
										p2.damage(dam);
									}
									
						
						
								}
									
								}
						}
					}
						
						
						
						
					}
				}
					
					
					
				}
				
				
				
			}
			
			
			
			//Player
			
			if(event.getHitEntity() != null)
			{
				Location loc = event.getHitEntity().getLocation();
				event.getHitEntity().getWorld().spawnParticle(Particle.EXPLOSION_HUGE, loc.getX() ,loc.getY(), loc.getZ(), 1, null);
				
				for(Entity e: event.getHitEntity().getWorld().getNearbyEntities(loc, 10, 10, 10))
				{
					int dam = 4;
					
					if(e.equals(event.getHitEntity()))
					{
						
						dam = 5;
						
						
						
					}
						
						
					
					
					
					if(e instanceof Player)
					{
						Player p = (Player) egg.getShooter();
						Player p2 = (Player)e;
						
						
						
						if((PlayerManager.isInGame(p.getName()) && PlayerManager.isInGame(p2.getName())))
						{

								if(!(p2.equals(p)))
								{
									if(Team.getTeam(p) == Team.getTeam(p2))
									{
										
										p.sendMessage(ChatColor.RED+""+ChatColor.BOLD+"Don't Attack Teammates!");
										
									}else 
									{
									
										

								
							
						
									if(dam >= p2.getHealth())
									{
						
										p2.getWorld().spawnParticle(Particle.REDSTONE, p2.getLocation(),20,1.2,1.2,1.2,1,new Particle.DustOptions(Color.RED, 5));
										Team a = Team.getTeam(p2);
										a.addLives(-1);
										ChatUtilities.broadcast(p2.getName()+ " was killed by "+p.getName());
										respawn(p2);
										Game.checkOver();
						
									}
									else 
									{
										p2.damage(dam);
									}
									
						
						
								}
									
								}
						}
						
						
						
						
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
		p.setFoodLevel(20);
		if(p.getFireTicks() != 0)
		{
			p.setFireTicks(0);
		}
		KitManager.giveKit(p);
		
		for (PotionEffect effect : p.getActivePotionEffects())
	        p.removePotionEffect(effect.getType());
		
		
	}
	
	
	
	private List<Entity> getEntitys(Player player, int range) {
		List<Entity> entitys = new ArrayList<Entity>();
		for (Entity e : player.getNearbyEntities(range, range, range)) {
			if (e instanceof LivingEntity) {
				if (getLookingAt(player, (LivingEntity) e)) {
					entitys.add(e);
				}
			}
		}

		return entitys;
	}
	
	private boolean getLookingAt(Player player, LivingEntity livingEntity) {
		Location eye = player.getEyeLocation();
		Vector toEntity = livingEntity.getEyeLocation().toVector()
				.subtract(eye.toVector());
		double dot = toEntity.normalize().dot(eye.getDirection());

		return dot > 0.99D;
	}
	
	
}
