package Therd;

import java.util.HashMap;

public class ManageClientThread {
	
	public static HashMap<String, OneClickedTherd> hm=new HashMap<String, OneClickedTherd>();
	//��hm�����һ���ͻ���ͨѶ�߳�
	public static  void addClientThread(String uid,OneClickedTherd ct)
	{
		hm.put(uid, ct);
	}
	//��ȡһ���߳�
	public static OneClickedTherd getClientThread(String uid)
	{
		return (OneClickedTherd)hm.get(uid);
	}
	//ɾ��һ���߳�
	public static void delClientThread(String uid){
		hm.remove(uid);
	}
}