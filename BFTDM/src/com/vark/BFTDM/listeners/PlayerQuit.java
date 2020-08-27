package com.vark.BFTDM.listeners;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import com.vark.BFTDM.ChatUtilities;
import com.vark.BFTDM.Game;

import com.vark.BFTDM.Main;
import com.vark.BFTDM.PlayerManager;
import com.vark.BFTDM.Team;

import net.md_5.bungee.api.ChatColor;

public class PlayerQuit extends GameListener{

	public PlayerQuit(Main pl) {
		super(pl);
		// TODO Auto-generated constructor stub
	}

	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event)
	{
	
		if(PlayerManager.isInGame(event.getPlayer().getName()))
		{
			PlayerManager.playerLeft(event.getPlayer());
			Team.remove(event.getPlayer());
			ChatUtilities.broadcast(event.getPlayer().getName()+" has left the Lobby"+" ("+PlayerManager.getPlayersInGame().size()+") !");
			if(Game.isStarted)
			{
				Game.checkOver();
			}
		}
		

	}
	
	

	
	
	
	@EventHandler
	public void cancelCommands(PlayerCommandPreprocessEvent event)
	{
		
		if(PlayerManager.isInGame(event.getPlayer().getName()))
		{
			event.getPlayer().sendMessage(ChatColor.RED+"You Cannot use commands while in game! Please disconnect if you would like to leave!");
			event.setCancelled(true);
			
		}
		
		
	}
	
	
	
	
}
