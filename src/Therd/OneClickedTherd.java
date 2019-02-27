package Therd;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

import com.qq.server.dao.FindUserInfo;
import com.qq.server.dao.GetAllFriend;
import com.qq.server.dao.GropManage;
import com.qq.server.dao.UpdateFriend;

import Message.Message;
import Message.UserBean;

public class OneClickedTherd extends Thread{
	Socket s;

	public OneClickedTherd(Socket s) {
		// 把服务器和该客户端的连接赋给s
		this.s = s;
	}


	public void run() {

		while (!isInterrupted()) {

			// 这里该线程就可以接收客户端的信息.
			try {
				ObjectInputStream ois = new ObjectInputStream(s
						.getInputStream());
				Message m = (Message) ois.readObject();

				 //System.out.println(m.getSender()+" 给 "+m.getGetter()+" 说:"+m.getCon());
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				// 对从客户端取得的消息进行类型判断，然后做相应的处理
				if (m.getTcp_ip() == 15) {
					// 一会完成转发.
					// 取得接收人的通信线程
				}

			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}

		}

	}

}
