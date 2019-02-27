package com.qq.server.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
	Connection con = null;
	public Connection getConnection(){
		try{

			Class.forName("com.mysql.jdbc.Driver");
			String conurl = "jdbc:mysql://localhost:3306/qqtalk?useUnicode=true&characterEncoding=UTF8";        
			String name = "root";
			String pass = "123456";
			con = DriverManager.getConnection(conurl,name,pass);
		}catch(ClassNotFoundException e){
			System.out.println("«˝∂Øº”‘ÿ ß∞‹");
		} catch (SQLException e) {
			System.out.println("¡¥Ω” ß∞‹");
		}
		return con;
	}
}
