package MoNeYBaGS_.TopPVP;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TopPVPCommandListener implements CommandExecutor {

	public TopPVP plugin;

	public TopPVPCommandListener(TopPVP plugin)
	{
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		Player player = null;
		if(sender instanceof Player)
		{
			player = (Player) sender;
		}
		if(cmd.getName().equalsIgnoreCase("kills"))
		{
			if(player != null)
			{
				if(plugin.getConfig().getInt("players." + 
						sender.getName() + ".Kills", 0) == 0)
				{
					sender.sendMessage(ChatColor.RED + "You have not killed anyone at all...");
					return true;
				}
				else if(plugin.getConfig().getInt("players." + 
						sender.getName() + ".Kills", 0) == 1)
				{
					sender.sendMessage("You have killed 1 time.");
					return true;
				}
				else
				{
					sender.sendMessage(ChatColor.RED + "You have killed " + plugin.getConfig().getInt("players." + 
							sender.getName() + ".Kills", 0) + " times.");
					return true;
				}
			}
			else
			{
				sender.sendMessage("This command can only be executed by a player..");
				return true;
			}
		}
		else if(cmd.getName().equalsIgnoreCase("deaths"))
		{
			if(player != null)
			{
				if(plugin.getConfig().getInt("players." +
						sender.getName() + ".Deaths", 0) == 0)
				{
					sender.sendMessage("You have never died!");
				}
				else if(plugin.getConfig().getInt("players." +
						sender.getName() + ".Deaths", 0) == 1)
				{
					sender.sendMessage("You have died once.");
				}
				else
				{
					sender.sendMessage(ChatColor.GREEN + "You have died " + plugin.getConfig().getInt("players." +
							sender.getName() + ".Deaths", 0) + " times.");
				}
				return true;
			}
			else
			{
				sender.sendMessage("This command can only be executed by a player..");
				return true;
			}
		}
		
		
		else if(cmd.getName().equalsIgnoreCase("kdr"))
		{
			if(player != null)
			{
				int deaths = plugin.getConfig().getInt("players." + 
						player.getName() + ".Deaths");
				int kills = plugin.getConfig().getInt("players." + 
						player.getName() + ".Kills");
				int gcd = GCD(kills, deaths);
				deaths = deaths/gcd;
				kills = kills/gcd;
				double ratio = Math.round(((double)kills/(double)deaths) * 100.0D) / 100.0D;
				if(deaths == 0)
				{
					ratio = kills;
				}
				else if(kills == 0)
				{
					ratio = 0.00;
				}
				sender.sendMessage(ChatColor.GREEN + "Your Kill/Death Ratio : " + ratio + " or " + kills + ":"
						+ deaths);
				return true;
			}
			else
			{
				sender.sendMessage("This command can only be executed by a player..");
				return true;
			}
		}
		else if(cmd.getName().equalsIgnoreCase("resetdeaths"))
		{
			if(player != null)
			{
				if(args.length == 1)
				{
					if(player.hasPermission("toppvp.resetdeaths"))
					{
						sender.sendMessage(ChatColor.GREEN + args[0] + "'s Deaths have been reset to '0'");
						plugin.getConfig().set("players." + args[0] + ".Deaths", 0);
						plugin.saveConfig();
						plugin.reloadConfig();
						return true;
					}
					else
					{
						sender.sendMessage(ChatColor.RED + "You do not have permission to do this.");
						return true;
					}
				}
				else
				{
					if(player.hasPermission("toppvp.resetdeaths"))
					{
						sender.sendMessage(ChatColor.GREEN + "Your Deaths have been reset to '0'");
						plugin.getConfig().set("players." + player.getName() + ".Deaths", 0);
						plugin.saveConfig();
						plugin.reloadConfig();
						return true;
					}
					else
						sender.sendMessage(ChatColor.RED + "You do not have permission");
				}
				return true;
			}
			else
			{
				sender.sendMessage("This command can only be executed by a player..");
				return true;
			}
		}

		else if(cmd.getName().equalsIgnoreCase("resetkills"))
		{
			if(player != null)
			{
				if(args.length == 1)
				{
					if(player.hasPermission("toppvp.resetkills"))
					{
						sender.sendMessage(ChatColor.GREEN + args[0] + "'s Kills have been reset to '0'");
						plugin.getConfig().set("players." + args[0] + ".Kills", 0);
						plugin.saveConfig();
						plugin.reloadConfig();
						return true;
					}
					else
					{
						sender.sendMessage(ChatColor.RED + "You do not have permission to do this.");
						return true;
					}
				}
				else
				{
					if(player.hasPermission("toppvp.resetkills"))
					{
						sender.sendMessage(ChatColor.GREEN + "Your Kills have been reset to '0'");
						plugin.getConfig().set("players." + player.getName() + ".Kills", 0);
						plugin.saveConfig();
						plugin.reloadConfig();
						return true;
					}
					else
						sender.sendMessage(ChatColor.RED + "You do not have permission to do this");
					return true;
				}
			}
			else
			{
				sender.sendMessage("This command can only be executed by a player..");
				return true;
			}
		}
		else if(cmd.getName().equalsIgnoreCase("pvphelp"))
		{
			sender.sendMessage(ChatColor.RED + "*****************TopPVP Commands*****************");
			sender.sendMessage(ChatColor.GOLD + "/kills - View your kills.");
			sender.sendMessage(ChatColor.GOLD + "/deaths - View your deaths.");
			sender.sendMessage(ChatColor.GOLD + "/kdr - View your Kill/Death ratio.");
			sender.sendMessage(ChatColor.GOLD + "/resetkills <player> - Reset a player's kills.");
			sender.sendMessage(ChatColor.GOLD + "/resetdeaths <player> - Reset a player's deaths.");
			sender.sendMessage(ChatColor.GOLD + "/pvphelp - Shows this dialogue.");
			sender.sendMessage(ChatColor.RED + "*************************************************");
			return true;
		}
		else if(cmd.getName().equalsIgnoreCase("leadkills"))
		{
			sender.sendMessage(ChatColor.RED + "LEADERBOARDS ARE COMING SOON!");
			return true;
		}
		else if(cmd.getName().equalsIgnoreCase("leaddeaths"))
		{
			sender.sendMessage("LEADERBOARDS ARE COMING SOON!");
			return true;
		}
		return false;
	}
	
	public int GCD(int a, int b){
		   if (b==0) return a;
		   return GCD(b,a%b);
	}
}
 