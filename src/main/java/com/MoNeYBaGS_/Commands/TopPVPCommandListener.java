package com.MoNeYBaGS_.Commands;

import java.util.ArrayList;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.MoNeYBaGS_.TopPVP;
import com.MoNeYBaGS_.Configurations.Nodes;
import com.MoNeYBaGS_.Leaderboards.Leaderboards;
import com.MoNeYBaGS_.Leaderboards.trimLeaderboards;


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
				if(player.hasPermission(Nodes.Permissions.General.getString()) || 
						player.hasPermission(Nodes.Permissions.Kills.getString()))
				{
					if(plugin.getPlayersConfig().getInt("players." + 
							player.getName() + ".Kills", 0) == 0)
					{
						player.sendMessage(ChatColor.GREEN + Nodes.Paths.KillsReturnNone.getString());
						return true;
					}
					else if(plugin.getPlayersConfig().getInt("players." + 
							player.getName() + ".Kills", 0) == 1)
					{
						player.sendMessage(Nodes.Paths.KillsReturnOnce.getString());
						return true;
					}
					else
					{
						player.sendMessage(ChatColor.RED + Nodes.Paths.KillsReturn1.getString() + 
								plugin.getPlayersConfig().getInt("players." + 
										player.getName() + ".Kills", 0) + Nodes.Paths.KillsReturn2.getString());
						return true;
					}
				}
				else
					player.sendMessage(ChatColor.RED + "You do not have permission...");
				return true;
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
				if(player.hasPermission(Nodes.Permissions.General.getString()) || 
						player.hasPermission(Nodes.Permissions.Deaths.getString()))
				{
					if(plugin.getPlayersConfig().getInt("players." +
							player.getName() + ".Deaths", 0) == 0)
					{
						player.sendMessage(ChatColor.RED + Nodes.Paths.DeathsReturnNone.getString());
					}
					else if(plugin.getPlayersConfig().getInt("players." +
							player.getName() + ".Deaths", 0) == 1)
					{
						player.sendMessage(ChatColor.RED + Nodes.Paths.DeathsReturnOnce.getString());
					}
					else
					{
						player.sendMessage(ChatColor.RED + Nodes.Paths.DeathsReturn1.getString() + 
								plugin.getPlayersConfig().getInt("players." +
										player.getName() + ".Deaths", 0) + Nodes.Paths.DeathsReturn2.getString());
					}
					return true;
				}
				else
					player.sendMessage(ChatColor.RED + "You do not have permission...");
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
				if(player.hasPermission(Nodes.Permissions.General.getString()) || 
						player.hasPermission(Nodes.Permissions.Deaths.getString()))
				{
					int deaths = plugin.getPlayersConfig().getInt("players." + 
							player.getName() + ".Deaths");
					int kills = plugin.getPlayersConfig().getInt("players." + 
							player.getName() + ".Kills");
					int gcd = GCD(kills, deaths);
					if(!(gcd == 0))
					{
						deaths = deaths/gcd;
						kills = kills/gcd;
					}
					double ratio = Math.round(((double)kills/(double)deaths) * 100.0D) / 100.0D;
					if(deaths == 0)
					{
						ratio = kills;
					}
					else if(kills == 0)
					{
						ratio = 0.00;
					}
					player.sendMessage(ChatColor.GREEN + "Your Kill/Death Ratio : " + ratio + " or " + kills + ":"
							+ deaths);
					return true;
				}
				else
					player.sendMessage(ChatColor.RED + "You do not have permission...");
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
					if(player.hasPermission(Nodes.Permissions.REsetDeaths.getString()))
					{
						player.sendMessage(ChatColor.GREEN + args[0] + Nodes.Paths.ResetDeaths.getString());
						plugin.reloadPlayersConfig();
						plugin.getPlayersConfig().set("players." + args[0] + ".Deaths", 0);
						plugin.savePlayersConfig();
						return true;
					}
					else
					{
						player.sendMessage(ChatColor.RED + "You do not have permission...");
						return true;
					}
				}
				else
				{
					if(player.hasPermission(Nodes.Permissions.REsetDeaths.getString()))
					{
						player.sendMessage(ChatColor.GREEN + Nodes.Paths.ResetDeathsYou.getString());
						plugin.reloadPlayersConfig();
						plugin.getPlayersConfig().set("players." + player.getName() + ".Deaths", 0);
						plugin.savePlayersConfig();
						return true;
					}
					else
						player.sendMessage(ChatColor.RED + "You do not have permission");
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
					if(player.hasPermission(Nodes.Permissions.ResetKills.getString()))
					{
						player.sendMessage(ChatColor.GREEN + args[0] + Nodes.Paths.ResetKills.getString());
						plugin.reloadPlayersConfig();
						plugin.getPlayersConfig().set("players." + args[0] + ".Kills", 0);
						plugin.savePlayersConfig();
						return true;
					}
					else
					{
						player.sendMessage(ChatColor.RED + "You do not have permission to do this.");
						return true;
					}
				}
				else
				{
					if(player.hasPermission(Nodes.Permissions.ResetKills.getString()))
					{
						player.sendMessage(ChatColor.GREEN + Nodes.Paths.ResetKillsYou.getString());
						plugin.reloadPlayersConfig();
						plugin.getPlayersConfig().set("players." + player.getName() + ".Kills", 0);
						plugin.savePlayersConfig();
						return true;
					}
					else
						player.sendMessage(ChatColor.RED + "You do not have permission to do this");
					return true;
				}
			}
			else
			{
				sender.sendMessage("This command can only be executed by a player..");
				return true;
			}
		}
		
		else if(cmd.getName().equalsIgnoreCase("setkills"))
		{
			if(player != null)
			{
				if(args.length == 2)
				{
					if(player.hasPermission(Nodes.Permissions.SetKills.getString()))
					{
						player.sendMessage(ChatColor.GREEN + "Kills for " + args[0] + " have been set to " + args[1]);
						plugin.reloadPlayersConfig();
						plugin.getPlayersConfig().set("players." + args[0] + ".Kills", Integer.parseInt(args[1].toString()));
						plugin.savePlayersConfig();
						return true;
					}
					else
					{
						player.sendMessage(ChatColor.RED + "You do not have permission to do this.");
						return true;
					}
				}
				else
				{
					player.sendMessage(ChatColor.RED + "Not enough arguments. Syntax is /setkills <player> <amount>.");
					return true;
				}
			}
			else
			{
				sender.sendMessage("This command can only be executed by a player..");
				return true;
			}
		}
		
		
		else if(cmd.getName().equalsIgnoreCase("setdeaths"))
		{
			if(player != null)
			{
				if(args.length == 2)
				{
					if(player.hasPermission(Nodes.Permissions.SetDeaths.getString()))
					{
						player.sendMessage(ChatColor.GREEN + "Deaths for " + args[0] + " have been set to " + args[1]);
						plugin.reloadPlayersConfig();
						plugin.getPlayersConfig().set("players." + args[0] + ".Deaths", args[1]);
						plugin.savePlayersConfig();
						return true;
					}
					else
					{
						player.sendMessage(ChatColor.RED + "You do not have permission to do this.");
						return true;
					}
				}
				else
				{
					player.sendMessage(ChatColor.RED + "Not enough arguments. Syntax is /setdeaths <player> <amount>.");
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
			if(player.hasPermission(Nodes.Permissions.Kills.getString()))
				sender.sendMessage(ChatColor.GOLD + "/kills - View your kills.");
			if(player.hasPermission(Nodes.Permissions.Deaths.getString()))
				sender.sendMessage(ChatColor.GOLD + "/deaths - View your deaths.");
			if(player.hasPermission(Nodes.Permissions.KDR.getString()))
				sender.sendMessage(ChatColor.GOLD + "/kdr - View your Kill/Death ratio.");
			if(player.hasPermission(Nodes.Permissions.ResetKills.getString()))
				sender.sendMessage(ChatColor.GOLD + "/resetkills <player> - Reset a player's kills.");
			if(player.hasPermission(Nodes.Permissions.REsetDeaths.getString()))
				sender.sendMessage(ChatColor.GOLD + "/resetdeaths <player> - Reset a players's deaths.");
			if(player.hasPermission(Nodes.Permissions.SetKills.getString()))
				sender.sendMessage(ChatColor.GOLD + "/setkills <player> <amount> - Set a player's kills");
			if(player.hasPermission(Nodes.Permissions.SetDeaths.getString()))
				sender.sendMessage(ChatColor.GOLD + "/setdeaths <player> <amount> - Set a player's deaths");
			if(player.hasPermission(Nodes.Permissions.KillsLeaderboards.getString()))
				sender.sendMessage(ChatColor.GOLD + "/leadkills - View Kills Leaderboard.");
			sender.sendMessage(ChatColor.GOLD + "/pvphelp - Shows this dialogue.");
			sender.sendMessage(ChatColor.RED + "****************************************************");
			return true;
		}
		else if(cmd.getName().equalsIgnoreCase("leadkills"))//needs testing
		{
			if(Nodes.Paths.AllowLeaderboards.getBool() == true)
			{
				if(!(player == null))
				{
					if(player.hasPermission(Nodes.Permissions.General.getString()) || 
							player.hasPermission(Nodes.Permissions.KillsLeaderboards.getString()))
					{
						Map<String, Integer> tree = leaderboards.getKillsLeaderboards();
						trimLeaderboards trim = new trimLeaderboards();
						ArrayList<String> top = trim.getTrimmed(tree.toString());

						sender.sendMessage(ChatColor.RED + "**************PVP Leaderboard**************");
						sender.sendMessage(ChatColor.GREEN + "1. " + top.get(0));
						for(int i = 1; i < top.size() && i <= Nodes.Paths.LeaderboardsAmount.getInt(); i++)
							sender.sendMessage(ChatColor.GOLD + Integer.toString(i+1) + ". " + top.get(i));
						sender.sendMessage(ChatColor.RED + "*********************************************");			
						return true;
					}
					else
					{
						player.sendMessage(ChatColor.RED + "You don't have permission...");
						return true;
					}
				}
				else
				{
					Map<String, Integer> tree = leaderboards.getKillsLeaderboards();
					trimLeaderboards trim = new trimLeaderboards();
					ArrayList<String> top = trim.getTrimmed(tree.toString());

					sender.sendMessage("**************PVP Leaderboard**************");
					for(int i = 0; i < top.size() && i <= Nodes.Paths.LeaderboardsAmount.getInt(); i++)
						sender.sendMessage(Integer.toString(i+1) + ". " + top.get(i));
					sender.sendMessage("*********************************************");	
					return true;
				}
			}
			else
				sender.sendMessage(Nodes.Paths.LeaderboardsFalse.getString());
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
