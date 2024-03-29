package com.vark.BFTDM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.entity.Player;

public class Team {
	
	private int lives;
	

	
	private static List<Team> allTeams = new ArrayList<Team>();
	
	
	
	private static HashMap<String, Team> playerTeams = new HashMap<String, Team>();
	
	private String teamName;
	
	public Team(String teamName) {
		this.teamName = teamName.trim();
		allTeams.add(this);
		lives = 2*PlayerManager.getPlayersInGame().size();
	}
	
	public String getName()
	{
		return teamName;
	}
	
	public void add(Player player)
	{
		playerTeams.put(player.getName(), this);
		
	}
	public void add(String player)
	{
		playerTeams.put(player, this);
		
	}
	
	
	public static boolean remove(Player player)
	{
		if(!hasTeam(player))
			return false;
		
			playerTeams.remove(player.getName());
			return true;
		
	}
	
	public static boolean hasTeam(Player player)
	{
		return playerTeams.containsKey(player.getName());
	}
	
	public static Team getTeam(Player player)
	{
		if(!hasTeam(player))
			return null;
		
		return playerTeams.get(player.getName());
	}
	
	public static List<Team> getAllTeams()
	{
		return allTeams;
	}
	
	public static Team getTeam(String name)
	{
		for(Team t: allTeams)
		{
			if(t.teamName.equalsIgnoreCase(name))
			{
				return t;
			}
			
		}
		return null;
	}
	
	
	
	public void addLives(int n)
	{
		lives+=n;
	}
	
	public int getLives()
	{
		return lives;
	}
	
	public static void clearTeams()
	{
		playerTeams.clear();
		allTeams.clear();
	}
	
	public int getPlayerCount()
	{
		int count = 0;
		for (Entry<String, Team> set : playerTeams.entrySet()) {
			if(set.getValue() == this)
			{
				count++;
			}
		}
		return count;
	}
}
