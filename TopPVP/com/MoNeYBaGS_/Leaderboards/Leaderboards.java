package com.MoNeYBaGS_.Leaderboards;

import java.util.Map;

import com.MoNeYBaGS_.TopPVP;


public class Leaderboards {
	
	private final TopPVP plugin;
	private final Players lead;
	
	public Leaderboards(TopPVP instance)
	{
		this.plugin = instance;
		lead = new Players(plugin);
	}
	
	public Map<String, Integer> getLeaderboards()
	{
		return lead.getLeaderboard();
	}
}
