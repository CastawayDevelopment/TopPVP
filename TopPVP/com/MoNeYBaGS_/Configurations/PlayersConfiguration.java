package com.MoNeYBaGS_.Configurations;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.MoNeYBaGS_.TopPVP;


public class PlayersConfiguration {

	private File configFile = new File("plugins/TopPVP/players.yml");
	private FileConfiguration playersCustomConfig = YamlConfiguration.loadConfiguration(configFile);
	private TopPVP plugin;
	
	public PlayersConfiguration(TopPVP instance)
	{
		this.plugin = instance;
		Nodes.load(new File(plugin.getDataFolder() + "/config.yml"));
	}
	
	public FileConfiguration getConfig()
	{
		if (playersCustomConfig == null) {
			reloadPlayersConfig();
		}
		return playersCustomConfig;
	}
	
	public void savePlayersConfig() {
	    if (playersCustomConfig == null || configFile == null) {
	    	System.out.println("NULL");
	    	return;
	    }
	    try {
	        playersCustomConfig.save(configFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + configFile, ex);
	    }
	}
	
	public void reloadPlayersConfig()
	{
		if (configFile == null) {
			configFile = new File("plugins/TopPVP/players.yml");
		}
		playersCustomConfig = YamlConfiguration.loadConfiguration(configFile);
	}
}
