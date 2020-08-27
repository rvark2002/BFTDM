package com.vark.BFTDM.listeners;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
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
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import com.vark.BFTDM.ChatUtilities;
import com.vark.BFTDM.Game;
import com.vark.BFTDM.Main;
import com.vark.BFTDM.PlayerManager;
import com.vark.BFTDM.Team;
import com.vark.BFTDM.kits.KitManager;





public class PowerManager extends GameListener
{

	public PowerManager(Main pl) {
		super(pl);
		// TODO Auto-generated constructor stub
	}

	
	public ArrayList<TNTPrimed> tnt= new ArrayList<TNTPrimed>();
	
	
	@EventHandler
	public void playerRightClick(PlayerInteractEvent event)
	{
		
		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK )
		{
			Player p = event.getPlayer();
		
			if(PlayerManager.isInGame(p.getName()))
			{
			
				//Vader
				
				
				if(isRSaber(p))
				{
					if(CM.checkCooldown(p))
					{
						p.sendMessage(ChatColor.RED+"Threw Lightsaber!");
						CM.setCooldown(p, 10);
						ArmorStand as = p.getWorld().spawn(p.getLocation(),ArmorStand.class);
						as.setVisible(false);		
				
						ItemStack is = p.getInventory().getItemInMainHand();
						as.setGravity(false);			
						as.setArms(true);
						as.getEquipment().setItemInMainHand(is);
						
						p.getInventory().setItem(0, new ItemStack(Material.AIR));
				

						saberThrow(p,as,is);
						
					}
					else {
						p.sendMessage(ChatColor.RED+"You Cant Throw Your Saber For Another "+CM.getCooldown(p) +" seconds");
					}
					
				}
				

					if ((p.getInventory().getItemInMainHand()
							.getType() == Material.MUSIC_DISC_WAIT)) {

						if (p.getInventory().getItemInMainHand().getItemMeta()
								.hasCustomModelData()) {

							if (p.getInventory().getItemInMainHand().getItemMeta()
									.getCustomModelData() == 201) 
							{

											if (CM.checkCC(p)) 
											{
												for (Entity e : getEntitys(p, 15)) 
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
																
															}else {
															double dam = 4;
												
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
																p2.setVelocity(new Vector(0,2,0));
																
																Vector v = p.getLocation().getDirection().multiply(2);
																v.setY(2);
																p2.setVelocity(v);
																
																p2.damage(dam);
																CM.setCC(p, 4);
																
																
																
																
																
															}
															}
												
												
														}
												}

												

												
												
											} 
										}
											}else
												p.sendMessage(ChatColor.RED
														+ "You cannot use this Ability for another "
														+ CM.getCC(p) + " seconds!");
									
								

							}

						}
					}
				
					
				
				
				
				//Luke
					
					if ((p.getInventory().getItemInMainHand()
							.getType() == Material.MUSIC_DISC_WAIT)) 
					{
						if (p.getInventory().getItemInMainHand().getItemMeta()
								.hasCustomModelData()) {

							if (p.getInventory().getItemInMainHand()
									.getItemMeta().getCustomModelData() == 181) {

								if (CM.checkCR(p)) 
								{
									


										Location loc = p.getLocation();
										// player.addPotionEffect(new
										// PotionEffect(PotionEffectType.SPEED,100,10));

										for (double i = 0; i < 2
												* Math.PI; i += 0.1) 
										{

											double x = Math.cos(i) * 10;
											double y = Math.sin(i) * 10;

											p.getWorld().spawnParticle(
													Particle.REDSTONE,
													loc.getX() + x, loc.getY(),
													loc.getZ() + y, 1,
													new Particle.DustOptions(Color
															.fromRGB(255, 174, 251),
															5));
										}

										
										for (Entity e : p.getNearbyEntities(10,
												10, 10)) 
										{
											if(Team.getTeam((Player)(e)) != Team.getTeam(p))
											{
												
												
											
											double px = p.getLocation().getX();
											double pz = p.getLocation().getZ();
											double ex = e.getLocation().getX();
											double ez = e.getLocation().getZ();

											double z = 0;
											double x = 0;

											if (px < ex)
												x = Math.round(
														1 * Math.abs(px - ex) * 100)
														/ 100;
											if (px > ex)
												x = Math.round(-1
														* Math.abs(px - ex) * 100)
														/ 100;

											if (pz < ez)
												z = Math.round(
														1 * Math.abs(pz - ez) * 100)
														/ 100;
											if (pz > ez)
												z = Math.round(-1
														* Math.abs(pz - ez) * 100)
														/ 100;

											e.setVelocity(new Vector(x, 1.4, z));

										}
										}

										CM.setCR(p, 12);
									

								} else {
									p.sendMessage(ChatColor.DARK_AQUA
											+ "You cannot use this for another "
											+ CM.getCR(p) + " seconds!");
								}
							}
						}
					}
					
					
					
					if ((p.getInventory().getItemInMainHand()
							.getType() == Material.MUSIC_DISC_WAIT)) 
					{

						if (p.getInventory().getItemInMainHand().getItemMeta()
								.hasCustomModelData()) {

							if (p.getInventory().getItemInMainHand().getItemMeta()
									.getCustomModelData() == 111) {

								if (!(p.isSneaking())) {

									for (Entity e : getEntitys(p, 9)) {
										if (CM.checkCP(p)) {

											if(Team.getTeam((Player)(e)) != Team.getTeam(p))
											{
											((LivingEntity) e).setVelocity(
													p.getLocation().getDirection()
															.multiply(5));


											CM.setCP(p, 4);
											}
											
										} else
											p.sendMessage(ChatColor.GREEN
													+ "You cannot use the Force for another "
													+ CM.getCP(p) + " seconds!");
									}
								}
							}

						}

					}
					
					//REY
					
					
					
						if ((p.getInventory().getItemInMainHand()
								.getType() == Material.MUSIC_DISC_WAIT)) {

							if (p.getInventory().getItemInMainHand().getItemMeta()
									.hasCustomModelData()) {

								if (p.getInventory().getItemInMainHand().getItemMeta()
										.getCustomModelData() == 141) {

									if (CM.checkCdh(p)) {

										p.addPotionEffect(new PotionEffect(
												PotionEffectType.REGENERATION, 100, 2));
										CM.setCdh(p, 20);
										Location loc = p.getLocation();

										for (double i = 0; i < 2 * Math.PI; i += 0.1) {
											double x = Math.cos(i) * 5;
											double y = Math.sin(i) * 5;

											p.getWorld().spawnParticle(
													Particle.REDSTONE, loc.getX() + x,
													loc.getY(), loc.getZ() + y, 1,
													new Particle.DustOptions(Color.YELLOW,
															5));

										}

										for (Entity ent : p.getNearbyEntities(5, 5,
												5)) {

											if (ent instanceof Player) {
												ent = (Player) ent;
												
												if(Team.getTeam(p) == Team.getTeam((Player) ent))
												{
												p.sendMessage(ChatColor.YELLOW
														+ "Force Healed " + ent.getName());
												ent.sendMessage(ChatColor.YELLOW
														+ "Force Healed by "
														+ p.getName());
												((LivingEntity) ent)
														.addPotionEffect(new PotionEffect(
																PotionEffectType.HEAL, 1,
																50));
												}
											}

										}

									} else {
										p.sendMessage(ChatColor.YELLOW
												+ "You cannot use this for another "
												+ CM.getCdh(p) + " seconds!");
									}
								}
							}
						}
						
						
						if(p.getInventory().getItemInMainHand().getType() == Material.MUSIC_DISC_WAIT)
						{
							if(p.getInventory().getItemInMainHand().hasItemMeta())
							{
								if(p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData())
								{
									if(p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 191)
									{
										if(CM.checkCS(p))
										{
											for(Entity e: p.getNearbyEntities(40, 30, 40))
											{
												if(e instanceof Player)
												{
													if(Team.getTeam(p) != Team.getTeam((Player)e))
													((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,500,10));
												}
												
											}
											
											CM.setCS(p, 12);
											
											
										}else {
											p.sendMessage(ChatColor.LIGHT_PURPLE
													+ "You cannot use this for another "
													+ CM.getCS(p) + " seconds!");
										}
									}
								}
							}
						}
						
					

					
					
					
					
					
					
					
					
					
					
					
					
					//Kylo
					
					
					
						if ((p.getInventory().getItemInMainHand()
								.getType() == Material.MUSIC_DISC_WAIT)) 
						{

							if (p.getInventory().getItemInMainHand().getItemMeta()
									.hasCustomModelData()) {

								if (p.getInventory().getItemInMainHand().getItemMeta()
										.getCustomModelData() == 221) {

									if (!(p.isSneaking())) {

										for (Entity e : getEntitys(p, 10)) {
											if (CM.checkCPU(p)) {

												if(Team.getTeam((Player)(e)) != Team.getTeam(p))
												{
												((LivingEntity) e).setVelocity(
														p.getLocation().getDirection()
																.multiply(-4));


												CM.setCPU(p, 6);
												}
											} else
												p.sendMessage(ChatColor.RED
														+ "You cannot use the Force for another "
														+ CM.getCPU(p) + " seconds!");
										}
									}
								}

							}

						}
						
						
						
						
						if ((p.getInventory().getItemInMainHand()
								.getType() == Material.MUSIC_DISC_WAIT)) {

							if (p.getInventory().getItemInMainHand().getItemMeta()
									.hasCustomModelData()) {

								if (p.getInventory().getItemInMainHand()
										.getItemMeta().getCustomModelData() == 171) {

									for (Entity ent : getEntitys(p, 8)) {

										
										
										if (CM.checkCF(p)) {
											if(Team.getTeam((Player)(ent)) != Team.getTeam(p))
											{
											p.sendMessage(
													ChatColor.BLUE + "Force Freeze");
											if (ent instanceof Player) {
												ent.sendMessage(ChatColor.BLUE
														+ "Force Frozen by "
														+ p.getName());
											}

										

												((LivingEntity) ent).addPotionEffect(
														new PotionEffect(
																PotionEffectType.SLOW,
																200, 100));

												ent.getWorld().spawnParticle(
														Particle.REDSTONE,
														ent.getLocation(), 100, 1.2,
														1.2, 1.2, 1,
														new Particle.DustOptions(
																Color.BLUE, 5));
												CM.setCF(p, 20);
											}
											

										} else {
											p.sendMessage(ChatColor.BLUE
													+ "You cannot use this for another "
													+ CM.getCF(p) + " seconds!");
										}
									}

								}
							}
						}
					
					
					
					
					
					
					
					
					
					
					//Palpatine
					
					
					
					
					
						// Force Lightning
						// ======================================================================================
						

							if ((p.getInventory().getItemInMainHand()
									.getType() == Material.MUSIC_DISC_WAIT)
									&& (!p.isSneaking())) {

								if (p.getInventory().getItemInMainHand().getItemMeta()
										.hasCustomModelData()) {

									if (p.getInventory().getItemInMainHand().getItemMeta()
											.getCustomModelData() == 161) {

										if (CM.checkCL(p)) {
											int tier = 25;
											int cd = 4;

											for (int i = 0; i < 5; i++) {
												p.getWorld()
														.strikeLightning(p
																.getTargetBlock(null, tier)
																.getLocation());
											}

											CM.setCL(p, cd);
										} else {
											p.sendMessage(ChatColor.DARK_RED
													+ "You cannot use this for another "
													+ CM.getCL(p) + " seconds!");
										}
									}
								}
							}

						}

						// Force Storm======================================================
						if (((p.getInventory().getItemInMainHand()
								.getType() == Material.MUSIC_DISC_WAIT))
								&& (p.isSneaking())) 
						{
						

								if (p.getInventory().getItemInMainHand().getItemMeta()
										.hasCustomModelData()) {

									if (p.getInventory().getItemInMainHand().getItemMeta()
											.getCustomModelData() == 161) {

										if (CM.checkCds(p)) 
										{

											for (Entity e : p.getNearbyEntities(10, 10,
													10)) {
												if (e instanceof Player) {
													
													Player p2 = (Player)e;
													if(Team.getTeam(p) != Team.getTeam(p2))
													{
													
													Location l = e.getLocation();
													p.getWorld().strikeLightning(l);
													}
												}
												p.sendMessage(
														ChatColor.DARK_RED + "Force Storm");
														CM.setCds(p, 20);
											}

										} else {
											p.sendMessage(ChatColor.DARK_RED
													+ "You cannot use this for another "
													+ CM.getCds(p) + " seconds!");
										}
									}
								}
						}

							

						if ((p.getInventory().getItemInMainHand()
								.getType() == Material.MUSIC_DISC_WAIT)) {
							if (p.getInventory().getItemInMainHand().getItemMeta()
									.hasCustomModelData()) {

								if (p.getInventory().getItemInMainHand()
										.getItemMeta().getCustomModelData() == 131) {

									for (Entity ent : getEntitys(p, 7)) {

										if (ent instanceof Player) 
										{
											double dam = 3.0;

											Player p2 = (Player)ent;

											if(Team.getTeam(p) != Team.getTeam(p2) )
											{
																						

											if (CM.checkCddr(p)) {
												p.sendMessage(
														ChatColor.RED + "Force Drain");
												p2.sendMessage(ChatColor.RED
														+ "Force Drained by "
														+ p.getName());

			
												if(dam >= p2.getHealth())
												{
									
													p2.getWorld().spawnParticle(Particle.REDSTONE, p2.getLocation(),20,1.2,1.2,1.2,1,new Particle.DustOptions(Color.RED, 5));
													Team a = Team.getTeam(p2);
													a.addLives(-1);
													ChatUtilities.broadcast(p2.getName()+ " was killed by "+p.getName());
													respawn(p2);
													Game.checkOver();
													
													double ph = p.getHealth() + dam;
													
													if (ph > 20.0) {
														p.setHealth(20.0);
													} else {
														p.setHealth(ph);

													}
									
												}
												else 
												{

													
													p2.damage(dam);
													CM.setCC(p, 4);
													
													ent.getWorld().spawnParticle(
															Particle.CLOUD,
															ent.getLocation(), 20, 1, 1, 1,
															1);
													ent.getWorld().spawnParticle(
															Particle.CLOUD,
															p.getLocation(), 20, 1, 1,
															1, 1);

													double ph = p.getHealth() + dam;
													
													if (ph > 20.0) {
														p.setHealth(20.0);
													} else {
														p.setHealth(ph);

													}

											
														CM.setCddr(p, 20);
													
													
													
													
													
												}

												

		
												

											} else {
												p.sendMessage(ChatColor.RED
														+ "You cannot use this for another "
														+ CM.getCddr(p)
														+ " seconds!");
											}
										}
										
									}
									}
								}
							}
						}
						
							
							
						
					
							if ((p.getInventory().getItemInMainHand()
									.getType() == Material.MUSIC_DISC_WAIT)) {

								if (p.getInventory().getItemInMainHand().getItemMeta()
										.hasCustomModelData()) {

									if (p.getInventory().getItemInMainHand().getItemMeta()
											.getCustomModelData() == 211) {

										if (CM.checkCsa(p)) {

											p.addPotionEffect(new PotionEffect(
													PotionEffectType.HEAL, 1, 2));
											p.addPotionEffect(new PotionEffect(
													PotionEffectType.DAMAGE_RESISTANCE, 240, 4));
											CM.setCsa(p, 20);
											Location loc = p.getLocation();

											for (double i = 0; i < 2 * Math.PI; i += 0.1) {
												double x = Math.cos(i) * 5;
												double y = Math.sin(i) * 5;

												p.getWorld().spawnParticle(
														Particle.REDSTONE, loc.getX() + x,
														loc.getY(), loc.getZ() + y, 1,
														new Particle.DustOptions(Color.RED,
																5));

											}

											for (Entity ent : p.getNearbyEntities(5, 5,
													5)) {

												if (ent instanceof Player) {
													ent = (Player) ent;
													
													if(Team.getTeam(p) == Team.getTeam((Player) ent))
													{

													ent.sendMessage(ChatColor.DARK_RED
															+ "Sith Aura Rages In You");
													((LivingEntity) ent)
															.addPotionEffect(new PotionEffect(
																	PotionEffectType.DAMAGE_RESISTANCE,60 ,2
																	));
													}else {
														ent.sendMessage(ChatColor.DARK_RED
																+ "Sith Aura Rages In You");
														((LivingEntity) ent)
																.addPotionEffect(new PotionEffect(
																		PotionEffectType.BLINDNESS,120 ,5
																		));
														
														((LivingEntity) ent)
														.addPotionEffect(new PotionEffect(
																PotionEffectType.CONFUSION,120 ,5
																));
														
														((LivingEntity) ent)
														.addPotionEffect(new PotionEffect(
																PotionEffectType.WITHER,60 ,1
																));
														
													}
												}

											}

										} else {
											p.sendMessage(ChatColor.DARK_RED
													+ "You cannot use this for another "
													+ CM.getCsa(p) + " seconds!");
										}
									}
								}
							}
					
					
					
					
					
					
					
					
					
					
					//Leia
					
					
						if(p.getInventory().getItemInMainHand().getType() == Material.MUSIC_DISC_WAIT)
						{
							ItemStack is = p.getInventory().getItemInMainHand();
							
							if(is.hasItemMeta())
								if(is.getItemMeta().hasCustomModelData())
									if(is.getItemMeta().getCustomModelData() == 241)
									{
										if(is.getAmount() > 1)
										{
											is.setAmount(is.getAmount()-1);
											p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,100,2));
											p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL,1,5));
											p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION,1000,1));
											
											
											p.getWorld().spawnParticle(Particle.REDSTONE,
													p.getLocation(), 100, 1.2,1.2, 1.2, 1,new Particle.DustOptions(
															Color.GREEN, 5));
											
										}

										
									}
						}
						
						if(p.getInventory().getItemInMainHand().getType() == Material.TNT)
						{
							ItemStack is = p.getInventory().getItemInMainHand();
							
							if(is.hasItemMeta())								
										if(is.getAmount() > 1)
										{
											is.setAmount(is.getAmount()-1);
											
											TNTPrimed t = p.getWorld().spawn(p.getLocation(), TNTPrimed.class);
											
											tnt.add(t);
											t.setVelocity(p.getLocation().getDirection().multiply(1.2));
											
											
											
											p.getWorld().spawnParticle(Particle.REDSTONE,
													p.getLocation(), 100, 1.2,1.2, 1.2, 1,new Particle.DustOptions(
															Color.ORANGE, 5));
											
										}

										
									
						}
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					//Bossk
						
					
						
						//Rampage
						if ((p.getInventory().getItemInMainHand()
								.getType() == Material.MUSIC_DISC_WAIT))
						{

							if (p.getInventory().getItemInMainHand().getItemMeta()
									.hasCustomModelData()) {

								if (p.getInventory().getItemInMainHand()
										.getItemMeta().getCustomModelData() == 251) {
									
									
									if(CM.checkCra(p))
									{
										
										CM.setCra(p, 20);
										
										p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,180,3));
										
										p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,120,2));
										p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP,120,2));
										p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,120,2));
										p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,180,6));
										
										p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,120,6));
										
										
										p.sendMessage(ChatColor.DARK_RED+""+ChatColor.BOLD+"Activated Primal Rampage!");
										
									}else {
										
										p.sendMessage(ChatColor.DARK_RED
												+ "You cannot use this for another "
												+ CM.getCra(p)
												+ " seconds!");
										
										
									}
									
									
									
									
								}
							}
						}
					
						//Toxic
						if ((p.getInventory().getItemInMainHand()
								.getType() == Material.MUSIC_DISC_WAIT)) {
							if (p.getInventory().getItemInMainHand().getItemMeta()
									.hasCustomModelData()) {

								if (p.getInventory().getItemInMainHand()
										.getItemMeta().getCustomModelData() == 231) {

									if(CM.checkCt(p))
									{
										
										for (double i = 0; i < 2 * Math.PI; i += 0.1) {
											double x = Math.cos(i) * 5;
											double y = Math.sin(i) * 5;

											p.getWorld().spawnParticle(
													Particle.REDSTONE, p.getLocation().getX() + x,
													p.getLocation().getY(), p.getLocation().getZ() + y, 1,
													new Particle.DustOptions(Color.GREEN,
															5));

										}
										
									for (Entity ent : p.getNearbyEntities(10, 10, 10)) {

										if (ent instanceof Player) 
										{
											

											Player p2 = (Player)ent;

											if(Team.getTeam(p) != Team.getTeam(p2) )
											{
																						

											
												p2.sendMessage(ChatColor.DARK_GREEN
														+ "You Have Been Poisoned!");
												
												p2.addPotionEffect(new PotionEffect(PotionEffectType.POISON,120,1));
												p2.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,120,1));
												
											}
														

													
												
													CM.setCt(p, 8);
										}
									}
									
									
									}
		

											 else {
												p.sendMessage(ChatColor.GREEN
														+ "You cannot use this for another "
														+ CM.getCt(p)
														+ " seconds!");
											
										}
										
									
									
								}
							}
						}
					
					
					
					
					
					
					
					
					
					
					//Obiwan
					
					
						if ((p.getInventory().getItemInMainHand()
								.getType() == Material.MUSIC_DISC_WAIT))
						{

							if (p.getInventory().getItemInMainHand().getItemMeta()
									.hasCustomModelData()) {

								if (p.getInventory().getItemInMainHand()
										.getItemMeta().getCustomModelData() == 151) {

									for (Entity ent : getEntitys(p, 10))

										if (ent.getType() == EntityType.PLAYER) {
											
											Player e = (Player) ent;
											if(Team.getTeam(p) != Team.getTeam(e))
											{
												
											if (CM.checkCdm(p)) {
												

												p.sendMessage(ChatColor.LIGHT_PURPLE
																+ "Force Mind Trick");


												
													e.addPotionEffect(new PotionEffect(
															PotionEffectType.BLINDNESS,
															120, 100));
													e.addPotionEffect(new PotionEffect(
															PotionEffectType.CONFUSION,
															120, 50));
													e.sendMessage(ChatColor.LIGHT_PURPLE
															+ p.getName()
															+ " used a Mind Trick on You!");
													e.getWorld().spawnParticle(
															Particle.REDSTONE,
															ent.getLocation(), 100, 1.2,
															1.2, 1.2, 1,
															new Particle.DustOptions(
																	Color.PURPLE, 5));
													CM.setCdm(p, 25);
												

											} else {
												p.sendMessage(
														ChatColor.LIGHT_PURPLE
																+ "You cannot use this for another "
																+ CM.getCdm(p)
																+ " seconds!");
												
												
												
												
											}
											
											
											
											
											}
										}
								}
							}
						}

						
						
						if ((p.getInventory().getItemInMainHand()
								.getType() == Material.STICK))
						{

							if (p.getInventory().getItemInMainHand().getItemMeta()
									.hasCustomModelData()) {

								if (p.getInventory().getItemInMainHand()
										.getItemMeta().getCustomModelData() == 105) {

									for (Entity ent : getEntitys(p, 5))

										if (ent.getType() == EntityType.PLAYER) {
											
											Player p2 = (Player) ent;
											if(Team.getTeam(p) != Team.getTeam(p2))
											{
												
											if (CM.checkSL(p)) {
												

												p.sendMessage(ChatColor.GOLD
																+ "Saber Lunge");


												p.setVelocity(p.getLocation().getDirection().multiply(2));
													
													CM.setSL(p, 10);
													
													double dam = 4;
													
													if(dam >= p2.getHealth())
													{
										
														p2.getWorld().spawnParticle(Particle.REDSTONE, p2.getLocation(),20,1.2,1.2,1.2,1,new Particle.DustOptions(Color.RED, 5));
														Team a = Team.getTeam(p2);
														a.addLives(-1);
														ChatUtilities.broadcast(p2.getName()+ " was killed by "+p.getName());
														respawn(p2);
														Game.checkOver();
										
													}
													else {
														
														p2.damage(dam);
														p2.setVelocity(p.getLocation().getDirection().multiply(2));
													}

													
													
													
												

											} else {
												p.sendMessage(
														ChatColor.GOLD
																+ "You cannot use this for another "
																+ CM.getSL(p)
																+ " seconds!");
												
												
												
												
											}
											
											
											
											
											}
										}
								}
							}
						}
					
					
					
					
					
					
					
					
					
				

			
		}
	
	}
	
	

	
	
	
	
	
	
	
	@EventHandler
	public void playerRightClickPlayer(PlayerInteractEntityEvent event)
	{
		
		
		if(event.getRightClicked() instanceof Player )
		{
			
			Player p = event.getPlayer();
			Player p2 = (Player) event.getRightClicked();
		
			if(PlayerManager.isInGame(p.getName()))
			{
			
			
				//Vader
				
				
				if(isRSaber(p))
				{
					if(CM.checkCooldown(p))
					{
						p.sendMessage(ChatColor.RED+"Threw Lightsaber!");
						CM.setCooldown(p, 10);
						ArmorStand as = p.getWorld().spawn(p.getLocation(),ArmorStand.class);
						as.setVisible(false);		
				
						ItemStack is = p.getInventory().getItemInMainHand();
						as.setGravity(false);			
						as.setArms(true);
						as.getEquipment().setItemInMainHand(is);
						
						p.getInventory().setItem(0, new ItemStack(Material.AIR));
				

						saberThrow(p,as,is);
						
					}
					else {
						p.sendMessage(ChatColor.RED+"You Cant Throw Your Saber For Another "+CM.getCooldown(p) +" seconds");
					}
					
				}
				

					if ((p.getInventory().getItemInMainHand()
							.getType() == Material.MUSIC_DISC_WAIT)) {

						if (p.getInventory().getItemInMainHand().getItemMeta()
								.hasCustomModelData()) {

							if (p.getInventory().getItemInMainHand().getItemMeta()
									.getCustomModelData() == 201) 
							{

											if (CM.checkCC(p)) 
											{

												if((PlayerManager.isInGame(p.getName()) && PlayerManager.isInGame(p2.getName())))
												{
		
														if(!(p2.equals(p)))
														{
															if(Team.getTeam(p) == Team.getTeam(p2))
															{
																
																p.sendMessage(ChatColor.RED+""+ChatColor.BOLD+"Don't Attack Teammates!");
																
															}else {
															double dam = 4;
												
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
																p2.setVelocity(new Vector(0,2,0));
																
																Vector v = p.getLocation().getDirection().multiply(2);
																v.setY(2);
																p2.setVelocity(v);
																
																p2.damage(dam);
																CM.setCC(p, 4);
																
																
																
																
																
															}
															}
												
												
														}
												}

												

												
												
											 
										
											}else
												p.sendMessage(ChatColor.RED
														+ "You cannot use this Ability for another "
														+ CM.getCC(p) + " seconds!");
									
								

							}

						}
					}
				
					
				
				
				
				//Luke
					
					if ((p.getInventory().getItemInMainHand()
							.getType() == Material.MUSIC_DISC_WAIT)) 
					{
						if (p.getInventory().getItemInMainHand().getItemMeta()
								.hasCustomModelData()) {

							if (p.getInventory().getItemInMainHand()
									.getItemMeta().getCustomModelData() == 181) {

								if (CM.checkCR(p)) 
								{
									


										Location loc = p.getLocation();
										// player.addPotionEffect(new
										// PotionEffect(PotionEffectType.SPEED,100,10));

										for (double i = 0; i < 2
												* Math.PI; i += 0.1) 
										{

											double x = Math.cos(i) * 10;
											double y = Math.sin(i) * 10;

											p.getWorld().spawnParticle(
													Particle.REDSTONE,
													loc.getX() + x, loc.getY(),
													loc.getZ() + y, 1,
													new Particle.DustOptions(Color
															.fromRGB(255, 174, 251),
															5));
										}

										
										for (Entity e : p.getNearbyEntities(10,
												10, 10)) 
										{
											if(Team.getTeam((Player)(e)) != Team.getTeam(p))
											{
												
												
											
											double px = p.getLocation().getX();
											double pz = p.getLocation().getZ();
											double ex = e.getLocation().getX();
											double ez = e.getLocation().getZ();

											double z = 0;
											double x = 0;

											if (px < ex)
												x = Math.round(
														1 * Math.abs(px - ex) * 100)
														/ 100;
											if (px > ex)
												x = Math.round(-1
														* Math.abs(px - ex) * 100)
														/ 100;

											if (pz < ez)
												z = Math.round(
														1 * Math.abs(pz - ez) * 100)
														/ 100;
											if (pz > ez)
												z = Math.round(-1
														* Math.abs(pz - ez) * 100)
														/ 100;

											e.setVelocity(new Vector(x, 1.4, z));

										}
										}

										CM.setCR(p, 12);
									

								} else {
									p.sendMessage(ChatColor.DARK_AQUA
											+ "You cannot use this for another "
											+ CM.getCR(p) + " seconds!");
								}
							}
						}
					}
					
					
					
					if ((p.getInventory().getItemInMainHand()
							.getType() == Material.MUSIC_DISC_WAIT)) 
					{

						if (p.getInventory().getItemInMainHand().getItemMeta()
								.hasCustomModelData()) {

							if (p.getInventory().getItemInMainHand().getItemMeta()
									.getCustomModelData() == 111) {

								

									
										if (CM.checkCP(p)) {

											if(Team.getTeam(p2) != Team.getTeam(p))
											{
											p2.setVelocity(
													p.getLocation().getDirection()
															.multiply(5));


											CM.setCP(p, 4);
											}
											
										} else
											p.sendMessage(ChatColor.GREEN
													+ "You cannot use the Force for another "
													+ CM.getCP(p) + " seconds!");
									
								
							}

						}

					}
					
					//REY
					
					
					
						if ((p.getInventory().getItemInMainHand()
								.getType() == Material.MUSIC_DISC_WAIT)) {

							if (p.getInventory().getItemInMainHand().getItemMeta()
									.hasCustomModelData()) {

								if (p.getInventory().getItemInMainHand().getItemMeta()
										.getCustomModelData() == 141) {

									if (CM.checkCdh(p)) {

										p.addPotionEffect(new PotionEffect(
												PotionEffectType.REGENERATION, 100, 2));
										CM.setCdh(p, 20);
										Location loc = p.getLocation();

										for (double i = 0; i < 2 * Math.PI; i += 0.1) {
											double x = Math.cos(i) * 5;
											double y = Math.sin(i) * 5;

											p.getWorld().spawnParticle(
													Particle.REDSTONE, loc.getX() + x,
													loc.getY(), loc.getZ() + y, 1,
													new Particle.DustOptions(Color.YELLOW,
															5));

										}

										for (Entity ent : p.getNearbyEntities(5, 5,
												5)) {

											if (ent instanceof Player) {
												ent = (Player) ent;
												
												if(Team.getTeam(p) == Team.getTeam((Player) ent))
												{
												p.sendMessage(ChatColor.YELLOW
														+ "Force Healed " + ent.getName());
												ent.sendMessage(ChatColor.YELLOW
														+ "Force Healed by "
														+ p.getName());
												((LivingEntity) ent)
														.addPotionEffect(new PotionEffect(
																PotionEffectType.HEAL, 1,
																50));
												}
											}

										}

									} else {
										p.sendMessage(ChatColor.YELLOW
												+ "You cannot use this for another "
												+ CM.getCdh(p) + " seconds!");
									}
								}
							}
						}
						
						
						if(p.getInventory().getItemInMainHand().getType() == Material.MUSIC_DISC_WAIT)
						{
							if(p.getInventory().getItemInMainHand().hasItemMeta())
							{
								if(p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData())
								{
									if(p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 191)
									{
										if(CM.checkCS(p))
										{
											for(Entity e: p.getNearbyEntities(40, 30, 40))
											{
												if(e instanceof Player)
												{
													if(Team.getTeam(p) != Team.getTeam((Player)e))
													((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,500,10));
												}
												
											}
											
											CM.setCS(p, 12);
											
											
										}else {
											p.sendMessage(ChatColor.LIGHT_PURPLE
													+ "You cannot use this for another "
													+ CM.getCS(p) + " seconds!");
										}
									}
								}
							}
						}
						
					

					
					
					
					
					
					
					
					
					
					
					
					
					//Kylo
					
					
					
						if ((p.getInventory().getItemInMainHand()
								.getType() == Material.MUSIC_DISC_WAIT)) 
						{

							if (p.getInventory().getItemInMainHand().getItemMeta()
									.hasCustomModelData()) {

								if (p.getInventory().getItemInMainHand().getItemMeta()
										.getCustomModelData() == 221) {

									if (!(p.isSneaking())) {

										
											if (CM.checkCPU(p)) {

												if(Team.getTeam(p2) != Team.getTeam(p))
												{
												p2.setVelocity(
														p.getLocation().getDirection()
																.multiply(-4));


												CM.setCPU(p, 6);
												}
											} else
												p.sendMessage(ChatColor.RED
														+ "You cannot use the Force for another "
														+ CM.getCPU(p) + " seconds!");
										
									}
								}

							}

						}
						
						
						
						
						if ((p.getInventory().getItemInMainHand()
								.getType() == Material.MUSIC_DISC_WAIT)) {

							if (p.getInventory().getItemInMainHand().getItemMeta()
									.hasCustomModelData()) {

								if (p.getInventory().getItemInMainHand()
										.getItemMeta().getCustomModelData() == 171) {

								

										
										
										if (CM.checkCF(p)) {
											if(Team.getTeam(p2) != Team.getTeam(p))
											{
											p.sendMessage(
													ChatColor.BLUE + "Force Freeze");
											
												p2.sendMessage(ChatColor.BLUE
														+ "Force Frozen by "
														+ p.getName());
											

										

												p2.addPotionEffect(
														new PotionEffect(
																PotionEffectType.SLOW,
																200, 100));

												p2.getWorld().spawnParticle(
														Particle.REDSTONE,
														p2.getLocation(), 100, 1.2,
														1.2, 1.2, 1,
														new Particle.DustOptions(
																Color.BLUE, 5));
												CM.setCF(p, 20);
											}
											

										} else {
											p.sendMessage(ChatColor.BLUE
													+ "You cannot use this for another "
													+ CM.getCF(p) + " seconds!");
										}
									

								}
							}
						}
					
					
					
					
					
					
					
					
					
					
					//Palpatine
					
					
					
					
					
						// Force Lightning
						// ======================================================================================
						

							if ((p.getInventory().getItemInMainHand()
									.getType() == Material.MUSIC_DISC_WAIT)
									&& (!p.isSneaking())) {

								if (p.getInventory().getItemInMainHand().getItemMeta()
										.hasCustomModelData()) {

									if (p.getInventory().getItemInMainHand().getItemMeta()
											.getCustomModelData() == 161) {

										if (CM.checkCL(p)) {
											int tier = 25;
											int cd = 3;

											for (int i = 0; i < 5; i++) {
												p.getWorld()
														.strikeLightning(p
																.getTargetBlock(null, tier)
																.getLocation());
											}

											CM.setCL(p, cd);
										} else {
											p.sendMessage(ChatColor.DARK_RED
													+ "You cannot use this for another "
													+ CM.getCL(p) + " seconds!");
										}
									}
								}
							}

						}

						// Force Storm======================================================
						if (((p.getInventory().getItemInMainHand()
								.getType() == Material.MUSIC_DISC_WAIT))
								&& (p.isSneaking())) 
						{
						

								if (p.getInventory().getItemInMainHand().getItemMeta()
										.hasCustomModelData()) {

									if (p.getInventory().getItemInMainHand().getItemMeta()
											.getCustomModelData() == 161) {

										if (CM.checkCds(p)) 
										{

											for (Entity e : p.getNearbyEntities(10, 10,
													10)) {
												if (e instanceof Player) {
													
												
													if(Team.getTeam(p) != Team.getTeam(p2))
													{
													
													Location l = e.getLocation();
													p.getWorld().strikeLightning(l);
													}
												}
												p.sendMessage(
														ChatColor.DARK_RED + "Force Storm");
														CM.setCds(p, 10);
											}

										} else {
											p.sendMessage(ChatColor.DARK_RED
													+ "You cannot use this for another "
													+ CM.getCds(p) + " seconds!");
										}
									}
								}
						}

							

						if ((p.getInventory().getItemInMainHand()
								.getType() == Material.MUSIC_DISC_WAIT)) {
							if (p.getInventory().getItemInMainHand().getItemMeta()
									.hasCustomModelData()) {

								if (p.getInventory().getItemInMainHand()
										.getItemMeta().getCustomModelData() == 131) {

								

											double dam = 3.0;

											

											if(Team.getTeam(p) != Team.getTeam(p2) )
											{
																						

											if (CM.checkCddr(p)) {
												p.sendMessage(
														ChatColor.RED + "Force Drain");
												p2.sendMessage(ChatColor.RED
														+ "Force Drained by "
														+ p.getName());

			
												if(dam >= p2.getHealth())
												{
									
													p2.getWorld().spawnParticle(Particle.REDSTONE, p2.getLocation(),20,1.2,1.2,1.2,1,new Particle.DustOptions(Color.RED, 5));
													Team a = Team.getTeam(p2);
													a.addLives(-1);
													ChatUtilities.broadcast(p2.getName()+ " was killed by "+p.getName());
													respawn(p2);
													Game.checkOver();
													
													double ph = p.getHealth() + dam;
													
													if (ph > 20.0) {
														p.setHealth(20.0);
													} else {
														p.setHealth(ph);

													}
									
												}
												else 
												{

													
													p2.damage(dam);
													CM.setCC(p, 4);
													
													p2.getWorld().spawnParticle(
															Particle.CLOUD,
															p2.getLocation(), 20, 1, 1, 1,
															1);
													p2.getWorld().spawnParticle(
															Particle.CLOUD,
															p.getLocation(), 20, 1, 1,
															1, 1);

													double ph = p.getHealth() + dam;
													
													if (ph > 20.0) {
														p.setHealth(20.0);
													} else {
														p.setHealth(ph);

													}

											
														CM.setCddr(p, 20);
													
													
													
													
													
												}

												

		
												

											} else {
												p.sendMessage(ChatColor.RED
														+ "You cannot use this for another "
														+ CM.getCddr(p)
														+ " seconds!");
											}
										}
										
									
									
								}
							}
						}
						
							
							
						
					
							if ((p.getInventory().getItemInMainHand()
									.getType() == Material.MUSIC_DISC_WAIT)) {

								if (p.getInventory().getItemInMainHand().getItemMeta()
										.hasCustomModelData()) {

									if (p.getInventory().getItemInMainHand().getItemMeta()
											.getCustomModelData() == 211) {

										if (CM.checkCsa(p)) {

											p.addPotionEffect(new PotionEffect(
													PotionEffectType.HEAL, 1, 2));
											p.addPotionEffect(new PotionEffect(
													PotionEffectType.DAMAGE_RESISTANCE, 160, 4));
											CM.setCsa(p, 20);
											Location loc = p.getLocation();

											for (double i = 0; i < 2 * Math.PI; i += 0.1) {
												double x = Math.cos(i) * 5;
												double y = Math.sin(i) * 5;

												p.getWorld().spawnParticle(
														Particle.REDSTONE, loc.getX() + x,
														loc.getY(), loc.getZ() + y, 1,
														new Particle.DustOptions(Color.RED,
																5));

											}

											for (Entity ent : p.getNearbyEntities(5, 5,
													5)) {

												if (ent instanceof Player) {
													ent = (Player) ent;
													
													if(Team.getTeam(p) == Team.getTeam((Player) ent))
													{

													ent.sendMessage(ChatColor.DARK_RED
															+ "Sith Aura Rages In You");
													((LivingEntity) ent)
															.addPotionEffect(new PotionEffect(
																	PotionEffectType.DAMAGE_RESISTANCE,120 ,2
																	));
													}else {
														ent.sendMessage(ChatColor.DARK_RED
																+ "Sith Aura Rages In You");
														((LivingEntity) ent)
																.addPotionEffect(new PotionEffect(
																		PotionEffectType.BLINDNESS,120 ,5
																		));
														
														((LivingEntity) ent)
														.addPotionEffect(new PotionEffect(
																PotionEffectType.CONFUSION,120 ,5
																));
														
														((LivingEntity) ent)
														.addPotionEffect(new PotionEffect(
																PotionEffectType.WITHER,60 ,1
																));
														
													}
												}

											}

										} else {
											p.sendMessage(ChatColor.DARK_RED
													+ "You cannot use this for another "
													+ CM.getCsa(p) + " seconds!");
										}
									}
								}
							}
					
					
					
					
					
					
					
					
					
					
					//Leia
					
					
						if(p.getInventory().getItemInMainHand().getType() == Material.MUSIC_DISC_WAIT)
						{
							ItemStack is = p.getInventory().getItemInMainHand();
							
							if(is.hasItemMeta())
								if(is.getItemMeta().hasCustomModelData())
									if(is.getItemMeta().getCustomModelData() == 241)
									{
										if(is.getAmount() > 1)
										{
											is.setAmount(is.getAmount()-1);
											p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,100,2));
											p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL,1,5));
											p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION,1000,1));
											
											
											p.getWorld().spawnParticle(Particle.REDSTONE,
													p.getLocation(), 100, 1.2,1.2, 1.2, 1,new Particle.DustOptions(
															Color.GREEN, 5));
											
										}

										
									}
						}
						
						if(p.getInventory().getItemInMainHand().getType() == Material.TNT)
						{
							ItemStack is = p.getInventory().getItemInMainHand();
							
							if(is.hasItemMeta())								
										if(is.getAmount() > 1)
										{
											is.setAmount(is.getAmount()-1);
											
											TNTPrimed t = p.getWorld().spawn(p.getLocation(), TNTPrimed.class);
											
											tnt.add(t);
											t.setVelocity(p.getLocation().getDirection().multiply(1.2));
											
											
											
											p.getWorld().spawnParticle(Particle.REDSTONE,
													p.getLocation(), 100, 1.2,1.2, 1.2, 1,new Particle.DustOptions(
															Color.ORANGE, 5));
											
										}

										
									
						}
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					//Bossk
						
					
						
						//Rampage
						if ((p.getInventory().getItemInMainHand()
								.getType() == Material.MUSIC_DISC_WAIT))
						{

							if (p.getInventory().getItemInMainHand().getItemMeta()
									.hasCustomModelData()) {

								if (p.getInventory().getItemInMainHand()
										.getItemMeta().getCustomModelData() == 251) {
									
									
									if(CM.checkCra(p))
									{
										
										CM.setCra(p, 20);
										
										p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,180,3));
										
										p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,120,2));
										p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP,120,2));
										p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,120,2));
										p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,180,6));
										
										p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,120,6));
										
										
										p.sendMessage(ChatColor.DARK_RED+""+ChatColor.BOLD+"Activated Primal Rampage!");
										
									}else {
										
										p.sendMessage(ChatColor.DARK_RED
												+ "You cannot use this for another "
												+ CM.getCra(p)
												+ " seconds!");
										
										
									}
									
									
									
									
								}
							}
						}
					
						//Toxic
						if ((p.getInventory().getItemInMainHand()
								.getType() == Material.MUSIC_DISC_WAIT)) {
							if (p.getInventory().getItemInMainHand().getItemMeta()
									.hasCustomModelData()) {

								if (p.getInventory().getItemInMainHand()
										.getItemMeta().getCustomModelData() == 231) {

									if(CM.checkCt(p))
									{
										
										for (double i = 0; i < 2 * Math.PI; i += 0.1) {
											double x = Math.cos(i) * 5;
											double y = Math.sin(i) * 5;

											p.getWorld().spawnParticle(
													Particle.REDSTONE, p.getLocation().getX() + x,
													p.getLocation().getY(), p.getLocation().getZ() + y, 1,
													new Particle.DustOptions(Color.GREEN,
															5));

										}
										
									for (Entity ent : p.getNearbyEntities(10, 10, 10)) {

										if (ent instanceof Player) 
										{
											

										

											if(Team.getTeam(p) != Team.getTeam(p2) )
											{
																						

											
												p2.sendMessage(ChatColor.DARK_GREEN
														+ "You Have Been Poisoned!");
												
												p2.addPotionEffect(new PotionEffect(PotionEffectType.POISON,120,1));
												p2.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,120,1));
												
											}
														

													
												
													CM.setCt(p, 8);
										}
									}
									
									
									}
		

											 else {
												p.sendMessage(ChatColor.GREEN
														+ "You cannot use this for another "
														+ CM.getCt(p)
														+ " seconds!");
											
										}
										
									
									
								}
							}
						}
					
					
					
					
					
					
					
					
					
					
					//Obiwan
					
					
						if ((p.getInventory().getItemInMainHand()
								.getType() == Material.MUSIC_DISC_WAIT))
						{

							if (p.getInventory().getItemInMainHand().getItemMeta()
									.hasCustomModelData()) {

								if (p.getInventory().getItemInMainHand()
										.getItemMeta().getCustomModelData() == 151) {

									

							
											
										
											if(Team.getTeam(p) != Team.getTeam(p2))
											{
												
											if (CM.checkCdm(p)) {
												

												p.sendMessage(ChatColor.LIGHT_PURPLE
																+ "Force Mind Trick");


												
													p2.addPotionEffect(new PotionEffect(
															PotionEffectType.BLINDNESS,
															350, 100));
													p2.addPotionEffect(new PotionEffect(
															PotionEffectType.CONFUSION,
															350, 50));
													p2.sendMessage(ChatColor.LIGHT_PURPLE
															+ p.getName()
															+ " used a Mind Trick on You!");
													p2.getWorld().spawnParticle(
															Particle.REDSTONE,
															p2.getLocation(), 100, 1.2,
															1.2, 1.2, 1,
															new Particle.DustOptions(
																	Color.PURPLE, 5));
													CM.setCdm(p, 25);
												

											} else {
												p.sendMessage(
														ChatColor.LIGHT_PURPLE
																+ "You cannot use this for another "
																+ CM.getCdm(p)
																+ " seconds!");
												
												
												
												
											}
											
											
											
											
											}
										
								}
							}
						}

						
						
						if ((p.getInventory().getItemInMainHand()
								.getType() == Material.STICK))
						{

							if (p.getInventory().getItemInMainHand().getItemMeta()
									.hasCustomModelData()) {

								if (p.getInventory().getItemInMainHand()
										.getItemMeta().getCustomModelData() == 105) {

								

							
											if(Team.getTeam(p) != Team.getTeam(p2))
											{
												
											if (CM.checkSL(p)) {
												

												p.sendMessage(ChatColor.GOLD
																+ "Saber Lunge");


												p.setVelocity(p.getLocation().getDirection().multiply(2));
													
													CM.setSL(p, 10);
													
													double dam = 4;
													
													if(dam >= p2.getHealth())
													{
										
														p2.getWorld().spawnParticle(Particle.REDSTONE, p2.getLocation(),20,1.2,1.2,1.2,1,new Particle.DustOptions(Color.RED, 5));
														Team a = Team.getTeam(p2);
														a.addLives(-1);
														ChatUtilities.broadcast(p2.getName()+ " was killed by "+p.getName());
														respawn(p2);
														Game.checkOver();
										
													}
													else {
														
														p2.damage(dam);
														p2.setVelocity(p.getLocation().getDirection().multiply(2));
													}

													
													
													
												

											} else {
												p.sendMessage(
														ChatColor.GOLD
																+ "You cannot use this for another "
																+ CM.getSL(p)
																+ " seconds!");
												
												
												
												
											}
											
											
											
											
											}
										
								}
							}
						}
					
					
					
					
					
					
					
					
					
				

			
		}
	
	}
	
	
	
	
	
	
	
	@EventHandler
	public void onTntExplode(EntityExplodeEvent event)
	{
		
		
		if(event.getEntity() instanceof TNTPrimed)
		{
			if(tnt.size() > 0)
			{
			
			for(TNTPrimed t: tnt)
			{
				if(event.getEntity().getEntityId() == t.getEntityId())
				{
			
				
					
				for(Entity e: event.getEntity().getNearbyEntities(10, 10, 10))
				{
					if(e instanceof Player)
					{
				
						
						Player p2 = (Player)e;
						
						if(Team.getTeam(p2) != Team.getTeam("heroes"))
						{
							
							p2.addPotionEffect(
							new PotionEffect(
									PotionEffectType.SLOW,
									200, 100));
							
							
							
							
							
						}
					}
					
				}
				
				
					event.setCancelled(true);
				
					//tnt.remove(t);
				
				}
			
			
			}
			
			
			}
			
		}
		

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void saberThrow(Player p,ArmorStand armorstand,ItemStack is)
	{
		
		
		new BukkitRunnable() {
			
			int a = 0;
	
			Location l = new Location(p.getLocation().getWorld(),p.getLocation().getX(),p.getLocation().getY()+1,p.getLocation().getZ(),p.getLocation().getYaw(),p.getLocation().getPitch());
			
			
			ArmorStand as = armorstand;

			
			@Override
			public void run() {
					
				if(a == 0)
				{
					l = l.clone().add(l.getDirection().multiply(1));
				}
				
					if(!(isAir(l)))
					{
						this.cancel();
						
						p.getInventory().setItem(0,is);
						as.remove();
						
					}
					else 
					{

					a+=1;
					setArmorStand(as,l,p);
					l = l.clone().add(l.getDirection().multiply(a/2));

				if(a==11)
				{
					as.teleport(p.getLocation());
				}
				if(a == 12)
				{
					as.remove();
					if(!(p.isDead()))
					{		
						if(PlayerManager.isInGame(p.getName()))
						{
							p.getInventory().setItem(0,is);
						}
						
					}
					
					this.cancel();
				}
					}
				
				
				
			}
			

			
		}.runTaskTimer(plugin, 0L, 3L);
		
		
		
	}
	
	
	
	
	
	
	public boolean isAir(Location l)
	{
	
		Location loc = new Location(l.getWorld(),l.getX(),l.getY()+0.5,l.getZ());
		Block b = loc.getBlock();
			
			if(b.getType() == Material.AIR)
			{
				
				return true;
				
			}
			if(b.getType() == Material.TALL_GRASS || b.getType() == Material.GRASS)
			{
				return true;
			}
			
			return false;
	}
	

	
	
	
	
	public void hitMob(ArmorStand as,Player p)
	{
		Location l = as.getLocation();
		if(l.getWorld().getNearbyEntities(l, 1, 1, 1).size() >0)
		{
			for(Entity e: l.getWorld().getNearbyEntities(l, 1, 1, 1))
			{
				
				if(e instanceof LivingEntity)
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
											
										}else {
										double dam = 4;
							
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
		

	
	
	public void setArmorStand(ArmorStand as,Location l,Player p)
	{

		as.teleport(l);
		as.setRightArmPose(new EulerAngle(0,as.getRightArmPose().getY()+45,0));	
		hitMob(as,p);	
	}	
	

	
	
	@EventHandler
	public void OnInteractAtEntity(PlayerInteractEntityEvent e) {
	
		if(e.getRightClicked() instanceof ArmorStand) {
	
			ArmorStand as = (ArmorStand)e;
			if(as.hasArms())
			{
				e.setCancelled(true);
			}
				
				
		}
	}
	
	@EventHandler
	public void dupeGlitch(PlayerArmorStandManipulateEvent event)
	{
		
		if(event.getRightClicked().hasArms())
		{
			
			event.setCancelled(true);
	
		}

		
	}

	public boolean isRSaber(Player p) 
	{

		if(!(p.isDead()))
		{
		
		
			if( !( (p.getInventory().getItemInMainHand().getType() == Material.AIR) || (p.getInventory().getItemInMainHand().getType() == null)) )
			{
				if(p.getInventory().getItemInMainHand().getType() == Material.STICK)
				{
				
					if(p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData())
					{
						int a = p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData();
					
					
							if(a == 102)
							{
								return true;
							}
					}
				}
			}
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
		
		
	}
	
	@EventHandler
	public void playerPlaceTnt(BlockPlaceEvent event)
	{
		
		Player p = event.getPlayer();
		
		if(PlayerManager.isInGame(p.getName()))
		{
			event.setCancelled(true);
		}
		
		
		
	}
	
	
	
	
	
	@EventHandler
	public void stopSwap(PlayerSwapHandItemsEvent event)
	{
		if(PlayerManager.isInGame(event.getPlayer().getName()))
			event.setCancelled(true);
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
