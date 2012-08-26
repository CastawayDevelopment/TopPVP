package com.MoNeYBaGS_.Configurations;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Nodes
{
	public static enum Permissions {
		
		General("pvp.general"),
		Kills("pvp.kills"),
		Deaths("pvp.deaths"),
		KDR("pvp.kdr"),
		ResetKills("pvp.resetkills"),
		REsetDeaths("pvp.resetdeaths"),
		KillsLeaderboards("pvp.leadkills"),
		SetKills("pvp.setkills"),
		SetDeaths("pvp.setdeaths"),
		KDRLeaderboards("pvp.leadkdr"),
		DeathsLeaderboards("pvp.leaddeaths");		
		
		private String perm;
		
		private Permissions(String perm)
		{
			this.perm = perm;
		}
		
		public String getString()
		{
			return (String) this.perm;
		}
	}
	
	
	public static enum Paths {
		
		DeathsReturn1("Messages.Deaths.Before Number", "You have died "),
		DeathsReturn2("Messages.Deaths.After Number", " times"),
		DeathsReturnOnce("Messages.Deaths.One Time", "You have died once."),
		DeathsReturnNone("Messages.Deaths.None", "You have never died."),
		
		KillsReturn1("Messages.Kills.Before Number", "You have killed "),
		KillsReturn2("Messages.Kills.After Number", " times"),
		KillsReturnOnce("Messages.Kills.One Time", "You have killed once."),
		KillsReturnNone("Messages.Kills.None", "You have not killed anyone at all..."),
		
		ResetDeathsYou("Messages.Reset.Deaths.Sender", "Your deaths have been reset."),
		ResetDeaths("Messages.Reset.Deaths.Another Player", "'s deaths have been reset."),
		ResetKillsYou("Messages.Reset.Kills.Sender", "Your kills have been reset."),
		ResetKills("Messages.Reset.Kills.Another Player", "'s kills have been reset."),
		
		LeaderboardsFalse("Messages.Leaderboards.Deny", "The Leaderboards are disabled."),
		
		AllowLeaderboards("Leaderboards.Allow", true),
		LeaderboardsAmount("Leaderboards.People to View", 10),
		Color("Leaderboards.Color", "GOLD");

		private String path;
		private Object value;
		
		private Paths(String path, Object value)
		{
			this.path = path;
			this.value = value;
		}
		
		public String getString()
		{
			return (String) this.value;
		}
		
		public Integer getInt()
		{
			return (Integer) this.value;
		}
		
		public Boolean getBool()
		{
			return (Boolean) this.value;
		}
		
		public ChatColor getChatColor()
		{
			return (ChatColor) this.value;
		}
		
		public String getPath()
		{
			return this.path;
		}
		
		public void setValue(Object value) {
            this.value = value;
        }
	}
	
	public static void load(File configuration) {
        FileConfiguration config = YamlConfiguration.loadConfiguration(configuration);
        for(Paths n: Paths.values())
        {
            if(!n.getPath().isEmpty())
            {
                if(config.get(n.getPath()) != null)
                {
                    n.setValue(config.get(n.getPath()));
                }
            }
        }
    }
}