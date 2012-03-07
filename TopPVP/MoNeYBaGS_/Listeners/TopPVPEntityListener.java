package MoNeYBaGS_.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import MoNeYBaGS_.TopPVP.TopPVP;

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
		if(entity instanceof Player)
		{
			Player victim = (Player) entity;
			plugin.log.info(plugin.pvp + "Player died: " + victim.getName() );
			
			plugin.getConfig().set("players." + victim.getName() + ".Deaths", 
					plugin.getConfig().getInt("players." + victim.getName() + ".Deaths", 0) + 1);
			plugin.saveConfig();
			plugin.reloadConfig();
			
			victim.sendMessage(ChatColor.GREEN + "You have died " + 
					plugin.getConfig().getInt("players." + victim.getName()
					+ ".Deaths", 1) + " times in total.");
			Player actualplayer = ((Player) entity).getKiller();
			if(actualplayer == tempplayer)
			{
				if(!(actualplayer == null))
				{
					plugin.getConfig().set("players." + actualplayer.getName() + ".Kills", 
							plugin.getConfig().getInt("players." + actualplayer.getName() + ".Kills", 0) + 1);
					
					plugin.saveConfig();
					plugin.reloadConfig();
					
					actualplayer.sendMessage(ChatColor.RED + "You have killed " + (plugin.getConfig().getInt("players." + actualplayer.getName()
							+ ".Kills")) + " different times in total.");	
					actualplayer = null;
				}
			}
		}
	}


}