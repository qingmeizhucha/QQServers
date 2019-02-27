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
		System.out.println("�������Ѿ�����");
		try {
			
			//��9999����
			ServerSocket ss=new ServerSocket(9999);
			//����,�ȴ�����
			while(true)
			{
				Socket s=ss.accept();//����һ������
				//���տͻ��˷�������Ϣ.
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());//��������
				ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());//�½�һ�������
				Message m = (Message)ois.readObject();//���յ�����Ϣ
				if(m.getTcp_ip() == 0){
					//�ж���֮������ϵ���߳�
					/*
					 * �ٶ��˲��ٲ���˵ֱ��stop�ᵼ��ĳ�ֲ��ɿ��������鷢��
					 * ������ʱ��û����ʲô�쳣������ʱʹ��stop�ж��߳�
					 */
					ManageClientThread.getClientThread(m.getUb().getId()).stop();
					ManageClientThread.delClientThread(m.getUb().getId());//���̳߳���ɾ��
					oos.writeObject(m);
					
				}else if(m.getTcp_ip() == 1){//��¼
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
					
				}else if(m.getTcp_ip() == 2){//ע��
					m.setTcp_ip(3);//ע��ɹ���ʶ
					UserBean ub = m.getUb();
					ub.setId(randomQQnum());
					new AddUser(ub);
					m.setUb(ub);
					oos.writeObject(m);
					Vector<String> fen = new Vector<String>();
					fen.add("����");
					fen.add("ͬѧ");
					fen.add("����");
					fen.add("İ����");
					for(int i=0; i<fen.size(); i++){
						new GropManage().addGrop(ub.getId(), fen.get(i));
					}
				}else if(m.getTcp_ip() == 6){//��ȡ���к��ѵ���Ϣ
					UserBean ub = new GetAllFriend().getFriend(m.getUb());
					m.setUb(ub);
					oos.writeObject(m);
				}else if(m.getTcp_ip() == 7){//��ȡ���еĺ��ѷ���
					UserBean ub = new GetAllFriend().getAllGrop(m.getUb().getId());
					m.setUb(ub);
					oos.writeObject(m);
				}else if(m.getTcp_ip() == 8){//��ȡ������Ϣ
					m.setUb(new FindUserInfo().FindUserInfo(m.getUb().getId()));
					oos.writeObject(m);
				}else if(m.getTcp_ip() == 9){//�޸ĺ��ѱ�ע
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
				}else if(m.getTcp_ip() == 16){//��Ӻ���
					
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
		long m = 10001;//��С��QQ��
		long n = 999999999;//����QQ��
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
