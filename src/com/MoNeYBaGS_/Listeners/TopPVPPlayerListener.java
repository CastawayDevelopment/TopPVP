package com.MoNeYBaGS_.Listeners;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

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
		plugin.config.reloadPlayersConfig();

		player = event.getPlayer();

		Map dbPlayer = plugin.config.getPlayer(player.getName());
		if(dbPlayer == null)
		{
			//create player
			plugin.log.info(plugin.pvp + plugin.cre + player.getName());
			String query = "INSERT INTO "+plugin.database.tableName+" (username, kills, deaths) VALUES ('"+player.getName()+"', 0, 0)";
			plugin.config.updatePlayer(query);
			plugin.config.reloadPlayersConfig();
		}
	}
}