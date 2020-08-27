package com.vark.BFTDM.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import com.vark.BFTDM.Main;
import com.vark.BFTDM.PlayerManager;
import com.vark.BFTDM.Team;



public class KitListener extends GameListener
{
	private Plugin plugin = Main.getPlugin(Main.class);
	private static Inventory invh;
	private static Inventory invv;

	public KitListener(Main pl) {
		super(pl);
		
		invh = Bukkit.createInventory(null,9,"KitSelH");
		invv = Bukkit.createInventory(null,9,"KitSelV");
		initializeItems();
		
		
	}

	


	


	
	

	
	
    public void initializeItems() {
    	

        invh.setItem(0,createGuiItem(101,Material.STICK, "§a§lLuke Skywalker"));
        invh.setItem(2,createGuiItem(103,Material.STICK, "§6§lRey"));   
        invh.setItem(4,createGuiItem(1,Material.MUSIC_DISC_CAT, "§d§lLeia"));
        invh.setItem(6,createGuiItem(105,Material.STICK, "§b§lObiwan"));
 

        invv.setItem(0,createGuiItem(102,Material.STICK, "§4§lDarth Vader"));
        invv.setItem(2,createGuiItem(104,Material.STICK, "§c§lKylo Ren"));   
        invv.setItem(4,createGuiItem(161,Material.MUSIC_DISC_WAIT, "§4§lPalpatine"));
        invv.setItem(6,createGuiItem(2,Material.MUSIC_DISC_MELLOHI, "§2§lBossk"));
        

    }
	
    public static Inventory getInvH()
    {
    	return invh;
    }
    public static Inventory getInvV()
    {
    	return invv;
    }
	
	

	
	
	

	
	
    protected ItemStack createGuiItem(int n,final Material material, final String name) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        if(n != 0)
        {
        	
        	meta.setCustomModelData(n);
        }
        // Set the name of the item
        meta.setDisplayName(name);

        // Set the lore of the item
       

        item.setItemMeta(meta);

        return item;
    }
	
	
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
   
        
       

    	if(PlayerManager.isInGame(e.getWhoClicked().getName()))
    	{
    		e.setCancelled(true);
    	}

        final ItemStack clickedItem = e.getCurrentItem();

        // verify current item is not null
        if (clickedItem == null || clickedItem.getType() == Material.AIR) return;

        Player p = (Player) e.getWhoClicked();

        // Using slots click is a best option for your inventory click's
       // p.sendMessage("You clicked at slot " + e.getRawSlot());
        
        
        if(e.getInventory() == invh || e.getInventory() == invv)
        {
        	
        	if(PlayerManager.isInGame(p.getName()))
        	{
        		if(Team.getTeam(p).equals(Team.getTeam("heroes")))
        		{
        			if(e.getRawSlot() == 0)
        			{
        				PlayerManager.assignClass(p, 0); //Luke
        				p.closeInventory();
        				
        			}
        			if(e.getRawSlot() == 2)
        			{
        				PlayerManager.assignClass(p, 1);//Rey
        				p.closeInventory();
        			}
        			if(e.getRawSlot() == 4)
        			{
        				PlayerManager.assignClass(p, 2);//Leia
        				p.closeInventory();
        			}
        			if(e.getRawSlot() == 6)
        			{
        				PlayerManager.assignClass(p, 3);//Kenobi
        				p.closeInventory();
        			}
        			
        		}
        	
        		
        		
        		
        		if(Team.getTeam(p).equals(Team.getTeam("villains")))
        		{
        			
        		
        			if(e.getRawSlot() == 0)
        			{
        				PlayerManager.assignClass(p, 0); //Vader
        				p.closeInventory();
        			}
        			if(e.getRawSlot() == 2)
        			{
        				
        				PlayerManager.assignClass(p, 1);//Kylo
        				p.closeInventory();
        			}
        			if(e.getRawSlot() == 4)
        			{
        				PlayerManager.assignClass(p, 2);//Palpatine
        				p.closeInventory();
        			}
        			if(e.getRawSlot() == 6)
        			{
        				PlayerManager.assignClass(p, 3);//Bossk
        				p.closeInventory();
        			}
        			
        		}
        	}
        	
        	
        	
        	
        	
        }

        
    }
    


	@EventHandler
    public void onInventoryClick(final InventoryDragEvent e) {
        if (e.getInventory() == getInvV() || e.getInventory() == getInvH()) {
          e.setCancelled(true);
        }
    }
	
		
	
	
	
	
	
	
	
	
	
	
	
	
	
			
	
	
	
	

	

	
	
	
	
	

	

	
	
		
		
		
	
	


	
	
	
	
	
	
	
	
	

	
	
	

		
		
		
}
	
	
	

