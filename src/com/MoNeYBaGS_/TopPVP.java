package com.MoNeYBaGS_;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.MoNeYBaGS_.Commands.Admin_Commands;
import com.MoNeYBaGS_.Commands.Basic;
import com.MoNeYBaGS_.Commands.Lead_Commands;
import com.MoNeYBaGS_.Configurations.Files;
import com.MoNeYBaGS_.Configurations.PlayersConfiguration;
import com.MoNeYBaGS_.Leaderboards.Leaderboards;
import com.MoNeYBaGS_.Listeners.TopPVPEntityListener;
import com.MoNeYBaGS_.Listeners.TopPVPPlayerListener;


public class TopPVP extends JavaPlugin 
{

	private Leaderboards lead;
	private Basic basic;
	private Lead_Commands leaderboards;
	private PlayersConfiguration config;
	private Admin_Commands admin;

	//Strings
	public String pvp = "[TopPVP]: ";
	public String cre = "Creating player ";

	//bukkit variables
	public Logger log = Logger.getLogger("Minecraft");

	public void onDisable() 
	{
		reloadConfig();
		saveConfig();
		log.info(pvp + " TopPVP Disabled!");
	}

	public void onEnable() 
	{
		if(!this.getDataFolder().exists())
			this.getDataFolder().mkdir();
		config = new PlayersConfiguration(this);
		config.getConfig().options().copyHeader(true);
		config.getConfig();
		getConfig();
		log.info(pvp + " TopPVP Enabled!");
		if(!(getConfig().getInt("Version") == 9))
		{
			saveConfig(); 
			ArrayList<String> files = new ArrayList<String>();
			if(!new File("plugins/TopPVP/players.conf").exists())
			{
				files.add("players.conf");
			}
			if(!new File("plugins/TopPVP/config.yml").exists())
			{
				files.add("config.yml");
				files.add("player.yml");
			}
			files.add("config_Template.yml");
			for(String name: files)
			{
				File configs = new File("plugins/TopPVP/" + name);
				InputStream jarURL = getClass().getResourceAsStream("/resources/" + name);
				try {
					Files.copyFile(jarURL, configs);
				} catch (Exception ex) {
					Logger.getLogger(TopPVP.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		reloadConfig();
		getConfig().set("Version", 9);
		saveConfig();
		lead = new Leaderboards(this);
		basic = new Basic(this, lead);
		leaderboards = new Lead_Commands(this, lead);
		admin = new Admin_Commands(this);
		
		new TopPVPPlayerListener(this);
		new TopPVPEntityListener(this, lead);

		getCommand("kills").setExecutor(basic);
		getCommand("deaths").setExecutor(basic);
		getCommand("kdr").setExecutor(basic);
		getCommand("resetdeaths").setExecutor(admin);
		getCommand("resetkills").setExecutor(admin);
		getCommand("leadkills").setExecutor(leaderboards);
		getCommand("leaddeaths").setExecutor(leaderboards);
		getCommand("leadkdr").setExecutor(leaderboards);
		getCommand("setkills").setExecutor(admin);
		getCommand("setdeaths").setExecutor(admin);
		getCommand("pvphelp").setExecutor(basic);
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