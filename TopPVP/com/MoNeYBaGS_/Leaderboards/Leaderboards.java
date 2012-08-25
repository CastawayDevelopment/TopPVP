package TopPVP.com.MoNeYBaGS_.Leaderboards;

import java.util.Map;

import com.MoNeYBaGS_.TopPVP;


public class Leaderboards {
	
	private final TopPVP plugin;
	private static Players lead = null;
	
	public Leaderboards(TopPVP instance)
	{
		this.plugin = instance;
		lead = new Players(plugin);
	}
	
	public static Map<String, Integer> getKillsLeaderboards()
	{
		return lead.getKillsLeaderboard();
	}
	
	public static Map<String, Double> getKDRLeaderboards()
	{
		return lead.getKDRLeaderboards();
	}
	
	public static Map<String, Integer> getDeathsLeaderboards()
	{
		return lead.getDeathsLeaderboards();
	}
}
