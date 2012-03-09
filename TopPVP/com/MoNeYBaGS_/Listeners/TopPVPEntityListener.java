package com.MoNeYBaGS_.Listeners;

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


public class TopPVPEntityListener implements Listener {
	
	private static TopPVP plugin;
	private Player tempplayer;

	public TopPVPEntityListener(TopPVP instance) {
		plugin = instance;
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
			plugin.log.info(plugin.pvp + "Player died: " + victim.getName() );
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
				}
			}
		}
	}


}