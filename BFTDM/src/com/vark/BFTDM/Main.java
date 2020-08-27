package com.vark.BFTDM;
import java.io.File;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.vark.BFTDM.listeners.BlasterListener;
import com.vark.BFTDM.listeners.CM;
import com.vark.BFTDM.listeners.RM;
import com.vark.BFTDM.listeners.KillListener;
import com.vark.BFTDM.listeners.KitListener;
import com.vark.BFTDM.listeners.PlayerJoin;
import com.vark.BFTDM.listeners.PlayerQuit;
import com.vark.BFTDM.listeners.PowerManager;


public class Main extends JavaPlugin
{

	
	public void onEnable()
	{
		CM.setupCooldown();
		
		RM.setupCooldown();
		
		
		PlayerManager.initalizePlayerManager();		
		registerListeners();
		
		getCommand("bftdm").setExecutor(new LocationManager());
		
		File userFolder = new File(this.getDataFolder(), "data");
		File data = new File(this.getDataFolder(), "locations");
		userFolder.mkdirs();
		data.mkdir();
		
	}
	
	
	public void onDisable() {}
	


	
	
	public void registerListeners()
	{
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerJoin(this),this);
		pm.registerEvents(new PlayerQuit(this),this);
		pm.registerEvents(new KillListener(this),this);
		pm.registerEvents(new KitListener(this),this);
		pm.registerEvents(new PowerManager(this),this);
		pm.registerEvents(new BlasterListener(this),this);
	}
	
}
//Create Hashmap of players in game