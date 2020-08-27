package com.vark.BFTDM;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;


public class Countdown 
{

	private static Plugin plugin = Main.getPlugin(Main.class);
	
	public static int countdowntime;
	

	
	private static boolean counterOn = false;
	
	public static void launchCountdown()
	{
		
		if(PlayerManager.getPlayersInGame().size() >= 4 && !counterOn)
		{
			ChatUtilities.broadcast("Minimum Player Requirements Fulfilled! Attemtping to start Game!");
		
			timer();
			
			counterOn = true;
		}
		else if(PlayerManager.getPlayersInGame().size() < 4){
			ChatUtilities.broadcast("Not Enough Players In Game!");
			counterOn = false;
		}
		
	}
	
	
	
	
	
	
	public static void timer()
	{
		counterOn = true;
		countdowntime = 60;
		new BukkitRunnable() {
			
			
			@Override
			public void run() 
			{
				
				
					countdowntime--;
				
				
				
			if((countdowntime%10 == 0 && countdowntime >=0)|| (countdowntime <=10 && countdowntime >=0))	
			{
				ChatUtilities.broadcast(countdowntime + " seconds left");
				
			}
			
			if(countdowntime == 30)//Choose Ranks
			{
				if(PlayerManager.getPlayersInGame().size() >= 4)
				{
				ChatUtilities.broadcast("Starting Game!");
				ChatUtilities.broadcast("Choose Class!");
				
				Game.assign();
				
				
				}
				else 
				{
					this.cancel();
			
					ChatUtilities.broadcast("Not Enough Players!");
					Game.setStarted(false);
					counterOn = false;
					
				}
			}
			if(countdowntime == 0)
			{
				if(PlayerManager.getPlayersInGame().size() >= 4)
				{
				ChatUtilities.broadcast("Teleporting To Arena!");
				this.cancel();
			
				Game.start();
				counterOn = false;
				
				}
				else 
				{
					this.cancel();
					ChatUtilities.broadcast("Not Enough Players!");
					Game.setStarted(false);
					
					counterOn = false;
					
				}
			}
				
				
				
				
				
			}
		}.runTaskTimer(plugin, 0L, 20L);
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
