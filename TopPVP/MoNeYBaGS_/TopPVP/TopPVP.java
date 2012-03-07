package MoNeYBaGS_.TopPVP;

import java.io.File;
import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

import MoNeYBaGS_.Commands.TopPVPCommandListener;
import MoNeYBaGS_.Leaderboards.Leaderboards;
import MoNeYBaGS_.Listeners.TopPVPEntityListener;
import MoNeYBaGS_.Listeners.TopPVPPlayerListener;

public class TopPVP extends JavaPlugin {

	private Leaderboards lead;
	private TopPVPCommandListener cmd;
	
	//Strings
	public String pvp = "[TopPVP]: ";
	public String cre = "Creating player ";
	
	//bukkit variables
	public Logger log = Logger.getLogger("Minecraft");

	public void onDisable() {
		log.info(pvp + " TopPVP Disabled!");
		saveConfig();
	}

	public void onEnable() {
		getConfig();
		log.info(pvp + " TopPVP Enabled!");
		if(!(getConfig().getInt("info.Version") == 5))
		{
			log.info("This is your first time using TopPVP v0.5. Your previous configuration files for" +
					" TopPVP have been deleted if you already had TopPVP. Nothing else has been affected." +
					" This means every players kills and deaths have been deleted that was linked with TopPVP. It was " +
					" needed to be done in order for the" +
					" leaderboards to work properly. Thank you, and visit the BukkitDev often.");
			File config = new File("plugins/TopPVP/config.yml");
			if(config.exists())
			{
				config.delete();
			}
			getConfig().set("info.Version", 5);
			saveConfig();
			reloadConfig();
		}
		
		new Files();
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


}