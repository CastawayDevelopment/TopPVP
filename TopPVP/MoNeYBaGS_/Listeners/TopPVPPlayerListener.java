package MoNeYBaGS_.Listeners;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import MoNeYBaGS_.TopPVP.TopPVP;

public class TopPVPPlayerListener implements Listener {
	
	private static TopPVP plugin;
	private Player player;
	
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
			plugin.saveConfig();
			plugin.reloadConfig();
			try {
				BufferedReader fin = new BufferedReader(new FileReader("plugins/TopPVP/players.conf"));
				String temp = fin.readLine();
				fin.close();
				BufferedWriter fout = new BufferedWriter(new FileWriter("plugins/TopPVP/players.conf"));
				fout.append(player.getName() + ";" + temp);
				fout.close();
			} catch (FileNotFoundException e) {
				plugin.log.getLevel();
				plugin.log.info(e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		plugin.saveConfig();
		plugin.reloadConfig();
	}
}