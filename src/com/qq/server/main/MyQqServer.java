package com.qq.server.main;
import java.net.*;
import java.util.Vector;

import com.qq.server.dao.AddUser;
import com.qq.server.dao.FindUserInfo;
import com.qq.server.dao.GetAllFriend;
import com.qq.server.dao.GropManage;
import com.qq.server.dao.UpdateFriend;
import Message.Message;
import Message.UserBean;
import Therd.ManageClientThread;
import Therd.OneClickedTherd;

import java.io.*;
public class MyQqServer {
	BufferedReader in = null;
	BufferedWriter out = null;
	public MyQqServer()
	{
		System.out.println("服务器已经启动");
		try {
			
			//在9999监听
			ServerSocket ss=new ServerSocket(9999);
			//阻塞,等待连接
			while(true)
			{
				Socket s=ss.accept();//接受一个连接
				//接收客户端发来的信息.
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());//接收数据
				ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());//新建一个输出流
				Message m = (Message)ois.readObject();//接收到的信息
				if(m.getTcp_ip() == 0){
					//中断与之保持联系的线程
					/*
					 * 百度了不少博客说直接stop会导致某种不可抗力的事情发生
					 * 不过暂时还没发现什么异常，就暂时使用stop中断线程
					 */
					ManageClientThread.getClientThread(m.getUb().getId()).stop();
					ManageClientThread.delClientThread(m.getUb().getId());//从线程池中删除
					oos.writeObject(m);
					
				}else if(m.getTcp_ip() == 1){//登录
					String id = m.getUb().getId();
					String pass = m.getUb().getPass();
					UserBean tub = new FindUserInfo().FindUserInfo(id);
					if(id.equals(tub.getId()) && pass.equals(tub.getPass())){
						if(ManageClientThread.getClientThread(id)!=null){
							m.setTcp_ip(0);
						}else{
							m.setTcp_ip(4);
						}
					}
					oos.writeObject(m);
					if(m.getTcp_ip() == 4){
						OneClickedTherd one = new OneClickedTherd(s);
						ManageClientThread.addClientThread(id, one);
						one.start();
					}
					
				}else if(m.getTcp_ip() == 2){//注册
					m.setTcp_ip(3);//注册成功标识
					UserBean ub = m.getUb();
					ub.setId(randomQQnum());
					new AddUser(ub);
					m.setUb(ub);
					oos.writeObject(m);
					Vector<String> fen = new Vector<String>();
					fen.add("家人");
					fen.add("同学");
					fen.add("朋友");
					fen.add("陌生人");
					for(int i=0; i<fen.size(); i++){
						new GropManage().addGrop(ub.getId(), fen.get(i));
					}
				}else if(m.getTcp_ip() == 6){//获取所有好友的信息
					UserBean ub = new GetAllFriend().getFriend(m.getUb());
					m.setUb(ub);
					oos.writeObject(m);
				}else if(m.getTcp_ip() == 7){//获取所有的好友分组
					UserBean ub = new GetAllFriend().getAllGrop(m.getUb().getId());
					m.setUb(ub);
					oos.writeObject(m);
				}else if(m.getTcp_ip() == 8){//获取个人信息
					m.setUb(new FindUserInfo().FindUserInfo(m.getUb().getId()));
					oos.writeObject(m);
				}else if(m.getTcp_ip() == 9){//修改好友备注
					new UpdateFriend().updatebeizhu(m.getUb().getId(), m.getUb().getPass(), m.getUb().getName());
					oos.writeObject(m);
				}else if(m.getTcp_ip() == 10){
					new UpdateFriend().DelFriend(m.getUb().getId(),m.getUb().getPass());
					oos.writeObject(m);
				}else if(m.getTcp_ip() == 11){
					new UpdateFriend().moveFriend(m.getUb().getId(),m.getUb().getPass(),m.getUb().getName());
					oos.writeObject(m);
				}else if(m.getTcp_ip() == 12){
					new GropManage().addGrop(m.getUb().getId(),m.getUb().getName());
					oos.writeObject(m);
				}else if(m.getTcp_ip() == 13){
					String myid = m.getUb().getId();
					String oldgrop = m.getUb().getPass();
					String newf = m.getUb().getName();
					new GropManage().updatename(myid,oldgrop,newf);
					new GropManage().update(myid,newf,oldgrop);
					oos.writeObject(m);
				}else if(m.getTcp_ip() == 14){
					String myid = m.getUb().getId();
					String oldgrop = m.getUb().getPass();
					String newgrop = m.getUb().getName();
					new GropManage().delGrop(myid,oldgrop);
					new GropManage().update(myid,newgrop,oldgrop);
					oos.writeObject(m);
				}else if(m.getTcp_ip() == 16){//添加好友
					
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			
		}
	}
	private String randomQQnum() {
		// TODO Auto-generated method stub
		long m = 10001;//最小的QQ号
		long n = 999999999;//最大的QQ号
		String QQ = Integer.toString((int)(m + Math.random() * (n - m + 1)));
		if(!(new FindUserInfo().FindUserInfo(QQ).getId().equals("!!"))){
			randomQQnum();
		}
		return QQ;
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		new MyQqServer();
		//new Registered();
	}
	
}
