package com.vark.BFTDM.kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.vark.BFTDM.PlayerManager;
import com.vark.BFTDM.Team;

import net.md_5.bungee.api.ChatColor;

public class KitManager {
	
	
	
	
	
	
	
	
	
	public static void giveKit(Player p)
	{
		
		if(Team.getTeam(p).getName().equals("heroes"))
		{
			if(PlayerManager.getClass(p) == 0)
				luke(p);
			if(PlayerManager.getClass(p) == 1)
				rey(p);
			if(PlayerManager.getClass(p) == 2)
				leia(p);
			if(PlayerManager.getClass(p) == 3)
				kenobi(p);
			
		}
		
		if(Team.getTeam(p).getName().equals("villains"))
		{
			
			if(PlayerManager.getClass(p) == 0)
				vader(p);
			if(PlayerManager.getClass(p) == 1)
				kylo(p);
			if(PlayerManager.getClass(p) == 2)
				palpatine(p);
			if(PlayerManager.getClass(p) == 3)
				bossk(p);
		}
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Heros=====================================
	
	//Luke
	
	public static void luke(Player p)
	{
		
		ItemStack saber = new ItemStack(Material.STICK);
		ItemMeta sabermeta = saber.getItemMeta();
		sabermeta.setCustomModelData(101);
		sabermeta.setDisplayName(ChatColor.GREEN+""+ChatColor.BOLD+"Lightsaber");
		saber.setItemMeta(sabermeta);
		
		p.getInventory().setItem(0, saber);
		
		ItemStack force = new ItemStack(Material.MUSIC_DISC_WAIT);
		ItemMeta forcemeta = force.getItemMeta();
		forcemeta.setCustomModelData(181);
		forcemeta.setDisplayName(ChatColor.LIGHT_PURPLE+""+ChatColor.BOLD+"Force Repulse");
		force.setItemMeta(forcemeta);	
		p.getInventory().setItem(1, force);
		
		ItemStack force1 = new ItemStack(Material.MUSIC_DISC_WAIT);
		ItemMeta force1meta = force1.getItemMeta();
		force1meta.setCustomModelData(111);
		force1meta.setDisplayName(ChatColor.AQUA+""+ChatColor.BOLD+"Force Push");
		force1.setItemMeta(force1meta);	
		p.getInventory().setItem(2, force1);
		
		
		
		
		
		
		
		
	}
	
	
	//Rey
	
	public static void rey(Player p)
	{
		
		ItemStack saber = new ItemStack(Material.STICK);
		ItemMeta sabermeta = saber.getItemMeta();
		sabermeta.setCustomModelData(103);
		sabermeta.setDisplayName(ChatColor.GOLD+""+ChatColor.BOLD+"Lightsaber");
		saber.setItemMeta(sabermeta);
		
		p.getInventory().setItem(0, saber);
		
		ItemStack force = new ItemStack(Material.MUSIC_DISC_WAIT);
		ItemMeta forcemeta = force.getItemMeta();
		forcemeta.setCustomModelData(141);
		forcemeta.setDisplayName(ChatColor.YELLOW+""+ChatColor.BOLD+"Force Heal");
		force.setItemMeta(forcemeta);	
		p.getInventory().setItem(1, force);
		
		ItemStack force1 = new ItemStack(Material.MUSIC_DISC_WAIT);
		ItemMeta force1meta = force1.getItemMeta();
		force1meta.setCustomModelData(191);
		force1meta.setDisplayName(ChatColor.DARK_AQUA+""+ChatColor.BOLD+"Force Sense");
		force1.setItemMeta(force1meta);	
		p.getInventory().setItem(2, force1);
		
		
		
		
		
	}
	
	
	//Leia
	
	public static void leia(Player p)
	{
		
		ItemStack saber = new ItemStack(Material.MUSIC_DISC_CAT);
		ItemMeta sabermeta = saber.getItemMeta();
		
		sabermeta.setDisplayName(ChatColor.YELLOW+""+ChatColor.BOLD+"E-11");
		saber.setItemMeta(sabermeta);
		
		p.getInventory().setItem(0, saber);
		
		ItemStack force = new ItemStack(Material.MUSIC_DISC_11);
		ItemMeta forcemeta = force.getItemMeta();
		
		forcemeta.setDisplayName(ChatColor.YELLOW+""+ChatColor.BOLD+"DH-17");
		force.setItemMeta(forcemeta);	
		p.getInventory().setItem(1, force);
		
		ItemStack force1 = new ItemStack(Material.MUSIC_DISC_WAIT);
		force1.setAmount(10);
		ItemMeta force1meta = force1.getItemMeta();
		force1meta.setCustomModelData(241);
		force1meta.setDisplayName(ChatColor.RED+""+ChatColor.BOLD+"Health Stim");
		force1.setItemMeta(force1meta);	
		p.getInventory().setItem(2, force1);
		
		

		ItemStack force2 = new ItemStack(Material.TNT);
		force2.setAmount(8);
		ItemMeta force2meta = force2.getItemMeta();
		force2meta.setDisplayName(ChatColor.GOLD+""+ChatColor.BOLD+"Stun Bomb");
		force2.setItemMeta(force2meta);	
		p.getInventory().setItem(3, force2);
		
	}
	
	
	//Kenobi
	
	public static void kenobi(Player p)
	{
		
		ItemStack saber = new ItemStack(Material.STICK);
		ItemMeta sabermeta = saber.getItemMeta();
		sabermeta.setCustomModelData(105);
		sabermeta.setDisplayName(ChatColor.BLUE+""+ChatColor.BOLD+"Lightsaber");
		saber.setItemMeta(sabermeta);
		
		p.getInventory().setItem(0, saber);
		
		ItemStack force = new ItemStack(Material.MUSIC_DISC_WAIT);
		ItemMeta forcemeta = force.getItemMeta();
		forcemeta.setCustomModelData(151);
		forcemeta.setDisplayName(ChatColor.DARK_PURPLE+""+ChatColor.BOLD+"Force Mind Trick");
		force.setItemMeta(forcemeta);	
		p.getInventory().setItem(1, force);
		
		

		
	}
	
	
	
	
	
	//Villains=====================================
	
	
	
	//Vader
	
	public static void vader(Player p)
	{
		
		ItemStack saber = new ItemStack(Material.STICK);
		ItemMeta sabermeta = saber.getItemMeta();
		sabermeta.setCustomModelData(102);
		sabermeta.setDisplayName(ChatColor.RED+""+ChatColor.BOLD+"Lightsaber");
		saber.setItemMeta(sabermeta);
		
		p.getInventory().setItem(0, saber);
		
		
		ItemStack choke = new ItemStack(Material.MUSIC_DISC_WAIT);
		ItemMeta chokemeta = choke.getItemMeta();
		chokemeta.setCustomModelData(201);
		chokemeta.setDisplayName(ChatColor.DARK_RED+""+ChatColor.BOLD+"Force Throw");
		choke.setItemMeta(chokemeta);	
		p.getInventory().setItem(1, choke);
		
		
		
		
		
		
		
	}
	
	//Kylo
	public static void kylo(Player p)
	{
		
		ItemStack saber = new ItemStack(Material.STICK);
		ItemMeta sabermeta = saber.getItemMeta();
		sabermeta.setCustomModelData(104);
		sabermeta.setDisplayName(ChatColor.RED+""+ChatColor.BOLD+"Lightsaber");
		saber.setItemMeta(sabermeta);
		
		p.getInventory().setItem(0, saber);
		
		
		ItemStack force = new ItemStack(Material.MUSIC_DISC_WAIT);
		ItemMeta forcemeta = force.getItemMeta();
		forcemeta.setCustomModelData(171);
		forcemeta.setDisplayName(ChatColor.DARK_BLUE+""+ChatColor.BOLD+"Force Freeze");
		force.setItemMeta(forcemeta);	
		p.getInventory().setItem(1, force);
		
		ItemStack force1 = new ItemStack(Material.MUSIC_DISC_WAIT);
		ItemMeta force1meta = force1.getItemMeta();
		force1meta.setCustomModelData(221);
		force1meta.setDisplayName(ChatColor.RED+""+ChatColor.BOLD+"Force Pull");
		force1.setItemMeta(force1meta);	
		p.getInventory().setItem(2, force1);
		
		
		
		
	}
	
	
	
	//Unknown1
	
	public static void palpatine(Player p)
	{
		
		

		ItemStack force = new ItemStack(Material.MUSIC_DISC_WAIT);
		ItemMeta forcemeta = force.getItemMeta();
		forcemeta.setCustomModelData(161);
		forcemeta.setDisplayName(ChatColor.DARK_RED+""+ChatColor.BOLD+"Force Lightning");
		force.setItemMeta(forcemeta);	
		p.getInventory().setItem(0, force);
		
		ItemStack force1 = new ItemStack(Material.MUSIC_DISC_WAIT);
		ItemMeta force1meta = force1.getItemMeta();
		force1meta.setCustomModelData(131);
		force1meta.setDisplayName(ChatColor.RED+""+ChatColor.BOLD+"Force Drain");
		force1.setItemMeta(force1meta);	
		p.getInventory().setItem(1, force1);
		
		ItemStack force2 = new ItemStack(Material.MUSIC_DISC_WAIT);
		ItemMeta force2meta = force2.getItemMeta();
		force2meta.setCustomModelData(211);
		force2meta.setDisplayName(ChatColor.DARK_RED+""+ChatColor.BOLD+"Sith Aura");
		force2.setItemMeta(force2meta);	
		p.getInventory().setItem(2, force2);
		

		
	}
	
	
	//Unknown2
	
	public static void bossk(Player p)
	{
		
		ItemStack saber = new ItemStack(Material.IRON_SWORD);

		
		p.getInventory().setItem(0, saber);
		
		ItemStack force1 = new ItemStack(Material.MUSIC_DISC_MELLOHI);
		ItemMeta force1meta = force1.getItemMeta();
		force1meta.setDisplayName(ChatColor.YELLOW+""+ChatColor.BOLD+"DLT-19X");
		force1.setItemMeta(force1meta);	
		p.getInventory().setItem(1, force1);
		
		ItemStack force2 = new ItemStack(Material.MUSIC_DISC_WAIT);
		ItemMeta force2meta = force2.getItemMeta();
		force2meta.setCustomModelData(231);
		force2meta.setDisplayName(ChatColor.DARK_GREEN+""+ChatColor.BOLD+"Toxic Gas");
		force2.setItemMeta(force2meta);	
		p.getInventory().setItem(2, force2);
		

		ItemStack force3 = new ItemStack(Material.MUSIC_DISC_WAIT);
		ItemMeta force3meta = force3.getItemMeta();
		force3meta.setCustomModelData(251);
		force3meta.setDisplayName(ChatColor.DARK_RED+""+ChatColor.BOLD+"Primal Rampage");
		force3.setItemMeta(force3meta);	
		p.getInventory().setItem(3, force3);
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
