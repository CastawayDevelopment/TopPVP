package com.MoNeYBaGS_.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.MoNeYBaGS_.TopPVP;
import com.MoNeYBaGS_.Configurations.Nodes;
import com.MoNeYBaGS_.Leaderboards.Leaderboards;

import java.util.Map;


public class Basic implements CommandExecutor {

	ChatColor black = ChatColor.BLACK;
	ChatColor dgray = ChatColor.DARK_GRAY;
	ChatColor purple = ChatColor.DARK_PURPLE;
	ChatColor aqua = ChatColor.AQUA;
	ChatColor daqua = ChatColor.DARK_AQUA;
	ChatColor dred = ChatColor.DARK_RED;
	ChatColor red = ChatColor.RED;
	ChatColor gold = ChatColor.GOLD;
	ChatColor gray = ChatColor.GRAY;
	
	private TopPVP plugin;
	
	public Basic(TopPVP _plugin, Leaderboards _leaderboards)
	{
		this.plugin = _plugin;
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
					Integer kills = (Integer)dbPlayer.get("kills");
					if(kills == 0)
					{
						
						player.sendMessage(ChatColor.BLACK + "======" + ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_GRAY + "TopPVP Message" + purple + "]" + ChatColor.BLACK + "======");
						player.sendMessage("");
						
						player.sendMessage(ChatColor.DARK_AQUA + Nodes.Paths.KillsReturnNone.getString());
						
						player.sendMessage("");
						player.sendMessage(ChatColor.BLACK + "======" + ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_GRAY + "TopPVP Message" + purple + "]" + ChatColor.BLACK + "======");
						
						
						return true;
					}
					else if(kills == 1)
					{
						
						player.sendMessage(ChatColor.BLACK + "======" + ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_GRAY + "TopPVP Message" + purple + "]" + ChatColor.BLACK + "======");
						player.sendMessage("");
						
						player.sendMessage(daqua + Nodes.Paths.KillsReturnOnce.getString());
						
						player.sendMessage("");
						player.sendMessage(ChatColor.BLACK + "======" + ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_GRAY + "TopPVP Message" + purple + "]" + ChatColor.BLACK + "======");
						
						
						return true;
						
						
						
					}
					else
					{
						
						player.sendMessage(ChatColor.BLACK + "======" + ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_GRAY + "TopPVP Message" + purple + "]" + ChatColor.BLACK + "======");
						player.sendMessage("");
						
						player.sendMessage(ChatColor.DARK_AQUA + Nodes.Paths.KillsReturn1.getString() + aqua +
 kills + daqua + Nodes.Paths.KillsReturn2.getString());
						
						player.sendMessage("");
						player.sendMessage(ChatColor.BLACK + "======" + ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_GRAY + "TopPVP Message" + purple + "]" + ChatColor.BLACK + "======");
						
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
					Integer deaths = (Integer)dbPlayer.get("deaths");

					if(deaths == 0)
					{
						
						player.sendMessage(ChatColor.BLACK + "======" + ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_GRAY + "TopPVP Message" + purple + "]" + ChatColor.BLACK + "======");
						player.sendMessage("");
						
						player.sendMessage(ChatColor.RED + Nodes.Paths.DeathsReturnNone.getString());
					
						player.sendMessage("");
						player.sendMessage(ChatColor.BLACK + "======" + ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_GRAY + "TopPVP Message" + purple + "]" + ChatColor.BLACK + "======");
					
					}
					else if(deaths == 1)
					{
						
						player.sendMessage(ChatColor.BLACK + "======" + ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_GRAY + "TopPVP Message" + purple + "]" + ChatColor.BLACK + "======");
						player.sendMessage("");
						
						player.sendMessage(ChatColor.DARK_AQUA + Nodes.Paths.DeathsReturnOnce.getString());
					
						player.sendMessage("");
						player.sendMessage(ChatColor.BLACK + "======" + ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_GRAY + "TopPVP Message" + purple + "]" + ChatColor.BLACK + "======");
						
					
					}
					else
					{
						
						player.sendMessage(ChatColor.BLACK + "======" + ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_GRAY + "TopPVP Message" + purple + "]" + ChatColor.BLACK + "======");
						player.sendMessage("");
						player.sendMessage(ChatColor.DARK_AQUA + Nodes.Paths.DeathsReturn1.getString() + aqua +
								deaths + daqua + Nodes.Paths.DeathsReturn2.getString());
						player.sendMessage("");
						player.sendMessage(ChatColor.BLACK + "======" + ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_GRAY + "TopPVP Message" + purple + "]" + ChatColor.BLACK + "======");
					
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
					player.sendMessage(ChatColor.BLACK + "======" + ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_GRAY + "TopPVP Message" + purple + "]" + ChatColor.BLACK + "======");
					player.sendMessage("");
					player.sendMessage(daqua + "Your Kill/Death Ratio : " + aqua + ratio + daqua + " or " + aqua + kills + daqua + ":"
							+ aqua + deaths);
					player.sendMessage("");
					player.sendMessage(ChatColor.BLACK + "======" + ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_GRAY + "TopPVP Message" + purple + "]" + ChatColor.BLACK + "======");
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
		
		
		else if(cmd.getName().equalsIgnoreCase("pvphelp"))
		{
			
			
			sender.sendMessage(ChatColor.BLACK + "===========" + ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_GRAY + "TopPVP Commands" + purple + "]" + ChatColor.BLACK + "===========");
			if(player.hasPermission(Nodes.Permissions.Kills.getString()));
		}else if(player.hasPermission(Nodes.Permissions.General.getString())){
				sender.sendMessage(gold + "/kills " + aqua + "- " + gray + "View your kills.");
			if(player.hasPermission(Nodes.Permissions.Deaths.getString()));
		}else if(player.hasPermission(Nodes.Permissions.General.getString())){
				sender.sendMessage(gold + "/deaths " + aqua + "- " + gray + "View your deaths.");
			if(player.hasPermission(Nodes.Permissions.KDR.getString()));
		}else if(player.hasPermission(Nodes.Permissions.General.getString())){
				sender.sendMessage(gold + "/kdr" + aqua + " - " + gray + "View your Kill/Death ratio.");
			if(player.hasPermission(Nodes.Permissions.ResetKills.getString()));
				sender.sendMessage(gold + "/resetkills <player>" + aqua + " - " + gray +  "Reset a player's kills.");
			if(player.hasPermission(Nodes.Permissions.REsetDeaths.getString()));
				sender.sendMessage(gold + "/resetdeaths <player>" + aqua + " - " + gray + "Reset a players's deaths.");
			if(player.hasPermission(Nodes.Permissions.SetKills.getString()));
				sender.sendMessage(gold + "/setkills <player> <amount>" + aqua + " - " + gray + "Set a player's kills.");
			if(player.hasPermission(Nodes.Permissions.SetDeaths.getString()));
				sender.sendMessage(gold + "/setdeaths <player> <amount>" + aqua + " - " + gray + "Set a player's deaths.");
			if(player.hasPermission(Nodes.Permissions.KillsLeaderboards.getString()));
		}else if(player.hasPermission(Nodes.Permissions.General.getString())){
				sender.sendMessage(gold + "/leadkills" + aqua + " - " + gray + "View Kills Leaderboard.");
				if(player.hasPermission(Nodes.Permissions.DeathsLeaderboards.getString()));
		}else if(player.hasPermission(Nodes.Permissions.General.getString())){
				sender.sendMessage(gold + "/leaddeaths" + aqua + " - " + gray + "View Deaths Leaderboard.");
				if(player.hasPermission(Nodes.Permissions.KDRLeaderboards.getString()));
		}else if(player.hasPermission(Nodes.Permissions.General.getString())){
				sender.sendMessage(gold + "/leadkdr" + aqua + " - " + gray + "View KDR Leaderboard.");
			sender.sendMessage(gold + "/pvphelp" + aqua +  " - " + gray + "Shows this dialogue.");
			sender.sendMessage(ChatColor.BLACK + "===========" + ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_GRAY + "TopPVP Commands" + purple + "]" + ChatColor.BLACK + "===========");
			return true;
		}
		
		return false;
	}

	public int GCD(int a, int b){
		if (b==0) return a;
		return GCD(b,a%b);
	}
}
