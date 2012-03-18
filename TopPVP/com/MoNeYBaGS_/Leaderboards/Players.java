package com.MoNeYBaGS_.Leaderboards;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.bukkit.entity.Player;

import com.MoNeYBaGS_.TopPVP;


public class Players {

	private ArrayList<String> playernames;
	private Player player;
	private int kills;
	private final TopPVP plugin;
	private Map<String, Integer> leaderboard;

	public Players(TopPVP instance) {
		this.plugin = instance;
		this.refreshLeaderboards();
	}

	private void refreshLeaderboards()
	{
		try {
			BufferedReader bin = new BufferedReader(new FileReader("plugins/TopPVP/players.conf"));
			String all = bin.readLine();
			playernames = returnPlayerArray(all);
			leaderboard = createLeaderboards(playernames);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Map<String, Integer> createLeaderboards(ArrayList<String> player)
	{
		Map<String, Integer> unsorted = new HashMap<String, Integer>();
		for(int i = 0; i < player.size(); i++)
		{
			unsorted.put(player.get(i), plugin.getPlayersConfig().getInt("players." + player.get(i).toString() + ".Kills"));
		}
		KillsComparator compare = new KillsComparator(unsorted);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		TreeMap<String, Integer> sorted = new TreeMap(compare);
		sorted.putAll(unsorted);
		return sorted;
	}

	private ArrayList<String> returnPlayerArray(String playerlist)
	{
		ArrayList<String> players = new ArrayList<String>();
		ArrayList<String> default_players = new ArrayList<String>();
		default_players.add("No players have killed");
		String temp = "";

		if(playerlist != null)
		{
			for(int i = 0; i < playerlist.length(); i++)
			{
				if(playerlist.charAt(i) == ';')
				{
					players.add(temp);
					temp = "";
				}
				else
				{
					temp += playerlist.charAt(i);
				}
			}
			return players;
		}
		return default_players;
	}

	public Player getPlayer()
	{
		return player;
	}

	public int getKills()
	{
		return kills;
	}

	public void setPlayer(Player _player)
	{
		this.player = _player;
	}

	public void setKills(int _kills)
	{
		this.kills = _kills;
	}

	public ArrayList<String> getPlayerNames()
	{
		return playernames;
	}

	public Map<String, Integer> getKillsLeaderboard()
	{
		this.refreshLeaderboards();
		return leaderboard;
	}
}
