package Therd;

import java.util.HashMap;

public class ManageClientThread {
	
	public static HashMap<String, OneClickedTherd> hm=new HashMap<String, OneClickedTherd>();
	//向hm中添加一个客户端通讯线程
	public static  void addClientThread(String uid,OneClickedTherd ct)
	{
		hm.put(uid, ct);
	}
	//获取一个线程
	public static OneClickedTherd getClientThread(String uid)
	{
		return (OneClickedTherd)hm.get(uid);
	}
	//删除一个线程
	public static void delClientThread(String uid){
		hm.remove(uid);
	}
}