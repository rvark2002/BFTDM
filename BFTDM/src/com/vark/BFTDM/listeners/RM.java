
package com.vark.BFTDM.listeners;

import java.util.HashMap;
import java.util.UUID;
import org.bukkit.entity.Player;

/**
 *
 * @Coding Legend Ritvik Varkhedkar
 */
public class RM 

{
        public static HashMap<UUID, Double> reload; //Reload
        public static HashMap<UUID, Double> ammo; //Ammo
        
        public static HashMap<UUID, Double> ammodh; //Ammo
        public static HashMap<UUID, Double> reloaddh; //Reload

        public static HashMap<UUID, Double> ammodh2; //Ammo
        public static HashMap<UUID, Double> reloaddh2; //Reload
        
        public static HashMap<UUID, Double> ammos; //Ammo
        public static HashMap<UUID, Double> reloads; //Reload
    
    
    public static void setupCooldown()
    {
        ammo = new HashMap<>();
        reload = new HashMap<>();
        ammodh = new HashMap<>();
        reloaddh = new HashMap<>();
        ammodh2 = new HashMap<>();
        reloaddh2 = new HashMap<>();
        
        ammos = new HashMap<>();
        reloads = new HashMap<>();
 
    }
    
    
    
    //setCooldown
    public static void setCooldown(Player player, int seconds)
    {
        double delay = System.currentTimeMillis() + (seconds*1000);
        reload.put(player.getUniqueId(),delay);
    }
    
    public static void setAmmo(Player player, double amm)
    {
        if(ammo.containsKey(player.getUniqueId()))
        {
        	ammo.remove(player.getUniqueId());
        }
        ammo.put(player.getUniqueId(),amm);
    }
    
    public static void setCooldowndh(Player player, int seconds)
    {
        double delay = System.currentTimeMillis() + (seconds*1000);
        reloaddh.put(player.getUniqueId(),delay);
    }
    
    public static void setAmmodh(Player player, double amm)
    {
        if(ammodh.containsKey(player.getUniqueId()))
        {
        	ammodh.remove(player.getUniqueId());
        }
        ammodh.put(player.getUniqueId(),amm);
    }
    
    public static void setCooldowndh2(Player player, int seconds)
    {
        double delay = System.currentTimeMillis() + (seconds*1000);
        reloaddh2.put(player.getUniqueId(),delay);
    }
    
    public static void setAmmodh2(Player player, double amm)
    {
        if(ammodh2.containsKey(player.getUniqueId()))
        {
        	ammodh2.remove(player.getUniqueId());
        }
        ammodh2.put(player.getUniqueId(),amm);
    }
    
    
    public static void setCooldowns(Player player, int seconds)
    {
        double delay = System.currentTimeMillis() + (seconds*1000);
        reloads.put(player.getUniqueId(),delay);
    }
    
    public static void setAmmos(Player player, double amm)
    {
        if(ammos.containsKey(player.getUniqueId()))
        {
        	ammos.remove(player.getUniqueId());
        }
        ammos.put(player.getUniqueId(),amm);
    }
    
    
    //getCooldown
    
    public static int getCooldown(Player player)
    {
        return Math.toIntExact(Math.round((reload.get(player.getUniqueId())- System.currentTimeMillis())/1000));
    }
   
    public static Double getAmmo(Player player)
    {
        return ammo.get(player.getUniqueId());
    }

    public static int getCooldowndh(Player player)
    {
        return Math.toIntExact(Math.round((reloaddh.get(player.getUniqueId())- System.currentTimeMillis())/1000));
    }
   
    public static Double getAmmodh(Player player)
    {
        return ammodh.get(player.getUniqueId());
    }
    
    
    public static int getCooldowndh2(Player player)
    {
        return Math.toIntExact(Math.round((reloaddh2.get(player.getUniqueId())- System.currentTimeMillis())/1000));
    }
   
    public static Double getAmmodh2(Player player)
    {
        return ammodh2.get(player.getUniqueId());
    }
    
    
    public static int getCooldowns(Player player)
    {
        return Math.toIntExact(Math.round((reloads.get(player.getUniqueId())- System.currentTimeMillis())/1000));
    }
   
    public static Double getAmmos(Player player)
    {
        return ammos.get(player.getUniqueId());
    }
    //check cooldown
    public static boolean checkCooldown(Player player)
    {
        if(!reload.containsKey(player.getUniqueId()) || reload.get(player.getUniqueId()) <= System.currentTimeMillis()){
            return true;
        }
        return false;
    }
    
   
    
    
    
    //check cooldown
    public static boolean checkAmmo(Player player)
    {
        if(ammo.containsKey(player.getUniqueId())){
        	if(ammo.get(player.getUniqueId()) > 0)
            return true;
        }
        return false;
    }  
    

    
    //check cooldown
    public static boolean checkCooldowndh(Player player)
    {
        if(!reloaddh.containsKey(player.getUniqueId()) || reloaddh.get(player.getUniqueId()) <= System.currentTimeMillis()){
            return true;
        }
        return false;
    }
    
   
    
    //check cooldown
    public static boolean checkAmmodh(Player player)
    {
        if(ammodh.containsKey(player.getUniqueId())){
        	if(ammodh.get(player.getUniqueId()) > 0)
            return true;
        }
        return false;
    }  
    
    
    //check cooldown
    public static boolean checkCooldowndh2(Player player)
    {
        if(!reloaddh2.containsKey(player.getUniqueId()) || reloaddh2.get(player.getUniqueId()) <= System.currentTimeMillis()){
            return true;
        }
        return false;
    }
    
   
    
    //check cooldown
    public static boolean checkAmmodh2(Player player)
    {
        if(ammodh2.containsKey(player.getUniqueId())){
        	if(ammodh2.get(player.getUniqueId()) > 0)
            return true;
        }
        return false;
    }  
    
    
    //check cooldown
    public static boolean checkCooldowns(Player player)
    {
        if(!reloads.containsKey(player.getUniqueId()) || reloads.get(player.getUniqueId()) <= System.currentTimeMillis()){
            return true;
        }
        return false;
    }
    
   
    
    //check cooldown
    public static boolean checkAmmos(Player player)
    {
        if(ammos.containsKey(player.getUniqueId())){
        	if(ammos.get(player.getUniqueId()) > 0)
            return true;
        }
        return false;
    }  

}
