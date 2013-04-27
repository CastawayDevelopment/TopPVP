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
	
	ChatColor black = ChatColor.BLACK;
	ChatColor dgray = ChatColor.DARK_GRAY;
	ChatColor purple = ChatColor.DARK_PURPLE;
	ChatColor aqua = ChatColor.AQUA;
	ChatColor daqua = ChatColor.DARK_AQUA;
	ChatColor dred = ChatColor.DARK_RED;
	ChatColor red = ChatColor.RED;
	ChatColor gold = ChatColor.GOLD;
	ChatColor gray = ChatColor.GRAY;

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
					Map dbPlayer = plugin.config.getPlayer(player.getName());
					int kills = (Integer)dbPlayer.get("kills");

					if(kills == 0)
					{
						player.sendMessage(ChatColor.GREEN + Nodes.Paths.KillsReturnNone.getString());
						return true;
					}
					else if(kills == 1)
					{
						player.sendMessage(Nodes.Paths.KillsReturnOnce.getString());
						return true;
					}
					else
					{
						player.sendMessage(ChatColor.RED + Nodes.Paths.KillsReturn1.getString() + 
								kills + Nodes.Paths.KillsReturn2.getString());
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
					Map dbPlayer = plugin.config.getPlayer(player.getName());
					int deaths = (Integer)dbPlayer.get("deaths");

					if(deaths == 0)
					{
						player.sendMessage(ChatColor.RED + Nodes.Paths.DeathsReturnNone.getString());
					}
					else if(deaths == 1)
					{
						player.sendMessage(ChatColor.RED + Nodes.Paths.DeathsReturnOnce.getString());
					}
					else
					{
						player.sendMessage(ChatColor.RED + Nodes.Paths.DeathsReturn1.getString() + 
								deaths + Nodes.Paths.DeathsReturn2.getString());
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
					Map dbPlayer = plugin.config.getPlayer(player.getName());
					int deaths = (Integer)dbPlayer.get("deaths");
					int kills = (Integer)dbPlayer.get("kills");

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

						String query = "UPDATE "+plugin.database.tableName+" SET deaths='0' WHERE username='"+player.getName()+"'";
						plugin.config.updatePlayer(query);
						plugin.config.reloadPlayersConfig();

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

						String query = "UPDATE "+plugin.database.tableName+" SET deaths='0' WHERE username='"+player.getName()+"'";
						plugin.config.updatePlayer(query);
						plugin.config.reloadPlayersConfig();

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

						String query = "UPDATE "+plugin.database.tableName+" SET kills='0' WHERE username='"+player.getName()+"'";
						plugin.config.updatePlayer(query);
						plugin.config.reloadPlayersConfig();

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

						String query = "UPDATE "+plugin.database.tableName+" SET kills='0' WHERE username='"+player.getName()+"'";
						plugin.config.updatePlayer(query);
						plugin.config.reloadPlayersConfig();

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
						String query = "UPDATE "+plugin.database.tableName+" SET kills='"+Integer.parseInt(args[1].toString())+"' WHERE username='"+player.getName()+"'";
						plugin.config.updatePlayer(query);
						plugin.config.reloadPlayersConfig();
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
						String query = "UPDATE "+plugin.database.tableName+" SET deaths='"+Integer.parseInt(args[1].toString())+"' WHERE username='"+player.getName()+"'";
						plugin.config.updatePlayer(query);
						plugin.config.reloadPlayersConfig();
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

						sender.sendMessage(ChatColor.BLACK + "=============" + ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_GRAY + "TopPVP Message" + purple + "]" + ChatColor.BLACK + "=============");
						sender.sendMessage("");
						
						
						sender.sendMessage(ChatColor.AQUA + "1. " + top.get(0));
						for(int i = 1; i < top.size() && i <= Nodes.Paths.LeaderboardsAmount.getInt(); i++)
							sender.sendMessage(ChatColor.DARK_AQUA + Integer.toString(i+1) + ". " + top.get(i));
						
						
						sender.sendMessage("");	
						sender.sendMessage(ChatColor.BLACK + "=============" + ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_GRAY + "TopPVP Message" + purple + "]" + ChatColor.BLACK + "=============");
							
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

					sender.sendMessage(ChatColor.BLACK + "=============" + ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_GRAY + "TopPVP Message" + purple + "]" + ChatColor.BLACK + "=============");
					sender.sendMessage("");
					
					for(int i = 0; i < top.size() && i <= Nodes.Paths.LeaderboardsAmount.getInt(); i++)
						sender.sendMessage(Integer.toString(i+1) + ". " + top.get(i));
					
					sender.sendMessage("");	
					sender.sendMessage(ChatColor.BLACK + "=============" + ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_GRAY + "TopPVP Message" + purple + "]" + ChatColor.BLACK + "=============");
					
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
