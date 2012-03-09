package MoNeYBaGS_.Commands;

import java.util.ArrayList;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import MoNeYBaGS_.TopPVP;
import MoNeYBaGS_.Configurations.Nodes;
import MoNeYBaGS_.Leaderboards.Leaderboards;
import MoNeYBaGS_.Leaderboards.trimLeaderboards;

public class TopPVPCommandListener implements CommandExecutor {

	private TopPVP plugin;
	private Leaderboards leaderboards;

	public TopPVPCommandListener(TopPVP _plugin, Leaderboards _leaderboards)
	{
		this.plugin = _plugin;
		this.leaderboards = _leaderboards;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		plugin.reloadConfig();
		Player player = null;
		if(sender instanceof Player)
		{
			player = (Player) sender;
		}
		if(cmd.getName().equalsIgnoreCase("kills"))
		{
			if(player != null)
			{
				if(plugin.getPlayersConfig().getInt("players." + 
						sender.getName() + ".Kills", 0) == 0)
				{
					sender.sendMessage(ChatColor.GREEN + Nodes.Paths.KillsReturnNone.getString());
					return true;
				}
				else if(plugin.getPlayersConfig().getInt("players." + 
						sender.getName() + ".Kills", 0) == 1)
				{
					sender.sendMessage(Nodes.Paths.KillsReturnOnce.getString());
					return true;
				}
				else
				{
					sender.sendMessage(ChatColor.RED + Nodes.Paths.KillsReturn1.getString() + 
							plugin.getPlayersConfig().getInt("players." + 
							sender.getName() + ".Kills", 0) + Nodes.Paths.KillsReturn2.getString());
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
				if(plugin.getPlayersConfig().getInt("players." +
						sender.getName() + ".Deaths", 0) == 0)
				{
					sender.sendMessage(ChatColor.RED + Nodes.Paths.DeathsReturnNone.getString());
				}
				else if(plugin.getPlayersConfig().getInt("players." +
						sender.getName() + ".Deaths", 0) == 1)
				{
					sender.sendMessage(ChatColor.RED + Nodes.Paths.DeathsReturnOnce.getString());
				}
				else
				{
					sender.sendMessage(ChatColor.RED + Nodes.Paths.DeathsReturn1.getString() + 
							plugin.getPlayersConfig().getInt("players." +
							sender.getName() + ".Deaths", 0) + Nodes.Paths.DeathsReturn2.getString());
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
				int deaths = plugin.getPlayersConfig().getInt("players." + 
						player.getName() + ".Deaths");
				int kills = plugin.getPlayersConfig().getInt("players." + 
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
						sender.sendMessage(ChatColor.GREEN + args[0] + Nodes.Paths.ResetDeaths.getString());
						plugin.reloadPlayersConfig();
						plugin.getPlayersConfig().set("players." + args[0] + ".Deaths", 0);
						plugin.savePlayersConfig();
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
						sender.sendMessage(ChatColor.GREEN + Nodes.Paths.ResetDeathsYou.getString());
						plugin.reloadPlayersConfig();
						plugin.getPlayersConfig().set("players." + player.getName() + ".Deaths", 0);
						plugin.savePlayersConfig();
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
						sender.sendMessage(ChatColor.GREEN + args[0] + Nodes.Paths.ResetKills.getString());
						plugin.reloadPlayersConfig();
						plugin.getPlayersConfig().set("players." + args[0] + ".Kills", 0);
						plugin.savePlayersConfig();
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
						sender.sendMessage(ChatColor.GREEN + Nodes.Paths.ResetKillsYou.getString());
						plugin.reloadPlayersConfig();
						plugin.getPlayersConfig().set("players." + player.getName() + ".Kills", 0);
						plugin.savePlayersConfig();
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
			sender.sendMessage(ChatColor.RED + "******************TopPVP Commands*******************");
			sender.sendMessage(ChatColor.GOLD + "/kills - View your kills.");
			sender.sendMessage(ChatColor.GOLD + "/deaths - View your deaths.");
			sender.sendMessage(ChatColor.GOLD + "/kdr - View your Kill/Death ratio.");
			sender.sendMessage(ChatColor.GOLD + "/resetkills <player> - Reset a player's kills.");
			sender.sendMessage(ChatColor.GOLD + "/resetdeaths <player> - Reset a player's deaths.");
			sender.sendMessage(ChatColor.GOLD + "/leadkills - View Kills Leaderboard.");
			sender.sendMessage(ChatColor.GOLD + "/pvphelp - Shows this dialogue.");
			sender.sendMessage(ChatColor.RED + "****************************************************");
			return true;
		}
		else if(cmd.getName().equalsIgnoreCase("leadkills"))
		{
			Map<String, Integer> tree = leaderboards.getLeaderboards();
			trimLeaderboards trim = new trimLeaderboards();
			ArrayList<String> top = trim.getTrimmed(tree.toString());
			player.sendMessage(ChatColor.RED + "**************PVP Leaderboard**************");
			for(int i = 0; i < top.size(); i++)
				player.sendMessage(ChatColor.GOLD + Integer.toString(i+1) + ". " + top.get(i));
			player.sendMessage(ChatColor.RED + "*********************************************");			
			return true;
		}
		else if(cmd.getName().equalsIgnoreCase("leaddeaths"))
		{
			sender.sendMessage("DEATH LEADERBOARDS ARE COMING SOON!");
			return true;
		}
		return false;
	}

	public int GCD(int a, int b){
		if (b==0) return a;
		return GCD(b,a%b);
	}
}
