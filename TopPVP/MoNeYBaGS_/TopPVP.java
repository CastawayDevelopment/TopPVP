package MoNeYBaGS_;

import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import MoNeYBaGS_.Commands.TopPVPCommandListener;
import MoNeYBaGS_.Configurations.Files;
import MoNeYBaGS_.Configurations.PlayersConfiguration;
import MoNeYBaGS_.Leaderboards.Leaderboards;
import MoNeYBaGS_.Listeners.TopPVPEntityListener;
import MoNeYBaGS_.Listeners.TopPVPPlayerListener;

public class TopPVP extends JavaPlugin 
{

	private Leaderboards lead;
	private TopPVPCommandListener cmd;
	private PlayersConfiguration config;

	//Strings
	public String pvp = "[TopPVP]: ";
	public String cre = "Creating player ";

	//bukkit variables
	public Logger log = Logger.getLogger("Minecraft");

	public void onDisable() 
	{
		log.info(pvp + " TopPVP Disabled!");
	}

	public void onEnable() 
	{
		new Files();
		config = new PlayersConfiguration(this);
		config.getConfig().options().copyHeader(true);
		config.getConfig();
		getConfig();
		log.info(pvp + " TopPVP Enabled!");
		if(!(getConfig().getInt("Version") == 5))
		{
			saveConfig();
			Files.extract("config.yml", "players.yml", "players.conf", "config_Template.yml");
			log.info("This is your first time using TopPVP v0.6. Your previous configuration files for" +
					" TopPVP have been deleted if you already had TopPVP. Nothing else has been affected." +
					" This means every players kills and deaths have been deleted that was linked with TopPVP. It was " +
					" needed to be done in order for the" +
					" configuration files to work properly. Thank you, and visit the BukkitDev often.");
			reloadConfig();
		}
		getConfig().set("Version", 6);
		lead = new Leaderboards(this);
		cmd = new TopPVPCommandListener(this, lead);
		new TopPVPPlayerListener(this);
		new TopPVPEntityListener(this);

		getCommand("kills").setExecutor(cmd);
		getCommand("deaths").setExecutor(cmd);
		getCommand("kdr").setExecutor(cmd);
		getCommand("resetdeaths").setExecutor(cmd);
		getCommand("resetkills").setExecutor(cmd);
		getCommand("leadkills").setExecutor(cmd);
		getCommand("pvphelp").setExecutor(cmd);
	}

	public void reloadPlayersConfig()
	{
		config.reloadPlayersConfig();
	}

	public FileConfiguration getPlayersConfig()
	{
		return config.getConfig();
	}
	
	public void savePlayersConfig() 
	{
	    config.savePlayersConfig();
	}
	
}