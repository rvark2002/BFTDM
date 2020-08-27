package com.vark.BFTDM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Player;

public class PlayerManager 
{

	private static List<String> playersInGame;
	
	private static HashMap<String,Integer> classSel;
	
	
	
	
	
	public static void initalizePlayerManager()
	{
		playersInGame = new ArrayList<String>();
		classSel = new HashMap<String,Integer>();
		
		
		
		
	}
	
	
	public static void playerJoined(Player p)
	{
		playersInGame.add(p.getName());
		classSel.put(p.getName(),0);
	}
	
	public static void playerLeft(Player p)
	{
		if(playersInGame.contains(p.getName()))
			playersInGame.remove(playersInGame.indexOf(p.getName()));
		
		if(classSel.containsKey(p.getName()))
			classSel.remove(p.getName());
	}
	
	public static List<String> getPlayersInGame()
	{
		return playersInGame;
	}
	
	
	public static boolean isInGame(String s)
	{
		for(String p : playersInGame)
		{
			if(p.equals(s))
			{
				return true;
			}
		}
		return false;
	}
	
	public static void clearDatabase()
	{
		playersInGame.clear();
		classSel.clear();
	}
	
	
	public static void assignClass(Player p, int n)
	{
		if(classSel.containsKey(p.getName()))
			classSel.replace(p.getName(), n);
	}
	
	public static Integer getClass(Player p)
	{
		if(classSel.containsKey(p.getName()))
			return classSel.get(p.getName());
		
		else
			return 0;
	}
	
	
	
	
}
