package com.MoNeYBaGS_.Leaderboards;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;

import com.MoNeYBaGS_.Database;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.bukkit.entity.Player;

import com.MoNeYBaGS_.TopPVP;


public class Players {

	private ArrayList<String> playernames;
	private Player player;
	private int kills;
	private final TopPVP plugin;
	private Map<String, Integer> killslead;
	private Map<String, Double> kdrlead;
	private Map<String, Integer> deathslead;

	public Players(TopPVP instance) {
		this.plugin = instance;
		this.refreshLeaderboards();
	}

	private void refreshLeaderboards()
	{
		/*try {
			BufferedReader bin = new BufferedReader(new FileReader("plugins/TopPVP/players.conf"));
			String all = bin.readLine();
			playernames = returnPlayerArray(all);
			killslead = createKillsLeaderboards(playernames);
			kdrlead = createKDRLeaderboards(playernames);
			deathslead = createDeathsLeaderboards(playernames);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  */

		plugin.config.reloadPlayersConfig();
		List mapList = plugin.config.databaseCache;

		playernames = new ArrayList<String>();
		Map<String, Integer> kills = new HashMap<String, Integer>();
		Map<String, Integer> deaths = new HashMap<String, Integer>();
		Map<String, Double> kdr = new HashMap<String, Double>();
		for (int i = 0; i < mapList.size(); i++) {
			Map map = (Map) mapList.get(i);
			String username = (String)map.get("username");
			Integer killsInt = (Integer)map.get("kills");
			Integer deathsInt = (Integer)map.get("deaths");
			double ratio = 0;
			if (deathsInt == 0)
				ratio = killsInt;
			else if (killsInt == 0)
				ratio = 0;
			else
				ratio = Math.round(((killsInt) / (deathsInt) * 100.0D)) / 100.0D;

			playernames.add((String)map.get("username"));
			kills.put(username, killsInt);
			deaths.put(username, deathsInt);
			kdr.put(username, ratio);
		}

		deathslead = createDeathsLeaderboards(playernames, deaths);
		killslead = createKillsLeaderboards(playernames, kills);
		kdrlead = createKDRLeaderboards(playernames, kdr);
	}
	
	private Map<String, Integer> createDeathsLeaderboards(ArrayList<String> player, Map<String, Integer> unsorted)
	{
		KillsComparator compare = new KillsComparator(unsorted);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		TreeMap<String, Integer> sorted = new TreeMap(compare);
		sorted.putAll(unsorted);
		return sorted;
	}

	private Map<String, Integer> createKillsLeaderboards(ArrayList<String> player, Map<String, Integer> unsorted)
	{
		KillsComparator compare = new KillsComparator(unsorted);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		TreeMap<String, Integer> sorted = new TreeMap(compare);
		sorted.putAll(unsorted);
		return sorted;
	}

	private Map<String, Double> createKDRLeaderboards(ArrayList<String> player, Map<String, Double> unsorted)
	{
		KDRComparator compareDouble = new KDRComparator(unsorted);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		TreeMap<String, Double> sorted = new TreeMap(compareDouble);
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
		return killslead;
	}

	public Map<String, Double> getKDRLeaderboards()
	{
		this.refreshLeaderboards();
		return kdrlead;
	}
	
	public Map<String, Integer> getDeathsLeaderboards()
	{
		this.refreshLeaderboards();
		return deathslead;
	}
}
