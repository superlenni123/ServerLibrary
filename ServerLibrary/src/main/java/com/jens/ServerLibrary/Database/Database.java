package com.jens.ServerLibrary.Database;

import java.sql.Connection;

import com.jens.ServerLibrary.Utils.DataSource;

public abstract class Database {
	
	protected DataSource source;
	protected Connection con;
	
	public Database(String host,
			int port,
			String database,
			String username,
			String password) {
		
		source = new DataSource(host, port, database, username, password);
	}
	
	public abstract Database connect() throws Exception;
	
	public abstract void disconnect() throws Exception;
	
	public abstract boolean isConnected() throws Exception;

}
