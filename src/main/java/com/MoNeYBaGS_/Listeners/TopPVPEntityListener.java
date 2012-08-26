package com.MoNeYBaGS_.Listeners;

import java.util.ArrayList;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import com.MoNeYBaGS_.TopPVP;
import com.MoNeYBaGS_.Configurations.Nodes;
import com.MoNeYBaGS_.Leaderboards.Leaderboards;
import com.MoNeYBaGS_.Leaderboards.trimLeaderboards;


public class TopPVPEntityListener implements Listener {

	private static TopPVP plugin;
	private Leaderboards leaderboards;
	private Player tempplayer;
	private ArrayList<String> tempkills = new ArrayList<String>();
	private ArrayList<String> tempkdr = new ArrayList<String>();
	private ArrayList<String> tempdeaths = new ArrayList<String>();
 
	public TopPVPEntityListener(TopPVP instance, Leaderboards leaderboards) {
		plugin = instance;
		this.leaderboards = leaderboards;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onEntityDamage(EntityDamageEvent event)
	{
		Entity entity = event.getEntity();
		if ((entity instanceof Player)) {
			if ((event instanceof EntityDamageByEntityEvent)) {
				EntityDamageByEntityEvent sub = (EntityDamageByEntityEvent)event;
				Entity attacker = sub.getDamager();
				if ((attacker instanceof Player)) {
					tempplayer = (Player)attacker;
					
					Map<String, Integer> tree_kills = leaderboards.getKillsLeaderboards();
					trimLeaderboards trim_kills = new trimLeaderboards();
					tempkills = trim_kills.getTrimCheck(tree_kills.toString());
					
					Map<String, Double> tree_kdr = leaderboards.getKDRLeaderboards();
					trimLeaderboards trim_kdr = new trimLeaderboards();
					tempkdr = trim_kdr.getTrimCheck(tree_kdr.toString());
					
					Map<String, Integer> tree_deaths = leaderboards.getDeathsLeaderboards();
					trimLeaderboards trim_deaths = new trimLeaderboards();
					tempdeaths = trim_deaths.getTrimCheck(tree_deaths.toString());
				}
			}
		}
		else
		{
			tempplayer = null;
		}
	}

	@EventHandler
	public void onEntityDeath(EntityDeathEvent event)
	{
		Entity entity = event.getEntity();
		plugin.reloadConfig();
		if(entity instanceof Player)
		{
			Player victim = (Player) entity;
			if(victim.getKiller() instanceof Player)
			{
				plugin.log.info(plugin.pvp + "Player Killed: " + victim.getName() );
				plugin.reloadPlayersConfig();
				plugin.getPlayersConfig().set("players." + victim.getName() + ".Deaths", 
						plugin.getPlayersConfig().getInt("players." + victim.getName() + ".Deaths", 0) + 1);
				plugin.savePlayersConfig();

				int deaths = plugin.getPlayersConfig().getInt("players." + victim.getName()
						+ ".Deaths", 1);
				if(deaths == 1)
				{
					victim.sendMessage(ChatColor.GREEN + Nodes.Paths.DeathsReturnOnce.getString());
				}
				else
					victim.sendMessage(ChatColor.GREEN + Nodes.Paths.DeathsReturn1.getString() + deaths
							+ Nodes.Paths.DeathsReturn2.getString());
				Player actualplayer = ((Player) entity).getKiller();
				if(actualplayer == tempplayer)
				{
					if(!(actualplayer == null))
					{
						plugin.reloadPlayersConfig();
						plugin.getPlayersConfig().set("players." + actualplayer.getName() + ".Kills", 
								plugin.getPlayersConfig().getInt("players." + actualplayer.getName() + ".Kills", 0) + 1);

						plugin.savePlayersConfig();

						int kills = (plugin.getPlayersConfig().getInt("players." + actualplayer.getName()
								+ ".Kills"));
						if(kills == 1)
						{
							actualplayer.sendMessage(ChatColor.RED + Nodes.Paths.KillsReturnOnce.getString());
						}
						else
							actualplayer.sendMessage(ChatColor.RED + Nodes.Paths.KillsReturn1.getString() + kills + 
									Nodes.Paths.KillsReturn2.getString());	
						actualplayer = null;
						
						Map<String, Integer> tree_kills = leaderboards.getKillsLeaderboards();
						trimLeaderboards trim_kills = new trimLeaderboards();
						ArrayList<String> temp_kills = trim_kills.getTrimCheck(tree_kills.toString());
						if(!(temp_kills.get(1).equals(tempkills.get(1))))
						{
							plugin.getServer().broadcastMessage(ChatColor.GOLD + temp_kills.get(0) + " is the new kills leader!");
							tempkills = temp_kills;
						}
						
						Map<String, Double> tree_kdr = leaderboards.getKDRLeaderboards();
						trimLeaderboards trim_kdr = new trimLeaderboards();
						ArrayList<String> temp_kdr = trim_kdr.getTrimCheck(tree_kdr.toString());
						if(!(temp_kdr.get(1).equals(tempkdr.get(1))))
						{
							plugin.getServer().broadcastMessage(ChatColor.GOLD + temp_kdr.get(0) + " is the new kdr leader!");
							tempkdr = temp_kdr;
						}
						
						Map<String, Integer> tree_deaths = leaderboards.getDeathsLeaderboards();
						trimLeaderboards trim_deaths = new trimLeaderboards();
						ArrayList<String> temp_deaths = trim_deaths.getTrimCheck(tree_deaths.toString());
						if(!(temp_deaths.get(1).equals(tempdeaths.get(1))))
						{
							plugin.getServer().broadcastMessage(ChatColor.GOLD + temp_deaths.get(0) + " is the new deaths leader!");
							tempdeaths = temp_deaths;
						}
					}
				}
			}
		}
	}


}