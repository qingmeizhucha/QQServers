package com.qq.server.dao;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.qq.server.tools.DBconnection;

public class UpdateFriend {
	DBconnection onecon = new DBconnection();
	Connection con = null;
	Statement stmt = null;
	public void updatebeizhu(String myid,String fid,String beizhu){
		con = (Connection) onecon.getConnection();
		try {
			stmt = (Statement) con.createStatement();
			stmt.executeUpdate("Update friend set beizhu='"+beizhu+"' where myid='"+myid+"' and friendid='"+fid+"'");
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void DelFriend(String id, String pass) {
		// TODO Auto-generated method stub
		con = (Connection) onecon.getConnection();
		try {
			stmt = (Statement) con.createStatement();
			stmt.executeUpdate("delete from friend where myid='"+id+"' and friendid='"+pass+"'");
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void moveFriend(String id, String pass, String name) {
		// TODO Auto-generated method stub
		con = (Connection) onecon.getConnection();
		try {
			stmt = (Statement) con.createStatement();
			stmt.executeUpdate("update friend set fenzuid='"+name+"' where myid='"+id+"' and friendid='"+pass+"'");
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
