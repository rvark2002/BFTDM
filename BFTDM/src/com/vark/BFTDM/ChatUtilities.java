package com.vark.BFTDM;

import static org.bukkit.ChatColor.*;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
public class ChatUtilities {
	
	
	public static void broadcast(String arg)
	{
		for(String s :PlayerManager.getPlayersInGame() )
		{
			Bukkit.getPlayer(s).sendMessage(starter() + arg);
		}
		
	}
	
	
	private static String starter()
	{
		
		return BLUE +"["+GOLD+"BFTDM"+BLUE+"]" + GOLD; 
	}
	

}




