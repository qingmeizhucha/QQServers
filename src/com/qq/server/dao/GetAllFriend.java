package com.qq.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.qq.server.tools.DBconnection;

import Message.UserBean;

public class GetAllFriend {

	public UserBean getFriend(UserBean ub) {
		// TODO Auto-generated method stub
		String id = ub.getId();
		Map<String ,Object> fri = new HashMap<String, Object>();
		DBconnection onecon = new DBconnection();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		con = onecon.getConnection();
		try{
			pstmt = con.prepareStatement("select * from friend where myid='"+id+"'");
			rs = pstmt.executeQuery();
			while(rs.next()){
				Map<String,String> f = new HashMap<String,String>();
				f.put(rs.getString(3), rs.getString(4));
				fri.put(rs.getString(2), f);
			}
			
		}catch(SQLException e1){
			e1.printStackTrace();
		}
		ub.setFriend(fri);
		return ub;
	}

	
	
	
	public UserBean getAllGrop(String id) {
		// TODO Auto-generated method stub
		ArrayList<String> gr = new ArrayList<String>();
		DBconnection onecon = new DBconnection();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		con = onecon.getConnection();
		try{
			pstmt = con.prepareStatement("select * from fenzu where id='"+id+"'");
			rs = pstmt.executeQuery();
			while(rs.next()){
				gr.add(rs.getString(2));
			}
			
		}catch(SQLException e1){
			e1.printStackTrace();
		}
		UserBean u = new UserBean();
		u.setGrop(gr);
		
		return u;
	}

	
	
	
	
}
