package com.jens.ServerLibrary.Database.Impl;

import java.sql.DriverManager;

import com.jens.ServerLibrary.Database.Database;

public class MySQLDataBase extends Database {

	public MySQLDataBase(String host, int port, String database, String username, String password) {
		super(host, port, database, username, password);
	}

	@Override
	public Database connect() throws Exception {
		
		con = DriverManager.getConnection("jdbc:mysql://" +
		source.getSource(1).toString() +":" +
		source.getSource(2).toString() + "/" +
		source.getSource(3).toString(),
		source.getSource(4).toString(),
		source.getSource(5).toString());
		
		return this;
	}

	@Override
	public void disconnect() throws Exception {
		if(isConnected()){
			con.close();
			
			con = null;
		}
	}

	@Override
	public boolean isConnected() {
		return (con == null ? false : true);
	}
}
