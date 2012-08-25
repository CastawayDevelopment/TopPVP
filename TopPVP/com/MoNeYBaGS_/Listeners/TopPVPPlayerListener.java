package TopPVP.com.MoNeYBaGS_.Listeners;

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

import com.MoNeYBaGS_.TopPVP;


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
		plugin.reloadPlayersConfig();
		player = event.getPlayer();
		plugin.reloadPlayersConfig();
		String playername = plugin.getPlayersConfig().getString("players." + player.getName());
		if(playername == null || playername == "")
		{
			//create player
			plugin.log.info(plugin.pvp + plugin.cre + player.getName());
			plugin.getPlayersConfig().set("players." + player.getName() + ".Kills", 0);
			plugin.getPlayersConfig().set("players." + player.getName() + ".Deaths", 0);
			plugin.savePlayersConfig();
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
}