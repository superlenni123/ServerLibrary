package com.jens.ServerLibrary.Database.Impl;

import java.sql.Connection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.jens.ServerLibrary.Utils.DataSource;

public abstract class Database {
	
	protected DataSource source;
	protected Connection con;
	protected ExecutorService exe;
	
	/**
	 * Initializes a new Database by using
	 * the Datasource.
	 * @param host gives the Database host
	 * @param port gives the Database port
	 * @param database gives the Database name
	 * @param username gives the Database username
	 * @param password gives the Database password
	 */
	
	public Database(String host,
			int port,
			String database,
			String username,
			String password) {
		
		source = new DataSource(host, port, database, username, password);
		exe = Executors.newCachedThreadPool();
	}
	
	/**
	 * Gives the connected Database.
	 * @return an instance of the connected Database
	 * @throws Exception if operation fails
	 */
	
	public abstract Database connect() throws Exception;
	
	/**
	 * Disconnects from the given Database.
	 * @throws Exception if the operation fails
	 */
	
	public abstract void disconnect() throws Exception;
	
	/**
	 * Checks if the given Database is connected.
	 * @return true/false
	 * @throws Exception if operation fails
	 */
	
	public boolean isConnected() {
		return (con == null ? false : true);
	}
	
	/**
	 * Gets the java.sql Connection of the
	 * created Database.
	 * @return java.sql.Connection
	 * @throws Exception if operation fails
	 */
	
	public Connection getConnection() {
		return con;
	}

}
