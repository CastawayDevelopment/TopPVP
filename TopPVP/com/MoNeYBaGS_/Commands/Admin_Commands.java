package com.MoNeYBaGS_.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.MoNeYBaGS_.TopPVP;
import com.MoNeYBaGS_.Configurations.Nodes;

public class Admin_Commands implements CommandExecutor{
	
	private TopPVP plugin;
	
	public Admin_Commands(TopPVP _plugin)
	{
		this.plugin = _plugin;
	}
	
	ChatColor black = ChatColor.BLACK;
	ChatColor dgray = ChatColor.DARK_GRAY;
	ChatColor purple = ChatColor.DARK_PURPLE;
	ChatColor aqua = ChatColor.AQUA;
	ChatColor daqua = ChatColor.DARK_AQUA;
	ChatColor dred = ChatColor.DARK_RED;
	ChatColor red = ChatColor.RED;
	ChatColor gold = ChatColor.GOLD;
	ChatColor gray = ChatColor.GRAY;
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
			String[] args) { 
		
		
		plugin.reloadConfig();
		Player player = null;
		if(sender instanceof Player)
		{
			player = (Player) sender;
		}
		
		
		if(cmd.getName().equalsIgnoreCase("resetdeaths"))
		{
			if(player != null)
			{
				if(args.length == 1)
				{
					if(player.hasPermission(Nodes.Permissions.REsetDeaths.getString()))
					{
						
						player.sendMessage(ChatColor.BLACK + "======" + ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_GRAY + "TopPVP Message" + purple + "]" + ChatColor.BLACK + "======");
						player.sendMessage("");

						player.sendMessage(ChatColor.AQUA + args[0] + daqua + Nodes.Paths.ResetDeaths.getString());
						
						player.sendMessage("");
						player.sendMessage(ChatColor.BLACK + "======" + ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_GRAY + "TopPVP Message" + purple + "]" + ChatColor.BLACK + "======");
						
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
						
						player.sendMessage(ChatColor.BLACK + "======" + ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_GRAY + "TopPVP Message" + purple + "]" + ChatColor.BLACK + "======");
						player.sendMessage("");
						
						player.sendMessage(ChatColor.DARK_RED + Nodes.Paths.ResetDeathsYou.getString());
						
						player.sendMessage(ChatColor.BLACK + "======" + ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_GRAY + "TopPVP Message" + purple + "]" + ChatColor.BLACK + "======");
						player.sendMessage("");
						
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
						
						player.sendMessage(ChatColor.BLACK + "======" + ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_GRAY + "TopPVP Message" + purple + "]" + ChatColor.BLACK + "======");
						player.sendMessage("");
						
						player.sendMessage(ChatColor.AQUA + args[0] + daqua + Nodes.Paths.ResetKills.getString());
						
						player.sendMessage("");
						player.sendMessage(ChatColor.BLACK + "======" + ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_GRAY + "TopPVP Message" + purple + "]" + ChatColor.BLACK + "======");
						
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
						player.sendMessage(ChatColor.BLACK + "======" + ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_GRAY + "TopPVP Message" + purple + "]" + ChatColor.BLACK + "======");
						player.sendMessage("");
						
						player.sendMessage(ChatColor.DARK_RED + Nodes.Paths.ResetKillsYou.getString());
						
						player.sendMessage("");
						player.sendMessage(ChatColor.BLACK + "======" + ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_GRAY + "TopPVP Message" + purple + "]" + ChatColor.BLACK + "======");
						
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
						
						player.sendMessage(ChatColor.BLACK + "======" + ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_GRAY + "TopPVP Message" + purple + "]" + ChatColor.BLACK + "======");
						player.sendMessage("");
						
						player.sendMessage(ChatColor.DARK_AQUA + "Kills for " + aqua + args[0] + daqua + " have been set to " + aqua + args[1]);
						
						player.sendMessage("");
						player.sendMessage(ChatColor.BLACK + "======" + ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_GRAY + "TopPVP Message" + purple + "]" + ChatColor.BLACK + "======");
						
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
					
					player.sendMessage(ChatColor.BLACK + "======" + ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_GRAY + "TopPVP Message" + purple + "]" + ChatColor.BLACK + "======");
					player.sendMessage("");
					
					player.sendMessage(ChatColor.RED + "Not enough arguments. Syntax is /setkills <player> <amount>.");
					
					player.sendMessage("");
					player.sendMessage(ChatColor.BLACK + "======" + ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_GRAY + "TopPVP Message" + purple + "]" + ChatColor.BLACK + "======");
					
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
						
						player.sendMessage(ChatColor.BLACK + "======" + ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_GRAY + "TopPVP Message" + purple + "]" + ChatColor.BLACK + "======");
						player.sendMessage("");
						
						player.sendMessage(ChatColor.DARK_AQUA + "Deaths for " + aqua + args[0] + daqua + " have been set to " + aqua + args[1]);
						
						player.sendMessage("");
						player.sendMessage(ChatColor.BLACK + "======" + ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_GRAY + "TopPVP Message" + purple + "]" + ChatColor.BLACK + "======");
						
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
					
					player.sendMessage(ChatColor.BLACK + "======" + ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_GRAY + "TopPVP Message" + purple + "]" + ChatColor.BLACK + "======");
					player.sendMessage("");
					
					player.sendMessage(ChatColor.RED + "Not enough arguments. Syntax is /setdeaths <player> <amount>.");
					
					player.sendMessage("");
					player.sendMessage(ChatColor.BLACK + "======" + ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_GRAY + "TopPVP Message" + purple + "]" + ChatColor.BLACK + "======");
					
					return true;
				}
			}
			else
			{
				sender.sendMessage("This command can only be executed by a player..");
				return true;
			}
		}
		return false;
	}

	
}
