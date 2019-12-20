package Beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConnectionPool {
	private static ConnectionPool instance = new ConnectionPool();
	private ArrayList<Connection> connections = new ArrayList<>();
	private static final int MAX_CONNECTIONS = 5;

	private ConnectionPool() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			for (int i = 0; i < MAX_CONNECTIONS; i++) {
				try {
					Connection c = DriverManager.getConnection("jdbc:sqlserver://localhost;databasename=CouponJDBC;integratedsecurity=true");
					connections.add(c);
				} catch (SQLException e) {

				}
			}
		} catch (
		ClassNotFoundException e1) {
			System.out.println(e1.getMessage());
		}
	}
	
	public static ConnectionPool getInstance() {
		return instance;
	}
	
	public synchronized Connection getConnction() {
		if(connections.isEmpty()) {
			try {
				wait();
			}catch(InterruptedException e) {
				System.out.println("Someone woke me up brutally!");
			}
		}
		Connection con = connections.get(0);
		connections.remove(0);
		return con;
	}
	public synchronized void returnConnection(Connection con) {
		connections.add(con);
		notify();
	}
	public void closeAllConnections() {
		for(int i= 0;i<MAX_CONNECTIONS;i++) {
			Connection c = connections.get(i);
			try {
				c.close();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}





























