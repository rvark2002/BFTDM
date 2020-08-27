package com.vark.BFTDM.listeners;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

public class CM 

{
    public static HashMap<UUID, Double> st; //Saber Throw
    
    public static HashMap<UUID, Double> cc; //Force Choke
    
    public static HashMap<UUID, Double> cr; //Force Repulse
    
    public static HashMap<UUID, Double> cp; //Force Push- Luke
    
    public static HashMap<UUID, Double> cdh; //Force Heal
    
    public static HashMap<UUID, Double> cs; //Force Sense
    
    public static HashMap<UUID, Double> cpu; //Force Pull
    
    public static HashMap<UUID, Double> cf; //Force Freeze
 
    public static HashMap<UUID, Double> cl; //Force Lightning
    
    public static HashMap<UUID, Double> cds; //Force Storm


    public static HashMap<UUID, Double> cddr; //Force Drain
    
    public static HashMap<UUID, Double> csa; //Sith Aura
    
    public static HashMap<UUID, Double> cdm; //Force Mindtrick
    
    
    public static HashMap<UUID, Double> ct; //Toxic
    
    public static HashMap<UUID, Double> cra; //Rampage
    
    public static HashMap<UUID, Double> sl; //SABER lUNGE
    
public static void setupCooldown()
{
    st = new HashMap<>();
    
    cc = new HashMap<>();
    cr = new HashMap<>();
    cp = new HashMap<>();
    cdh = new HashMap<>();
    cs = new HashMap<>();
    cpu = new HashMap<>();
    cf = new HashMap<>();
    cl = new HashMap<>();
    cds = new HashMap<>();
    cddr = new HashMap<>();
    csa = new HashMap<>();
    
    cdm = new HashMap<>();
    
    ct = new HashMap<>();
    
    cra = new HashMap<>();
    
    sl = new HashMap<>();
}



//setCooldown
public static void setCooldown(Player player, int seconds)
{
    double delay = System.currentTimeMillis() + (seconds*1000);
    st.put(player.getUniqueId(),delay);
    
}

public static void setCC(Player player, int seconds)
{
    double delay = System.currentTimeMillis() + (seconds*1000);
    cc.put(player.getUniqueId(),delay);
    
}


public static void setCR(Player player, int seconds)
{
    double delay = System.currentTimeMillis() + (seconds*1000);
    cr.put(player.getUniqueId(),delay);
    
}


public static void setCP(Player player, int seconds)
{
    double delay = System.currentTimeMillis() + (seconds*1000);
    cp.put(player.getUniqueId(),delay);
    
}


public static void setCdh(Player player, int seconds)
{
    double delay = System.currentTimeMillis() + (seconds*1000);
    cdh.put(player.getUniqueId(),delay);
    
}


public static void setCS(Player player, int seconds)
{
    double delay = System.currentTimeMillis() + (seconds*1000);
    cs.put(player.getUniqueId(),delay);
    
}


public static void setCPU(Player player, int seconds)
{
    double delay = System.currentTimeMillis() + (seconds*1000);
    cpu.put(player.getUniqueId(),delay);
    
}

public static void setCF(Player player, int seconds)
{
    double delay = System.currentTimeMillis() + (seconds*1000);
    cf.put(player.getUniqueId(),delay);
    
}


public static void setCL(Player player, int seconds)
{
    double delay = System.currentTimeMillis() + (seconds*1000);
    cl.put(player.getUniqueId(),delay);
    
}

public static void setCds(Player player, int seconds)
{
    double delay = System.currentTimeMillis() + (seconds*1000);
    cds.put(player.getUniqueId(),delay);
    
}

public static void setCddr(Player player, int seconds)
{
    double delay = System.currentTimeMillis() + (seconds*1000);
    cddr.put(player.getUniqueId(),delay);
    
}


public static void setCsa(Player player, int seconds)
{
    double delay = System.currentTimeMillis() + (seconds*1000);
    csa.put(player.getUniqueId(),delay);
    
}

public static void setCdm(Player player, int seconds)
{
    double delay = System.currentTimeMillis() + (seconds*1000);
    cdm.put(player.getUniqueId(),delay);
    
}


public static void setCra(Player player, int seconds)
{
    double delay = System.currentTimeMillis() + (seconds*1000);
    cra.put(player.getUniqueId(),delay);
    
}

public static void setCt(Player player, int seconds)
{
    double delay = System.currentTimeMillis() + (seconds*1000);
    ct.put(player.getUniqueId(),delay);
    
}


public static void setSL(Player player, int seconds)
{
    double delay = System.currentTimeMillis() + (seconds*1000);
    sl.put(player.getUniqueId(),delay);
    
}
//getCooldown

public static int getCooldown(Player player)
{
    return Math.toIntExact(Math.round((st.get(player.getUniqueId())- System.currentTimeMillis())/1000));
}

public static int getCC(Player player)
{
    return Math.toIntExact(Math.round((cc.get(player.getUniqueId())- System.currentTimeMillis())/1000));
}

public static int getCR(Player player)
{
    return Math.toIntExact(Math.round((cr.get(player.getUniqueId())- System.currentTimeMillis())/1000));
}

public static int getCP(Player player)
{
    return Math.toIntExact(Math.round((cp.get(player.getUniqueId())- System.currentTimeMillis())/1000));
}

public static int getCdh(Player player)
{
    return Math.toIntExact(Math.round((cdh.get(player.getUniqueId())- System.currentTimeMillis())/1000));
}

public static int getCS(Player player)
{
    return Math.toIntExact(Math.round((cs.get(player.getUniqueId())- System.currentTimeMillis())/1000));
}


public static int getCPU(Player player)
{
    return Math.toIntExact(Math.round((cpu.get(player.getUniqueId())- System.currentTimeMillis())/1000));
}

public static int getCF(Player player)
{
    return Math.toIntExact(Math.round((cf.get(player.getUniqueId())- System.currentTimeMillis())/1000));
}

public static int getCL(Player player)
{
    return Math.toIntExact(Math.round((cl.get(player.getUniqueId())- System.currentTimeMillis())/1000));
}

public static int getCds(Player player)
{
    return Math.toIntExact(Math.round((cds.get(player.getUniqueId())- System.currentTimeMillis())/1000));
}

public static int getCddr(Player player)
{
    return Math.toIntExact(Math.round((cddr.get(player.getUniqueId())- System.currentTimeMillis())/1000));
}

public static int getCsa(Player player)
{
    return Math.toIntExact(Math.round((csa.get(player.getUniqueId())- System.currentTimeMillis())/1000));
}

public static int getCdm(Player player)
{
    return Math.toIntExact(Math.round((cdm.get(player.getUniqueId())- System.currentTimeMillis())/1000));
}


public static int getCt(Player player)
{
    return Math.toIntExact(Math.round((ct.get(player.getUniqueId())- System.currentTimeMillis())/1000));
}

public static int getCra(Player player)
{
    return Math.toIntExact(Math.round((cra.get(player.getUniqueId())- System.currentTimeMillis())/1000));
}


public static int getSL(Player player)
{
    return Math.toIntExact(Math.round((sl.get(player.getUniqueId())- System.currentTimeMillis())/1000));
}
//check cooldown
public static boolean checkCooldown(Player player)
{
    if(!st.containsKey(player.getUniqueId()) || st.get(player.getUniqueId()) <= System.currentTimeMillis()){
        return true;
    }
    return false;
}

public static boolean checkCC(Player player)
{
    if(!cc.containsKey(player.getUniqueId()) || cc.get(player.getUniqueId()) <= System.currentTimeMillis()){
        return true;
    }
    return false;
}



public static boolean checkCR(Player player)
{
    if(!cr.containsKey(player.getUniqueId()) || cr.get(player.getUniqueId()) <= System.currentTimeMillis()){
        return true;
    }
    return false;
}

public static boolean checkCP(Player player)
{
    if(!cp.containsKey(player.getUniqueId()) || cp.get(player.getUniqueId()) <= System.currentTimeMillis()){
        return true;
    }
    return false;
}

public static boolean checkCdh(Player player)
{
    if(!cdh.containsKey(player.getUniqueId()) || cdh.get(player.getUniqueId()) <= System.currentTimeMillis()){
        return true;
    }
    return false;
}

public static boolean checkCS(Player player)
{
    if(!cs.containsKey(player.getUniqueId()) || cs.get(player.getUniqueId()) <= System.currentTimeMillis()){
        return true;
    }
    return false;
}


public static boolean checkCPU(Player player)
{
    if(!cpu.containsKey(player.getUniqueId()) || cpu.get(player.getUniqueId()) <= System.currentTimeMillis()){
        return true;
    }
    return false;
}

public static boolean checkCF(Player player)
{
    if(!cf.containsKey(player.getUniqueId()) || cf.get(player.getUniqueId()) <= System.currentTimeMillis()){
        return true;
    }
    return false;
}

public static boolean checkCL(Player player)
{
    if(!cl.containsKey(player.getUniqueId()) || cl.get(player.getUniqueId()) <= System.currentTimeMillis()){
        return true;
    }
    return false;
}


public static boolean checkCds(Player player)
{
    if(!cds.containsKey(player.getUniqueId()) || cds.get(player.getUniqueId()) <= System.currentTimeMillis()){
        return true;
    }
    return false;
}

public static boolean checkCddr(Player player)
{
    if(!cddr.containsKey(player.getUniqueId()) || cddr.get(player.getUniqueId()) <= System.currentTimeMillis()){
        return true;
    }
    return false;
}

public static boolean checkCsa(Player player)
{
    if(!csa.containsKey(player.getUniqueId()) || csa.get(player.getUniqueId()) <= System.currentTimeMillis()){
        return true;
    }
    return false;
}





public static boolean checkCdm(Player player)
{
    if(!cdm.containsKey(player.getUniqueId()) || cdm.get(player.getUniqueId()) <= System.currentTimeMillis()){
        return true;
    }
    return false;
}



public static boolean checkCt(Player player)
{
    if(!ct.containsKey(player.getUniqueId()) || ct.get(player.getUniqueId()) <= System.currentTimeMillis()){
        return true;
    }
    return false;
}





public static boolean checkCra(Player player)
{
    if(!cra.containsKey(player.getUniqueId()) || cra.get(player.getUniqueId()) <= System.currentTimeMillis()){
        return true;
    }
    return false;
}


public static boolean checkSL(Player player)
{
    if(!sl.containsKey(player.getUniqueId()) || sl.get(player.getUniqueId()) <= System.currentTimeMillis()){
        return true;
    }
    return false;
}








}
