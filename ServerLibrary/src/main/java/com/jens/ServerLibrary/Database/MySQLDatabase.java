package com.jens.ServerLibrary.Database;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import com.jens.ServerLibrary.Database.Impl.Database;

public class MySQLDatabase extends Database {

	public MySQLDatabase(String host, int port, String database, String username, String password) {
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
	
	public PreparedStatement prepareStatement(String query) throws SQLException {
		if(isConnected()){
			return con.prepareStatement(query);
		} else {
			throw new SQLException("SQLDatabase found no Connection");
		}
	}
	
	public int executeUpdate(PreparedStatement ps) throws Exception {
		if(isConnected()){
			
			Future<Integer> future = exe.submit(new Callable<Integer>() {

				@Override
				public Integer call() throws Exception {
					return ps.executeUpdate();
				}
			});
			
			return future.get();
		} else {
			throw new SQLException("SQLDatabase found no Connection");
		}
	}
	
	public int executeUpdate(String query) throws Exception {
		if(isConnected()){
			
			Future<Integer> future = exe.submit(new Callable<Integer>() {

				@Override
				public Integer call() throws Exception {
					return con.prepareStatement(query).executeUpdate();
				}
			});
			
			return future.get();
		} else {
			throw new SQLException("SQLDatabase found no Connection");
		}
	}
	
	public ResultSet executeQuery(PreparedStatement ps) throws Exception {
		if(isConnected()){
			
			Future<ResultSet> future = exe.submit(new Callable<ResultSet>() {

				@Override
				public ResultSet call() throws Exception {
					return ps.executeQuery();
				}
			});
			
			return future.get();
		} else {
			throw new SQLException("SQLDatabase found no Connection");
		}
	}
	
	public ResultSet executeQuery(String query) throws Exception {
		if(isConnected()){
			
			Future<ResultSet> future = exe.submit(new Callable<ResultSet>() {

				@Override
				public ResultSet call() throws Exception {
					return con.prepareStatement(query).executeQuery();
				}
			});
			
			return future.get();
		} else {
			throw new SQLException("SQLDatabase found no Connection");
		}
	}
}
