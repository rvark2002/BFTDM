package com.vark.BFTDM;

import java.io.File;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.sun.istack.internal.NotNull;


import net.md_5.bungee.api.ChatColor;

public class LocationManager implements CommandExecutor
{

	
	private Plugin plugin = Main.getPlugin(Main.class);

	public boolean onCommand(@NotNull CommandSender cs, @NotNull Command arg1, @NotNull String arg2,
			@NotNull String[] arg3) 
	{
		if(cs instanceof Player)
		{
			Player p = (Player)cs;
			if(p.hasPermission("bftdm.admin"))
			{
			if(arg3.length >= 1)
				if(arg3[0].equals("setloc"))
				{
					if(validMap(arg3[1]))
					{
					
							int r = Integer.parseInt(arg3[2]);
							if(r<10 && r>=0)
							{
								setLoc(r,p,arg3[1]);
						return true;
							}else {
								p.sendMessage(ChatColor.RED + "Invalid Arguements!");
								return true;
								}
					}else {
					
						p.sendMessage(ChatColor.RED + "Invalid Map Name");
						return true;
						}
					
				}
				
				
				else if(arg3[0].equals("setmap"))
					{
					
					
						if(arg3.length == 2)
						{
							setMap(arg3[1],p);
							return true;
						
						}else {
							p.sendMessage(ChatColor.RED + "/bftdm setmap [map]");
							return true;
						}
					
					
					
					}
			
				
				
					else {
					
					p.sendMessage(ChatColor.RED + "/bftdm setloc [map] [number]");
					p.sendMessage(ChatColor.RED + "/bftdm setmap [map]");
					return true;
					}
					
					
					}else {
						p.sendMessage(ChatColor.RED + "Invalid Permissions");
						return true;
					}
		
				
			}
	
		
		
		
		
		return false;
	}

	
	
	
	private void setMap(String s, Player p) {
		
		
		
		File file = new File(plugin.getDataFolder()+"/data/"+"respawnloc"+".yml");
		
		if (!file.exists()) 
		{ //Checks if the file doesn't exist
			try {
				file.createNewFile(); //Tries to create the file
				FileConfiguration config = YamlConfiguration.loadConfiguration(file);
				config.save(file); 
				
			} catch (IOException ex) {
				p.sendMessage("Error 401 Contact An Admin");
			} 
		}
	
		else 
		{
			try {
				FileConfiguration config = YamlConfiguration.loadConfiguration(file);
	
			
				config.createSection(s);
				config.save(file); 
				p.sendMessage(ChatColor.AQUA+"Created Map: " + config.getConfigurationSection(s).toString());
		
			}catch (IOException ex) {
				p.sendMessage("Error 501 Contact an Admin");
			}
		}
		
		
		
	}




	public boolean validMap(String s)
	{
		
		
		
		File file = new File(plugin.getDataFolder()+"/data/"+"respawnloc"+".yml");
		if(file.exists())
		{
			
			FileConfiguration config = YamlConfiguration.loadConfiguration(file);
			
			return config.isConfigurationSection(s);
			
			
			
			
			
		}
		
		
		
		
		
		
		
		
		
		return false;
		
	}
	
	public void setLoc(int n, Player p, String map)
	{
		
	
		
		File file = new File(plugin.getDataFolder()+"/data/"+"respawnloc"+".yml");
		
		// creates a new file with the given path example: File file = new File("plugins/Test/" + player.getUniqueId() + ".yml");
    		if (file.exists()) 
    		{ //Checks if the file doesn't exist
    			
    		
    				FileConfiguration config = YamlConfiguration.loadConfiguration(file);
    				
    				Location loc = p.getLocation();
    				ConfigurationSection cs = config.getConfigurationSection(map);
    				
    				cs.set("path"+n,loc);

    				try {
						config.save(file);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
    				
    				p.sendMessage(ChatColor.AQUA+"Respawn "+n+" Set: X: "+config.get("locx")+" Y: "+config.get("locy")+" Z: "+config.get("locz"));

    			
    		}
    	
    		else 
    		{
    			p.sendMessage(ChatColor.RED+"File Not Found Error!");
    		}	
	}

}
