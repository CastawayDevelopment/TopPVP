package MoNeYBaGS_.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import MoNeYBaGS_.TopPVP.TopPVP;

public class TopPVPPlayerListener implements Listener {
	
	public static TopPVP plugin;
	public Player player;
	
	public TopPVPPlayerListener(TopPVP instance) {
		plugin = instance;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		plugin.reloadConfig();
		player = event.getPlayer();
		String playername = plugin.getConfig().getString("players." + player.getName());
		if(playername == null || playername == "")
		{
			//create player
			plugin.log.info(plugin.pvp + plugin.cre + player.getName());
			plugin.getConfig().set("players." + player.getName() + ".Kills", 0);
			plugin.getConfig().set("players." + player.getName() + ".Deaths", 0);
			plugin.getConfig().set("number." + (plugin.getConfig().getInt("number.players")+1), 1);
			plugin.saveConfig();
			plugin.reloadConfig();
		}
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		plugin.saveConfig();
		plugin.reloadConfig();
	}
}