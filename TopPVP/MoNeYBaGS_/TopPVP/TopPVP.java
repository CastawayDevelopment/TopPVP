package MoNeYBaGS_.TopPVP;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

public class TopPVP extends JavaPlugin {
	
	private final TopPVPCommandListener cmd = new TopPVPCommandListener(this);
	
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
		
		new TopPVPEntityListener(this);
		new TopPVPPlayerListener(this);
		
		getCommand("kills").setExecutor(cmd);
		getCommand("deaths").setExecutor(cmd);
		getCommand("kdr").setExecutor(cmd);
		getCommand("resetdeaths").setExecutor(cmd);
		getCommand("resetkills").setExecutor(cmd);
		getCommand("pvphelp").setExecutor(cmd);
	}


}