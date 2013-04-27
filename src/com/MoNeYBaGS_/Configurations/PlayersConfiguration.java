package com.MoNeYBaGS_.Configurations;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.MoNeYBaGS_.Database;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.MoNeYBaGS_.TopPVP;


public class PlayersConfiguration {

	private TopPVP plugin;
	public List databaseCache;

	public PlayersConfiguration(TopPVP instance)
	{
		this.plugin = instance;
		Nodes.load(new File(plugin.getDataFolder() + "/config.yml"));
	}

	public List getConfig()
	{
		if (databaseCache == null) {
			reloadPlayersConfig();
		}
		return databaseCache;
	}

	public void reloadPlayersConfig()
	{
		try {
			Database database = plugin.database;
			QueryRunner runner = new QueryRunner();
			Connection connection = database.getConnection();

			Logger logger = plugin.getLogger();
			List mapList = (List) runner.query(connection, "SELECT * FROM "+database.tableName, new MapListHandler());

			databaseCache = mapList;
		} catch (SQLException exc) {
			System.out.println("Cannot fetch rows from DB");
		}
	}

	public void updatePlayer(String query) {
		try {
			Connection connection = plugin.database.getConnection();
			QueryRunner runner = new QueryRunner();
			runner.update(connection, query);

			DbUtils.close(connection);
		} catch (SQLException exc) {
			System.out.println("Cannot update SQL record");
		}
	}

	public Map getPlayer(String name) {
		for (int i = 0; i < databaseCache.size(); i++) {
			Map player = (Map)databaseCache.get(i);

			if (name.equals(player.get("username"))) {
				return player;
			}
		}

		return null;
	}
}
