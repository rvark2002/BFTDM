package com.vark.BFTDM;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import com.vark.BFTDM.kits.KitManager;
import com.vark.BFTDM.listeners.KitListener;

public class Game 
{

	private static Plugin plugin = Main.getPlugin(Main.class);
	
	
	
	
	private static String mapName;
	
	
	
	
	
	public static boolean canStart;
	
	public static boolean isStarted;
	
	
	

	
	
	public static String getMapName()
	{
		return mapName;
	}
	

	
	
	
	
	

	public static boolean canStart() {
		
		return canStart;
	}
	
	
	
	public static void setStarted(boolean b)
	{
		isStarted = b;
	}
	
	
	public static void assign()
	{
		
		 new Team("villains");
		 new Team("heroes");
		 
	
		 List<String> playerlist = PlayerManager.getPlayersInGame();
		 
		 Collections.shuffle(playerlist);
		 
		 
			int n = (int)((Math.random()*9)-1);
			for(String player: playerlist)
			{
				if(n%2 == 0)
				{
					
				}else {
					
				}
				
				int i = n%2;
				Team.getTeam(Team.getAllTeams().get(i).getName()).add(player);
				
				ChatUtilities.broadcast(player +" has joined the "+Team.getAllTeams().get(i).getName());
				n++;
				Player p = Bukkit.getPlayer(player);
				
				if(Team.getTeam(p) == Team.getTeam("heroes"))
				{
					p.openInventory(KitListener.getInvH());
				
				}
				if(Team.getTeam(p) == Team.getTeam("villains"))
				{
					p.openInventory(KitListener.getInvV());
					
				}
			}
			
			isStarted = true;
			
			
			
	}
	
	
	public static void setMap(String s)
	{
		mapName = s;
	}
	
	public static void start()
	{	

		File file = new File(plugin.getDataFolder()+"/data/"+"respawnloc"+".yml");
		
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);

		String mapPick = getRandomMap();

		ConfigurationSection cs = config.getConfigurationSection(mapPick);
		
		setMap(mapPick);
		
		
	
	
		for(String player: PlayerManager.getPlayersInGame())
		{


			
		
			Bukkit.getPlayer(player).getInventory().clear();
			KitManager.giveKit(Bukkit.getPlayer(player));
			Bukkit.getPlayer(player).setGameMode(GameMode.ADVENTURE);
			Player p = Bukkit.getPlayer(player);
			p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
			p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 100));
			p.setFoodLevel(20);
			
	
			
			int n1 = (int) ((Math.random()*8)+1);

			
		
			
			Location loc = (Location) cs.get("path"+n1);
			

	
			
			
			
			p.teleport(loc);
			
			
		}
		
		
		
		
	}
	
	public static void stop()
	{
		isStarted = false;
		
		
		
		
		Team.clearTeams();
		for(String s: PlayerManager.getPlayersInGame())
		{
			File file = new File(plugin.getDataFolder()+"/data/"+"respawnloc"+".yml");
			
			FileConfiguration config = YamlConfiguration.loadConfiguration(file);
			


			ConfigurationSection cs = config.getConfigurationSection(mapName);
			
		
			
			Location loc = (Location) cs.get("path0");
	
			
		
			Bukkit.getPlayer(s).teleport(loc);
			Bukkit.getPlayer(s).getInventory().clear();
			
			Player p = Bukkit.getPlayer(s);
			p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
			p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 100));
			p.setFoodLevel(20);
		
			
			for (PotionEffect effect : p.getActivePotionEffects())
		        p.removePotionEffect(effect.getType());
		}
		

		PlayerManager.clearDatabase();
	}









	public static void checkOver()
	{
		Team a = Team.getTeam("villains");
		Team b = Team.getTeam("heroes");
		
		if(a.getLives() <= 0)
		{
			ChatUtilities.broadcast("The "+b.getName()+" have Won!");	
			giveExp(b.getName(), a.getName());
			stop();
				
		}
		if(b.getLives() <= 0)
		{
			giveExp(a.getName(),b.getName());
			ChatUtilities.broadcast("The "+a.getName()+" have Won!");
			stop();
					
		}
		
		if(a.getPlayerCount() <= 0)
		{
			ChatUtilities.broadcast("The "+a.getName()+" has forfeit!");
			stop();
			
		}
		if(b.getPlayerCount() <= 0)
		{
			ChatUtilities.broadcast("The "+b.getName()+" has forfeit!");
			stop();
			
		}
	}
	
	
	
	
	
	
	public static void giveExp(String winner, String loser)
	{
		
		for(String s: PlayerManager.getPlayersInGame())
		{
			
			String a = Team.getTeam(Bukkit.getPlayer(s)).getName();
			
			if(a.equals(winner))
			{
				
				
				Levels.addexp(Bukkit.getPlayer(s), 15);
				
				Bukkit.getPlayer(s).sendMessage(ChatColor.GOLD+" You Gained 10xp for Winning!");
				
				
			}
			else
			{
				
				Levels.addexp(Bukkit.getPlayer(s), 5);
				
				Bukkit.getPlayer(s).sendMessage(ChatColor.GOLD+" You Gained 3xp for Playing!");
				
			}
			
			
			
			
			
			
			
			
		}
		
		
		
		
	}
	
	
	
	
	
	
	
	public static String getRandomMap()
	{
		
		
		
		
		File file = new File(plugin.getDataFolder()+"/data/"+"respawnloc"+".yml");
		
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		Set<String> keys = config.getKeys(false);
		

		List<String> maps = new ArrayList<String>();
		for(String s: keys)
		{
			if(!s.equals("lobby"))
			maps.add(s);
		}
		
		Collections.shuffle(maps);
		
		String map = maps.get(0);
		
		
		return map;
		
		
	
	}
	
	
	
	
	
	
}
