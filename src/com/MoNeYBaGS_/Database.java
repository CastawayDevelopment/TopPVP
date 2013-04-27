package com.MoNeYBaGS_;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author matejkramny
 */
public class Database {
	public TopPVP topPVP;
	public String tableName = "toppvp";
	private String username = "root";
	private String password = "";
	private String url = "jdbc:mysql://localhost:3306/minecraft";

	public ResultSetHandler<Boolean> returnBoolean = new ResultSetHandler<Boolean>() {
		public Boolean handle(ResultSet rs) {
			try {
				rs.next();
			} catch (SQLException ex) {
				return false;
			}

			return true;
		}
	};

	public Connection getConnection() throws SQLException {
		// Connection is handled by bukkit! We just need to retrieve it..
		return DriverManager.getConnection(url, username, password);
	}

	public void connect() {
		boolean exists = tableExists();
		if (!exists) {
			// Create it.
			String query = "CREATE TABLE IF NOT EXISTS "+tableName+" (" +
					"id int(255) NOT NULL AUTO_INCREMENT," +
					"username varchar(32) NOT NULL," +
					"kills int(255) NOT NULL DEFAULT '0'," +
					"deaths int(255) NOT NULL DEFAULT '0'," +
					"UNIQUE KEY `username` (`username`)," +
					"PRIMARY KEY `id` (`id`))" +
					"ENGINE=InnoDB DEFAULT CHARSET=utf8;";
			QueryRunner runner = new QueryRunner();

			try {
				Connection connection = getConnection();
				runner.update(connection, query);

				DbUtils.close(connection);
			} catch (SQLException exc) {
				System.out.println("[TopPvP] Error creating table" + exc);
			}
		} else {
			topPVP.getLogger().info("Table exists.");
		}
	}

	public boolean tableExists() {
		boolean exists = false;
		try {
			QueryRunner runner = new QueryRunner();
			Connection connection = getConnection();

			try {
				exists = runner.query(connection, "SELECT id FROM "+tableName, returnBoolean);
			} catch (SQLException exc) {
				exists = false;
			}

			topPVP.getLogger().info("Table exists: "+exists);
			DbUtils.close(connection);
		} catch (Exception exc) {
			topPVP.getLogger().warning("Cannot connect to database server");
			exists = false;
		} finally {
			return exists;
		}
	}
}
