/*
 * 根据ID查找用户信息
 */
package com.qq.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qq.server.tools.DBconnection;

import Message.UserBean;

public class FindUserInfo {
	public UserBean FindUserInfo(String id) {
		UserBean us;
		// TODO Auto-generated constructor stub
		us = new UserBean();
		DBconnection onecon = new DBconnection();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		con = onecon.getConnection();
		int f = 0;
		try{
			pstmt = con.prepareStatement("select * from user where id='"+id+"'");
			rs = pstmt.executeQuery();
			while(rs.next()){
				f = 1;
				us.setId(rs.getString(1));
				us.setPass(rs.getString(2));
				us.setNickname(rs.getString(3));
				us.setSex(rs.getString(4));
				us.setBir(rs.getString(5));
				us.setTitle(rs.getString(6));
				us.setIcon(rs.getString(7));
				us.setName(rs.getString(8));
				us.setTel(rs.getString(9));
			}
			if(f == 0){
				us.setId("!!");
			}
		}catch(SQLException e1){
			e1.printStackTrace();
		}
		return us;
	}
}
