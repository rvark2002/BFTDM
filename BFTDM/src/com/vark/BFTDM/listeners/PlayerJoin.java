package com.vark.BFTDM.listeners;

import static org.bukkit.ChatColor.BLUE;
import static org.bukkit.ChatColor.GOLD;

import java.io.File;


import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.vark.BFTDM.ChatUtilities;
import com.vark.BFTDM.Countdown;
import com.vark.BFTDM.Game;
import com.vark.BFTDM.Main;
import com.vark.BFTDM.PlayerManager;

public class PlayerJoin extends GameListener
{

	
	private Plugin plugin = Main.getPlugin(Main.class);
	
	public PlayerJoin(Main pl) {
		super(pl);
	}

	
	
	
	@EventHandler
	public void placeSign(SignChangeEvent event)
	{
		
		if(event.getBlock().getType() == Material.OAK_WALL_SIGN)
		{
			
		
			
			if(event.getLine(0).equals("BFTDM"))
			{
			
				if(event.getPlayer().hasPermission("bftdm.admin"))
				{
					event.setLine(0,BLUE +"["+GOLD+"BFTDM"+BLUE+"]");
					event.setLine(1, ChatColor.BOLD+"JOIN");
					
					event.getPlayer().sendMessage(ChatColor.GREEN+"Made a BlockfrontTDM Sign");
				}else {
					
					event.getPlayer().sendMessage(ChatColor.RED+"You Cannot Do This!");
				}
			}
			
		}
		
		
	}
	
	
	
	@EventHandler
	public void playerJoinHub(PlayerJoinEvent event)
	{
		
		Player p = event.getPlayer();
		
		if(p.getWorld().getName().equals("minigame"))
		{
			
			
			
			File file = new File(plugin.getDataFolder()+"/data/"+"respawnloc"+".yml");
			
			FileConfiguration config = YamlConfiguration.loadConfiguration(file);
			
	
			ConfigurationSection cs = config.getConfigurationSection("naboo");
			
			Location loc = (Location) cs.get("path0");
			
			p.teleport(loc);
			
			p.sendMessage(ChatColor.RED+"Teleported To Game Hub Because of leaving Mid-Game!");
			
			p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
			p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 100));
			p.setFoodLevel(20);
		
			
			for (PotionEffect effect : p.getActivePotionEffects())
		        p.removePotionEffect(effect.getType());
			
			p.getInventory().clear();
			
			
		}
		
		
		
		
		
	}
	
	
	
	
	
	
	
	@EventHandler
	public void onPlayerJoin(PlayerInteractEvent event)
	{
	
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
			Player p = event.getPlayer();
			if(event.getClickedBlock().getType() == Material.OAK_WALL_SIGN)
			{
				
				Sign s = (Sign)event.getClickedBlock().getState();
				if(s.getLine(0).equals(BLUE +"["+GOLD+"BFTDM"+BLUE+"]"))
				{
					if(s.getLine(1).equals( ChatColor.BOLD+"JOIN"))
					{
						
						
						if(Game.isStarted)
						{
							p.sendMessage(ChatColor.RED+"Game has already started!");
						}
						else {
						if(PlayerManager.isInGame(p.getName()))
						{
							p.sendMessage(ChatColor.RED+"You Are Already In The Game!");
						}
						else {
						PlayerManager.playerJoined(p);
						p.sendMessage(ChatColor.RED+"Joined Team Death Match Game!");
						ChatUtilities.broadcast(p.getName()+" has joined the Lobby"+" ("+PlayerManager.getPlayersInGame().size()+") !");
						Countdown.launchCountdown();
						
						
						File file = new File(plugin.getDataFolder()+"/data/"+"respawnloc"+".yml");
						
						FileConfiguration config = YamlConfiguration.loadConfiguration(file);
						
					
						ConfigurationSection cs = config.getConfigurationSection("lobby");
						
						Location loc = (Location) cs.get("path0");
						
						p.teleport(loc);

						}
						
						
						
						}
						
					}
				}
			}
		}
		
		


		
		
	}
	
	
	
	
	
	
}
