/*
 * Ìí¼Ó×¢²áÐÅÏ¢
 */
package com.qq.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qq.server.tools.DBconnection;

import Message.UserBean;

public class AddUser {

	public AddUser(UserBean ub) {
		// TODO Auto-generated constructor stub
		DBconnection onecon = new DBconnection();
		Connection con = null;
		PreparedStatement pstmt = null;
		@SuppressWarnings("unused")
		ResultSet rs = null;
		con = onecon.getConnection();
		try{
			pstmt = con.prepareStatement("insert into user values(?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, ub.getId());
			pstmt.setString(2, ub.getPass());
			pstmt.setString(3, ub.getNickname());
			pstmt.setString(4, ub.getSex());
			pstmt.setString(5, ub.getBir());
			pstmt.setString(6, ub.getTitle());
			pstmt.setString(7, ub.getIcon());
			pstmt.setString(8, ub.getName());
			pstmt.setString(9, ub.getTel());
			pstmt.executeUpdate();
		}catch(SQLException e1){
			e1.printStackTrace();
		}
	}

}
