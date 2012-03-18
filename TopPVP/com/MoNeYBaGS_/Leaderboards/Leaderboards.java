package com.MoNeYBaGS_.Leaderboards;

import java.util.Map;

import com.MoNeYBaGS_.TopPVP;


public class Leaderboards {
	
	private final TopPVP plugin;
	private static Players lead = null;
	
	public Leaderboards(TopPVP instance)
	{
		this.plugin = instance;
		lead = new Players(plugin);
	}
	
	public static Map<String, Integer> getLeaderboards()
	{
		return lead.getKillsLeaderboard();
	}
}
