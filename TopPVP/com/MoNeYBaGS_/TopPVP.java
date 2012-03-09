package com.MoNeYBaGS_;

import java.io.File;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.MoNeYBaGS_.Commands.TopPVPCommandListener;
import com.MoNeYBaGS_.Configurations.Files;
import com.MoNeYBaGS_.Configurations.PlayersConfiguration;
import com.MoNeYBaGS_.Leaderboards.Leaderboards;
import com.MoNeYBaGS_.Listeners.TopPVPEntityListener;
import com.MoNeYBaGS_.Listeners.TopPVPPlayerListener;


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
		if(!(getConfig().getInt("Version") == 6))
		{
			saveConfig(); 
			String[] names = {"config.yml", "config_Template.yml", "players.conf", "players.yml"};
			for(String name: names)
			{
				File configs = new File("plugins/TopPVP/" + name);
				InputStream jarURL = getClass().getResourceAsStream("/resources/" + name);
				try {
					Files.copyFile(jarURL, configs);
				} catch (Exception ex) {
					Logger.getLogger(TopPVP.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
			JOptionPane.showMessageDialog(null, "TopPVP has been updated to v0.6, and in this update, many configuration\n" +
					"files have been made. This means your current " +
					"configuration files\nhave been deleted. Sorry for the inconvienience\n" +
					"Read the template for more information on how to edit/configure the config files.\n\n" +
					"DO NOT EDIT THE \"VERSION\" NODE IN THE CONFIG FILES OR EVERYTHING\n" +
					"WILL BE DELETED!!!", "TopPVP 0.6", JOptionPane.PLAIN_MESSAGE);
		}
		reloadConfig();
		getConfig().set("Version", 6);
		saveConfig();
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