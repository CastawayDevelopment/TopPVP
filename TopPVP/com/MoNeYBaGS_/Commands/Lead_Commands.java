package TopPVP.com.MoNeYBaGS_.Commands;

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

public class Lead_Commands implements CommandExecutor{
	
	private TopPVP plugin;
	public Lead_Commands(TopPVP _plugin, Leaderboards _leaderboards)
	{
		this.plugin = _plugin;
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
			String[] args) {
		
		plugin.reloadConfig();
		Player player = null;
		if(sender instanceof Player)
		{
			player = (Player) sender;
		}
		
		if(cmd.getName().equalsIgnoreCase("leadkills"))
		{
			if(Nodes.Paths.AllowLeaderboards.getBool() == true)
			{
				if(!(player == null))
				{
					if(player.hasPermission(Nodes.Permissions.General.getString()) || 
							player.hasPermission(Nodes.Permissions.KillsLeaderboards.getString()))
					{
						Map<String, Integer> tree = Leaderboards.getKillsLeaderboards();
						trimLeaderboards trim = new trimLeaderboards();
						ArrayList<String> top = trim.getTrimmed(tree.toString());

						sender.sendMessage(ChatColor.RED + "**************PVP Kills Leaderboard**************");
						sender.sendMessage(ChatColor.GREEN + "1. " + top.get(0) + " Kills");
						for(int i = 1; i < top.size() && i <= Nodes.Paths.LeaderboardsAmount.getInt(); i++)
							sender.sendMessage(ChatColor.GOLD + Integer.toString(i+1) + ". " + top.get(i) + " Kills");
						sender.sendMessage(ChatColor.RED + "**************************************************");			
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
					Map<String, Integer> tree = Leaderboards.getKillsLeaderboards();
					trimLeaderboards trim = new trimLeaderboards();
					ArrayList<String> top = trim.getTrimmed(tree.toString());

					sender.sendMessage("**************PVP Kills Leaderboard**************");
					for(int i = 0; i < top.size() && i <= Nodes.Paths.LeaderboardsAmount.getInt(); i++)
						sender.sendMessage(Integer.toString(i+1) + ". " + top.get(i) + " Kills");
					sender.sendMessage("**************************************************");	
					return true;
				}
			}
			else
				sender.sendMessage(Nodes.Paths.LeaderboardsFalse.getString());
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("leadkdr"))
		{
			if(Nodes.Paths.AllowLeaderboards.getBool() == true)
			{
				if(!(player == null))
				{
					if(player.hasPermission(Nodes.Permissions.General.getString()) || 
							player.hasPermission(Nodes.Permissions.KDRLeaderboards.getString()))
					{
						Map<String, Double> tree = Leaderboards.getKDRLeaderboards();
						trimLeaderboards trim = new trimLeaderboards();
						ArrayList<String> top = trim.getTrimmed(tree.toString());

						sender.sendMessage(ChatColor.RED + "**************PVP KDR Leaderboard**************");
						sender.sendMessage(ChatColor.GREEN + "1. " + top.get(0));
						for(int i = 1; i < top.size() && i <= Nodes.Paths.LeaderboardsAmount.getInt(); i++)
							sender.sendMessage(ChatColor.GOLD + Integer.toString(i+1) + ". " + top.get(i));
						sender.sendMessage(ChatColor.RED + "**************************************************");			
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
					Map<String, Integer> tree = Leaderboards.getKillsLeaderboards();
					trimLeaderboards trim = new trimLeaderboards();
					ArrayList<String> top = trim.getTrimmed(tree.toString());

					sender.sendMessage("**************PVP KDR Leaderboard**************");
					for(int i = 0; i < top.size() && i <= Nodes.Paths.LeaderboardsAmount.getInt(); i++)
						sender.sendMessage(Integer.toString(i+1) + ". " + top.get(i));
					sender.sendMessage("**************************************************");	
					return true;
				}
			}
			else
				sender.sendMessage(Nodes.Paths.LeaderboardsFalse.getString());
			return true;
		}
		
		else if(cmd.getName().equalsIgnoreCase("leaddeaths"))
		{
			if(Nodes.Paths.AllowLeaderboards.getBool() == true)
			{
				if(!(player == null))
				{
					if(player.hasPermission(Nodes.Permissions.General.getString()) || 
							player.hasPermission(Nodes.Permissions.DeathsLeaderboards.getString()))
					{
						Map<String, Integer> tree = Leaderboards.getDeathsLeaderboards();
						trimLeaderboards trim = new trimLeaderboards();
						ArrayList<String> top = trim.getTrimmed(tree.toString());

						sender.sendMessage(ChatColor.RED + "**************PVP Deaths Leaderboard**************");
						sender.sendMessage(ChatColor.RED + "1. " + top.get(0) + " Deaths");
						for(int i = 1; i < top.size() && i <= Nodes.Paths.LeaderboardsAmount.getInt(); i++)
							sender.sendMessage(ChatColor.GOLD + Integer.toString(i+1) + ". " + top.get(i) + " Deaths");
						sender.sendMessage(ChatColor.RED + "*****************************************************");			
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
					Map<String, Integer> tree = Leaderboards.getKillsLeaderboards();
					trimLeaderboards trim = new trimLeaderboards();
					ArrayList<String> top = trim.getTrimmed(tree.toString());

					sender.sendMessage("**************PVP Deaths Leaderboard**************");
					for(int i = 0; i < top.size() && i <= Nodes.Paths.LeaderboardsAmount.getInt(); i++)
						sender.sendMessage(Integer.toString(i+1) + ". " + top.get(i) + " Deaths");
					sender.sendMessage("*****************************************************");	
					return true;
				}
			}
			else
				sender.sendMessage(Nodes.Paths.LeaderboardsFalse.getString());
			return true;
		}
		
		return false;
	}

	
}
