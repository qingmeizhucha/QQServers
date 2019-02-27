package com.qq.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qq.server.tools.DBconnection;

public class GropManage {
	DBconnection onecon = new DBconnection();
	Connection con = null;
	PreparedStatement pstmt = null;
	public void addGrop(String id,String name){
		ResultSet rs = null;
		con = onecon.getConnection();
		try{
			pstmt = con.prepareStatement("insert into fenzu values(?,?)");
			pstmt.setString(1,id);
			pstmt.setString(2, name);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		}catch(SQLException e1){
			e1.printStackTrace();
		}
	}
	public void delGrop(String myid, String oldgrop) {
		// TODO Auto-generated method stub
		con = onecon.getConnection();
		try{
			pstmt = con.prepareStatement("delete from fenzu where id='"+myid+"' and name='"+oldgrop+"'");
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		}catch(SQLException e1){
			e1.printStackTrace();
		}
	}
	public void update(String myid, String newgrop,String oldgrop) {
		// TODO Auto-generated method stub
		con = onecon.getConnection();
		try{
			pstmt = con.prepareStatement("update friend set fenzuid='"+newgrop+"' where myid='"+myid+"' and fenzuid='"+oldgrop+"'");
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		}catch(SQLException e1){
			e1.printStackTrace();
		}
	}
	public void updatename(String myid, String oldgrop, String newf) {
		// TODO Auto-generated method stub
		con = onecon.getConnection();
		try{
			pstmt = con.prepareStatement("update fenzu set name='"+newf+"' where id='"+myid+"' and name='"+oldgrop+"'");
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		}catch(SQLException e1){
			e1.printStackTrace();
		}
	}
}
