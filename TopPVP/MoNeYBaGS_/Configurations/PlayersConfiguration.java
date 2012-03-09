package MoNeYBaGS_.Configurations;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import MoNeYBaGS_.TopPVP;

public class PlayersConfiguration {

	private FileConfiguration playersCustomConfig = null;
	private File configFile = null;
	private static final String customConfigFile = null;
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
	    if (playersCustomConfig == null || customConfigFile == null) {
	    return;
	    }
	    try {
	        playersCustomConfig.save(customConfigFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + customConfigFile, ex);
	    }
	}
	
	public void reloadPlayersConfig()
	{
		if (configFile == null) {
			configFile = new File(plugin.getDataFolder(), "players.yml");
		}
		playersCustomConfig = YamlConfiguration.loadConfiguration(configFile);

		// Look for defaults in the jar
		InputStream defConfigStream = plugin.getResource("players.yml");
		if (defConfigStream != null) {
			YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
			playersCustomConfig.setDefaults(defConfig);
		}
	}
}
